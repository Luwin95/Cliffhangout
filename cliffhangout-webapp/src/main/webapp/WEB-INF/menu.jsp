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
        <s:if test="#session.sessionUser!= null">
            <%--<li>Bonjour <c:out value="${sessionScope.sessionUser.login}"/></li>--%>
            <li>Bonjour <s:property value="%{#session.sessionUser.login}"/></li>
            <s:if test="#session.sessionUser.image != null">
                <li class="profile-img dropdown">
                    <button class="button-profile" id="menu1" data-toggle="dropdown">
                        <%--<img src="${pageContext.request.contextPath}/resources/images/user/<c:out value="${sessionScope.sessionUser.image.path}"/>"/>--%>
                        <img src="/resources/images/user/<s:property value="%{#session.sessionUser.image.path}"/>"/>
                    </button>
                    <ul class="dropdown-menu">
                        <li><a href="#">Mon profil</a></li>
                        <li><a href="<s:url action='logout' namespace="/"/>">Se déconnecter</a></li>
                    </ul>
                </li>
            </s:if>
            <s:else>
                <li class="profile-img dropdown">
                    <button class="button-profile" id="menu2" data-toggle="dropdown">
                        <img src="/resources/images/user/icone-grimpeur.png"/>
                    </button>
                    <ul class="dropdown-menu">
                        <li><a href="#">Mon profil</a></li>
                        <li><a href="<s:url action='logout' namespace="/"/>">Se déconnecter</a></li>
                    </ul>
                </li>
            </s:else>
        </s:if>
        <s:else>
            <li><a href="<s:url action='signin' namespace="/"/>">S'inscrire</a></li>
            <li><a href="<s:url action='login' namespace="/"/>">Se connecter</a></li>
        </s:else>
    </ul>
</div>
<div class="menu">
    <ul>
        <li><a href="<s:url action='home' namespace="/"/>">Accueil</a></li>
        <li><a href="<s:url action='search' namespace="/"/>">Rechercher un site</a></li>
        <s:if test="#session.sessionUser!= null ">
            <li><a href="<s:url action='logout' namespace="/"/>">Se déconnecter</a></li>
            <li><a href="<s:url action='home' namespace="/subscriber"/>">Espace abonné</a></li>
        </s:if>
        <s:else>
            <li><a href="<s:url action='login' namespace="/"/>">Se connecter</a></li>
        </s:else>
    </ul>
</div>

