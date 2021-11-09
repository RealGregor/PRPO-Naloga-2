package Zrno;

import Entitete.Uporabnik;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;


@ApplicationScoped
public class UporabnikZrno {

    private Logger logger = Logger.getLogger(UporabnikZrno.class.getName());

    @PostConstruct
    private void init() {
        logger.info("Incializacija zrna " + UporabnikZrno.class.getSimpleName());
        logger.info("Zrno z id-jem: " + UUID.randomUUID().toString());
    }

    @PreDestroy
    private void destroy(){
        logger.info("Deinicializacija zrna " + UporabnikZrno.class.getSimpleName());
    }

    @PersistenceContext(unitName = "lokacijski-opomniki-jpa")
    private EntityManager em;

    //CRUD operacije
    //CREATE
    @Transactional
    public Uporabnik dodajUporabnika(Uporabnik uporabnik){
        if(uporabnik != null){
            em.persist(uporabnik);
        }
        return uporabnik;
    }
    //READ
    public List<Uporabnik> pridobiUporabnike() {
        Query q = em.createNamedQuery("Uporabnik.getAll");
        List<Uporabnik> resultSet = (List<Uporabnik>)q.getResultList();
        return resultSet;
    }

    public Uporabnik pridobiUporabnika(int uporabnikId) {
        Query q = em.createNamedQuery("Uporabnik.getById");
        q.setParameter("id",uporabnikId);
        Uporabnik uporabnik = (Uporabnik)q.getSingleResult();
        return uporabnik;
    }
    //UPDATE
    @Transactional
    public Uporabnik posodobiUporabnika(int uporabnikId, Uporabnik uporabnik){
        Uporabnik u = em.find(Uporabnik.class, uporabnikId);
        uporabnik.setId(u.getId());
        em.merge(uporabnik);
        return uporabnik;
    }
    //DELETE
    @Transactional
    public boolean odstraniUporabnika(int uporabnikId) {
        Uporabnik uporabnik = pridobiUporabnika(uporabnikId);
        if(uporabnik != null) {
            em.remove(uporabnik);
            return true;
        }
        return false;
    }

    //CriteriaAPI
    public List<Uporabnik> getUporabnikiCriteriaAPI() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Uporabnik> c = cb.createQuery(Uporabnik.class);
        Root<Uporabnik> emp = c.from(Uporabnik.class);
        TypedQuery<Uporabnik> query = em.createQuery(c);
        List<Uporabnik> resultSet = query.getResultList();
        return resultSet;
    }
}
