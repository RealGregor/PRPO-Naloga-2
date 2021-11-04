import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name= "uporabnik")
@NamedQueries(value =
        {
                //vrni vse uporabnike
                @NamedQuery(name = "Uporabnik.getAll",
                        query = "SELECT x FROM Uporabnik x"),
                //vrni vse uporabnike
                @NamedQuery(name = "Uporabnik.getAllUsernames",
                        query = "SELECT x.uporabniskoIme FROM Uporabnik x"),
                //vrni vse uporabnike z določenim imenom
                @NamedQuery(name = "Uporabnik.getUsersByName",
                        query = "SELECT x FROM Uporabnik x WHERE x.ime = :ime"),
                //vrni vse uporabnike z določenim imenom
                @NamedQuery(name = "Uporabnik.getUserByEmail",
                        query = "SELECT x FROM Uporabnik x WHERE x.email = :email"),
                //vrni vse polnilne postaje uporabnika
                @NamedQuery(name = "Uporabnik.vrniLastnistva",
                        query = "SELECT x.lastnistva FROM Uporabnik x WHERE x.id = :id")
        })

public class Uporabnik implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uporabnik_id")
    private Integer id;

    private String ime;
    private String priimek;
    private String email;

    @Column(name = "uporabnisko_ime")
    private String uporabniskoIme;

    @OneToMany(mappedBy = "uporabnik")
    private List<Najem> najemi;

    @OneToMany(mappedBy = "uporabnik")
    private List<Lastnistvo> lastnistva;

    @OneToMany(mappedBy = "uporabnik")
    private List<Rezervacija> rezervacije;

    //getters and setters
    public Integer getId() {
        return this.id;
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

    public String getPriimek() {
        return this.priimek;
    }

    public void setPriimek(String priimek) {
        this.priimek = priimek;
    }

    public String getUporabniskoIme() {
        return this.uporabniskoIme;
    }

    public void setUporabniskoIme(String uporabniskoIme) {
        this.uporabniskoIme = uporabniskoIme;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public List<Lastnistvo> getLastnistva() {
        return this.lastnistva;
    }

    public void setLastnistva(List<Lastnistvo> lastnistva) {
        this.lastnistva = lastnistva;
    }
    public List<Najem> getNajemi() {
        return this.najemi;
    }

    public void setNajemi(List<Najem> najemi) {
        this.najemi = najemi;
    }

    public List<Rezervacija> getRezervacije() {
        return rezervacije;
    }

    public void setRezervacije(List<Rezervacija> rezervacije) {
        this.rezervacije = rezervacije;
    }
}
