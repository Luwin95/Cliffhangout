<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="container">
    <h1>Ajouter un site</h1>
    <h2>Site</h2>
    <form method="post" action="<s:url action="addSite"/>" class="form-horizontal">
        <div class="form-group row">
            <label for="siteName" class="col-xs-offset-3 col-xs-2">Nom : </label>
            <div class="col-xs-4">
                <input type="text" name="siteBean.name" class="form-control" id="siteName"
                       value="<s:property value="siteBean.name"/>"
                       data-minlength="2"
                       data-error="Le nom de site doit faire deux caractères au minimum"/>
                <div class="help-block with-errors"></div>
                <s:fielderror fieldName="siteBean.name" cssClass="errorMessage"/>
            </div>
        </div>
        <div class="form-group row">
            <label for="siteDescription" class="col-xs-offset-3 col-xs-2">Description : </label>
            <div class="col-xs-4">
                <textarea id="siteDescription" name="siteBean.description" class="form-control"
                          data-error="La description du site ne peut être vide"><s:property value="siteBean.description"/></textarea>
                <div class="help-block with-errors"></div>
                <s:fielderror fieldName="siteBean.description" cssClass="errorMessage"/>
            </div>
        </div>
        <div class="form-group row">
            <label for="siteSectorNb" class="col-xs-offset-3 col-xs-2">Nombre de secteurs : </label>
            <div class="col-xs-4">
                <input type="number" id="siteSectorNb" name="nbSector"  class="form-control"/>
                <div class="help-block with-errors"></div>
                <s:fielderror fieldName="nbSector" cssClass="errorMessage"/>
            </div>
        </div>
        <div id="sectors">

        </div>
    </form>
</div>
<div id="sectorsTemplate" style="display:none;">
    <div class="form-group row">
        <label for="siteSector[__IDX__]WaysNb" class="col-xs-offset-3 col-xs-2">Nombre de voie du secteur n°__REALIDX__: </label>
        <div class="col-xs-4">
            <input type="number" id="siteSector[__IDX__]WaysNb" name="nbWay[__IDX__]"  class="form-control waysNb"/>
            <div class="help-block with-errors"></div>
            <s:fielderror fieldName="siteBean.description" cssClass="errorMessage"/>
        </div>
    </div>
</div>
<div id="waysTemplate" style="display:none;">
    <div class="form-group row">
        <label for="way[__WAYIDX__]Lengths[__IDX__]Nb" class="col-xs-offset-3 col-xs-2">Nombre de voie du secteur n°__REALIDX__: </label>
        <div class="col-xs-4">
            <input type="number" id="way[__WAYIDX__]Lengths[__IDX__]Nb" name="nbLength[__WAYIDX__][__IDX__]"  class="form-control waysNb"/>
            <div class="help-block with-errors"></div>
            <s:fielderror fieldName="siteBean.description" cssClass="errorMessage"/>
        </div>
    </div>
    <div id="ways[__IDX__]">

    </div>
</div>