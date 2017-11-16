<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="container">
    <h1>Ajouter un site</h1>
    <h2>Site</h2>
    <s:form cssClass="form-horizontal">
        <div class="form-group row">
            <label for="siteName" class="col-xs-offset-3 col-xs-2">Nom : </label>
            <div class="col-xs-4"><input type="text" name="siteBean.name" class="form-control" id="siteName"/></div>
        </div>
        <div class="form-group row">
            <label for="siteDescription" class="col-xs-offset-3 col-xs-2">Description : </label>
            <div class="col-xs-4"><textarea id="siteDescription" name="siteBean.description" class="form-control"></textarea></div>
        </div>
        <div class="form-group row">
            <label for="siteLocation" class="col-xs-offset-3 col-xs-2">Commune : </label>
            <div class="col-xs-4"><input type="text" name="siteBean.location" class="form-control" id="siteLocation"/></div>
        </div>
        <div class="form-group row">
            <label for="sitePostcode" class="col-xs-offset-3 col-xs-2">Code postal : </label>
            <div class="col-xs-4"><input name ="siteBean.postcode" type="number" class="form-control" id="sitePostcode"></div>
        </div>
        <div class="row">
            <button class="btn btn-info col-xs-offset-4 col-xs-4" id="addSector">Ajouter un secteur</button>
        </div>
        <div class="row">
            <input type="submit" class="btn btn-warning col-xs-offset-4 col-xs-4" value="Ajouter Site">
        </div>
        <div class="sectors">
        </div>
    </s:form>
    <div id="sectorTemplate" style="display:none;">
        <div class="sector">
            <div class="form-group row">
                <label for="sectorName[__IDX__]" class="col-xs-offset-3 col-xs-2">Nom : </label>
                <div class="col-xs-4"><input type="text" name="sectorsBeans[__IDX__].name" class="form-control" id="sectorName[__IDX__]"/></div>
            </div>
            <div class="form-group row">
                <label for="sectorDescription[__IDX__]" class="col-xs-offset-3 col-xs-2">Description : </label>
                <div class="col-xs-4"><input type="text" name="sectorsBeans[__IDX__].description" class="form-control" id="sectorDescription[__IDX__]"/></div>
            </div>

        </div>
    </div>
</div>