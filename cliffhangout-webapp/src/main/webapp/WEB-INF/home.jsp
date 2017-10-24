<%--
  Created by IntelliJ IDEA.
  User: Ben
  Date: 24/10/2017
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Accueil</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
              rel="stylesheet"
              integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
              crossorigin="anonymous">
        <link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Merriweather" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Anton" rel="stylesheet">
    </head>
    <body>
        <div class="﻿body">
            <div class="menu">
                <ul>
                    <li><a href="#">Accueil</a></li>
                    <li><a href="#">Rechercher un site</a></li>
                    <li><a href="#">Se connecter</a></li>
                </ul>
            </div>
            <div class="welcome">
                <h1>Cliffhangout</h1>
                <p>Bienvenue sur le premier site communautaire dédié à l'escalade</p>
            </div>
            <div class="footer">
                <ul>
                    <li>Développé par Ben's Company </li>
                    <li>-</li>
                    <li>Copyright 2017 ©</li>
                    <li>-</li>
                    <li>Cliffhangout</li>
                </ul>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.2.1.min.js"
                integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
                crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
                integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
                crossorigin="anonymous"></script>
    </body>
</html>
