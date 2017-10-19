<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Accueil</title>
    </head>
    <body>
        <h2>Hello World!</h2>
        <p>Ceci est une jsp</p>
        <p>login : ${ user.login } </p>
        <p>password : ${ user.password }</p>
        <p>salt : ${ user.salt }</p>
        <p>email : ${ user.email }</p>
        <p>role : ${ user.role }</p>

        <form method="POST">
            <input type="submit" value="modifier utilisateur"/>
        </form>
    </body>
</html>
