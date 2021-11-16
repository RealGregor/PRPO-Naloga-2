package Zrno;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import java.util.logging.Logger;

@ApplicationScoped
public class BelezenjeKlicevZrno {
    private Logger logger = Logger.getLogger(BelezenjeKlicevZrno.class.getName());

    private int counter = 0;

    @PostConstruct
    private void init() {
        logger.info("Incializacija zrna " + BelezenjeKlicevZrno.class.getSimpleName());

    }

    @PreDestroy
    private void destroy(){
        logger.info("Deinicializacija zrna " + BelezenjeKlicevZrno.class.getSimpleName());
    }

    public void povecaj() {
        counter++;
        logger.info("MethodCallCounter: " + counter);
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
