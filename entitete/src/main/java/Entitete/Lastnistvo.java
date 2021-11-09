package Entitete;

import javax.persistence.*;

@Entity
@Table(name= "lastnistvo")
@NamedQueries(value =
        {
                //vrni vsa lastnistva
                @NamedQuery(name = "Lastnistvo.getAll",
                        query = "SELECT x FROM Lastnistvo x"),
                //vrni lastnistvo
                @NamedQuery(name = "Lastnistvo.getById",
                        query = "SELECT x FROM Lastnistvo x WHERE x.id = :idLastnistva"),
                //vrni vsa lastnistva uporabnika z id
                @NamedQuery(name = "Lastnistvo.vrniVsaLasnistvaId",
                        query = "SELECT x.postaja FROM Lastnistvo x WHERE x.uporabnik = :uporabnikId"),
                //vrni vsa lastnike neke polnilnice
                @NamedQuery(name = "Lastnistvo.vrniVseLastnike",
                        query = "SELECT x.uporabnik FROM Lastnistvo x WHERE x.postaja = :postajaId"),
                //vrni vse postaje
                @NamedQuery(name = "Lastnistvo.vrniVsePostaje",
                        query = "SELECT DISTINCT x.postaja FROM Lastnistvo x"),
        })

public class Lastnistvo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lastnistvo_id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "postaja_id")
    private Postaja postaja;

    @ManyToOne
    @JoinColumn(name = "uporabnik_id")
    private Uporabnik uporabnik;

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
}
