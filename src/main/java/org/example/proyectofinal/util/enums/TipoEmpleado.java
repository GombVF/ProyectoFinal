package org.example.proyectofinal.util.enums;

public enum TipoEmpleado {
    EJECUTIVO("Ejecutivo"),
    ANALISTA("Analista"),
    COORDINADOR("Coordinador"),
    JEFE_DE_AREA("Jefe de área"),
    SUBDIRECTOR("Subdirector"),
    DIRECTOR("Director");


    private String tipoEmpleado;

    TipoEmpleado(String tipoEmpleado){
        this.tipoEmpleado = tipoEmpleado;
    }

    public String getTipoEmpleado(){
        return this.tipoEmpleado;
    }
}
