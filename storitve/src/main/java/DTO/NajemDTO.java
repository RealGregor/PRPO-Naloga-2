package DTO;

import Entitete.Postaja;
import Entitete.Uporabnik;

public class NajemDTO extends BaseDTO{
    private Uporabnik uporabnik;
    private Postaja postaja;


    @Override
    public boolean validate(){
        //TODO: implement

        return true;
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
