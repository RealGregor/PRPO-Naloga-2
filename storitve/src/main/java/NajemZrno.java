import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@ApplicationScoped

public class NajemZrno {

    @PersistenceContext(unitName = "lokacijski-opomniki-jpa")
    private EntityManager em;


    public List<Najem> getNajeme() {
        Query q = em.createNamedQuery("Najem.getAll");
        List<Najem> resultSet = (List<Najem>)q.getResultList();
        return resultSet;
    }

}
