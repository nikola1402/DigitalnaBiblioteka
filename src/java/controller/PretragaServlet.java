/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.util.JSON;
import connection.DBBroker;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.json.JsonObject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sun.misc.IOUtils;

/**
 *
 * @author nikol
 */
public class PretragaServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet IzmenaServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet IzmenaServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        //processRequest(request, response);
        
        DBBroker conn = new DBBroker();
        String akcija = request.getParameter("akcija");
        String naziv = new String();
        String id = new String();
        Object oid = null ;
        GridFSDBFile fajl = new GridFSDBFile();
        ArrayList<String> nazivi = new ArrayList();
        List<GridFSDBFile> fajlovi = null;
        ArrayList<String> l = new ArrayList();
        ArrayList<List> liste = new ArrayList();
        
        if (akcija.equals("Pretrazi po nazivu")) {
           
            String nazivDela1 = request.getParameter("nazivDela1");               
            System.out.println("naziv dela : "+nazivDela1);
            fajlovi = conn.nadjiObjekatNazivi(nazivDela1);
        
        }
       
        else if (akcija.equals("Pretrazi po autoru")){
            
            String autor = request.getParameter("autor");               
            System.out.println("autor dela : "+autor);
            fajlovi = conn.nadjiObjekatAutor(autor);
           
        }
       
       
        for (int i=0; i<fajlovi.size();i++){
            fajl = fajlovi.get(i);
            naziv = fajl.getFilename();

            oid = fajl.getId();
            id = oid.toString();

            nazivi.add(naziv);

            DBObject o = fajl.getMetaData();
            Object imeo = o.get("imeAutora");
            Object prezimeo = o.get("prezimeAutora");
            Object godinao = o.get("godina");
            Object jeziko = o.get("jezik");
            Object kolekcijao = o.get("kolekcija");
            Object stamparijao = o.get("stamparija");
            Object mestoo = o.get("mesto");
            String imes = imeo.toString();
            String prezimes = prezimeo.toString();
            String godinas = godinao.toString();
            String jeziks = jeziko.toString();
            String kolekcijas = kolekcijao.toString();
            String stamparijas = stamparijao.toString();
            String mestos = mestoo.toString();
            
            ArrayList li = new ArrayList();                      
            li.add(naziv);
            li.add(imes);
            li.add(prezimes);
            li.add(jeziks);
            li.add(kolekcijas);
            li.add(godinas);
            li.add(stamparijas);
            li.add(mestos);
            li.add(id);

            liste.add(li);
        }

        request.setAttribute("oid", oid);
        request.setAttribute("nazivi", nazivi);
        request.setAttribute("liste", liste);
        RequestDispatcher rd = request.getRequestDispatcher("/PrikazObjekta.jsp");
        rd.forward(request, response);
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
