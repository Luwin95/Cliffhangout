<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<div class="container login-form">
    <div class="row">
        <h2>Se connecter à Cliffhangout</h2>
        <form method="post" class="col-xs-offset-3 col-xs-6">
            <div class="row">
                <label for="login" class="col-sm-4">Login</label>
                <input type="text" name="login" id="login" class="col-sm-7"/>
                <c:if test="${ !empty form.errors}">
                    <span class="error">${form.errors.login}</span>
                </c:if>
            </div>
            <div class="row">
                <label for="mdp" class="col-sm-4">Mot de passe</label>
                <input type="password" name="mdp" id="mdp" class="col-sm-7"/>
                <c:if test="${ !empty form.errors}">
                    <span class="error">${form.errors.password}</span>
                </c:if>
            </div>
            <div class="row">
                <input type="submit" class="btn-cliffhangout col-xs-offset-4 col-xs-4" value="Valider" />
            </div>
        </form>
    </div>
    <div class="row">
        <p>Nouveau sur Cliffhangout ? <a href="#">Créer un compte</a></p>
    </div>
</div>

