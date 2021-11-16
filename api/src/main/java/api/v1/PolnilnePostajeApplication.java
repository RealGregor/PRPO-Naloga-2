package api.v1;

import api.v1.viri.UporabnikiVir;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import com.kumuluz.ee.cors.annotations.CrossOrigin;
import java.util.Set;

@ApplicationPath("v1")
@CrossOrigin(supportedMethods = "GET, POST, PUT, DELETE, HEAD, OPTIONS")
public class PolnilnePostajeApplication extends Application {
    /*@Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new
                java.util.HashSet<Class<?>>();
        resources.add(UporabnikiVir.class);
        return resources;
    }*/
}
