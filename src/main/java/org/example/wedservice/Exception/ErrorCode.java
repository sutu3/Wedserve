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
    DESCRIPTION_NOT_FOUND(1001,"Description Not Found",HttpStatus.NOT_FOUND),
    MATERIAL_NOT_FOUND(1001,"Material Not Found", HttpStatus.NOT_FOUND),
    MATERIAL_INVALID(1002,"Material Invalid",HttpStatus.BAD_REQUEST),
    MATERIAL_IS_EXITED(1003,"Material Is Exited",HttpStatus.BAD_REQUEST),
    PRODUCT_NOT_FOUND(1001,"Product Not Found", HttpStatus.NOT_FOUND),
    PRODUCT_INVALID(1002,"Product Invalid", HttpStatus.BAD_REQUEST),
    PRODUCT_IS_EXITED(1003,"Product Is Exited", HttpStatus.BAD_REQUEST),
    PURCHASE_NOT_FOUND(1001,"Purgacy Not Found", HttpStatus.NOT_FOUND),
    PURCHASE_ITEM_NOT_FOUND(1001,"Purgacy Not Found", HttpStatus.NOT_FOUND),

    UNCATEGORY(9999,"Uncategorized",HttpStatus.INTERNAL_SERVER_ERROR);

    ErrorCode(int Code,String Message, HttpStatusCode sponse){
        this.code = Code;
        this.message = Message;
        this.status = sponse;
    }
    private int code;
    private String message;
    private HttpStatusCode status;
}
