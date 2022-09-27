<%@page contentType="text/html" pageEncoding= "UTF-8"  %>
<%@page import ="java.util.List"  %>

    <%  //etiquetas JSP para colocar codigo Java
        List <String> errores = (List<String>)request.getAttribute("errores");

         //siempre hay que hacer un cash de los atibutos que obtenemos

    %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="style.css">
    <title>Formulario Login</title>
</head>
<body>

<%
if(errores!= null && errores.size()>0){
%>
<ul>
<%
for(String error:errores){
%>

<li>
<%out.print(error);  %>
</li>

<%}%>
</ul>
<%}%>


<div class="box">
    <h2>Iniciar Sesión</h2>
    <form action="/webapp-cookie/login" method="post">
        <fieldset>
            <br>
            <div class="inputBox">
                <input type="text" name="username" id="username" class="inputUser">
                <label for="username" class="labelInput">Username</label>
            </div>
            <br><br>
            <div class="inputBox">
                <input type="password" name="password" id="password" class="inputUser">
                <label for="password" class="labelInput">Password</label>
            </div>
            <br><br>
            <div>
                <input type="submit" name="submit" id="submit" value="login">
            </div>
        </fieldset>
    </form>
</div>
</body>
</html>