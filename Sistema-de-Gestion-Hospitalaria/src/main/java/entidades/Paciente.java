package entidades;

import jakarta.persistence.*;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Entity
@SuperBuilder

public class Paciente extends Persona{
    @Id
    private Long id;

    private String direccion;
    private String telefono;

    @OneToOne
    @JoinColumn(name = "id-Historia_Clinica",nullable = false)
    private HistoriaClinica historiaClinica;

    @ManyToOne
    @JoinColumn(name = "id-hospital")
    @Setter
    private Hospital hospital;

    @OneToMany(mappedBy = "paciente")
    private List<Cita> citas;

}
