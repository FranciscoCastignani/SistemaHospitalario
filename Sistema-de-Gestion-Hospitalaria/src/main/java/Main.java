import entidades.Hospital;
import jakarta.persistence.EntityManagerFactory;
import patrones.SingletonDB;

public class Main {
    public static void main(String[] args) {
        try{

        SingletonDB singletonDB = new SingletonDB("Sistema-Hospitalario-PU");
        EntityManagerFactory emf = singletonDB.ConectarBDD() ;



        emf.close();

        System.out.println("Todo correcto");


        } catch (Throwable t) {
            System.err.println("= = = = = Error detectado:  " + t);
            t.printStackTrace();
        }
    }
}