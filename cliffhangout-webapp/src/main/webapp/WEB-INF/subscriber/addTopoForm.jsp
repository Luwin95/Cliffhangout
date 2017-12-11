<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="container">
    <h1><s:if test="%{idTopo != null}">Modifier votre topo</s:if><s:else>Créer votre Topo</s:else> </h1>
    <form method="post" class="form-horizontal" data-toggle="validator" enctype="multipart/form-data" id="newSubscriber">
        <div class="form-group row">
            <label for="topoName" class="col-xs-offset-3 col-xs-2">Titre (nom) du topos </label>
            <div class="col-xs-4">
                <input type="text" name="topoBean.name" id="topoName" class="col-sm-4 form-control" data-minlength="2" data-error="Le nom doit faire au minimum deux caractères" value="<s:property value="topoToEdit.name"/> " required/>
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
                <input type="text" name="topoBean.description" id="topoDescription" class="col-sm-4 form-control" data-error="La decription ne peut être vide" value="<s:property value="topoToEdit.description"/> " required/>
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
        <div class="form-group row">
            <div class="checkbox">
                <label class="col-xs-offset-3">
                    <s:checkbox id="topoBorrowed" name="topoBean.borrowed" value="false"/>
                    <!--<input type="checkbox" name="topoBean.borrowed" id="topoBorrowed" value="false"/>-->
                    Autoriser le prêt de ce topo
                </label>
            </div>
        </div>
        <div class="row">
            <div class="col-md-2 col-md-offset-5 col-sm-4 col-sm-offset-4 col-xs-6 col-xs-offset-3">
                <s:submit cssClass="btn btn-info submitForm" align="center" value="Valider"/>
            </div>
        </div>
    </form>
    <s:if test='%{sitesChosen != null}'>
        <h3>Sites sélectionnés</h3>
        <ul>
            <form method="post">
                <s:iterator value="sitesChosen">
                    <li><input type="checkbox" name="sitesToRemove[<s:property value="id"/>]" value="<s:property value="id"/>"/> <s:property value="name"/> (département : <s:property value="departement.name"/>)</li>
                </s:iterator>
                <input type="submit" class="btn btn-danger" value="Supprimer les sites sélectionnés"/>
            </form>
        </ul>
    </s:if>
    <s:else>
        <s:url var="searchUrl" action="editTopoSearch">
            <s:param name="idTopo"><s:property value="idTopo"/></s:param>
        </s:url>
        <h3>Sites du topo</h3>
        <ul>
            <s:iterator value="#session.sitesTopo">
                <li><s:property value="name"/> (département : <s:property value="departement.name"/>)</li>
            </s:iterator>
        </ul>
        <div class="row">
            <div class="col-md-2 col-md-offset-5 col-sm-4 col-sm-offset-4 col-xs-6 col-xs-offset-3">
                <a href="${searchUrl}" class="btn btn-info" style="margin-bottom: 20px;">Modifier les sites de votre topo</a>
            </div>
        </div>
    </s:else>
</div>