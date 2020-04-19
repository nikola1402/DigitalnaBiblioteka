<%-- 
    Document   : PrikazObjekta
    Created on : Sep 25, 2016, 9:02:05 PM
    Author     : nikol
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>Prikaz</title>
    </head>
    <body>

        <%
            ArrayList<String> nazivi = (ArrayList) request.getAttribute("nazivi");
            ArrayList<List> l = (ArrayList) request.getAttribute("liste");
            Object oid = (Object) request.getAttribute("oid");
            //String naziv = (String) request.getAttribute("naziv");
            
            String naziv;
            
            for (int i=0; i<nazivi.size();i++){
                
                naziv = nazivi.get(i);
                List lista = l.get(i);
                
            %>
     
            <form action="PrikazServlet" method="post">
                <div id="prikaz">
                
                <input type="radio" align="center" name="izbor" 
                       value="<% out.println(lista.get(0)+" "+lista.get(1)+" "+lista.get(2)+" "+lista.get(3)+" "+lista.get(4)
                               +" "+lista.get(5)+" "+lista.get(6)+" "+lista.get(7)+" "+oid);%>">
                Naziv dela : <input type="text" name="naziv" value="<%out.println(lista.get(0));%>" size="30">
                <br>
                Ime autora: <input type="text" name="ime" value="<% out.println(lista.get(1));  %>"><br>
                Prezime autora: <input type="text" name="prezime" value="<% out.println(lista.get(2));  %>"><br>
                Jezik: <input type="text" name="jezik" value="<% out.println(lista.get(3));  %>"><br>
                Kolekcija <input type="text" name="kolekcija" value="<% out.println(lista.get(4));  %>"><br>
                Godina <input type="text" name="godina" value="<% out.println(lista.get(5));  %>"><br>
                Stamparija: <input type="text" name="stamparija" value="<% out.println(lista.get(6));  %>"><br>
                Mesto: <input type="text" name="mesto" value="<%  out.println(lista.get(7)); %>"><br>
                <input type="hidden" name="id" value="<% out.println(lista.get(8));%>" ><br>
                
                



                 </div>  
                <%  }  %>
               
                <br>
                <div id="dugmeCentar">
                <input type="submit" value="Prikazi" name="akcija">
                <input type="submit" value="Azuriraj" name="akcija"> 
                </div>
            </form>    
                              
                

        
            
            
            
    </body>
</html>
