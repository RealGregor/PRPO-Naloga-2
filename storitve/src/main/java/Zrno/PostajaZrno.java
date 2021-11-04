package Zrno;

import Entitete.Postaja;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@ApplicationScoped

public class PostajaZrno {

    @PersistenceContext(unitName = "lokacijski-opomniki-jpa")
    private EntityManager em;


    public List<Postaja> getPostaje() {
        Query q = em.createNamedQuery("Postaja.getAll");
        List<Postaja> resultSet = (List<Postaja>)q.getResultList();
        return resultSet;
    }

}
