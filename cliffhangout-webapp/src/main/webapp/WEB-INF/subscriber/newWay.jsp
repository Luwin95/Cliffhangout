<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="container" >
    <h1>Ajouter une voie</h1>
    <form class="form-horizontal" id="saveForm">
        <div class="form-group row">
            <label for="wayName" class="col-xs-offset-3 col-xs-2">Nom : </label>
            <div class="col-xs-4">
                <input type="text" name="wayBean.name" class="form-control" id="wayName" value="<s:if test="wayToEdit!=null"><s:property value="wayToEdit.name"/></s:if><s:else><s:property value="wayBean.name"/></s:else>" required/>
                <div class="help-block with-errors"></div>
                <s:fielderror fieldName="wayBean.name" cssClass="errorMessage"/>
            </div>
        </div>
        <div class="form-group row">
            <label for="wayHeight" class="col-xs-offset-3 col-xs-2">Hauteur (en m) : </label>
            <div class="col-xs-4">
                <input type="number" name="heightString" class="form-control" id="wayHeight" step="0.01" value="<s:if test="wayToEdit!=null"><s:property value="wayToEdit.height"/></s:if><s:else><s:property value="wayBean.heightString"/></s:else>" required/>
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
        <div class="form-group row">
            <label for="wayLengthsNb" class="col-xs-offset-3 col-xs-2">Nombre de longueurs : </label>
            <div class="col-xs-4">
                <input type="number" name="lengthNb" class="form-control" id="wayLengthsNb" value="<s:property value="lengthNb"/>" required/>
                <div class="help-block with-errors"></div>
                <s:fielderror fieldName="lengthNb" cssClass="errorMessage"/>
            </div>
        </div>
        <div id="lengths">
        </div>
        <div class="row">
            <input type="hidden" name="idSector" value="<s:property value="idSector"/>" form="saveForm"/>
            <input type="submit" class="btn btn-warning col-xs-offset-4 col-xs-4" value="Ajouter Voie" form="saveForm">
        </div>
    </form>
</div>
<div id="lengthsTemplate" style="display:none;">
    <div id="length[__IDX__]">
        <h2>Longueur  n°__REALIDX__</h2>
        <div class="form-group row">
            <label for="wayLengthsName[__IDX__]" class="col-xs-offset-3 col-xs-2">Nom : </label>
            <div class="col-xs-4">
                <input type="text" id="wayLengthsName[__IDX__]" name="wayBean.lengths[__IDX__].name"  class="form-control" required/>
                <div class="help-block with-errors"></div>
            </div>
        </div>
        <div class="form-group row">
            <label for="wayLengthsDescription[__IDX__]" class="col-xs-offset-3 col-xs-2">Description : </label>
            <div class="col-xs-4">
                <textarea id="wayLengthsDescription[__IDX__]" name="wayBean.lengths[__IDX__].description"  class="form-control" required></textarea>
                <div class="help-block with-errors"></div>
            </div>
        </div>
        <div class="form-group row">
            <label for="length[__IDX__]pointsNb" class="col-xs-offset-3 col-xs-2">Nombre de points : </label>
            <div class="col-xs-4">
                <input type="number" id="length[__IDX__]pointsNb" name="pointsNb[__IDX__]"  class="form-control" required/>
                <div class="help-block with-errors"></div>
            </div>
        </div>
    </div>
</div>