package entidades;

import enumeraciones.EspecialidadMedica;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED,force = true)

public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150)
    private String nombre;

    //Organización por especialidad médica
    @Enumerated(EnumType.STRING)
    private EspecialidadMedica especialidadMedica;

    //Agrupa médicos de la misma especialidad
    @OneToMany(mappedBy = "departamento",cascade = CascadeType.ALL)
    private List<Medico> medicos;

    //Administra salas especializadas
    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL)
    private List<Sala> salasEspecializadas;

    @ManyToOne
    @JoinColumn(name = "id-Hospital")
    @Setter
    @Getter
    private Hospital hospital;

    @Builder
    public Departamento(String nombre, EspecialidadMedica especialidadMedica){
        this.nombre = nombre;
        this.especialidadMedica = especialidadMedica;
        this.medicos = new ArrayList<>();
        this.salasEspecializadas = new ArrayList<>();
    }

    public void addMedico(Medico medico){
        if(medico.getEspecialidad() != especialidadMedica){
            throw new IllegalArgumentException("Solo se aceptan medicos de "+especialidadMedica+"en este departamento");
        }
        if(medicos.contains(medico)){
            throw new IllegalArgumentException(medico.getNombreCompleto()+" ya es parte del departamento");
        }
        agregarMedico(medico);
    }

    private void agregarMedico(Medico medico){
        medicos.add(medico);
        medico.setDepartamento(this);
    }

    public List<Sala> getSalasEspecializadas(){
        return Collections.unmodifiableList(salasEspecializadas);
    }
    public List<Medico> getMedicos(){
        return Collections.unmodifiableList(medicos);
    }

    public void crearSala(String identificador, String tipoSala){
        Sala sala =new Sala(identificador,tipoSala,this);
        salasEspecializadas.add(sala);
        System.out.println("sala creada correctamente");
    }
}
