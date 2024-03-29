package com.mycompany.webkauppa;

import com.mycompany.webkauppa.model.tietokantayhteydet.TuoteDAOInMemory;
import com.mycompany.webkauppa.ohjaus.Komentotehdas;
import com.mycompany.webkauppa.sovelluslogiikka.Ostoskori;
import com.mycompany.webkauppa.sovelluslogiikka.Varasto;
import com.mycompany.webkauppa.ulkoiset_rajapinnat.PankkiFasaadi;
import com.mycompany.webkauppa.ulkoiset_rajapinnat.ToimitusjarjestelmaFasaadi;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public abstract class WebKauppaServlet extends HttpServlet {

    protected HttpSession sessio;
    protected Varasto varasto;
    protected Komentotehdas komennot;
    protected PankkiFasaadi pankki;
    protected ToimitusjarjestelmaFasaadi toimitusjarjestelma;

    public WebKauppaServlet() {
        varasto = new Varasto(new TuoteDAOInMemory());
        komennot = new Komentotehdas();
        pankki = new PankkiFasaadi();
        toimitusjarjestelma = new ToimitusjarjestelmaFasaadi();
    }

    public void naytaSivu(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    public Ostoskori haeSessionOstoskori(HttpServletRequest request) {
        haeSessio(request);
        if (sessio.getAttribute("ostoskori") == null) {
            sessio.setAttribute("ostoskori", new Ostoskori());
        }
        Ostoskori ostoskori = (Ostoskori) sessio.getAttribute("ostoskori");
        return ostoskori;
    }

    public void haeSessio(HttpServletRequest request) {
        sessio = request.getSession();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
