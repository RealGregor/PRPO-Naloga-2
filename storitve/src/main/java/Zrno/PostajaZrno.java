package Zrno;

import Entitete.Postaja;
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
public class PostajaZrno {

    private Logger logger = Logger.getLogger(PostajaZrno.class.getName());

    @PostConstruct
    private void init() {
        logger.info("Incializacija zrna " + PostajaZrno.class.getSimpleName());
    }

    @PreDestroy
    private void destroy(){
        logger.info("Deinicializacija zrna " + PostajaZrno.class.getSimpleName());
    }

    @PersistenceContext(unitName = "lokacijski-opomniki-jpa")
    private EntityManager em;


    //CRUD operacije
    //CREATE
    @Transactional
    public Postaja dodajPostajo(Postaja postaja){
        if(postaja != null){
            em.persist(postaja);
        }
        return postaja;
    }

    //READ
    public List<Postaja> pridobiPostaje() {
        Query q = em.createNamedQuery("Postaja.getAll");
        List<Postaja> resultSet = (List<Postaja>)q.getResultList();
        return resultSet;
    }

    public Postaja pridobiPostajo(int postajaId) {
        Query q = em.createNamedQuery("Postaja.getById");
        q.setParameter("idPostaje",postajaId);
        Postaja postaja = (Postaja)q.getSingleResult();
        return postaja;
    }
    //UPDATE
    @Transactional
    public Postaja posodobiPostajo(int postajaId, Postaja postaja){
        Postaja p = em.find(Postaja.class, postajaId);
        postaja.setId(p.getId());
        em.merge(postaja);
        return postaja;
    }
    //DELETE
    @Transactional
    public boolean odstraniPostajo(int postajaId) {
        Postaja postaja = pridobiPostajo(postajaId);
        if(postaja != null) {
            em.remove(postaja);
            return true;
        }
        return false;
    }
}
