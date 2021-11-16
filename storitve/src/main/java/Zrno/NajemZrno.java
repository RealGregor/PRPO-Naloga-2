package Zrno;

import Entitete.Najem;
import Entitete.Uporabnik;
import storitve.interceptorji.BeleziKlice;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@ApplicationScoped
public class NajemZrno {

    private Logger logger = Logger.getLogger(NajemZrno.class.getName());

    @PostConstruct
    private void init() {
        logger.info("Incializacija zrna " + NajemZrno.class.getSimpleName());
        logger.info("Zrno z id-jem: " + UUID.randomUUID().toString());
    }

    @PreDestroy
    private void destroy(){
        logger.info("Deinicializacija zrna " + NajemZrno.class.getSimpleName());
    }

    @PersistenceContext(unitName = "lokacijski-opomniki-jpa")
    private EntityManager em;

    //CRUD operacije
    //CREATE
    @BeleziKlice
    @Transactional
    public Najem dodajNajem(Najem najem){
        if(najem != null){
            em.persist(najem);
        }
        return najem;
    }
    //READ
    @BeleziKlice
    public List<Najem> pridobiNajeme() {
        Query q = em.createNamedQuery("Najem.getAll");
        List<Najem> resultSet = (List<Najem>)q.getResultList();
        return resultSet;
    }

    @BeleziKlice
    public Najem pridobiNajem(int najemId) {
        Query q = em.createNamedQuery("Najem.getById");
        q.setParameter("najemId",najemId);
        Najem najem = (Najem)q.getSingleResult();
        return najem;
    }
    //UPDATE
    @BeleziKlice
    @Transactional
    public Najem posodobiNajem(int najemId, Najem najem){
        Najem n = em.find(Najem.class, najemId);
        najem.setId(n.getId());
        em.merge(najem);
        return najem;
    }
    //DELETE
    @BeleziKlice
    @Transactional
    public boolean odstraniNajem(int najemId) {
        Najem najem = pridobiNajem(najemId);
        if(najem != null) {
            em.remove(najem);
            return true;
        }
        return false;
    }
}
