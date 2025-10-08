package patrones;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class SingletonDB {
    private EntityManagerFactory emf;
    private static String PU;

    public EntityManagerFactory ConectarBDD(){
        if (emf == null){
            emf = Persistence.createEntityManagerFactory(PU);
        }
        return emf;
    }

    public SingletonDB(String PU){
        SingletonDB.PU = PU;
    }

}
