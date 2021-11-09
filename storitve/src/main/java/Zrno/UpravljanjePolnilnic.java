package Zrno;
import DTO.PostajeDTO;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.logging.Logger;

@ApplicationScoped
public class UpravljanjePolnilnic {
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
        logger.info("Incializacija zrna " + UpravljanjePolnilnic.class.getSimpleName());
    }

    @PreDestroy
    private void destroy(){
        logger.info("Deinicializacija zrna " + UpravljanjePolnilnic.class.getSimpleName());
    }

    //poslovne metode
    public double minCenaPolnjenja(PostajeDTO dto){
        double minCena = 999999;


    }


}
