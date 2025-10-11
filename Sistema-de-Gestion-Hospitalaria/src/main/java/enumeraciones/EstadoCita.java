package enumeraciones;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

public enum EstadoCita {
    PROGRAMADA("Programada"),
    EN_CURSO("En_Curso"),
    COMPLETADA("Completada"),
    CANCELADA("Cancelada"),
    NO_ASISTIO("No_Asistio");

    @Getter
    private final String descripcion;

}
