package Entitete;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name= "postaja")
@NamedQueries(value =
        {
                //vrni vse postaje
                @NamedQuery(name = "Postaja.getAll",
                        query = "SELECT p FROM Postaja p"),
                //vrni vse lokacije postaj
                @NamedQuery(name = "Postaja.getAllLocations",
                        query = "SELECT p.lokacija FROM Postaja p"),
                //vrni vse postaje na določeni lokaciji
                @NamedQuery(name = "Postaja.getStationsByLocation",
                        query = "SELECT p FROM Postaja p WHERE p.lokacija = :lokacija"),
                //vrni vse postaje s ceno manjšo od podane
                @NamedQuery(name = "Postaja.getStationsCheaperThan",
                        query = "SELECT p FROM Postaja p WHERE p.cena < :cena"),
        })

public class Postaja implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postaja_id")
    private Integer id;

    private String ime;
    private String specifikacije;
    private String lokacija;
    private double cena;

    @Column(name = "obratovalni_cas_zacetek")
    private String obratovalniCasZacetek;

    @Column(name = "obratovalni_cas_konec")
    private String obratovalniCasKonec;

    @OneToOne(mappedBy = "postaja")
    // @JoinColumn(name = "postaja_id")
    private Najem najem;

    @OneToOne(mappedBy = "postaja")
    // @JoinColumn(name = "postaja_id")
    private Lastnistvo lastnistvo;

    @OneToMany(mappedBy = "postaja")
    // @JoinColumn(name = "postaja_id")
    private List<Rezervacija> rezervacije;


    //getters and setters
    public Integer getId() {
        return this.id;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public String getSpecifikacije() {
        return specifikacije;
    }

    public void setSpecifikacije(String specifikacije) {
        this.specifikacije = specifikacije;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIme() {
        return this.ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }
    
    public String getObratovalniCasZacetek() {
        return this.obratovalniCasZacetek;
    }

    public void setObratovalniCasZacetek(String cas) {
        this.obratovalniCasZacetek= cas;
    }
    
    public String getObratovalniCasKonec() {
        return this.obratovalniCasKonec;
    }

    public void setObratovalniCasKonec(String cas) {
        this.obratovalniCasKonec = cas;
    }

    public Najem getNajem() {
        return this.najem;
    }

    public void setNajem(Najem najem) {
        this.najem = najem;
    }

    public Lastnistvo getLastnistvo() {
        return this.lastnistvo;
    }

    public void setLastnistvo(Lastnistvo lastnistvo) {
        this.lastnistvo = lastnistvo;
    }

    public List<Rezervacija> getRezervacije() {
        return this.rezervacije;
    }

    public void setRezervacije(List<Rezervacija> rezervacije) {
        this.rezervacije = rezervacije;
    }
}
