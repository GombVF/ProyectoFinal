package org.example.proyectofinal.util.conveters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.example.proyectofinal.util.enums.TipoPersona;

@Converter(autoApply = true)
public class TipoPersonaConverter implements AttributeConverter<TipoPersona, String> {
    @Override
    public String convertToDatabaseColumn(TipoPersona tipoPersona) {
        return tipoPersona != null ? tipoPersona.getTipoPersona() : null;
    }

    @Override
    public TipoPersona convertToEntityAttribute(String tipoPersona) {

        return tipoPersona != null ? TipoPersona.fromString(tipoPersona) : null;
    }
}