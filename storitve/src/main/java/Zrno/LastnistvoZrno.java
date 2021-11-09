package Zrno;

import Entitete.Lastnistvo;
import Entitete.Uporabnik;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class LastnistvoZrno {

    private Logger logger = Logger.getLogger(LastnistvoZrno.class.getName());

    @PostConstruct
    private void init() {
        logger.info("Incializacija zrna " + LastnistvoZrno.class.getSimpleName());
    }

    @PreDestroy
    private void destroy(){
        logger.info("Deinicializacija zrna " + LastnistvoZrno.class.getSimpleName());
    }

    @PersistenceContext(unitName = "lokacijski-opomniki-jpa")
    private EntityManager em;

    //CRUD operacije
    //CREATE
    @Transactional
    public Lastnistvo dodajLastnistvo(Lastnistvo lastnistvo){
        if(lastnistvo != null){
            em.persist(lastnistvo);
        }
        return lastnistvo;
    }
    //READ
    public List<Lastnistvo> pridobiLastnistva() {
        Query q = em.createNamedQuery("Lastnistvo.getAll");
        List<Lastnistvo> resultSet = (List<Lastnistvo>)q.getResultList();
        return resultSet;
    }

    public Lastnistvo pridobiLastnistvo(int lastnistvoId) {
        Query q = em.createNamedQuery("Lastnistvo.getById");
        q.setParameter("idLastnistva",lastnistvoId);
        Lastnistvo lastnistvo = (Lastnistvo)q.getSingleResult();
        return lastnistvo;
    }
    //UPDATE
    @Transactional
    public Lastnistvo posodobiLastnistvo(int lastnistvoId, Lastnistvo lastnistvo){
        Lastnistvo l = em.find(Lastnistvo.class, lastnistvoId);
        lastnistvo.setId(l.getId());
        em.merge(lastnistvo);
        return lastnistvo;
    }
    //DELETE
    @Transactional
    public boolean odstraniLastnistvo(int lastnistvoId) {
        Lastnistvo lastnistvo = pridobiLastnistvo(lastnistvoId);
        if(lastnistvo != null) {
            em.remove(lastnistvo);
            return true;
        }
        return false;
    }
}