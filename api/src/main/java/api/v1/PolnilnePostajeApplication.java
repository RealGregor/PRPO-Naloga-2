package api.v1;

import api.v1.viri.UporabnikiVir;

import com.kumuluz.ee.cors.annotations.CrossOrigin;

import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import com.kumuluz.ee.cors.annotations.CrossOrigin;
import com.kumuluz.ee.security.annotations.Keycloak;
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
@OpenAPIDefinition(info = @Info(title = "PolnilnePostajeApi", version = "v1.0.0", contact = @Contact(), license = @License(name="")), servers = @Server(url = "http://localhost:8080"), security
        = @SecurityRequirement(name = "openid-connect"))
@CrossOrigin(supportedMethods = "GET, POST, PUT, DELETE, HEAD, OPTIONS")
//@DeclareRoles({"user", "admin"})
public class PolnilnePostajeApplication extends Application {

}
