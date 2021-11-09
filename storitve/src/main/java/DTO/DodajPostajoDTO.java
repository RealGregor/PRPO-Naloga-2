package DTO;

import Entitete.Postaja;
import Entitete.Uporabnik;

public class DodajPostajoDTO{
    private Uporabnik uporabnik;
    private Postaja postaja;

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
