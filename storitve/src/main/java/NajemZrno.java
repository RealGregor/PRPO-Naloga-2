import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped

public class NajemZrno {

    @PersistenceContext(unitName = "lokacijski-opomniki-jpa")
    private EntityManager em;


    public List<Najem> getNajeme() {

        // implementacija
        return null;
    }

}
