/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectto.api;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import connectto.facades.ServicesFacade;
import connectto.entities.Services;
import connectto.entities.Actions;
import connectto.entities.Signals;
import connectto.entities.Conections;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.DataOutputStream;
import java.util.List;
import java.util.Collection;
import java.util.Map;
import javax.persistence.Cache;
import javax.persistence.EntityGraph;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnitUtil;
import javax.persistence.Query;
import javax.persistence.SynchronizationType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.metamodel.Metamodel;

/**
 *
 * @author paul.jeanbour
 */
@WebServlet(name = "receive", urlPatterns = {"/receive"})
public class emiter extends HttpServlet {
    
    @PersistenceContext(unitName = "ConnectToPU")
    EntityManagerFactory emFactory;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String credential = request.getParameter("credential");
            String signal_name = request.getParameter("signal");
            EntityManager em = emFactory.createEntityManager();
            List<Services> services = em
                    .createNamedQuery("Services.findByCredential")
                    .setParameter(":credential", credential)
                    .getResultList();
            
            if(services.size()<0){
                Collection<Signals> signals = services.get(0).getSignalsCollection();
                for(Signals signal : signals){
                    if(signal.getName().equals(signal_name)){
                        for(Conections connection : signal.getConectionsCollection()){
                            System.out.println(connection.getIdSignals().getFullName()+" -> "+connection.getIdActions().getFullName());
                            String url_to_call = connection.getIdActions().getUrl();
                            URL url = new URL(url_to_call);
                            HttpURLConnection http = (HttpURLConnection) url.openConnection();
                                DataOutputStream wr = new DataOutputStream (http.getOutputStream());
                                wr.close();
                        }
                    }
                }
            }
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
