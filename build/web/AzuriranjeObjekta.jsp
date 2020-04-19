<%-- 
    Document   : AzuriranjeObjekta
    Created on : Oct 1, 2016, 1:07:27 AM
    Author     : nikol
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>Azuriranje</title>
    </head>
    <body>
        <h1 style="text-align:center">Unesite zeljene izmene:</h1>
        
        <%
            ArrayList <String> l = (ArrayList) request.getAttribute("lista");
            ArrayList <String> ls = new ArrayList();
            for (int i=0; i<l.size();i++){
            String s = l.get(i);
            ls.add(s);
        
            }
            %>
            <div id="azuriranje">
                <div class="azuriranje">
            <form action="IzmenaServlet" method="post"> 
                
                Naziv dela: <input type="text" name="nazivDela" value="<% out.println(l.get(0)); %>"><br><br>
                Ime autora: <input type="text" name="ime" value="<% out.println(l.get(1));  %>"><br><br>
                Prezime autora: <input type="text" name="prezime" value="<% out.println(l.get(2));  %>"><br><br>
                Jezik: <input type="text" name="jezik" value="<% out.println(l.get(3));  %>"><br><br>
                Kolekcija: <input type="text" name="kolekcija" value="<% out.println(l.get(4));  %>"><br><br>
                Godina izdanja: <input type="text" name="godina" value="<% out.println(l.get(5));  %>"><br><br>
                Stamparija: <input type="text" name="stamparija" value="<% out.println(l.get(6));  %>"><br><br>
                Mesto: <input type="text" name="mesto" value="<%  out.println(l.get(7)); %>"><br><br>
                <input type="hidden" name="id" value="<% out.println(l.get(8)); %>"><br>
                <input type="submit" value="Azuriraj" name="akcija">
            </form>
                </div>
            </div>
    </body>
    
</html>
