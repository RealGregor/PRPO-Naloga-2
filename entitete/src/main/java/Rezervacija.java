import org.eclipse.persistence.jpa.jpql.parser.DateTime;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name= "rezervacija")
@NamedQueries(value =
        {
                //vrni vse rezervacije
                @NamedQuery(name = "Rezervacija.getAll",
                        query = "SELECT x FROM Rezervacija x"),
                //vrni rezervacijo
                @NamedQuery(name = "Rezervacija.vrniRezervacijo",
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
    @JoinColumn(name = "uporabnik_id")
    private Uporabnik uporabnik;

    @ManyToOne
    @JoinColumn(name = "postaja_id")
    private Postaja postaja;

    LocalDateTime zacetekRezervacije;
    LocalDateTime konecRezervacije;

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

    public LocalDateTime getZacetekRezervacije() {
        return zacetekRezervacije;
    }

    public void setZacetekRezervacije(LocalDateTime zacetekRezervacije) {
        this.zacetekRezervacije = zacetekRezervacije;
    }

    public LocalDateTime getKonecRezervacije() {
        return konecRezervacije;
    }

    public void setKonecRezervacije(LocalDateTime konecRezervacije) {
        this.konecRezervacije = konecRezervacije;
    }
}
