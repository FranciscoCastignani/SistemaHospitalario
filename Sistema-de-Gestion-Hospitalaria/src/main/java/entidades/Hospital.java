package entidades;

import jakarta.persistence.*;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED,force = true)
@ToString

public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true,length = 200)
    private final String nombre;

    @Column(nullable = false,length = 300)
    private final String direccion;

    @Column(nullable = false,length = 20)
    private final String telefono;

    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Departamento> departamentos;

    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Paciente> pacientes;

    @Builder
    protected Hospital(String nombre, String telefono, String direccion) {
        validarString(nombre, "El nombre del hospital no puede ser nulo ni vacío");
        validarString(telefono, "La dirección no puede ser nula ni vacía");
        validarString(direccion, "El teléfono no puede ser nulo ni vacío");

        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;

        this.pacientes = new ArrayList<>();
        this.departamentos = new ArrayList<>();
    }

    @ToString.Include(name = "CantidadDepartamentos")
    public int getCantDepartamentos(){
        return departamentos.size();
    }
    @ToString.Include(name = "CantidadPacientes")
    public int getCantPacientes(){
        return pacientes.size();
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

    public static void consultarInfromacionHospital(EntityManagerFactory emf, Long hospitalId){
        Hospital hospital = null;
        EntityManager em = emf.createEntityManager();
        try{
        TypedQuery<Hospital> query = em.createQuery(
                "SELECT h FROM Hospital h WHERE h.id = :id", Hospital.class).setParameter("id", hospitalId);
        hospital = query.getSingleResult();
            System.out.println(hospital.toString());
        } catch (RuntimeException e){
            throw e;
        } finally{
            if(em.isOpen()){
                em.close();
            }
        }
    }

    public List<Departamento> getDepartamentos(){
        return Collections.unmodifiableList(departamentos);
    }

    public List<Paciente> getPacientes(){
        return Collections.unmodifiableList(pacientes);
    }

}
