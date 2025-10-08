package entidades;

import enumeraciones.EstadoCita;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity

public class Cita {
    @Id
    private Long id;

    private LocalDateTime fechaHora;

    private BigDecimal costo;

    private String observaciones;

    //Vincula Paciente
    @OneToOne
    @JoinColumn(name = "id-Paciente")
    private Paciente paciente;

    //Vincula Médico
    @ManyToOne
    @JoinColumn(name = "id-Medico")
    private Medico medico;

    //Vincula Sala
    @OneToOne
    @JoinColumn(name = "id-Sala")
    private Sala sala;

    //Estado Cita
    @Enumerated(EnumType.STRING)
    private EstadoCita estado;

}
