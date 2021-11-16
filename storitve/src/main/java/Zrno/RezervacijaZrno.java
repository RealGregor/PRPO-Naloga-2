package Zrno;

import Entitete.Rezervacija;
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
public class RezervacijaZrno {

    private Logger logger = Logger.getLogger(RezervacijaZrno.class.getName());

    @PostConstruct
    private void init() {
        logger.info("Incializacija zrna " + RezervacijaZrno.class.getSimpleName());
        logger.info("Zrno z id-jem: " + UUID.randomUUID().toString());
    }

    @PreDestroy
    private void destroy(){
        logger.info("Deinicializacija zrna " + RezervacijaZrno.class.getSimpleName());
    }

    @PersistenceContext(unitName = "lokacijski-opomniki-jpa")
    private EntityManager em;

    //CRUD operacije
    //CREATE
    @BeleziKlice
    @Transactional
    public Rezervacija dodajRezervacijo(Rezervacija rezervacija){
        if(rezervacija != null){
            em.persist(rezervacija);
        }
        return rezervacija;
    }
    //READ
    @BeleziKlice
    public List<Rezervacija> pridobiRezervacije() {
        Query q = em.createNamedQuery("Rezervacija.getAll");
        List<Rezervacija> resultSet = (List<Rezervacija>)q.getResultList();
        return resultSet;
    }

    @BeleziKlice
    public Rezervacija pridobiRezervacijo(int rezervacijaId) {
        Query q = em.createNamedQuery("Rezervacija.getById");
        q.setParameter("rezervacijaId",rezervacijaId);
        Rezervacija rezervacija = (Rezervacija)q.getSingleResult();
        return rezervacija;
    }
    //UPDATE
    @BeleziKlice
    @Transactional
    public Rezervacija posodobiRezervacijo(int rezervacijaId, Rezervacija rezervacija){
        Rezervacija r = em.find(Rezervacija.class, rezervacijaId);
        rezervacija.setId(r.getId());
        em.merge(rezervacija);
        return rezervacija;
    }
    //DELETE
    @BeleziKlice
    @Transactional
    public boolean odstraniRezervacijo(int rezervacijaId) {
        Rezervacija rezervacija = pridobiRezervacijo(rezervacijaId);
        if(rezervacija != null) {
            em.remove(rezervacija);
            return true;
        }
        return false;
    }
}
