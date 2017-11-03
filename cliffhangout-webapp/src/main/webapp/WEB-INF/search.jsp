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
                    <s:checkbox name="addCriteria" id="addCriteria" label="Ajouter critères de recherche"></s:checkbox>
                    <div class="criterias">
                        <p>
                            <s:checkbox name="criteriaLocation" id="criteria-location" label="Localisation"></s:checkbox>
                        <div class="criterias-location">
                            <label for="region"><input type="radio" name="criteriaLocationValue" value="region" checked/>Régions</label>
                            <select id="region" name="criteriaRegion">
                                <option value="0" selected="selected">Toutes les régions</option>
                                <c:forEach items="${ regions }" var="region" varStatus="status">
                                    <option value="${region.id}">${region.name}</option>
                                </c:forEach>
                            </select>
                            <br/>
                            <label for="departement"><input type="radio" name="criteriaLocationValue" value="departement"/>Départements</label>
                            <select id="departement" name="criteriaDepartement">
                                <option value="0" selected="selected">Tous les départements</option>
                                <c:forEach items="${ departements }" var="departement" varStatus="status">
                                    <option value="${departement.code}">${departement.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        </p>
                        <p>
                            <s:checkbox name="criteriaQuotation" id="criteria-cotation" label="Cotations"></s:checkbox>
                        <div class="criterias-cotation">
                            <label><input type="radio" name="criteriaQuotationValue" value="minimum" checked/>Cotations minimale</label>
                            <div class="criteria-cotation-min">
                                <select name="criteriaQuotationMinValue">
                                    <c:forEach var="cotation" begin="3" end="9" step="1">
                                        <option>${cotation}a</option>
                                        <option>${cotation}b</option>
                                        <option>${cotation}c</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <label><input type="radio" name="criteriaQuotationValue" value="maximum"/>Cotations maximale</label>
                            <div class="criteria-cotation-max">
                                <select name="criteriaQuotationMaxValue">
                                    <c:forEach var="cotation" begin="3" end="9" step="1">
                                        <option>${cotation}a</option>
                                        <option>${cotation}b</option>
                                        <option>${cotation}c</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        </p>
                        <p>
                            <s:checkbox name="criteriaWays" id="criteria-ways" label="Nombre de voies"></s:checkbox>
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
        <c:if test="${ !empty result }">
            <div class="results">
                <h2>Résultats de votre recherche</h2>
                <p>${ result }</p>
                <c:if test="${ !empty sites }">
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
                            <c:forEach items="${ sites }" var="site" varStatus="status">
                                <tr>
                                    <s:url var="siteUrl" action="site">
                                        <s:param name="idSite">${site.id}</s:param>
                                    </s:url>
                                    <td><a href="${siteUrl}">${site.name}</a></td>
                                    <td>${site.location}</td>
                                    <td>${site.postcode}</td>
                                    <td>${fn:length(site.sectors)}</td>
                                    <td>${site.description}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:if>
            </div>
        </c:if>
    </div>
</div>
<c:if test="${empty result}">
    <div class="last_sites">
        <div class="container page-section">
            <h2>Les derniers sites publiés</h2>
            <c:if test="${ !empty lastSites }">
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
                        <c:forEach items="${ lastSites }" var="site" varStatus="status">
                            <tr>
                                <s:url var="siteUrl" action="site">
                                    <s:param name="idSite">${site.id}</s:param>
                                </s:url>
                                <td><a href="${siteUrl}">${site.name}</a></td>
                                <td>${site.location}</td>
                                <td>${site.postcode}</td>
                                <td>${fn:length(site.sectors)}</td>
                                <td>${site.description}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
        </div>
    </div>
</c:if>


