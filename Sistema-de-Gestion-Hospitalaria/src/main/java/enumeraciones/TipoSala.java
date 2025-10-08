package enumeraciones;

public enum TipoSala {
    CONSULTORIO("Consultorio"),
    QUIROFANO("Quirofano"),
    EMERGENCIAS("Emergencias");

    private final String descripcion;

    TipoSala(String descripcion){
        this.descripcion = descripcion;
    }
}
