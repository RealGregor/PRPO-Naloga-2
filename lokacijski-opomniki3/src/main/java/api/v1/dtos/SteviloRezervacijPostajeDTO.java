package api.v1.dtos;

public class SteviloRezervacijPostajeDTO {
    public SteviloRezervacijPostajeDTO(Integer postaja_id, Integer steviloRezervacij){
        this.postaja_id=postaja_id;
        this.steviloRezervacij=steviloRezervacij;
    }
    private Integer postaja_id;
    private Integer steviloRezervacij;

    public Integer getPostaja_id() {
        return postaja_id;
    }

    public void setPostaja_id(Integer postaja_id) {
        this.postaja_id = postaja_id;
    }

    public Integer getSteviloRezervacij() {
        return steviloRezervacij;
    }

    public void setSteviloRezervacij(Integer steviloRezervacij) {
        this.steviloRezervacij = steviloRezervacij;
    }
}
