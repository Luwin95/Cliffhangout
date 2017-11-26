<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="container">
    <h1><s:property value="topo.name"/></h1>
    <p><s:property value="topo.description"/></p>
    <h2>Sites du topo</h2>
    <ul>
        <s:iterator value="topo.sites">
            <li><s:property value="name"/> ( Département : <s:property value="departement.name"/> )</li>
        </s:iterator>
    </ul>
    <form method="post" class="form-horizontal" data-toggle="validator" id="newBorrowing">
        <div class="form-group row">
            <label for="startDate" class="col-xs-offset-3 col-xs-2">Date de début d'emprunt</label>
            <div class="col-xs-4">
                <input type="date" name="startDate" id="startDate" class="col-sm-4 form-control" required/>
                <div class="help-block with-errors"></div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-offset-3 col-xs-6">
                <s:fielderror fieldName="startDate" cssClass="errorMessage"/>
            </div>
        </div>
        <div class="form-group row">
            <label for="endDate" class="col-xs-offset-3 col-xs-2">Date de fin d'emprunt</label>
            <div class="col-xs-4">
                <input type="date" name="endDate" id="endDate" class="col-sm-4 form-control" required/>
                <div class="help-block with-errors"></div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-offset-3 col-xs-6">
                <s:fielderror fieldName="endDate" cssClass="errorMessage"/>
            </div>
        </div>
        <div class="row">
            <s:submit cssClass="btn btn-info col-xs-offset-5 col-xs-2 submitForm"  value="Valider"/>
        </div>
    </form>
</div>
