<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<div class="container login-form">
    <div class="row">
        <h1>Se connecter à Cliffhangout</h1>
        <s:if test="%{message!=null}">
            <div class="row">
                <div class="col-sm-offset-3 col-sm-6 col-xs-offset-1 col-xs-10 alert alert-danger">
                    <s:property value="message"/>
                </div>
            </div>
        </s:if>
        <form method="post" class="col-sm-offset-3 col-sm-6 col-xs-offset-1 col-xs-10">
            <div class="row">
                <label for="login" class="col-sm-4 col-xs-5">Login</label>
                <input type="text" name="username" id="login" class="col-sm-4 col-xs-6" required/>
                <s:fielderror fieldName="username" cssClass="col-xs-12 errorMessage"/>
            </div>
            <div class="row">
                <label for="mdp" class="col-sm-4 col-xs-5">Mot de passe</label>
                <input type="password" name="password" id="mdp" class="col-sm-4 col-xs-6" required/>
                <s:fielderror fieldName="password" cssClass="col-xs-12 errorMessage"/>
            </div>
            <div class="row">
                <s:submit cssClass="btn-cliffhangout col-xs-offset-4 col-xs-4"  value="Valider"/>
            </div>
        </form>
    </div>
    <div class="row">
        <p>Nouveau sur Cliffhangout ? <a href="<s:url action="signin" namespace="/"/>">Créer un compte</a></p>
    </div>
</div>

