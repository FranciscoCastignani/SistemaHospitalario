package entidades;

import enumeraciones.EspecialidadMedica;
import jakarta.persistence.*;

import java.util.List;

@Entity

public class Departamento {
    @Id
    private Long id;

    private String nombre;

    //Organización por especialidad médica
    @Enumerated(EnumType.STRING)
    private EspecialidadMedica especialidadMedica;

    //Agrupa médicos de la misma especialidad
    @OneToMany(mappedBy = "departamento")
    private List<Medico> medicos;

    //Administra salas especializadas
    @OneToMany(mappedBy = "departamento")
    private List<Sala> salasEspecializadas;

    @ManyToOne
    @JoinColumn(name = "id-Hospital")
    private Hospital hospital;
}
