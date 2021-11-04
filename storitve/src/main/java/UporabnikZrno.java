import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
    public List<Uporabnik> getUporabnikiCriteriaAPI() {
        // implementacija
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Uporabnik> c = cb.createQuery(Uporabnik.class);
        Root<Uporabnik> emp = c.from(Uporabnik.class);
        List<Uporabnik> resultSet = (List<Uporabnik>)c.select(emp);
        return resultSet;
    }
}
