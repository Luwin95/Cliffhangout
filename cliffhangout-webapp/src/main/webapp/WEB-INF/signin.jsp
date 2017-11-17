<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<div class="container">
    <h1>S'inscrire à Cliffhangout</h1>
    <form method="post" class="form-horizontal" data-toggle="validator">
        <div class="form-group row">
            <label for="login" class="col-xs-offset-3 col-xs-2">Login</label>
            <div class="col-xs-4">
                <input type="text" name="userBean.login" id="login" class="col-sm-4 form-control" data-minlength="2" data-error="Le login doit faire deux caractères au minimum" required/>
                <div class="help-block with-errors"></div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-offset-3 col-xs-6">
                <s:fielderror fieldName="userBean.login" cssClass="errorMessage"/>
            </div>
        </div>
        <div class="form-group row">
            <label for="mdp" class="col-xs-offset-3 col-xs-2">Mot de passe</label>
            <div class="col-xs-4">
                <input type="password" name="userBean.password" id="mdp" class="form-control" data-minlength="6" data-error="Le mot de passe doit faire au minimum 6 caractères" required/>
                <div class="help-block with-errors"></div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-offset-3 col-xs-6">
                <s:fielderror fieldName="userBean.password" cssClass="errorMessage"/>
            </div>
        </div>
        <div class="form-group row">
            <label for="mdpConfirmation" class="col-xs-offset-3 col-xs-2">Répéter mot de passe</label>
            <div class="col-xs-4">
                <input type="password" name="passwordConfirmation" id="mdpConfirmation" class="col-sm-4 form-control" data-match="#mdp" data-error="Les deux mots de passes sont différents" required/>
                <div class="help-block with-errors"></div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-offset-3 col-xs-6">
                <s:fielderror fieldName="passwordConfirmation" cssClass="errorMessage"/>
            </div>
        </div>
        <div class="form-group row">
            <label for="email" class="col-xs-offset-3 col-xs-2">Email</label>
            <div class="col-xs-4">
                <input type="email" name="userBean.email" id="email" class="col-sm-4 form-control" data-error="Cette adresse email est invalide" required/>
                <div class="help-block with-errors"></div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-offset-3 col-xs-6">
                <s:fielderror fieldName="userBean.email" cssClass="errorMessage"/>
            </div>
        </div>
        <div class="row">
            <s:submit cssClass="btn btn-info col-xs-offset-5 col-xs-2 submitForm"  value="Valider"/>
        </div>
    </form>
</div>