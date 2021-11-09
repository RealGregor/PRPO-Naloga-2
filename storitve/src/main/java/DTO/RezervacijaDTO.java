package DTO;

import java.util.Date;

import Entitete.Postaja;
import Entitete.Uporabnik;

public class RezervacijaDTO extends BaseDTO{
    private Uporabnik uporabnik;
    private Postaja postaja;

    private Date zacetekRezervacije;
    private Date konecRezervacije;


    @Override
    public boolean validate(){
        //TODO: implement

        return true;
    }


    public Date getKonecRezervacije() {
        return konecRezervacije;
    }


    public void setKonecRezervacije(Date konecRezervacije) {
        this.konecRezervacije = konecRezervacije;
    }


    public Date getZacetekRezervacije() {
        return zacetekRezervacije;
    }


    public void setZacetekRezervacije(Date zacetekRezervacije) {
        this.zacetekRezervacije = zacetekRezervacije;
    }


    public Postaja getPostaja() {
        return postaja;
    }


    public void setPostaja(Postaja postaja) {
        this.postaja = postaja;
    }


    public Uporabnik getUporabnik() {
        return uporabnik;
    }


    public void setUporabnik(Uporabnik uporabnik) {
        this.uporabnik = uporabnik;
    }
}
