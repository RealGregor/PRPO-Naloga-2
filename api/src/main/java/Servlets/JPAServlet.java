package Servlets;

import Entitete.Postaja;
import Entitete.Uporabnik;
import Zrno.PostajaZrno;
import Zrno.UporabnikZrno;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/servlet")
public class JPAServlet extends HttpServlet {

    @Inject
    private UporabnikZrno uporabnikiZrno;

    @Inject
    private PostajaZrno postajeZrno;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        List<Uporabnik> uporabniki = uporabnikiZrno.pridobiUporabnike();
        resp.getWriter().println("Podatki o uporabnikih pridobljeni z JPQL:");
        // izpis uporabnikov na spletno stran
        for (Uporabnik x : uporabniki) {
            resp.getWriter().println(x.getIme()+" "+x.getPriimek() + " "+ x.getUporabniskoIme() + " " + x.getEmail());
        }
        Postaja postaja = postajeZrno.pridobiPostajo(1);
        resp.getWriter().println(postaja.getIme()+" "+postaja.getLokacija() + " "+ postaja.getCena() + " " + postaja.getObratovalniCasZacetek()+ " " + postaja.getObratovalniCasKonec());


        /*uporabniki = uporabnikiZrno.getUporabnikiCriteriaAPI();
        resp.getWriter().println("Podatki o uporabnikih pridobljeni z CriteriaAPI:");
        // izpis uporabnikov na spletno stran
        for (Uporabnik x : uporabniki) {
            resp.getWriter().println(x.getIme()+" "+x.getPriimek() + " "+ x.getUporabniskoIme() + " " + x.getEmail());
        }*/
    }
}