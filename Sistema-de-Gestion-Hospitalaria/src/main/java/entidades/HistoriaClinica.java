package entidades;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Entity

public class HistoriaClinica {
    @Id
    private Long id;

    private LocalDate fechaCreacion;
    private String numeroHistoria;

    @OneToOne(mappedBy = "historiaClinica")
    private Paciente paciente;

    @ElementCollection
    @CollectionTable(name = "Diagnostico-Medico", joinColumns =  @JoinColumn(name = "id-Historia_Clinica"))
    private List<String> diagnositosMedicos;

    @ElementCollection
    @CollectionTable(name = "Tratamiento-Prescrito", joinColumns =  @JoinColumn(name = "id-Historia_Clinica"))
    private List<String> tratamientosPrescritos;

    @ElementCollection
    @CollectionTable(name = "Alergia-Conocida", joinColumns =  @JoinColumn(name = "id-Historia_Clinica"))
    private List<String> alergiasConocidas;

}
