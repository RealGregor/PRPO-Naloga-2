package storitve.interceptorji;

import Zrno.BelezenjeKlicevZrno;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.UUID;
import java.util.logging.Logger;

@Interceptor
@BeleziKlice
public class BelezenjeKlicevInterceptor {

    @Inject
    private BelezenjeKlicevZrno belezenjeKlicevZrno;

    private Logger logger = Logger.getLogger(BelezenjeKlicevInterceptor.class.getName());

    @AroundInvoke
    public Object BelezenjeKlicev(InvocationContext context) throws Exception {
        belezenjeKlicevZrno.povecaj();
        logger.info("AAAAAAAAAAAAAAAAAAAAAAAAAAA" + context.toString() +context.getMethod());
        return context.proceed();
    }
}
