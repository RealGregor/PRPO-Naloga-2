package DTO;

public class PostajeDTO {
    private String ime;
    private String specifikacije;
    private String lokacija;
    private double cena;
    private String obratovalniCasZacetek;
    private String obratovalniCasKonec;

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getSpecifikacije() {
        return specifikacije;
    }

    public void setSpecifikacije(String specifikacije) {
        this.specifikacije = specifikacije;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getObratovalniCasZacetek() {
        return obratovalniCasZacetek;
    }

    public void setObratovalniCasZacetek(String obratovalniCasZacetek) {
        this.obratovalniCasZacetek = obratovalniCasZacetek;
    }

    public String getObratovalniCasKonec() {
        return obratovalniCasKonec;
    }

    public void setObratovalniCasKonec(String obratovalniCasKonec) {
        this.obratovalniCasKonec = obratovalniCasKonec;
    }
}
