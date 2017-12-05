<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="container">
    <h1>Modifier Site</h1>
    <form method="post" class="form-horizontal" data-toggle="validator">
        <div class="form-group row">
            <label for="siteName" class="col-xs-offset-3 col-xs-2">Nom : </label>
            <div class="col-xs-4">
                <input type="text" name="siteBean.name" class="form-control" id="siteName"
                       value="<s:property value="siteToEdit.name"/>"
                       data-minlength="2"
                       data-error="Le nom de site doit faire deux caractères au minimum"/>
                <div class="help-block with-errors"></div>
                <s:fielderror fieldName="siteToEdit.name" cssClass="errorMessage"/>
            </div>
        </div>
        <div class="form-group row">
            <label for="siteDescription" class="col-xs-offset-3 col-xs-2">Description : </label>
            <div class="col-xs-4">
                    <textarea id="siteDescription" name="siteBean.description" class="form-control"
                              data-error="La description du site ne peut être vide"><s:property value="siteToEdit.description"/></textarea>
                <div class="help-block with-errors"></div>
                <s:fielderror fieldName="siteBean.description" cssClass="errorMessage"/>
            </div>
        </div>
        <div class="form-group row">
            <label for="siteLocation" class="col-xs-offset-3 col-xs-2">Commune : </label>
            <div class="col-xs-4">
                <input type="text" name="siteBean.location" class="form-control" id="siteLocation"
                       value="<s:property value="siteToEdit.location"/>"
                       data-minlength="2"
                       data-error="Le nom de la commune du site doit faire deux caractères au minimum"/>
                <div class="help-block with-errors"></div>
                <s:fielderror fieldName="siteBean.location" cssClass="errorMessage"/>
            </div>
        </div>
        <div class="form-group row">
            <label for="sitePostcode" class="col-xs-offset-3 col-xs-2">Code postal : </label>
            <div class="col-xs-4">
                <input name ="siteBean.postcode" type="number" class="form-control" id="sitePostcode"
                       value="<s:property value="siteToEdit.postcode"/>"
                       data-minlength="5"
                       data-error="Un code postale est composé de 5 caractères"/>
                <div class="help-block with-errors"></div>
                <s:fielderror fieldName="siteBean.postcode" cssClass="errorMessage"/>
            </div>
        </div>
        <div class="row">
            <input type="submit" class="btn btn-warning submitSite col-xs-offset-4 col-xs-4" value="Ajouter Site">
        </div>
    </form>
</div>

