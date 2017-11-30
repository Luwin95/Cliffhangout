<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="container">
    <h1>Ajouter un site</h1>
    <h2>Site</h2>
    <form method="post" action="<s:url action="addSiteWays"/>" class="form-horizontal">
        <div id="sectors">
            <s:if test="sectors != null && sectors.size>0">
                <s:iterator value="sectors" begin="0" status="status">
                    <h3>Secteur n°<s:property value="%{#status.index+1}"/> : <s:property value="name"/></h3>
                    <div id="sector[<s:property value="%{#status.index}"/>]Ways">
                        <s:set name="sectorWays" value="sectors[%{#status.index}].ways"/>
                        <s:if test="ways!= null && ways.size>0">
                            <s:iterator value="ways" begin="0" status="statusWay">
                                <div id="sector[<s:property value="%{#status.index}"/>]Way[<s:property value="%{#statusWay.index}"/>]" >
                                    <h3>Voie n°<s:property value="%{#statusWay.index+1}"/></h3>
                                    <div class="form-group row">
                                        <label for="sector[<s:property value="%{#status.index}"/>]WayName[<s:property value="%{#statusWay.index}"/>]" class="col-xs-offset-3 col-xs-2">Nom : </label>
                                        <div class="col-xs-4">
                                            <input type="text" name="sectors[<s:property value="%{#status.index}"/>].ways[<s:property value="%{#statusWay.index}"/>].name" class="form-control" id="sector[<s:property value="%{#status.index}"/>]WayName[<s:property value="%{#statusWay.index}"/>]"/>
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="sector[<s:property value="%{#status.index}"/>]WayHeight[<s:property value="%{#statusWay.index}"/>]" class="col-xs-offset-3 col-xs-2">Hauteur (en m) : </label>
                                        <div class="col-xs-4">
                                            <input type="number" name="sectors[<s:property value="%{#status.index}"/>].ways[<s:property value="%{#statusWay.index}"/>].heightString" class="form-control" id="sector[<s:property value="%{#status.index}"/>]WayHeight[<s:property value="%{#statusWay.index}"/>]"/>
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="sector[<s:property value="%{#status.index}"/>]WayQuotation[<s:property value="%{#statusWay.index}"/>]" class="col-xs-offset-3 col-xs-2">Hauteur (en m) : </label>
                                        <div class="col-xs-4">
                                            <select name="sectors[<s:property value="%{#status.index}"/>].ways[<s:property value="%{#statusWay.index}"/>].quotation.name" id="sector[<s:property value="%{#status.index}"/>]WayQuotation[<s:property value="%{#statusWay.index}"/>]" class="form-control">
                                                <s:iterator status="cotation" begin="3" end="9" step="1">
                                                    <option value="<s:property value="%{#cotation.index+1}"/>"><s:property value="%{#cotation.index +3}"/>a</option>
                                                    <option value="<s:property value="%{#cotation.index+2}"/>"><s:property value="%{#cotation.index +3}"/>b</option>
                                                    <option value="<s:property value="%{#cotation.index+3}"/>"><s:property value="%{#cotation.index +3}"/>c</option>
                                                </s:iterator>
                                            </select>
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <button id="deleteSector[<s:property value="%{#status.index}"/>]Way[<s:property value="%{#statusWay.index}"/>]" class="btn btn-danger deleteWay">Supprimer Voie</button>
                                    </div>
                                </div>
                            </s:iterator>
                        </s:if>
                    </div>
                </s:iterator>
            </s:if>
        </div>
        <div class="row">
            <input type="submit" class="btn btn-warning submitSite col-xs-offset-4 col-xs-4" value="Ajouter Site">
        </div>
    </form>
    <div id="wayTemplate"  style="display:none;">
        <h3>Voie n°__REALIDX__</h3>
        <div class="form-group row">
            <label for="sector[__SECTORIDX__]WayName[__IDX__]" class="col-xs-offset-3 col-xs-2">Nom : </label>
            <div class="col-xs-4">
                <input type="text" name="sectors[__SECTORIDX__].ways[__IDX__].name" class="form-control" id="sector[__SECTORIDX__]WayName[__IDX__]"/>
                <div class="help-block with-errors"></div>
            </div>
        </div>
        <div class="form-group row">
            <label for="sector[__SECTORIDX__]WayHeight[__IDX__]" class="col-xs-offset-3 col-xs-2">Hauteur (en m) : </label>
            <div class="col-xs-4">
                <input type="number" name="sectors[__SECTORIDX__].ways[__IDX__].heightString" class="form-control" id="sector[__SECTORIDX__]WayHeight[__IDX__]"/>
                <div class="help-block with-errors"></div>
            </div>
        </div>
        <div class="form-group row">
            <label for="sector[__SECTORIDX__]WayQuotation[__IDX__]" class="col-xs-offset-3 col-xs-2">Hauteur (en m) : </label>
            <div class="col-xs-4">
                <select name="sectors[__SECTORIDX__].ways[__IDX__].quotation.name" id="sector[__SECTORIDX__]WayQuotation[__IDX__]">
                    <s:iterator status="cotation" begin="3" end="9" step="1">
                        <option value="<s:property value="%{#cotation.index+1}"/>"><s:property value="%{#cotation.index +3}"/>a</option>
                        <option value="<s:property value="%{#cotation.index+2}"/>"><s:property value="%{#cotation.index +3}"/>b</option>
                        <option value="<s:property value="%{#cotation.index+3}"/>"><s:property value="%{#cotation.index +3}"/>c</option>
                    </s:iterator>
                </select>
                <div class="help-block with-errors"></div>
            </div>
        </div>
        <div class="row">
            <button id="deleteSector[__SECTORIDX__]Way[__IDX__]" class="btn btn-danger">Supprimer Voie</button>
        </div>
    </div>
</div>