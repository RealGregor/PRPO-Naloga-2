package DTO;

import Entitete.Lastnistvo;
import Entitete.Najem;
import Entitete.Rezervacija;

import java.util.List;

public class UporabnikDTO {
    private Integer id;
    private String ime;
    private String priimek;
    private String email;
    private String uporabniskoIme;
    private List<Najem> najemi;
    private List<Lastnistvo> lastnistva;
    private List<Rezervacija> rezervacije;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPriimek() {
        return priimek;
    }

    public void setPriimek(String priimek) {
        this.priimek = priimek;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUporabniskoIme() {
        return uporabniskoIme;
    }

    public void setUporabniskoIme(String uporabniskoIme) {
        this.uporabniskoIme = uporabniskoIme;
    }

    public List<Najem> getNajemi() {
        return najemi;
    }

    public void setNajemi(List<Najem> najemi) {
        this.najemi = najemi;
    }

    public List<Lastnistvo> getLastnistva() {
        return lastnistva;
    }

    public void setLastnistva(List<Lastnistvo> lastnistva) {
        this.lastnistva = lastnistva;
    }

    public List<Rezervacija> getRezervacije() {
        return rezervacije;
    }

    public void setRezervacije(List<Rezervacija> rezervacije) {
        this.rezervacije = rezervacije;
    }


}
