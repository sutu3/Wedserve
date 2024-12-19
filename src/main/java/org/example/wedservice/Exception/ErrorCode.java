package org.example.wedservice.Exception;


import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@NoArgsConstructor
public enum ErrorCode {

    SIZE_NOT_FOUND(1001,"Size Not Found",HttpStatus.NOT_FOUND),
    SIZE_INVALID(1002,"Size Invalid",HttpStatus.BAD_REQUEST),
    SIZE_IS_EXITED(1003,"Size Is Exited",HttpStatus.BAD_REQUEST),
    COLOR_NOT_FOUND(1001,"Color Not Found",HttpStatus.NOT_FOUND),
    COLOR_INVALID(1002,"Color Invalid",HttpStatus.BAD_REQUEST),
    COLOR_IS_EXITED(1003,"Color Is Exited",HttpStatus.BAD_REQUEST),
    CATEGORY_NOT_FOUND(1001,"Category Not Found",HttpStatus.NOT_FOUND),
    CATEGORY_INVALID(1002,"Category Invalid",HttpStatus.BAD_REQUEST),
    CATEGORY_IS_EXITED(1003,"Category Is Exited",HttpStatus.BAD_REQUEST),
    GENDER_IS_EXITED(1003,"Gendering Is Exited",HttpStatus.BAD_REQUEST),
    GENDER_INVALID(1002,"Gender Invalid",HttpStatus.BAD_REQUEST),
    DESCRIPTION_NOT_FOUND(1001,"Description Not Found",HttpStatus.NOT_FOUND),
    MATERIAL_NOT_FOUND(1001,"Material Not Found", HttpStatus.NOT_FOUND),
    MATERIAL_INVALID(1002,"Material Invalid",HttpStatus.BAD_REQUEST),
    MATERIAL_IS_EXITED(1003,"Material Is Exited",HttpStatus.BAD_REQUEST),
    PRODUCT_NOT_FOUND(1001,"Product Not Found", HttpStatus.NOT_FOUND),
    PRODUCT_INVALID(1002,"Product Invalid", HttpStatus.BAD_REQUEST),
    PRODUCT_IS_EXITED(1003,"Product Is Exited", HttpStatus.BAD_REQUEST),
    PURCHASE_NOT_FOUND(1001,"Purgacy Not Found", HttpStatus.NOT_FOUND),
    PURCHASE_ITEM_NOT_FOUND(1001,"Purgacy Not Found", HttpStatus.NOT_FOUND),
    USER_NOT_FOUND(1001,"User Not Found", HttpStatus.NOT_FOUND),
    USERNAME_INVALID(1002,"Username Invalid",HttpStatus.BAD_REQUEST),
    USERNAME_IS_EXITED(1003,"Username Is Exited", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1004,"Password Invalid", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1005,"Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNCATEGORIZED(9999,"Uncategorized",HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001,"Invalid key",HttpStatus.BAD_REQUEST),
    PERMISSION_IS_EXITED(1003,"Permission Is Exited", HttpStatus.BAD_REQUEST),
    PERMISSION_NOT_FOUND(1001,"Permission Not Found", HttpStatus.NOT_FOUND),
    ROLE_IS_EXITED(1003,"Role Is Exited", HttpStatus.BAD_REQUEST),
    ROLE_NOT_FOUND(1001,"Role Not Found", HttpStatus.NOT_FOUND),
    VERSION_IS_EXITED(1003,"Version Is Exited", HttpStatus.BAD_REQUEST),
    VERSION_NOT_FOUND(1001,"Version Not Found", HttpStatus.NOT_FOUND),
    VARIENT_NOT_FOUND(1001,"Varient Not Found",HttpStatus.NOT_FOUND),
    IMAGE_NOT_FOUND(1001,"Image Not Found",HttpStatus.NOT_FOUND),
    VARIANTS_EMPTY(1005,"Varient Is Empty",HttpStatus.NOT_FOUND),
    DESCRIPTIONS_EMPTY(1005,"Description Is Empty",HttpStatus.NOT_FOUND),
    PURCHASE_ITEMS_EMPTY(1005,"Purchase items Is Empty",HttpStatus.NOT_FOUND),
    INVENTORYS_EMPTY(1005,"Inventorys Is Empty",HttpStatus.NOT_FOUND),
    ORDER_IS_EXITED(1003,"Order Is Exited", HttpStatus.BAD_REQUEST),
    ORDER_NOT_FOUND(1001,"Order Not Found", HttpStatus.NOT_FOUND),
    ORDER_ITEM_IS_EXITED(1003,"Order item Is Exited", HttpStatus.BAD_REQUEST),
    ORDER_ITEM_NOT_FOUND(1001,"Order item Not Found", HttpStatus.NOT_FOUND),
    INVENTORY_NOT_FOUND(1001,"Inventory Not Found", HttpStatus.NOT_FOUND),
    UNAUTHORIZED(9998,"You don't as permission" ,HttpStatus.FORBIDDEN );
    ErrorCode(int Code,String Message, HttpStatusCode sponse){
        this.code = Code;
        this.message = Message;
        this.status = sponse;
    }
    private int code;
    private String message;
    private HttpStatusCode status;
}
