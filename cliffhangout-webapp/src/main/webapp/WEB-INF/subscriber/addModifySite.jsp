<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="container">
    <h1>Ajouter un site</h1>
    <h2>Site</h2>
    <form method="post" action="<s:url action="addSite"/>" class="form-horizontal">
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
        <div id="sectors">
            <s:if test="siteBean.sectors != null && siteBean.sectors.size>1">
                <s:iterator value="siteBean.sectors" begin="1" status="status">
                    <div id="sector[<s:property value="%{#status.index}"/>]" class="sectorItem">
                        <h3>Secteur n°<s:property value="%{#status.index+1}"/></h3>
                        <div class="form-group row">
                            <label for="sectorName[<s:property value="%{#status.index}"/>]" class="col-xs-offset-3 col-xs-2">Nom : </label>
                            <div class="col-xs-4"><input type="text" name="siteBean.sectors[<s:property value="%{#status.index}"/>].name" class="form-control" id="sectorName[<s:property value="%{#status.index}"/>]"/></div>
                        </div>
                        <div class="form-group row">
                            <label for="sectorDescription[<s:property value="%{#status.index}"/>]" class="col-xs-offset-3 col-xs-2">Description : </label>
                            <div class="col-xs-4"><input type="text" name="siteBean.sectors[<s:property value="%{#status.index}"/>].description" class="form-control" id="sectorDescription[<s:property value="%{#status.index}"/>]"/></div>
                        </div>
                        <div class="row">
                            <button id="deleteSector[<s:property value="%{#status.index}"/>]" class="btn btn-danger">Supprimer Secteur</button>
                        </div>
                    </div>
                </s:iterator>
            </s:if>
        </div>
        <div class="row">
            <button class="btn btn-info col-xs-offset-4 col-xs-4" id="addSector">Ajouter un secteur</button>
        </div>
        <div class="row">
            <input type="submit" class="btn btn-warning submitSite col-xs-offset-4 col-xs-4" value="Ajouter Site">
        </div>
    </form>
    <div id="sectorTemplate" style="display:none;">
        <div id="sector[__IDX__]" class="sectorItem">
            <h3>Secteur n°__REALIDX__</h3>
            <div class="form-group row">
                <label for="sectorName[__IDX__]" class="col-xs-offset-3 col-xs-2">Nom : </label>
                <div class="col-xs-4"><input type="text" name="siteBean.sectors[__IDX__].name" class="form-control" id="sectorName[__IDX__]"/></div>
            </div>
            <div class="form-group row">
                <label for="sectorDescription[__IDX__]" class="col-xs-offset-3 col-xs-2">Description : </label>
                <div class="col-xs-4"><input type="text" name="siteBean.sectors[__IDX__].description" class="form-control" id="sectorDescription[__IDX__]"/></div>
            </div>
            <div class="row">
                <button id="deleteSector[__IDX__]" class="btn btn-danger">Supprimer Secteur</button>
            </div>
        </div>
    </div>
</div>