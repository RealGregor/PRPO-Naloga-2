package api.v1;

import api.v1.viri.UporabnikiVir;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import com.kumuluz.ee.cors.annotations.CrossOrigin;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

import java.util.Set;

@SecurityScheme(name = "openid-connect", type = SecuritySchemeType.OPENIDCONNECT,
        openIdConnectUrl = "http://auth-server-url/.well-known/openid-configuration")
@OpenAPIDefinition(
        info = @Info(
                title = "API za polnilne postaje",
                version = "v1",
                description = "API, ki omogoča enostavno upravljanje z polnilnimi postajami.",
                contact = @Contact(
                        name = "Žiga Drab, Gregor Bučar",
                        url = "https://github.com/ziga7631",
                        email = "ziga.drab@gmail.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0.html"
                )
        ),
        security = @SecurityRequirement(name = "openid-connect"),
        servers = @Server(url ="http://localhost:8080/v1"))
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
