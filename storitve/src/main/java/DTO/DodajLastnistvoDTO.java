package DTO;

import Entitete.Postaja;
import Entitete.Uporabnik;

public class DodajLastnistvoDTO {
    private Integer uporabnik_id;
    private Integer postaja_id;

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
