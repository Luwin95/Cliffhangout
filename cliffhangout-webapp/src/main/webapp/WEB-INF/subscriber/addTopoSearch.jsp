<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="search">
    <h1>Choisir les sites que votre topos concernera</h1>
    <div class="container page-section">
        <div class="row">
            <s:if test='%{result == "" || result==null}'>
                <form method="post" class=" col-md-offset-2 col-md-8 search-form">
                    <div class="row">
                        <input type="text" placeholder="Rechercher un site" class="search-input col-xs-10" name="siteName" id="search-input" required/>
                        <button type="submit" class="search-button col-xs-2" id="search_button1"><i class="glyphicon glyphicon-search"></i></button>
                    </div>
                    <div class="row">
                        <s:checkbox name="addCriteria" id="addCriteria" label="Ajouter critères de recherche"/>
                        <div class="criterias">
                            <p>
                                    <s:checkbox name="criteriaLocation" id="criteria-location" label="Localisation"/>
                            <div class="criterias-location">
                                <label for="region"><input type="radio" name="criteriaLocationValue" value="region" checked/>Régions</label>
                                <select id="region" name="criteriaRegion">
                                    <option value="0" selected="selected">Toutes les régions</option>
                                    <s:iterator value="regions">
                                        <option value="<s:property value="id"/>"><s:property value="name"/></option>
                                    </s:iterator>

                                </select>
                                <br/>
                                <label for="departement"><input type="radio" name="criteriaLocationValue" value="departement"/>Départements</label>
                                <select id="departement" name="criteriaDepartement">
                                    <option value="0" selected="selected">Tous les départements</option>
                                    <s:iterator value="departements">
                                        <option value="<s:property value="code"/>"><s:property value="name"/></option>
                                    </s:iterator>
                                </select>
                            </div>
                            </p>
                            <p>
                                    <s:checkbox name="criteriaQuotation" id="criteria-cotation" label="Cotations"/>
                            <div class="criterias-cotation">
                                <label><input type="radio" name="criteriaQuotationValue" value="minimum" checked/>Cotations minimale</label>
                                <div class="criteria-cotation-min">
                                    <select name="criteriaQuotationMinValue">
                                        <s:iterator status="cotation" begin="3" end="9" step="1">
                                            <option><s:property value="%{#cotation.index +3}"/>a</option>
                                            <option><s:property value="%{#cotation.index +3}"/>b</option>
                                            <option><s:property value="%{#cotation.index +3}"/>c</option>
                                        </s:iterator>

                                    </select>
                                </div>
                                <label><input type="radio" name="criteriaQuotationValue" value="maximum"/>Cotations maximale</label>
                                <div class="criteria-cotation-max">
                                    <select name="criteriaQuotationMaxValue">
                                        <s:iterator status="cotation" begin="3" end="9" step="1">
                                            <option><s:property value="%{#cotation.index +3}"/>a</option>
                                            <option><s:property value="%{#cotation.index +3}"/>b</option>
                                            <option><s:property value="%{#cotation.index +3}"/>c</option>
                                        </s:iterator>
                                    </select>
                                </div>
                            </div>
                            </p>
                            <p>
                                    <s:checkbox name="criteriaWays" id="criteria-ways" label="Nombre de voies"/>
                            <div class="criterias-ways">
                                <label for="way-number-min">Nombre de voies minimum</label>
                                <input id="way-number-min" type="number" name="criteriaWayNumberMin"/>
                                <br/>
                                <label for="way-number-max">Nombre de voies maximum</label>
                                <input id="way-number-max" type="number" name="criteriaWayNumberMax"/>
                            </div>
                            </p>
                        </div>
                        <div class="row">
                            <div class="col-xs-offset-5 col-xs-2">
                                <input type="submit" value="Rechercher" class="btn-cliffhangout " id="search_button2"/>
                            </div>
                        </div>
                    </div>
                </form>
            </s:if>
        </div>
        <s:if test='%{result != "" && result!=null}'>
            <div class="results">
                <form method="post">
                    <h2>Résultats de votre recherche</h2>
                    <p>${ result }</p>
                    <s:if test="%{sites!=null && sites.size()!=0}">
                        <div class="table-responsive">
                            <table class="table table-hover table-bordered">
                                <thead>
                                <tr>
                                    <th>Ajouter</th>
                                    <th>Nom</th>
                                    <th>Localisation</th>
                                    <th>Code Postal</th>
                                    <th>Nombre de secteurs</th>
                                    <th>Description</th>
                                </tr>
                                </thead>
                                <tbody>
                                <s:iterator value="sites">
                                    <tr>
                                        <td><input type="checkbox" name="siteToAdd[<s:property value="id"/>]" value="<s:property value="id"/>"/></td>
                                        <s:url var="siteUrl" action="site">
                                            <s:param name="idSite"><s:property value="id"/></s:param>
                                        </s:url>
                                        <td><a href="${siteUrl}"><s:property value="name"/></a></td>
                                        <td><s:property value="location"/></td>
                                        <td><s:property value="postcode"/> </td>
                                        <td><s:property value="%{sectors.size()}"/> </td>
                                        <td><s:property value="description"/></td>
                                    </tr>
                                </s:iterator>
                                </tbody>
                            </table>
                        </div>
                        <input type="submit" value="Ajouter les sites" class="btn btn-warning"/>
                    </s:if>
                </form>
            </div>
            <a href="<s:url action="addTopoSearch"/>" class="btn btn-info">Effectuer une nouvelle recherche</a>
        </s:if>
        <s:if test='%{sitesChosen != null}'>
            <h3>Sites sélectionnés</h3>
            <ul>
                <form method="post">
                    <s:iterator value="sitesChosen">
                        <li><input type="checkbox" name="sitesToRemove[<s:property value="id"/>]" value="<s:property value="id"/>"/> <s:property value="name"/> (département : <s:property value="departement.name"/>)</li>
                    </s:iterator>
                    <input type="submit" class="btn btn-danger" value="Supprimer les sites sélectionnés"/>
                </form>
            </ul>
            <a href="<s:url action="addTopo"/>" class="btn btn-warning">Passer à la création du topos</a>
        </s:if>
    </div>
</div>
