<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="container">
    <h1>Modifier Site</h1>
    <form method="post" class="form-horizontal" data-toggle="validator" enctype="multipart/form-data">
        <div class="form-group row">
            <label for="siteName" class="col-xs-offset-3 col-xs-2">Nom : </label>
            <div class="col-xs-4">
                <input type="text" name="siteBean.name" class="form-control" id="siteName"
                       value="<s:property value="siteToEdit.name"/>"
                       data-minlength="2"
                       data-error="Le nom de site doit faire deux caractères au minimum"/>
                <div class="help-block with-errors"></div>
                <s:fielderror fieldName="siteToEdit.name" cssClass="errorMessage"/>
            </div>
        </div>
        <div class="form-group row">
            <label for="siteDescription" class="col-xs-offset-3 col-xs-2">Description : </label>
            <div class="col-xs-4">
                    <textarea id="siteDescription" name="siteBean.description" class="form-control"
                              data-error="La description du site ne peut être vide"><s:property value="siteToEdit.description"/></textarea>
                <div class="help-block with-errors"></div>
                <s:fielderror fieldName="siteBean.description" cssClass="errorMessage"/>
            </div>
        </div>
        <div class="form-group row">
            <label for="siteLocation" class="col-xs-offset-3 col-xs-2">Commune : </label>
            <div class="col-xs-4">
                <input type="text" name="siteBean.location" class="form-control" id="siteLocation"
                       value="<s:property value="siteToEdit.location"/>"
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
                       value="<s:property value="siteToEdit.postcode"/>"
                       data-minlength="5"
                       data-error="Un code postale est composé de 5 caractères"/>
                <div class="help-block with-errors"></div>
                <s:fielderror fieldName="siteBean.postcode" cssClass="errorMessage"/>
            </div>
        </div>
        <div class="form-group row">
            <label for="siteImages" class="col-xs-offset-3 col-xs-2">Ajouter des images du site</label>
            <div class="col-xs-4">
                <input type="file" name="uploads" id="siteImages" class="col-sm-4 form-control"
                       accept="image/jpeg,image/gif,image/png,image/bmp" multiple/>
                <div class="help-block with-errors"></div>
            </div>
        </div>
        <div class="row">
            <input type="submit" class="btn btn-warning submitSite col-xs-offset-4 col-xs-4" value="Ajouter Site">
        </div>
    </form>
    <s:if test="%{#session.uploads !=null && #session.uploads.size()!=0}">
        <h2>Images du site</h2>
        <s:iterator value="#session.uploadsFileName" status="status">
            <div class="row">
                <div class="col-xs-offset-4 col-xs-4 fileNames">
                    <s:property/>
                </div>
                <div class="col-xs-1">
                    <form>
                        <input type="hidden" name="imageSessionToDelete" value="<s:property value="%{#status.index}"/>"/>
                        <button type="submit" class="btn btn-danger btn-sm col-xs-offset-4 col-xs-8"><span class="glyphicon glyphicon-remove"></span> </button>
                    </form>
                </div>
            </div>
        </s:iterator>
    </s:if>
    <s:if test="%{siteToEdit.images !=null && siteToEdit.images.size()!=0}">
        <h2>Images du site</h2>
        <s:iterator value="siteToEdit.images">
            <div class="row">
                <img src="/uploadCliffhangout/images/site/<s:property value="path"/>" class="col-sm-offset-4 col-sm-4 col-xs-offset-3 col-xs-6" title="<s:property value="title"/>" alt="<s:property value="alt"/>"/>
            </div>
            <div class="row">
                <form>
                    <input type="hidden" name="imageToDelete" value="<s:property value="id"/>"/>

                    <input type="submit" value="Supprimer image" name="deleteImage" class="btn btn-danger col-xs-offset-4 col-xs-4"/>
                </form>
            </div>
        </s:iterator>
    </s:if>
</div>

