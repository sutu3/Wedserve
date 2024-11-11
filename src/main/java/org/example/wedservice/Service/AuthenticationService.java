package org.example.wedservice.Service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.example.wedservice.Dto.Request.AuthenticationRequest;
import org.example.wedservice.Dto.Request.IntrospectRequest;
import org.example.wedservice.Dto.Request.LogOutRequest;
import org.example.wedservice.Dto.Response.AuthenticationResponse;
import org.example.wedservice.Dto.Response.IntrospectResponse;
import org.example.wedservice.Entity.InvalidateToken;
import org.example.wedservice.Entity.User;
import org.example.wedservice.Exception.AppException;
import org.example.wedservice.Exception.ErrorCode;
import org.example.wedservice.Repository.InvalidateRepository;
import org.example.wedservice.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UserRepository userRepository;
    InvalidateRepository invalidateRepository;
    @NonFinal
    @Value("${jwt.signedJWT}")
    protected String SIGN_KEY;

    public AuthenticationResponse isAuthenticated(AuthenticationRequest request) throws JOSEException {
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_FOUND));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean result = passwordEncoder.matches(request.getPassword(),
                user.getPassword());
        if (!result) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        var token = generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(result)
                .build();
    }

    public IntrospectResponse instrospect(IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();
        boolean valid=true;
        try {
            verifyToken(token);
        }catch (AppException e){
            valid=false;
        }
        return IntrospectResponse.builder()
                .valid(valid)
                .build();
    }

    private String generateToken(User user) throws JOSEException {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("dailun.com")
                .issueTime(new Date())
                .jwtID(UUID.randomUUID().toString())
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
                .claim("scope",customScope(user))
                .build();
        Payload payload = new Payload(claimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);
        jwsObject.sign(new MACSigner(SIGN_KEY.getBytes()));
        return jwsObject.serialize();
    }
    private SignedJWT verifyToken(String token) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(SIGN_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);
        Date expiryTime = (signedJWT.getJWTClaimsSet().getExpirationTime());
        var verified=signedJWT.verify(verifier);
        if (!(verified && expiryTime.after(new Date()))){
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        if(invalidateRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID()))
        {
            throw new AppException(ErrorCode.UNAUTHENTICATED);

        }
        var verifiers = signedJWT.verify(verifier);
        return signedJWT;
    }
    public void logout(LogOutRequest request) throws ParseException, JOSEException {
        var signToken=verifyToken(request.getToken());
        String jit=signToken.getJWTClaimsSet().getJWTID();
        Date exp=signToken.getJWTClaimsSet().getExpirationTime();
        InvalidateToken invalidateToken= InvalidateToken.builder()
                .id(jit)
                .expiryTime(exp)
                .build();
        invalidateRepository.save(invalidateToken);
    }
    private String customScope(User user){
        StringJoiner springJoiner=new StringJoiner("");
                if(!CollectionUtils.isEmpty(user.getRoles())){
                    user.getRoles().forEach(role->{
                        springJoiner.add("ROLE_"+role.getName());
                        if(!CollectionUtils.isEmpty(role.getPermissions()))
                        {
                            role.getPermissions().forEach(permission->
                                springJoiner.add(permission.getName())
                            );
                        }

                    });
                }
                return springJoiner.toString();
    }
}
