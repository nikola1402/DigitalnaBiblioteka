<%-- 
    Document   : PretragaAutor
    Created on : Sep 30, 2016, 8:00:44 PM
    Author     : nikol
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>Pretraga po autoru</title>
    </head>
    <body>
        
        <div id="pretraga">
            <div class="pretraga">
        <form action="PretragaServlet" method="post">
            Unesite ime i prezime autora: <input type="text" name="autor"><br><br>
            <input type="submit" name="akcija" value="Pretrazi po autoru"><br>
        </form>
            </div>
        </div>
    </body>
</html>
