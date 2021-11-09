package Zrno;

import DTO.*;
import Entitete.Najem;
import Entitete.Rezervacija;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.logging.Logger;

@ApplicationScoped
public class UpravljanjePolnilnicZrno {
    private Logger logger = Logger.getLogger(UporabnikZrno.class.getName());

    @Inject
    private UporabnikZrno uporabnikiZrno;
    @Inject
    private PostajaZrno postajeZrno;
    @Inject
    private LastnistvoZrno lastnistvaZrno;
    @Inject
    private RezervacijaZrno rezervacijeZrno;
    @Inject
    private NajemZrno najemiZrno;

    @PostConstruct
    private void init() {
        logger.info("Incializacija zrna " + UpravljanjePolnilnicZrno.class.getSimpleName());
    }

    @PreDestroy
    private void destroy() {
        logger.info("Deinicializacija zrna " + UpravljanjePolnilnicZrno.class.getSimpleName());
    }

    // poslovne metode
    // 1. Uporabnik?
    // 2. Polnilnice
    // 3. Rezervacije
    // 4. Najemi

    public void najemPolnilnice(NajemDTO najemDTO) {
        if (!najemDTO.validate()) {
            return;
        }
        var najem = new Najem();

        najem.setUporabnik(najemDTO.getUporabnik());
        najem.setPostaja(najemDTO.getPostaja());
        najem.setCasPolnjenja(0);

        najemiZrno.dodajNajem(najem);
    }

    public void rezervacijaPolnilnice(RezervacijaDTO rezervacijaDTO) {
        if (!rezervacijaDTO.validate()) {
            return;
        }

        var rezervacija = new Rezervacija();

        rezervacija.setUporabnik(rezervacijaDTO.getUporabnik());
        rezervacija.setPostaja(rezervacijaDTO.getPostaja());

        rezervacija.setZacetekRezervacije(rezervacijaDTO.getZacetekRezervacije());
        rezervacija.setKonecRezervacije(rezervacijaDTO.getKonecRezervacije());

        rezervacijeZrno.dodajRezervacijo(rezervacija);
    }

    public double minCenaPolnjenja(PostajeDTO dto) {
        double minCena = 999999;
        return 0;

    }

}
