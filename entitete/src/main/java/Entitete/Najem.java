package Entitete;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name= "najem")
@NamedQueries(value =
        {
                //vrni vse najeme
                @NamedQuery(name = "Najem.getAll",
                        query = "SELECT x FROM Najem x"),
                //vrni najem
                @NamedQuery(name = "Najem.getById",
                        query = "SELECT x FROM Najem x WHERE x.id = :najemId"),
                //vrni vse najeme uporabnika z id
                @NamedQuery(name = "Najem.vrniNajemeUporabnika",
                        query = "SELECT x FROM Najem x WHERE x.uporabnik = :uporabnikId"),
                //vrni vsa najeme neke postaje
                @NamedQuery(name = "Najem.vrniVseNajemePostaje",
                        query = "SELECT x FROM Najem x WHERE x.postaja = :postajaId"),
                //vrni vse že najete postaje
                @NamedQuery(name = "Najem.vrniZeNajetePostaje",
                        query = "SELECT DISTINCT x.postaja FROM Najem x"),
        })

public class Najem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rajem_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "uporabnik_id")
    private Uporabnik uporabnik;

    @OneToOne
    @JoinColumn(name = "postaja_id")
    private Postaja postaja;

    @Column(name = "cas_polnjenja")
    private Integer casPolnjenja;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "zacetek_polnjenja")
    private Date zacetekPolnjenja;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Uporabnik getUporabnik() {
        return uporabnik;
    }

    public void setUporabnik(Uporabnik uporabnik) {
        this.uporabnik = uporabnik;
    }

    public Postaja getPostaja() {
        return postaja;
    }

    public void setPostaja(Postaja postaja) {
        this.postaja = postaja;
    }

    public Integer getCasPolnjenja() {
        return casPolnjenja;
    }

    public void setCasPolnjenja(Integer casPolnjenja) {
        this.casPolnjenja = casPolnjenja;
    }

    public Date getZacetekPolnjenja() {
        return zacetekPolnjenja;
    }
    public void setZacetekPolnjenja(Date zacetekPolnjenja) {
        this.zacetekPolnjenja = zacetekPolnjenja;
    }
}
