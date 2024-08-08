package com.felipe_dias.backend_java_spring_test.model.enums;

public enum Nivel {

    USER("User"),
    ADMIN("Admin");

    private String label;

     Nivel (String label){
        this.label = label;
    }

    public String getLabel(){
         return label;
    }
    public static Nivel valueOfLabel(String label) {
        for (Nivel value : Nivel.values()) {
            if (value.label.equals(label)) {
                return value;
            }
        }
        throw new IllegalArgumentException();
    }
}
