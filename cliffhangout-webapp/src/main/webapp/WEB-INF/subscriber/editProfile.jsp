<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<div class="container">
    <h1>Modifier mon compte</h1>
    <form method="post" class="form-horizontal" enctype="multipart/form-data" id="newSubscriber">
        <div class="form-group row">
            <label for="login" class="col-xs-offset-3 col-xs-2">Login</label>
            <div class="col-xs-4">
                <input type="text" name="userBean.login" id="login" class="col-sm-4 form-control" value="<s:property value="userSession.login"/>"/>
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
                <input type="password" name="userBean.password" id="mdp" class="form-control"/>
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
                <input type="password" name="passwordConfirmation" id="mdpConfirmation" class="col-sm-4 form-control"/>
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
                <input type="email" name="userBean.email" id="email" class="col-sm-4 form-control"  value="<s:property value="userSession.email"/>"/>
                <div class="help-block with-errors"></div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-offset-3 col-xs-6">
                <s:fielderror fieldName="userBean.email" cssClass="errorMessage"/>
            </div>
        </div>
        <div class="form-group row">
            <label for="profileImage" class="col-xs-offset-3 col-xs-2">Image de profil</label>
            <div class="col-xs-4">
                <input type="file" name="upload" id="profileImage" class="col-sm-4 form-control" value="Ajouter une image de profile"
                       accept="image/jpeg,image/gif,image/png,image/bmp"/>
                <div class="help-block with-errors"></div>
            </div>
        </div>
        <div class="row">
            <s:submit cssClass="btn btn-info col-xs-offset-5 col-xs-2 submitForm"  value="Valider"/>
        </div>
    </form>
</div>
