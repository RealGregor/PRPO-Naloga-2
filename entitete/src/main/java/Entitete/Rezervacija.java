package Entitete;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name= "rezervacija")
@NamedQueries(value =
        {
                //vrni vse rezervacije
                @NamedQuery(name = "Rezervacija.getAll",
                        query = "SELECT x FROM Rezervacija x"),
                //vrni rezervacijo
                @NamedQuery(name = "Rezervacija.getById",
                        query = "SELECT x FROM Rezervacija x WHERE x.id = :rezervacijaId"),
                //vrni vse rezervacije uporabnika z id
                @NamedQuery(name = "Rezervacija.vrniRezervacijeUporabnika",
                        query = "SELECT x FROM Rezervacija x WHERE x.uporabnik = :uporabnikId"),
                //vrni vsa rezervacije neke postaje
                @NamedQuery(name = "Rezervacija.vrniVseLastnike",
                        query = "SELECT x FROM Rezervacija x WHERE x.postaja = :postajaId"),
        })

public class Rezervacija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rezervacija_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "postaja_id")
    private Postaja postaja;

    @ManyToOne
    @JoinColumn(name = "uporabnik_id")
    private Uporabnik uporabnik;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "zacetek_rezervacije")
    Date zacetekRezervacije;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "konec_rezervacije")
    Date konecRezervacije;

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

    public Date getZacetekRezervacije() {
        return zacetekRezervacije;
    }

    public void setZacetekRezervacije(Date zacetekRezervacije) {
        this.zacetekRezervacije = zacetekRezervacije;
    }

    public Date getKonecRezervacije() {
        return konecRezervacije;
    }

    public void setKonecRezervacije(Date konecRezervacije) {
        this.konecRezervacije = konecRezervacije;
    }

    /*public String getZacetekRezervacije() {
        return zacetekRezervacije;
    }

    public void setZacetekRezervacije(String zacetekRezervacije) {
        this.zacetekRezervacije = zacetekRezervacije;
    }

    public String getKonecRezervacije() {
        return konecRezervacije;
    }

    public void setKonecRezervacije(String konecRezervacije) {
        this.konecRezervacije = konecRezervacije;
    }
    */

}
