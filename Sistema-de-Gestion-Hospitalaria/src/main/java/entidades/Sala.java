package entidades;

import enumeraciones.TipoSala;
import jakarta.persistence.*;

import java.util.List;

@Entity

public class Sala {
    //Identificación única por número
    @Id
    private Long id;

    private String numero;

    //Clasificación por tipo
    @Enumerated(EnumType.STRING)
    private TipoSala tipoSala;

    //Pertenece a un departamento específico
    @ManyToOne
    @JoinColumn(name = "id- Departamento")
    private Departamento departamento;

    //Gestiona citas programadas
    @OneToMany(mappedBy = "sala")
    private List<Cita> citas;
}
