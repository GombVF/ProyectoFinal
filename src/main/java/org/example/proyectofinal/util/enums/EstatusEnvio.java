package org.example.proyectofinal.util.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.Data;

public enum EstatusEnvio {
    SOLICITUD("Solicitud"),
    ATENCION("Atención"),
    ENVIADO("Enviado"),
    ENTREGADO("Entregado");



    private String estatus;

    EstatusEnvio(String estatus){
        this.estatus = estatus;
    }
    public String getEstatus() {
        return this.estatus;
    }

    public static EstatusEnvio fromString(String estatus) {
        for (EstatusEnvio ee : EstatusEnvio.values()) {
            if (ee.getEstatus().equalsIgnoreCase(estatus)) {
                return ee;
            }
        }
        throw new IllegalArgumentException("No enum constant " + EstatusEnvio.class.getCanonicalName() + "." + estatus);
    }
}
