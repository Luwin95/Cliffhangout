<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="container">
    <h1>Ajouter un site</h1>
    <h2>Site</h2>
    <s:set var="cptWay" value="0"/>
    <s:if test="#session.site!= null">
        <p>Nom: <s:property value="siteBean.name"/></p>
        <p>Description: <s:property value="siteBean.description"/></p>
        <p>Commune: <s:property value="siteBean.location"/></p>
        <p>Code Postale: <s:property value="siteBean.postcode"/></p>
        <s:if test="siteBean.sectors!=null && siteBean.sectors.size>0">
            <s:iterator value="siteBean.sectors" begin="0" status="status">
                <h3>Secteur n°<s:property value="%{#status.index+1}"/></h3>
                <p>Nom: <s:property value="name"/> </p>
                <p>Description : <s:property value="description"/></p>
                <form>
                    <input type="hidden" name="editSector" value="true"/>
                    <input type="hidden" name="idSector" value="<s:property value="%{#status.index}"/>"/>
                    <input type="submit" class="btn btn-info" value="Modifier secteur"/>
                </form>
                <s:if test="ways!=null && ways.size >0">
                    <s:set var="cptWay" value="%{#cptWay+1}"/>
                    <s:iterator value="ways"  begin="0" status="statusVoie">
                        <h4>Voie n°<s:property value="%{#statusVoie.index+1}"/></h4>
                        <p>Nom : <s:property value="name"/> </p>
                        <p>Hauteur : <s:property value="height"/> </p>
                        <p>Cotation : <s:property value="quotation.name"/></p>
                        <form>
                            <input type="hidden" name="editWay" value="true"/>
                            <input type="hidden" name="idSector" value="<s:property value="%{#status.index}"/>"/>
                            <input type="hidden" name="idWay" value="<s:property value="%{#statusVoie.index}"/>"/>
                            <input type="submit" class="btn btn-info" value="Modifier voie"/>
                        </form>
                    </s:iterator>
                </s:if>
                <form>
                    <input type="hidden" name="addWay" value="true"/>
                    <input type="hidden" name="idSector" value="<s:property value="%{#status.index}"/>"/>
                    <input type="submit" class="btn btn-warning" value="Ajouter une voie"/>
                </form>
            </s:iterator>
        </s:if>
        <form>
            <input type="hidden" name="addSector" value="true"/>
            <input type="submit" class="btn btn-warning" value="Ajouter un secteur"/>
        </form>
        <s:if test="%{#cptWay == siteBean.sectors.size && #cptWay!=0}">
            <form>
                <input type="hidden" name="addSite" value="true"/>
                <input type="submit" class="btn btn-success" value="Ajouter un site"/>
            </form>
        </s:if>
    </s:if>
    <s:else>
        <form method="post" class="form-horizontal" data-toggle="validator">
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
            <div class="row">
                <input type="submit" class="btn btn-warning submitSite col-xs-offset-4 col-xs-4" value="Ajouter Site">
            </div>
        </form>
    </s:else>
</div>