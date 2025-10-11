import entidades.Departamento;
import entidades.Hospital;
import enumeraciones.EspecialidadMedica;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import patrones.SingletonDB;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        SingletonDB singletonDB = new SingletonDB("hospital-persistence-unit");
        EntityManagerFactory emf = singletonDB.ConectarBDD() ;
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try{

           Hospital hospital1 = Hospital.builder()
                    .nombre("Hospital 1")
                    .telefono("123-1234567")
                    .direccion("Direccion falsa Hospital 1")
                    .build();
            Departamento cardiologia = Departamento.builder()
                    .nombre("Cardiología")
                    .especialidadMedica(EspecialidadMedica.CARDIOLOGIA)
                    .build();
            Departamento dermatologia = Departamento.builder()
                    .nombre("Dermatologia")
                    .especialidadMedica(EspecialidadMedica.DERMATOLOGIA)
                    .build();
            Departamento ginecologia = Departamento.builder()
                    .nombre("Ginecologia")
                    .especialidadMedica(EspecialidadMedica.GINECOLOGIA)
                    .build();
            hospital1.addDepartamento(cardiologia);
            hospital1.addDepartamento(dermatologia);
            hospital1.addDepartamento(ginecologia);

            ginecologia.crearSala("GIN-101", "Consultorio");
            dermatologia.crearSala("DERM-101", "Consultorio");
            cardiologia.crearSala("CARD-101", "Consultorio");

            ginecologia.getSalasEspecializadas().getFirst().mostrarHorarios(LocalDate.now());
            em.persist(hospital1);

            //Hospital.consultarInfromacionHospital(emf,1L);

            System.out.println("Todo correcto");

            em.getTransaction().commit();
        } catch (Throwable t) {
            em.getTransaction().rollback();
            System.err.println("= = = = = Error detectado:  " + t);
            t.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}