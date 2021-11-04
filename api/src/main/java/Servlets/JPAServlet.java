package Servlets;

import Entitete.Uporabnik;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Uporabnik> uporabniki = uporabnikiZrno.getUporabnikiCriteriaAPI();

        // izpis uporabnikov na spletno stran
        for (Uporabnik x : uporabniki) {
            resp.getWriter().println(x.getIme()+" "+x.getPriimek() + " "+ x.getUporabniskoIme() + " " + x.getEmail());
        }
    }
}