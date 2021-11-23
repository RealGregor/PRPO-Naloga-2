package DTO;

import java.util.Date;

public class RezervacijaDTO extends BaseDTO {
    private Integer uporabnik_id;
    private Integer postaja_id;

    private Date zacetekRezervacije;
    private Date konecRezervacije;

    @Override
    public boolean validate() {

        if (uporabnik_id == null || postaja_id == null || zacetekRezervacije == null ||konecRezervacije== null) {
            return false;
        } else if (zacetekRezervacije.after(konecRezervacije)) {
            //nemore rezervirati negativen range
            return false;
        }else if(zacetekRezervacije.before(new Date())){
            //nemore rezervirati v preteklosti
            return false;
        }
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
}
