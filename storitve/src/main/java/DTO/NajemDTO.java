package DTO;

import Entitete.Postaja;
import Entitete.Uporabnik;

import javax.persistence.Column;
import java.util.Date;

public class NajemDTO extends BaseDTO{

    private Integer uporabnik_id;
    private Integer postaja_id;
    private Integer casPolnjenja;
    private Date zacetekPolnjenja;

    @Override
    public boolean validate(){
        //TODO: implement
        if(uporabnik_id == null || postaja_id == null){
            return false;
        }
        return true;
    }

    public Integer getUporabnik_id() {
        return uporabnik_id;
    }

    public void setUporabnik_id(Integer uporabnik_id) {
        this.uporabnik_id = uporabnik_id;
    }

    public Integer getPostaja_id() {
        return postaja_id;
    }

    public void setPostaja_id(Integer postaja_id) {
        this.postaja_id = postaja_id;
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
