<%-- 
    Document   : UnosObjekta
    Created on : Sep 25, 2016, 12:11:45 AM
    Author     : nikol
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>Unos objekta</title>
    </head>
    <body>
        
        <h1 style="text-align:center">Unesite novi objekat:</h1>
   
        <div id="unosObjekta">
            <div class="unosObjekta">
        
        <form method="post" action="UnosServlet" enctype="multipart/form-data">
            
            <input type="file" name="objekat" value="" width="150" /><br><br>
            Ime autora: <input type="text" name="ime"><br><br>
            Prezime autora: <input type="text" name="prezime"><br><br>
            Godina: <input type="text" name="godina"><br><br>
            Jezik: <input type="text" name="jezik"><br><br>
            Kolekcija: <input type="text" name="kolekcija"><br><br>
            Stamparija: <input type="text" name="stamparija"><br><br>
            Mesto izdavanja: <input type="text" name="mesto"><br><br>
            
            <input type="submit" value="Sacuvaj">
           
            
        </form>
            </div>
        </div>
        
    </body>
</html>
