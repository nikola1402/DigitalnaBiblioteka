<%-- 
    Document   : Pretraga
    Created on : Sep 30, 2016, 7:59:15 PM
    Author     : nikol
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>Pretraga</title>
    </head>
    <body>
        
        <h1 style="text-align:center">Izaberite opciju pretrage:</h1>
        
        <div id="izborNaslovna">
        <form action="PretragaNaziv.jsp">
            <h3><input type="submit" value="Po nazivu" name="akcija" class="dugmePretraga"></h3>
        </form>
        
        <form action="PretragaAutor.jsp">
            <h3><input type="submit" value="Po autoru" name="akcija" class="dugmePretraga"></h3>
        </form>
        </div>
        
    </body>
</html>
