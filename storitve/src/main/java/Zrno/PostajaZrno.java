package Zrno;

import Entitete.Postaja;
import Entitete.Uporabnik;
import storitve.interceptorji.BeleziKlice;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@ApplicationScoped
//@RequestScoped
public class PostajaZrno {

    private Logger logger = Logger.getLogger(PostajaZrno.class.getName());

    @PostConstruct
    private void init() {
        logger.info("Incializacija zrna " + PostajaZrno.class.getSimpleName());
        logger.info("Zrno z id-jem: " + UUID.randomUUID().toString());
    }

    @PreDestroy
    private void destroy(){
        logger.info("Deinicializacija zrna " + PostajaZrno.class.getSimpleName());
    }

    @PersistenceContext(unitName = "lokacijski-opomniki-jpa")
    private EntityManager em;


    //CRUD operacije
    //CREATE
    @BeleziKlice
    @Transactional
    public Postaja dodajPostajo(Postaja postaja){
        if(postaja != null){
            em.persist(postaja);
        }
        return postaja;
    }

    //READ
    @BeleziKlice
    public List<Postaja> pridobiPostaje() {
        Query q = em.createNamedQuery("Postaja.getAll");
        List<Postaja> resultSet = (List<Postaja>)q.getResultList();
        return resultSet;
    }

    @BeleziKlice
    public Postaja pridobiPostajo(int postajaId) {
        Query q = em.createNamedQuery("Postaja.getById");
        q.setParameter("idPostaje",postajaId);
        Postaja postaja = (Postaja)q.getSingleResult();
        return postaja;
    }
    //UPDATE
    @BeleziKlice
    @Transactional
    public Postaja posodobiPostajo(int postajaId, Postaja postaja){
        Postaja p = em.find(Postaja.class, postajaId);
        postaja.setId(p.getId());
        em.merge(postaja);
        return postaja;
    }
    //DELETE
    @BeleziKlice
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
