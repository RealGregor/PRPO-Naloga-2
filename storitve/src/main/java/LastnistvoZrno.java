import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@ApplicationScoped

public class LastnistvoZrno {

    @PersistenceContext(unitName = "lokacijski-opomniki-jpa")
    private EntityManager em;


    public List<Lastnistvo> getLastnistva() {
        Query q = em.createNamedQuery("Lastnistvo.getAll");
        List<Lastnistvo> resultSet = (List<Lastnistvo>)q.getResultList();
        return resultSet;
    }

}
