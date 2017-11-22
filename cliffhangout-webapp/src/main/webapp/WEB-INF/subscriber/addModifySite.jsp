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
            <label for="siteLocation" class="col-xs-offset-3 col-xs-2">Commune : </label>
            <div class="col-xs-4">
                <input type="text" name="siteBean.location" class="form-control" id="siteLocation"
                       value="<s:property value="siteBean.location"/>"
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
                       value="<s:property value="siteBean.postcode"/>"
                       data-minlength="5"
                       data-error="Un code postale est composé de 5 caractères"/>
                <div class="help-block with-errors"></div>
                <s:fielderror fieldName="siteBean.postcode" cssClass="errorMessage"/>
            </div>
        </div>
        <div id="sectors">
            <s:if test="siteBean.sectors != null && siteBean.sectors.size>1">
                <s:iterator value="siteBean.sectors" begin="1" status="status">
                    <div id="sector[<s:property value="%{#status.index}"/>]" class="sectorItem">
                        <h3>Secteur n°<s:property value="%{#status.index+1}"/></h3>
                        <div class="form-group row">
                            <label for="sectorName[<s:property value="%{#status.index}"/>]" class="col-xs-offset-3 col-xs-2">Nom : </label>
                            <div class="col-xs-4">
                                <input type="text" name="siteBean.sectors[<s:property value="%{#status.index}"/>].name" class="form-control" id="sectorName[<s:property value="%{#status.index}"/>]"
                                       value="<s:property value="siteBean.sectors[%{#status.index}].name"/>"
                                       data-minlength="2"
                                       data-error="Le nom du secteur doit faire deux caractères au minimum"/>
                                <div class="help-block with-errors"></div>
                                <s:set name="fieldName" value="siteBean.sectors[%{#status.index}].name"/>
                                <s:fielderror fieldName="%{#fieldName}" cssClass="errorMessage"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="sectorDescription[<s:property value="%{#status.index}"/>]" class="col-xs-offset-3 col-xs-2">Description : </label>
                            <div class="col-xs-4">
                                <input type="text" name="siteBean.sectors[<s:property value="%{#status.index}"/>].description" class="form-control" id="sectorDescription[<s:property value="%{#status.index}"/>]"
                                       value="<s:property value="siteBean.sectors[%{#status.index}].description"/>"
                                       data-error="La description du secteur ne peut être vide"/>
                                <div class="help-block with-errors"></div>
                                <s:set name="fieldName" value="siteBean.sectors[%{#status.index}].description"/>
                                <s:fielderror fieldName="%{#fieldName}" cssClass="errorMessage"/>
                            </div>
                        </div>
                        <div id="sector[<s:property value="%{#status.index}"/>]Ways">
                            <s:set name="sectorWays" value="siteBean.sectors[%{#status.index}].ways"/>
                            <s:if test="sectorWays!= null && %{#sectorWays.size}>1">
                                <s:iterator value="siteBean.sectors[%{#status.index}].ways" begin="1" status="statusWay">
                                    <div id="sector[<s:property value="%{#status.index}"/>]Way[<s:property value="%{#statusWay.index}"/>]" >
                                        <h3>Voie n°<s:property value="%{#statusWay.index}"/></h3>
                                        <div class="form-group row">
                                            <label for="sector[<s:property value="%{#status.index}"/>]WayName[<s:property value="%{#statusWay.index}"/>]" class="col-xs-offset-3 col-xs-2">Nom : </label>
                                            <div class="col-xs-4">
                                                <input type="text" name="siteBean.sectors[<s:property value="%{#status.index}"/>].ways[<s:property value="%{#statusWay.index}"/>].name" class="form-control" id="sector[<s:property value="%{#status.index}"/>]WayName[<s:property value="%{#statusWay.index}"/>]"/>
                                                <div class="help-block with-errors"></div>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label for="sector[<s:property value="%{#status.index}"/>]WayHeight[<s:property value="%{#statusWay.index}"/>]" class="col-xs-offset-3 col-xs-2">Hauteur (en m) : </label>
                                            <div class="col-xs-4">
                                                <input type="number" name="siteBean.sectors[<s:property value="%{#status.index}"/>].ways[<s:property value="%{#statusWay.index}"/>].height" class="form-control" id="sector[<s:property value="%{#status.index}"/>]WayHeight[<s:property value="%{#statusWay.index}"/>]"/>
                                                <div class="help-block with-errors"></div>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label for="sector[<s:property value="%{#status.index}"/>]WayQuotation[<s:property value="%{#statusWay.index}"/>]" class="col-xs-offset-3 col-xs-2">Hauteur (en m) : </label>
                                            <div class="col-xs-4">
                                                <select name="siteBean.sectors[<s:property value="%{#status.index}"/>].ways[<s:property value="%{#statusWay.index}"/>].quotation" id="sector[<s:property value="%{#status.index}"/>]WayQuotation[<s:property value="%{#statusWay.index}"/>]">
                                                    <s:iterator status="cotation" begin="3" end="9" step="1">
                                                        <option value="<s:property value="%{#cotation.index+1}"/>"><s:property value="%{#cotation.index +3}"/>a</option>
                                                        <option value="<s:property value="%{#cotation.index+2}"/>"><s:property value="%{#cotation.index +3}"/>b</option>
                                                        <option value="<s:property value="%{#cotation.index+3}"/>"><s:property value="%{#cotation.index +3}"/>c</option>
                                                    </s:iterator>
                                                </select>
                                                <div class="help-block with-errors"></div>
                                            </div>
                                            <div class="row">
                                                <button id="deleteSector[<s:property value="%{#status.index}"/>]Way[<s:property value="%{#statusWay.index}"/>]" class="btn btn-danger">Supprimer Voie</button>
                                            </div>
                                        </div>
                                    </div>
                                </s:iterator>
                            </s:if>
                        </div>
                        <div class="row">
                            <button class="btn btn-info addWay" id="addWay[<s:property value="%{#status.index}"/>]">Ajouter une voie</button>
                        </div>
                        <div class="row">
                            <button id="deleteSector[<s:property value="%{#status.index}"/>]" class="btn btn-danger deleteSector">Supprimer Secteur</button>
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
                <div class="col-xs-4">
                    <input type="text" name="siteBean.sectors[__IDX__].name" class="form-control" id="sectorName[__IDX__]" data-minlength="2"
                           data-error="Le nom du secteur doit faire deux caractères au minimum"/>
                    <div class="help-block with-errors"></div>
                </div>
            </div>
            <div class="form-group row">
                <label for="sectorDescription[__IDX__]" class="col-xs-offset-3 col-xs-2">Description : </label>
                <div class="col-xs-4">
                    <input type="text" name="siteBean.sectors[__IDX__].description" class="form-control" id="sectorDescription[__IDX__]"
                           data-error="La description du secteur ne peut être vide"/>
                    <div class="help-block with-errors"></div>
                </div>
            </div>
            <div class="row">
                <button id="deleteSector[__IDX__]" class="btn btn-danger">Supprimer Secteur</button>
            </div>
            <div class="row">
                <button class="btn btn-info col-xs-offset-4 col-xs-4 addWay" id="addWay[__IDX__]">Ajouter un secteur</button>
            </div>
        </div>
    </div>
    <div id="wayTemplate"  style="display:none;">
        <div id="sector[__SECTORIDX__]Way[__IDX__]" >
            <h3>Voie n°__REALIDX__</h3>
            <div class="form-group row">
                <label for="sector[__SECTORIDX__]WayName[__IDX__]" class="col-xs-offset-3 col-xs-2">Nom : </label>
                <div class="col-xs-4">
                    <input type="text" name="siteBean.sectors[__SECTORIDX__].ways[__IDX__].name" class="form-control" id="sector[__SECTORIDX__]WayName[__IDX__]"/>
                    <div class="help-block with-errors"></div>
                </div>
            </div>
            <div class="form-group row">
                <label for="sector[__SECTORIDX__]WayHeight[__IDX__]" class="col-xs-offset-3 col-xs-2">Hauteur (en m) : </label>
                <div class="col-xs-4">
                    <input type="number" name="siteBean.sectors[__SECTORIDX__].ways[__IDX__].height" class="form-control" id="sector[__SECTORIDX__]WayHeight[__IDX__]"/>
                    <div class="help-block with-errors"></div>
                </div>
            </div>
            <div class="form-group row">
                <label for="sector[__SECTORIDX__]WayQuotation[__IDX__]" class="col-xs-offset-3 col-xs-2">Hauteur (en m) : </label>
                <div class="col-xs-4">
                    <select name="siteBean.sectors[__SECTORIDX__].ways[__IDX__].quotation" id="sector[__SECTORIDX__]WayQuotation[__IDX__]">
                        <s:iterator status="cotation" begin="3" end="9" step="1">
                            <option value="<s:property value="%{#cotation.index+1}"/>"><s:property value="%{#cotation.index +3}"/>a</option>
                            <option value="<s:property value="%{#cotation.index+2}"/>"><s:property value="%{#cotation.index +3}"/>b</option>
                            <option value="<s:property value="%{#cotation.index+3}"/>"><s:property value="%{#cotation.index +3}"/>c</option>
                        </s:iterator>
                    </select>
                    <div class="help-block with-errors"></div>
                </div>
                <div class="row">
                    <button id="deleteSector[__SECTORIDX__]Way[__IDX__]" class="btn btn-danger">Supprimer Voie</button>
                </div>
            </div>
        </div>
    </div>
</div>