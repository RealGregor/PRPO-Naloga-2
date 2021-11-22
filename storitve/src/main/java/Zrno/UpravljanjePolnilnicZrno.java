package Zrno;

import DTO.*;
import Entitete.*;
import storitve.interceptorji.BeleziKlice;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Date;
import java.util.UUID;
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
        logger.info("Zrno z id-jem: " + UUID.randomUUID().toString());
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

    @BeleziKlice
    public Najem najemPolnilnice(NajemDTO najemDTO) {
        if (!najemDTO.validate()) {
            return null;
        }
        var najem = new Najem();

        //TODO preveri ce je v tem casu polnilnica ze zasedena- trenutno v najemu ali rezervirana
        Uporabnik uporabnik = uporabnikiZrno.pridobiUporabnika(najemDTO.getUporabnik_id());
        Postaja postaja = postajeZrno.pridobiPostajo(najemDTO.getPostaja_id());

        najem.setUporabnik(uporabnik);
        najem.setPostaja(postaja);
        najem.setCasPolnjenja(0);
        najem.setZacetekPolnjenja(new Date()); //now

        najemiZrno.dodajNajem(najem); //TODO: potem dodaj, da se potekli najemi izbrisejo
        return najem;
    }

    @BeleziKlice
    public Rezervacija rezervacijaPolnilnice(RezervacijaDTO rezervacijaDTO) {
        if (!rezervacijaDTO.validate()) {
            return null;
        }

        //TODO preveri ce je v tem casu polnilnica ze zasedena- trenutno v najemu ali rezervirana

        var rezervacija = new Rezervacija();
        Uporabnik uporabnik = uporabnikiZrno.pridobiUporabnika(rezervacijaDTO.getUporabnik_id());
        Postaja postaja = postajeZrno.pridobiPostajo(rezervacijaDTO.getPostaja_id());

        rezervacija.setUporabnik(uporabnik);
        rezervacija.setPostaja(postaja);
        rezervacija.setZacetekRezervacije(rezervacijaDTO.getZacetekRezervacije());
        rezervacija.setKonecRezervacije(rezervacijaDTO.getKonecRezervacije());

        rezervacijeZrno.dodajRezervacijo(rezervacija);
        return rezervacija;
    }

    @BeleziKlice
    public Lastnistvo dodajLastnistvo(DodajLastnistvoDTO dodajLastnistvoDTO) {

        if (dodajLastnistvoDTO == null || dodajLastnistvoDTO.getUporabnik_id() == null) {
            logger.info("Uporabnik ne obstaja. Postaje ni možno dodati");
        } else if (dodajLastnistvoDTO.getPostaja_id() == null) {
            logger.info("Nova postaja ni ustrezna");
        } else if (!ustreznoDodajLastnistvo(dodajLastnistvoDTO)) {
            logger.info("Postajo ni mogoce dodati");
        } else {
            Postaja postaja = postajeZrno.pridobiPostajo(dodajLastnistvoDTO.getPostaja_id());
            Uporabnik uporabnik = uporabnikiZrno.pridobiUporabnika(dodajLastnistvoDTO.getUporabnik_id());

            if(postaja == null || uporabnik == null)return null;

            var lastnistvo = new Lastnistvo();
            lastnistvo.setPostaja(postaja);
            lastnistvo.setUporabnik(uporabnik);
            lastnistvaZrno.dodajLastnistvo(lastnistvo);
            return(lastnistvo);
        }
        return null;
    }

    @BeleziKlice
    public boolean ustreznoDodajLastnistvo(DodajLastnistvoDTO dodajLastnistvoDTO) {
        //preveri ce ze obstaja lastnistvo za to polnilnico...
        Lastnistvo lastnistvo = lastnistvaZrno.pridobiLastnistvoPostaje(dodajLastnistvoDTO.getPostaja_id());
        System.out.println(dodajLastnistvoDTO.getPostaja_id());
        System.out.println(lastnistvo);
        if(lastnistvo == null)return true;
        return false;
    }
}
