<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<div class="container">
    <div class="row">
        <div class="image-site col-md-offset-5 col-md-2">
        </div>
    </div>
    <div class="row">
        <div class="col-md-8">
            <h1><c:out value="${site.name}"/></h1>
        </div>
    </div>
    <div class="row">
        <div class="col-md-8 site-content">
            <c:if test="${!empty site.images}">
                <div class="row">
                    <div id="my_carousel" class="carousel slide col-md-offset-1 col-md-10" data-ride="carousel">
                        <!-- Bulles -->
                        <ol class="carousel-indicators">
                            <c:forEach items="${ site.images }" var="image" varStatus="status">
                                <c:choose>
                                    <c:when test="${status.count-1 == 0}"><li data-target="#my_carousel" data-slide-to="${status.count-1}" class="active"></li></c:when>
                                    <c:otherwise><li data-target="#my_carousel" data-slide-to="${status.count-1}"></li></c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </ol>
                        <!-- Slides -->
                        <div class="carousel-inner">
                            <c:forEach items="${ site.images }" var="image" varStatus="status">
                                <c:choose>
                                    <c:when test="${status.count-1 == 0}">
                                        <div class="item active">
                                            <div class="carousel-page">
                                                <img src="${pageContext.request.contextPath}/resources/images/site/<c:out value="${image.path}"/>" alt="<c:out value="${image.alt}"/>" title="<c:out value="${image.title}"/>" class="img-responsive" style="margin:0px auto;" />
                                            </div>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="item">
                                            <div class="carousel-page">
                                                <img src="${pageContext.request.contextPath}/resources/images/site/<c:out value="${image.path}"/>" alt="<c:out value="${image.alt}"/>" title="<c:out value="${image.title}"/>" class="img-responsive" style="margin:0px auto;" />
                                            </div>
                                        </div>
                                    </c:otherwise>
                                </c:choose>

                            </c:forEach>
                        </div>
                        <!-- Contrôles -->
                        <a class="left carousel-control" href="#my_carousel" data-slide="prev">
                            <span class="glyphicon glyphicon-chevron-left"></span>
                        </a>
                        <a class="right carousel-control" href="#my_carousel" data-slide="next">
                            <span class="glyphicon glyphicon-chevron-right"></span>
                        </a>
                    </div>
                </div>
            </c:if>
            <div class="row">
                <div class="col-md-offset-1 col-md-10">
                    <p><c:out value="${ site.description}"/> </p>
                    <c:forEach items="${ site.sectors }" var="sector" varStatus="status">
                        <h2>Secteur : <c:out value="${sector.name}"/></h2>
                        <p><c:out value="${sector.description}"/></p>
                        <c:forEach items="${ sector.ways }" var="way" varStatus="status">
                            <h3><c:out value="${way.name}"/></h3>
                            <p>Cette voie est composée de <c:out value="${way.pointsNb}"/> points et s'étend sur une hauteur de <c:out value="${way.height}"/> m. Elle a été enregistré en tant que voie de catégorie <c:out value="${way.quotation.name}"/>.</p>
                            <p> Elle comporte <c:out value="${fn:length(way.lengths)}"/> <c:choose><c:when test="${fn:length(way.lengths) >1}">longueurs</c:when><c:when test="${fn:length(way.lengths) <=1}">longueur</c:when></c:choose> comme suit :
                            <ul>
                                <c:forEach items="${ way.lengths }" var="length" varStatus="status">
                                    <li><c:out value="${length.name}"/>, longueur constituée de <c:out value="${fn:length(length.points)}"/> points</li>
                                </c:forEach>
                            </ul>
                            </p>
                        </c:forEach>
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="col-md-offset-1 col-md-3">
            <aside>
                <h2>Carte d'identité</h2>
                <ul>
                    <li><span class="id-list-title">Localisation : </span><c:out value="${site.location}"/> </li>
                    <li><span class="id-list-title">Région : </span><c:out value="${site.departement.region.name}"/></li>
                    <li><span class="id-list-title">Département : </span><c:out value="${site.departement.name}"/></li>
                </ul>
                <p class="aside-footer">Ajouté par <c:out value="${site.creator.login}"/></p>
            </aside>
            <aside>
                <h2>Topos</h2>
                <c:choose>
                    <c:when test="${!empty topos}">
                    </c:when>
                    <c:otherwise>
                        <p>Ce site d'escalade n'est lié à aucun topo</p>
                        <p class="aside-footer"><button class="btn-cliffhangout">Ajouter un topo</button></p>
                    </c:otherwise>
                </c:choose>
            </aside>
        </div>

    </div>
    <div class="row comments-section">
        <div class="col-md-offset-1 col-md-10 ">
            <div class="row comments-form">
                <h2>Commentaires</h2>
                <form method="post">
                    <textarea name="comment-content" id="comment-content" placeholder="Laisser un commentaire"></textarea>
                    <br/><input type="submit" value="Laisser un commentaire" class="btn-cliffhangout"/>
                </form>
            </div>
            <c:if test="${!empty site.comments }">
                <div class="row comments-display">
                    <c:forEach items="${ site.comments }" var="comment" varStatus="status">
                        <div class="row comment">
                            <div class="col-xs-1">
                                <c:choose>
                                    <c:when test="${!empty comment.author.image}">
                                        <div class="profile-img">
                                            <img src="${pageContext.request.contextPath}/resources/images/user/<c:out value="${comment.author.image.path}"/>"/>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="profile-img">
                                            <img src="${pageContext.request.contextPath}/resources/images/user/icone-grimpeur.png"/>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="col-xs-11 comment-content">
                                <c:out value="${comment.author.login}"/> a dit : <c:out value="${comment.content}"/>
                            </div>
                        </div>
                        <c:if test="${!empty comment.children }">
                            <c:forEach items="${ comment.children }" var="child" varStatus="status">
                                <div class="row comment" style="margin-left:20px;">
                                    <div class="col-xs-1">
                                        <c:choose>
                                            <c:when test="${!empty child.author.image}">
                                                <div class="profile-img">
                                                    <img src="${pageContext.request.contextPath}/resources/images/user/<c:out value="${child.author.image.path}"/>"/>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="profile-img">
                                                    <img src="${pageContext.request.contextPath}/resources/images/user/icone-grimpeur.png"/>
                                                </div>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                    <div class="col-xs-11 comment-content">
                                        <c:out value="${child.author.login}"/> a répondu : <c:out value="${child.content}"/>
                                    </div>
                                </div>
                                <c:if test="${!empty child.children }">
                                    <c:forEach items="${ child.children }" var="childsChild" varStatus="status">
                                        <p style="margin-left:20px;"><c:out value="${childsChild.author.login}"/> a répondu : <c:out value="${childsChild.content}"/></p>
                                    </c:forEach>
                                </c:if>
                            </c:forEach>
                        </c:if>
                    </c:forEach>
                </div>
            </c:if>
        </div>
    </div>
</div>