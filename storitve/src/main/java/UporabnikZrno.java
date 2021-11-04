import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@ApplicationScoped

public class UporabnikZrno {

    @PersistenceContext(unitName = "lokacijski-opomniki-jpa")
    private EntityManager em;


    public List<Uporabnik> getUporabniki() {
        // implementacija
        Query q = em.createNamedQuery("Uporabnik.getAll");
        List<Uporabnik> resultSet = (List<Uporabnik>)q.getResultList();
        return resultSet;
    }

}
