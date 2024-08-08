package com.felipe_dias.backend_java_spring_test.model.enums;

public enum Status {

    PENDENTE(1),
    EM_ANDAMENTO(2),
    CONCLUIDA(3);

    private int code;

    Status(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    public static Status valueOf(int code){

        for(Status value : Status.values()){
            if(value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException();
    }

}
