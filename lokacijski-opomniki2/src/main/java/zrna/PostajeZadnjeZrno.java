package zrna;
import api.v1.dtos.Postaja;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class PostajeZadnjeZrno {
    private Logger logger = Logger.getLogger(PostajeZadnjeZrno.class.getName());

    LinkedList<Postaja> postaje;

    @PostConstruct
    private void init() {
        logger.info("Incializacija zrna " + PostajeZadnjeZrno.class.getSimpleName());
        postaje=new LinkedList<>();

    }

    @PreDestroy
    private void destroy(){
        logger.info("Deinicializacija zrna " + PostajeZadnjeZrno.class.getSimpleName());
    }

    public List<Postaja> vrniZadnjeRezerviranePostaje() {
        return postaje;
    }

    public Postaja dodajNovoRezerviranoPostajo(Postaja postaja ) {
        if(postaje.size()>=3){
            postaje.pollLast();
        }
        postaje.addFirst(postaja);
        return postaja;
    }
}
