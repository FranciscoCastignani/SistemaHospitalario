package entidades;

import enumeraciones.TipoSangre;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.Period;

@MappedSuperclass
@SuperBuilder

public abstract class Persona {
    @Column(nullable = false)
    protected String nombre;

    @Column(nullable = false)
    protected String apellido;

    @Column(nullable = false)
    protected String dni;

    @Column(nullable = false)
    protected LocalDate fechaNacimiento;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    protected TipoSangre tipoSangre;

    public String getNombreCompleto(){
        return nombre+" "+apellido;
    }
    public int getEdad(){
        return Period.between(fechaNacimiento,LocalDate.now()).getYears();
    }
}
