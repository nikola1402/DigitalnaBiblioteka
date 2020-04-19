/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import connection.DBBroker;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nikol
 */
public class PrikazServlet extends HttpServlet {

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
            out.println("<title>Servlet PrikazServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PrikazServlet at " + request.getContextPath() + "</h1>");
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
        ArrayList <String> l = new ArrayList();

        if (akcija.equals("Prikazi")){
            
            String lista = request.getParameter("izbor");
            String[] arr = lista.split(" ");    
            l.addAll(Arrays.asList(arr));
            String nazivDela = l.get(0);
            System.out.println(nazivDela);
            List<GridFSDBFile> fajlovi = conn.nadjiObjekatNazivi(nazivDela); 

            for (int i=0; i<fajlovi.size(); i++){
            
                GridFSDBFile fajl = fajlovi.get(i);
        
                FileOutputStream outputStream = new FileOutputStream("C:\\MongoDB\\Server\\3.2\\data\\db\\"+nazivDela);
                fajl.writeTo(outputStream);

                File pdfFile = new File("C:\\MongoDB\\Server\\3.2\\data\\db\\"+nazivDela);
                response.setContentType("application/pdf");
                response.addHeader("Content-Disposition", "inline; filename=" + nazivDela);
                response.setContentLength((int) pdfFile.length());

                FileInputStream fileInputStream = new FileInputStream(pdfFile);
                OutputStream responseOutputStream = response.getOutputStream();
                int bytes;
                while ((bytes = fileInputStream.read()) != -1) {
                    responseOutputStream.write(bytes);
                }
       
                outputStream.flush();
                outputStream.close();
                responseOutputStream.close();
                fileInputStream.close();
                outputStream = null;
                System.gc();
                pdfFile.delete();
            }
        } else if (akcija.equals("Azuriraj")){
            
            String lista = request.getParameter("izbor");
            ArrayList <String> ls = new ArrayList();

            String[] arr = lista.split(" ");    
            l.addAll(Arrays.asList(arr));
        
            for (int i=0; i<l.size();i++){
                String s = l.get(i);
                ls.add(s);
            }

            request.setAttribute("lista", ls);
            RequestDispatcher rd = request.getRequestDispatcher("/AzuriranjeObjekta.jsp");
            rd.forward(request, response);
        }

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
