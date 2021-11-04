import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped

public class RezervacijaZrno {

    @PersistenceContext(unitName = "lokacijski-opomniki-jpa")
    private EntityManager em;


    public List<Rezervacija> getRezervacije() {

        // implementacija
        return null;
    }

}
