<%--
  Created by IntelliJ IDEA.
  User: Ben
  Date: 25/10/2017
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<a class="burger-link">
    <div class="burger"></div>
</a>
<div class="menu-user">
    <ul>
        <c:choose>
            <c:when test="${ sessionScope.sessionUser != null }">
                <li>Bonjour <c:out value="${sessionScope.sessionUser.login}"/></li>
                <c:choose>
                    <c:when test="${!empty sessionScope.sessionUser.image}">
                        <li class="profile-img dropdown">
                            <button class="button-profile" id="menu1" data-toggle="dropdown">
                                <img src="${pageContext.request.contextPath}/resources/images/user/<c:out value="${sessionScope.sessionUser.image.path}"/>"/>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                                <li><a href="#">Mon profil</a></li>
                                <li><a href="<s:url action='logout'/>">Se déconnecter</a></li>
                            </ul>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="profile-img dropdown">
                            <button class="button-profile" id="menu2" data-toggle="dropdown">
                                <img src="${pageContext.request.contextPath}/resources/images/user/icone-grimpeur.png"/>
                            </button>
                            <ul class="dropdown-menu">
                                <li><a href="#">Mon profil</a></li>
                                <li><a href="<s:url action='logout'/>">Se déconnecter</a></li>
                            </ul>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>
                <li><a href="#">S'inscrire</a></li>
                <li><a href="<s:url action='login'/>">Se connecter</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
</div>
<div class="menu">
    <ul>
        <li><a href="<s:url action='home'/>">Accueil</a></li>
        <li><a href="<s:url action='search'/>">Rechercher un site</a></li>
        <c:choose>
            <c:when test="${ sessionScope.sessionUser != null }">
                <li><a href="<s:url action='logout'/>">Se déconnecter</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="<s:url action='login'/>">Se connecter</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
</div>

