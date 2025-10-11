package entidades;

import enumeraciones.EspecialidadMedica;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@SuperBuilder

public class Medico extends Persona{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Embedded
    private Matricula matriculaProfecional;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Getter
    private EspecialidadMedica especialidad;

    @ManyToOne
    @JoinColumn(name = "id-Departamento")
    @Setter
    private Departamento departamento;

    @OneToMany(mappedBy = "medico")
    private List<Cita> citas;

}
