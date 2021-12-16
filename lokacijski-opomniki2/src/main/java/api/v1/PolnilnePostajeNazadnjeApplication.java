package api.v1;
import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@DeclareRoles({"user", "admin"})
@ApplicationPath("v1")
public class PolnilnePostajeNazadnjeApplication extends Application{
}
