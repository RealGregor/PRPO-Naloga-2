import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@ApplicationScoped

public class RezervacijaZrno {

    @PersistenceContext(unitName = "lokacijski-opomniki-jpa")
    private EntityManager em;


    public List<Rezervacija> getRezervacije() {
        Query q = em.createNamedQuery("Rezervacija.getAll");
        List<Rezervacija> resultSet = (List<Rezervacija>)q.getResultList();
        return resultSet;
    }

}
