<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="container" >
    <h1>Ajouter une voie</h1>
    <s:url var="actionUrl" action="editWay">
        <s:param name="idSector"><s:property value="idSector"/></s:param>
        <s:param name="idWay"><s:property value="idWay"/></s:param>
    </s:url>
    <form class="form-horizontal" id="saveForm" action="${actionUrl}"></form>
    <form id="addForm" action="${actionUrl}"></form>
    <div class="form-horizontal">
        <div class="form-group row">
            <label for="wayName" class="col-xs-offset-3 col-xs-2">Nom : </label>
            <div class="col-xs-4">
                <input type="text" name="wayBean.name" class="form-control" id="wayName" value="<s:if test="wayToEdit!=null"><s:property value="wayToEdit.name"/></s:if><s:else><s:property value="wayBean.name"/></s:else>" form="saveForm" required/>
                <div class="help-block with-errors"></div>
                <s:fielderror fieldName="wayBean.name" cssClass="errorMessage"/>
            </div>
        </div>
        <div class="form-group row">
            <label for="wayHeight" class="col-xs-offset-3 col-xs-2">Hauteur (en m) : </label>
            <div class="col-xs-4">
                <input type="number" name="heightString" class="form-control" id="wayHeight" step="0.01" value="<s:if test="wayToEdit!=null"><s:property value="wayToEdit.height"/></s:if><s:else><s:property value="wayBean.heightString"/></s:else>" form="saveForm" required/>
                <div class="help-block with-errors"></div>
                <s:fielderror fieldName="heightString" cssClass="errorMessage"/>
            </div>
        </div>
        <div class="form-group row">
            <label for="wayQuotation" class="col-xs-offset-3 col-xs-2">Cotation : </label>
            <div class="col-xs-4">
                <select name="wayBean.quotation.name" id="wayQuotation" class="form-control" form="saveForm" required>
                    <s:if test="wayToEdit!=null"><option value="<s:property value="wayToEdit.quotation.name"/>" selected><s:property value="wayToEdit.quotation.name"/></option></s:if>
                    <s:iterator status="cotation" begin="3" end="9" step="1">
                        <option value="<s:property value="%{#cotation.index+3}"/>a"><s:property value="%{#cotation.index +3}"/>a</option>
                        <option value="<s:property value="%{#cotation.index+3}"/>b"><s:property value="%{#cotation.index +3}"/>b</option>
                        <option value="<s:property value="%{#cotation.index+3}"/>c"><s:property value="%{#cotation.index +3}"/>c</option>
                    </s:iterator>
                </select>
                <div class="help-block with-errors"></div>
                <s:fielderror fieldName="wayBean.quotation.name" cssClass="errorMessage"/>
            </div>
        </div>
        <input type="hidden" name="idSector" value="<s:property value="idSector"/>"/>
        <div id="lengths">
            <s:if test="wayToEdit!=null">
                <s:iterator status="status" value="wayToEdit.lengths">
                    <div id="length[<s:property value="%{#status.index}"/>]" class="lengthItem">
                        <h2>Longueur  n°<s:property value="%{#status.index+1}"/></h2>
                        <div class="form-group row">
                            <label for="wayLengthsName[<s:property value="%{#status.index}"/>]" class="col-xs-offset-3 col-xs-2">Nom : </label>
                            <div class="col-xs-4">
                                <input type="text" id="wayLengthsName[<s:property value="%{#status.index}"/>]" name="wayBean.lengths[<s:property value="%{#status.index}"/>].name"  class="form-control" value="<s:property value="name"/>" form="saveForm" required/>
                                <div class="help-block with-errors"></div>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="wayLengthsDescription[<s:property value="%{#status.index}"/>]" class="col-xs-offset-3 col-xs-2">Description : </label>
                            <div class="col-xs-4">
                                <textarea id="wayLengthsDescription[<s:property value="%{#status.index}"/>]" name="wayBean.lengths[<s:property value="%{#status.index}"/>].description"  class="form-control" form="saveForm" required><s:property value="description"/></textarea>
                                <div class="help-block with-errors"></div>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="length[<s:property value="%{#status.index}"/>]pointsNb" class="col-xs-offset-3 col-xs-2">Nombre de points : </label>
                            <div class="col-xs-4">
                                <input type="number" id="length[<s:property value="%{#status.index}"/>]pointsNb" name="pointsNb[<s:property value="%{#status.index}"/>]"  value="<s:property value="points.size"/>" form="saveForm" class="form-control" required/>
                                <div class="help-block with-errors"></div>
                            </div>
                        </div>
                        <div class="row">
                            <form>
                                <input type="hidden" name="idWay" value="<s:property value="idWay"/>" />
                                <input type="hidden" name="idSector" value="<s:property value="idSector"/>" />
                                <input type="hidden" name="lengthToDelete" value="<s:property value="%{#status.index}"/>" />
                                <input type="submit" id="deleteLength[<s:property value="%{#status.index}"/>]" class="btn btn-danger deleteLength"  value="Supprimer longueur"/>
                            </form>
                        </div>
                    </div>
                </s:iterator>
                <div class="row">
                    <input type="hidden" name="addLength" value="true" form="addForm"/>
                    <input type="hidden" name="idWay" value="<s:property value="idWay"/>" form="addForm"/>
                    <input type="hidden" name="idSector" value="<s:property value="idSector"/>" form="addForm"/>
                    <input type="submit" class="btn btn-info" id="addLength" value="Ajouter une longueur" form="addForm"/>
                </div>
            </s:if>
        </div>
        <div class="row">
            <input type="hidden" name="idWay" value="<s:property value="idWay"/>" form="saveForm"/>
            <input type="hidden" name="idSector" value="<s:property value="idSector"/>" form="saveForm"/>
            <input type="submit" class="btn btn-warning col-xs-offset-4 col-xs-4" value="Ajouter Voie" form="saveForm">
        </div>
    </div>
</div>