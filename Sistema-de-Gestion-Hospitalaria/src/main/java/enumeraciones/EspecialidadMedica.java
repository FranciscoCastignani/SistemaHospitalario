package enumeraciones;

import lombok.Getter;

public enum EspecialidadMedica {
    CARDIOLOGIA("Cardiologia"),
    NEUROLOGIA("Neurologia"),
    PEDIATRIA("Pediatria"),
    TRAUMATOLOGIA("Traumatologia"),
    GINECOLOGIA("Ginecologia"),
    UROLOGIA("Urologia"),
    OFTALMOLOGIA("Oftalmologia"),
    DERMATOLOGIA("Dermatologia"),
    PSIQUIATRIA("Psiquiatria"),
    MEDICINA_GENERAL("Medicina_General"),
    CIRUGIA_GENERAL("Cirugia_General"),
    ANESTESIOLOGIA("Anestesiologia");

    @Getter
    private final String descripcion;

    EspecialidadMedica(String descripcion){
        this.descripcion = descripcion;
    }
}
