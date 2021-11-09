package Zrno;

import DTO.*;
import Entitete.Lastnistvo;
import Entitete.Najem;
import Entitete.Postaja;
import Entitete.Rezervacija;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
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

    public void dodajPolnilnico(DodajPostajoDTO dodajPostajaDTO){
        if (dodajPostajaDTO ==null || dodajPostajaDTO.getUporabnik() == null) {
            logger.info("Uporabnik ne obstaja. Postaje ni možno dodati");
        }else if(dodajPostajaDTO.getPostaja() ==null ){
            logger.info("Nova postaja ni ustrezna");
        }else if(!ustreznoDodajPostajo(dodajPostajaDTO)){
            logger.info("Podatki o lastniku in postaji ne ustrezajo");
        }else{
            var postaja = new Postaja();
            postaja.setCena(dodajPostajaDTO.getPostaja().getCena());
            postaja.setId(dodajPostajaDTO.getPostaja().getId());
            postaja.setIme(dodajPostajaDTO.getPostaja().getIme());
            postaja.setLokacija(dodajPostajaDTO.getPostaja().getLokacija());
            postaja.setObratovalniCasKonec(dodajPostajaDTO.getPostaja().getObratovalniCasKonec());
            postaja.setObratovalniCasZacetek(dodajPostajaDTO.getPostaja().getObratovalniCasZacetek());
            postaja.setSpecifikacije(dodajPostajaDTO.getPostaja().getSpecifikacije());
            postajeZrno.dodajPostajo(postaja);
            //postajeZrno.dodajPostajo(dodajPostajaDTO.getPostaja());
            var lastnistvo = new Lastnistvo();
            lastnistvo.setPostaja(postaja);
            lastnistvo.setUporabnik(dodajPostajaDTO.getUporabnik());
            lastnistvaZrno.dodajLastnistvo(lastnistvo);
        }

    }
    public boolean ustreznoDodajPostajo(DodajPostajoDTO dodajPostajoDTO){
        if(postajeZrno.pridobiPostajo(dodajPostajoDTO.getPostaja().getId()) == null){
            if(uporabnikiZrno.odstraniUporabnika(dodajPostajoDTO.getUporabnik().getId())){
                return true;
            }
        }
        return false;
    }
}