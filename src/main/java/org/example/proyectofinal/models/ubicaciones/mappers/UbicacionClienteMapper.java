package org.example.proyectofinal.models.ubicaciones.mappers;

import org.example.proyectofinal.models.codigosPostales.CodigoPostal;
import org.example.proyectofinal.models.estados.Estado;
import org.example.proyectofinal.models.municipios.Municipio;
import org.example.proyectofinal.models.ubicaciones.Ubicacion;
import org.example.proyectofinal.models.ubicaciones.dtos.UbicacionClienteDto;
import org.example.proyectofinal.service.CodigoPostalService;
import org.example.proyectofinal.service.EstadoService;
import org.example.proyectofinal.service.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
public class UbicacionClienteMapper {
    @Autowired
    private CodigoPostalService codigoPostalService;

    public Ubicacion UbicacionClienteDtoToUbicacion(UbicacionClienteDto dto){
        CodigoPostal cp = codigoPostalService.getCodigoPostalByCodigoPostal(dto.getCodigoPostal());
        return new Ubicacion(dto.getCalle(), dto.getExterior(), dto.getInterior(), dto.getReferencia()
            , cp);
    }

    public UbicacionClienteDto UbicacionToUbicacionClienteDto(Ubicacion ubicacion){
        CodigoPostal cp =
            codigoPostalService.getCodigoPostalByCodigoPostal(ubicacion.getCodigoPostal().getCodigoPostal());
        return new UbicacionClienteDto(ubicacion.getCalle(), ubicacion.getExterior(),
            ubicacion.getInterior(), ubicacion.getReferencias(), cp.getCodigoPostal(),cp.getColonia(),
            cp.getMunicipio().getMunicipio(), cp.getEstado().getEstado());
    }
}
