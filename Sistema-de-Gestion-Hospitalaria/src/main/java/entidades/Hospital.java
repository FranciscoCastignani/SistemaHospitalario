package entidades;

import jakarta.persistence.*;

import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor

public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true,length = 200)
    private String nombre;

    @Column(nullable = false,length = 300)
    private String direccion;

    @Column(nullable = false,length = 20)
    private String telefono;

    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
    private List<Departamento> departamentos;

    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
    private List<Paciente> pacientes;

    @Builder
    public Hospital(String nombre, String telefono, String direccion) {
        this.pacientes = new ArrayList<>();
        this.departamentos = new ArrayList<>();

        validarString(nombre, "El nombre del hospital no puede ser nulo ni vacío");
        validarString(telefono, "La dirección no puede ser nula ni vacía");
        validarString(direccion, "El teléfono no puede ser nulo ni vacío");

        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public void validarString (String campo,String mensajeError) throws IllegalArgumentException{
        if(campo == null || campo.isBlank()){
            throw new IllegalArgumentException(mensajeError);
        }
    }

    public void addPaciente(Paciente paciente){
        pacientes.add(paciente);
        paciente.setHospital(this);
    }

    public void addDepartamento(Departamento departamento){
        departamentos.add(departamento);
        departamento.setHospital(this);
    }
}
