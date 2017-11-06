<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<div class="container login-form">
    <div class="row">
        <h1>Se connecter à Cliffhangout</h1>
        <form method="post" class="col-xs-offset-3 col-xs-6">
            <div class="row">
                <label for="login" class="col-sm-4">Login</label>
                <!--input type="text" name="login" id="login" class="col-sm-4" value="<c:out value="${user.login}"/>" required/-->
                <s:textfield name="username" id="login" cssClass="col-sm-4" required="true"/>
            </div>
            <div class="row">
                <label for="mdp" class="col-sm-4">Mot de passe</label>
                <!--input type="password" name="mdp" id="mdp" class="col-sm-4" required/-->
                <s:password name="password" id="mdp" cssClass="col-sm-4" required="true"/>
            </div>
            <div class="row">
                <input type="submit" class="btn-cliffhangout col-xs-offset-4 col-xs-4" value="Valider" />
            </div>
        </form>
    </div>
    <div class="row">
        <c:choose>
            <c:when test="${ !empty loginForm.errors}">
                <p class="error">${loginForm.result}</p>
            </c:when>
            <c:otherwise>
                <p class="success">${loginForm.result}</p>
            </c:otherwise>
        </c:choose>
    </div>
    <div class="row">
        <p>Nouveau sur Cliffhangout ? <a href="#">Créer un compte</a></p>
    </div>
</div>

