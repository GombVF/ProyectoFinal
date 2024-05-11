package org.example.proyectofinal.util.enums;

public enum TipoPersona {
    MORAL("Moral"),
    FISICA("Física");

    private String tipoPersona;

    TipoPersona(String tipoPersona){
        this.tipoPersona = tipoPersona;
    }

    public String getTipoPersona(){
        return this.tipoPersona;
    }
}
