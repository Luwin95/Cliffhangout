<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="container">
    <h1>Créer votre Topo</h1>
    <form method="post" class="form-horizontal" data-toggle="validator" enctype="multipart/form-data" id="newSubscriber">
        <div class="form-group row">
            <label for="topoName" class="col-xs-offset-3 col-xs-2">Titre (nom) du topos </label>
            <div class="col-xs-4">
                <input type="text" name="topoBean.name" id="topoName" class="col-sm-4 form-control" data-minlength="2" data-error="Le nom doit faire au minimum deux caractères" required/>
                <div class="help-block with-errors"></div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-offset-3 col-xs-6">
                <s:fielderror fieldName="topoBean.name" cssClass="errorMessage"/>
            </div>
        </div>
        <div class="form-group row">
            <label for="topoDescription" class="col-xs-offset-3 col-xs-2">Description</label>
            <div class="col-xs-4">
                <input type="text" name="topoBean.description" id="topoDescription" class="col-sm-4 form-control" data-error="La decription ne peut être vide" required/>
                <div class="help-block with-errors"></div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-offset-3 col-xs-6">
                <s:fielderror fieldName="topoBean.description" cssClass="errorMessage"/>
            </div>
        </div>
        <div class="form-group row">
            <label for="topoFile" class="col-xs-offset-3 col-xs-2">Fichier Topo(pdf)</label>
            <div class="col-xs-4">
                <input type="file" name="upload" id="topoFile" class="col-sm-4 form-control" value="Ajouter un fichier"
                       accept="application/pdf"
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