package Zrno;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.interceptor.InvocationContext;
import javax.naming.InitialContext;

import java.util.HashMap;
import java.util.logging.Logger;

@ApplicationScoped
public class BelezenjeKlicevZrno {
    private Logger logger = Logger.getLogger(BelezenjeKlicevZrno.class.getName());

    private HashMap<String, Integer> counter = new HashMap<String, Integer>();

    @PostConstruct
    private void init() {
        logger.info("Incializacija zrna " + BelezenjeKlicevZrno.class.getSimpleName());

    }

    @PreDestroy
    private void destroy() {
        logger.info("Deinicializacija zrna " + BelezenjeKlicevZrno.class.getSimpleName());
    }

    public void povecaj(InvocationContext context) {
        var representationName = context.getMethod().getDeclaringClass().getName() + "." + context.getMethod().getName() + "()";

        int currentValue = 1;
        if (counter.containsKey(representationName)) {
            currentValue = counter.get(representationName) + 1;
            counter.put(representationName, currentValue);
        } else {
            counter.put(representationName, 1);
        }
        logger.info(representationName + " call count: " + currentValue);
    }

    public HashMap<String, Integer> getCounter() {
        return counter;
    }

    public void setCounter(HashMap<String, Integer> counter) {
        this.counter = counter;
    }
}
