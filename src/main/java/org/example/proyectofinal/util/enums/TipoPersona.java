package org.example.proyectofinal.util.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

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
    public static TipoPersona fromString(String tipoPersona) {
        for (TipoPersona tp : TipoPersona.values()) {
            if (tp.getTipoPersona().equalsIgnoreCase(tipoPersona)) {
                return tp;
            }
        }
        throw new IllegalArgumentException("No enum constant " + TipoPersona.class.getCanonicalName() + "." + tipoPersona);
    }
}
