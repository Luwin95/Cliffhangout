<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="search">
    <h1>Les sites d'escalade</h1>
    <div class="container page-section">
        <div class="row">
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
        </div>
        <s:if test='%{result != "" && result!=null}'>
            <div class="results">
                <h2>Résultats de votre recherche</h2>
                <p>${ result }</p>
                <s:if test="%{sites!=null && sites.size()!=0}">
                    <div class="table-responsive">
                        <table class="table table-hover table-bordered">
                            <thead>
                                <tr>
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
                                        <s:url var="siteUrl" action="site">
                                            <s:param name="idSite"><s:property value="id"/></s:param>
                                        </s:url>
                                        <td><a href="${siteUrl}"><s:property value="name"/></a></td>
                                        <td><s:property value="location"/></td>
                                        <td><s:property value="postcode"/> </td>
                                        <td><s:property value="%{sectors.size()}"/> </td>
                                        <td><s:if test="description.length()>50"><s:property value="description.substring(0,50)"/>...</s:if><s:else><s:property value="description"/></s:else></td>
                                    </tr>
                                </s:iterator>
                            </tbody>
                        </table>
                    </div>
                </s:if>
            </div>
        </s:if>
    </div>
</div>
<s:if test='%{result == "" || result==null}'>
    <div class="last_sites">
        <div class="container page-section">
            <h2>Les derniers sites publiés</h2>
            <s:if test="%{lastSites!=null && lastSites.size()!=0}">
                <div class="table-responsive">
                    <table class="table table-hover table-bordered">
                        <thead>
                        <tr>
                            <th>Nom</th>
                            <th>Localisation</th>
                            <th>Code Postal</th>
                            <th>Nombre de secteurs</th>
                            <th>Description</th>
                        </tr>
                        </thead>
                        <tbody>
                        <s:iterator value="lastSites">
                            <tr>
                                <s:url var="siteUrl" action="site">
                                    <s:param name="idSite"><s:property value="id"/></s:param>
                                </s:url>
                                <td><a href="${siteUrl}"><s:property value="name"/></a></td>
                                <td><s:property value="location"/></td>
                                <td><s:property value="postcode"/> </td>
                                <td><s:property value="%{sectors.size()}"/> </td>
                                <td><s:if test="description.length()>50"><s:property value="description.substring(0,50)"/>...</s:if><s:else><s:property value="description"/></s:else></td>
                            </tr>
                        </s:iterator>
                        </tbody>
                    </table>
                </div>
            </s:if>
        </div>
    </div>
</s:if>



