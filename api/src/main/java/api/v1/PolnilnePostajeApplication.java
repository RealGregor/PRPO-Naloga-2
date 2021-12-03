package api.v1;

import api.v1.viri.UporabnikiVir;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import com.kumuluz.ee.cors.annotations.CrossOrigin;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.servers.Server;


import java.util.Set;

@SecurityScheme(securitySchemeName = "openid-connect", type = SecuritySchemeType.OPENIDCONNECT,
        openIdConnectUrl = "http://auth-server-url/.well-known/openid-configuration")
@ApplicationPath("v1")
/*@OpenAPIDefinition(
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
        servers = @Server(url = "http://localhost:8080"))*/
@OpenAPIDefinition(info = @Info(title = "CustomerApi", version = "v2.0.0", contact = @Contact(), license = @License(name="something")), servers = @Server(url = "http://localhost:8080"), security
        = @SecurityRequirement(name = "openid-connect"))
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
