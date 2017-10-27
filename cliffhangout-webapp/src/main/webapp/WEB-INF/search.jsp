<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<div class="search">
    <h1>Les sites d'escalade</h1>
    <div class="container page-section">
        <div class="row">
            <form method="post" class=" col-md-offset-2 col-md-8 search-form">
                <input type="text" placeholder="Rechercher un site" class="search-input col-xs-10" name="site-name"/>
                <button type="submit" class="search-button col-xs-2"><i class="glyphicon glyphicon-search"></i></button>
                <p><label><input type="checkbox" id="addCriteria" name="addCriteria"/>Ajouter des critères de recherche </label></p>
                <div class="criterias">
                    <p>
                        <label><input type="checkbox" name="criteria-location" id="criteria-location"/>Localisation</label>
                        <div class="criterias-location">
                            <label for="region"><input type="radio" name="criteria-location-value" value="region" checked/>Régions</label>
                            <select id="region" name="criteria-region">
                                <option value="0" selected="selected">Toutes les régions</option>
                                <c:forEach items="${ regions }" var="region" varStatus="status">
                                    <option value="${region.id}">${region.name}</option>
                                </c:forEach>
                            </select>
                            <br/>
                            <label for="departement"><input type="radio" name="criteria-location-value" value="departement"/>Départements</label>
                            <select id="departement" name="criteria-departement">
                                <option value="0" selected="selected">Tous les départements</option>
                                <c:forEach items="${ departements }" var="departement" varStatus="status">
                                    <option value="${departement.code}">${departement.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </p>
                    <p>
                        <label><input type="checkbox" name="criteria-cotation" id="criteria-cotation"/>Cotations</label>
                        <div class="criterias-cotation">
                            <label><input type="radio" name="criteria-cotation-value" value="minimum" checked/>Cotations minimale</label>
                            <div class="criteria-cotation-min">
                                <select name="criteria-cotation-min-value">
                                    <c:forEach var="cotation" begin="3" end="9" step="1">
                                        <option>${cotation}a</option>
                                        <option>${cotation}b</option>
                                        <option>${cotation}c</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <label><input type="radio" name="criteria-cotation-value" value="maximum"/>Cotations maximale</label>
                            <div class="criteria-cotation-max">
                                <select name="criteria-cotation-max-value">
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
                        <label><input type="checkbox" name="criteria-ways" id="criteria-ways"/>Nombre de voies</label>
                    <div class="criterias-ways">
                        <label for="way-number-min">Nombre de voies minimum</label>
                        <input id="way-number-min" type="number" name="criteria-way-number-min"/>
                        <br/>
                        <label for="way-number-max">Nombre de voies maximum</label>
                        <input id="way-number-max" type="number" name="criteria-way-number-max"/>
                    </div>
                    </p>
                    <div class="row">
                        <div class="col-xs-offset-5 col-xs-2">
                            <input type="submit" value="Rechercher" class="btn-cliffhangout"/>
                        </div>
                    </div>
                </div>

            </form>
        </div>
        <c:if test="${ !empty result }">
            <div class="results">
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
                                    <td><a href="${pageContext.request.contextPath}/site/${site.id}">${site.name}</a></td>
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
                            <td><a href="${pageContext.request.contextPath}/site/${site.id}">${site.name}</a></td>
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
