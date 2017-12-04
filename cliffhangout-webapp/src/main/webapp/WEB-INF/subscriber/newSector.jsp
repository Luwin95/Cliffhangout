<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="container" >
    <h1>Ajouter un secteur</h1>
    <form class="form-horizontal" data-toggle="validator">
        <div class="form-group row">
            <label for="sectorName" class="col-xs-offset-3 col-xs-2" >Nom : </label>
            <div class="col-xs-4">
                <input type="text" name="sectorBean.name" class="form-control" id="sectorName" data-minlength="2"
                       data-error="Le nom du secteur doit faire deux caractères au minimum" value="<s:if test="sectorToEdit!=null"><s:property value="sectorToEdit.name"/></s:if><s:else><s:property value="sectorBean.name"/></s:else>" required/>
                <div class="help-block with-errors"></div>
                <s:fielderror fieldName="sectorBean.name" cssClass="errorMessage"/>
            </div>
        </div>
        <div class="form-group row">
            <label for="sectorDescription" class="col-xs-offset-3 col-xs-2">Description : </label>
            <div class="col-xs-4">
                <textarea name="sectorBean.description" class="form-control" id="sectorDescription"
                          data-error="La description du secteur ne peut être vide" required>
                    <s:if test="sectorToEdit!=null">
                        <s:property value="sectorToEdit.description"/>
                    </s:if>
                    <s:else>
                        <s:property value="sectorBean.description"/>
                    </s:else>
                </textarea>
                <div class="help-block with-errors"></div>
                <s:fielderror fieldName="sectorBean.description" cssClass="errorMessage"/>
            </div>
        </div>
        <div class="row">
            <input type="submit" class="btn btn-warning col-xs-offset-4 col-xs-4" value="Ajouter Secteur">
        </div>
    </form>
</div>