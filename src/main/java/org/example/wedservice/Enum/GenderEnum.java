package org.example.wedservice.Enum;

public enum GenderEnum {
    Male(1,"Male"),
    Felame(2,"Female"),
    ;
   GenderEnum(int flat, String gender) {
        this.flat=flat;
        this.gender=gender;
    }
    private int flat;
   private String gender;
}
