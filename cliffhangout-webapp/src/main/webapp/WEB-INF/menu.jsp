<%--
  Created by IntelliJ IDEA.
  User: Ben
  Date: 25/10/2017
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<a class="burger-link">
    <div class="burger"></div>
</a>
<div class="menu-user">
    <ul>
        <li><a href="#">S'inscrire</a></li>
        <li><a href="#">Se connecter</a></li>
    </ul>
</div>
<div class="menu">
    <ul>
        <li><a href="${pageContext.request.contextPath}/home">Accueil</a></li>
        <li><a href="${pageContext.request.contextPath}/search">Rechercher un site</a></li>
        <li><a href="#">Se connecter</a></li>
    </ul>
</div>

