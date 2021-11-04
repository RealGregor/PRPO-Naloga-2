import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped

public class UporabnikZrno {

    @PersistenceContext(unitName = "lokacijski-opomniki-jpa")
    private EntityManager em;


    public List<Uporabnik> getUporabniki() {

        // implementacija
        return null;
    }

}
