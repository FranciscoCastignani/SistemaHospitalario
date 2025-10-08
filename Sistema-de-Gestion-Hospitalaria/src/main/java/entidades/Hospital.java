package entidades;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity

public class Hospital {
    @Id
    private Long id;

    private String nombre;
    private String direccion;
    private String telefono;

    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
    private List<Departamento> departamentos;

    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
    private List<Paciente> pacientes;
}
