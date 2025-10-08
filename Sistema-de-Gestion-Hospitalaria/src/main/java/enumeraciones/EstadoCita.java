package enumeraciones;

import lombok.Getter;

public enum EstadoCita {
    PROGRAMADA("Programada"),
    EN_CURSO("En_Curso"),
    COMPLETADA("Completada"),
    CANCELADA("Cancelada"),
    NO_ASISTIO("No_Asistio");

    @Getter
    private final String descripcion;

    EstadoCita(String descripcion){
        this.descripcion = descripcion;
    }
}
