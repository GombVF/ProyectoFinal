package org.example.proyectofinal.util.conveters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.example.proyectofinal.util.enums.EstatusEnvio;

@Converter(autoApply = true)
public class EstatusEnvioConverter implements AttributeConverter<EstatusEnvio, String> {
    @Override
    public String convertToDatabaseColumn(EstatusEnvio tipoPersona) {
        return tipoPersona != null ? tipoPersona.getEstatus() : null;
    }

    @Override
    public EstatusEnvio convertToEntityAttribute(String tipoPersona) {

        return tipoPersona != null ? EstatusEnvio.fromString(tipoPersona) : null;
    }
}