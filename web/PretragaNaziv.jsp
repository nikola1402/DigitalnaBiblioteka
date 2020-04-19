<%-- 
    Document   : PretragaObjekta
    Created on : Sep 25, 2016, 8:44:21 PM
    Author     : nikol
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>Pretraga objekta</title>
    </head>
    <body>
        <div id="pretraga">
            <div class="pretraga">
        <form action="PretragaServlet" method="post">
            Unesite naziv dela: <input type="text" name="nazivDela1"><br><br>
            <input type="submit" name="akcija" value="Pretrazi po nazivu"><br>
        </form>
            </div>
        </div>
    </body>
</html>
