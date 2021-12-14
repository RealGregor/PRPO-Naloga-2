package zrna;
import api.v1.dtos.SteviloRezervacijPostajeDTO;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class PostajeRezervacijeZrno {
    private Logger logger = Logger.getLogger(PostajeRezervacijeZrno.class.getName());

    HashMap<Integer, Integer> rezervacijeDanes;
    LocalDate trenutniDan;

    @PostConstruct
    private void init() {
        logger.info("Incializacija zrna " + PostajeRezervacijeZrno.class.getSimpleName());
        rezervacijeDanes = new HashMap<Integer, Integer>();
        trenutniDan =LocalDate.now();
    }

    public void preveriCeNovDan(){
        if (!(LocalDate.now()).isEqual(trenutniDan)){
            //nov dan
            init();
        }
    }
    @AroundInvoke
    public Object postajeRezervacijeZrno(InvocationContext context) throws Exception {
        preveriCeNovDan();
        return context.proceed();
    }

    @PreDestroy
    private void destroy(){
        logger.info("Deinicializacija zrna " + PostajeRezervacijeZrno.class.getSimpleName());
    }

    public List<SteviloRezervacijPostajeDTO> vrniSteviloRezervacijPostaj(){
        List<SteviloRezervacijPostajeDTO> rezervacijePostaj = new ArrayList<>();
        for (Integer i : rezervacijeDanes.keySet()) {
            rezervacijePostaj.add(new SteviloRezervacijPostajeDTO(i,rezervacijeDanes.get(i)));
        }
        return rezervacijePostaj;
    }

    public int povecajSteviloRezervacijPostaje(int postaja_id){
        if(rezervacijeDanes.get(postaja_id)==null){
            //postaja danes se ni bila rezervirana
            rezervacijeDanes.put(postaja_id,1);
            return 1;
        }
        int steviloRezervacij = rezervacijeDanes.get(postaja_id)+1;
        rezervacijeDanes.put(postaja_id,steviloRezervacij);
        return steviloRezervacij;
    }

    public int vrniSteviloRezervacijPostaje(int postaja_id){
        return (rezervacijeDanes.get(postaja_id)==null ? 0: rezervacijeDanes.get(postaja_id));
    }

}
