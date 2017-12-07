<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<div class="container">
    <h1>S'inscrire à Cliffhangout</h1>
    <form method="post" class="form-horizontal" data-toggle="validator" enctype="multipart/form-data" id="newSubscriber">
        <div class="form-group row">
            <label for="login" class="col-sm-offset-3 col-xs-offset-1 col-sm-2 col-xs-3">Login</label>
            <div class="col-sm-4 col-xs-6">
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
            <label for="mdp" class="col-sm-offset-3 col-xs-offset-1 col-sm-2 col-xs-3">Mot de passe</label>
            <div class="col-sm-4 col-xs-6">
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
            <label for="mdpConfirmation" class="col-sm-offset-3 col-xs-offset-1 col-sm-2 col-xs-3">Répéter mot de passe</label>
            <div class="col-sm-4 col-xs-6">
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
            <label for="email" class="col-sm-offset-3 col-xs-offset-1 col-sm-2 col-xs-3">Email</label>
            <div class="col-sm-4 col-xs-6">
                <input type="email" name="userBean.email" id="email" class="col-sm-4 form-control" data-error="Cette adresse email est invalide" required/>
                <div class="help-block with-errors"></div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-offset-3 col-xs-6">
                <s:fielderror fieldName="userBean.email" cssClass="errorMessage"/>
            </div>
        </div>
        <div class="form-group row">
            <label for="profileImage" class="col-sm-offset-3 col-xs-offset-1 col-sm-2 col-xs-3">Image de profil</label>
            <div class="col-sm-4 col-xs-6">
                <input type="file" name="upload" id="profileImage" class="col-sm-4 form-control" value="Ajouter une image de profile"
                       accept="image/jpeg,image/gif,image/png,image/bmp"
                       data-filesize="1000"
                       data-error="Taille du fichier incorrecte"/>
                <div class="help-block with-errors"></div>
            </div>
        </div>
        <div class="row">
            <s:submit cssClass="btn btn-info col-xs-offset-5 col-xs-2 submitForm"  value="Valider"/>
        </div>
    </form>
</div>