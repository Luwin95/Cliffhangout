<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<div class="container">
    <div class="row">
        <div class="image-site col-md-offset-5 col-md-2">
        </div>
    </div>
    <div class="row">
        <div class="col-md-8">
            <h1><s:property value="site.name"/></h1>
        </div>
    </div>
    <div class="row">
        <div class="col-md-8 site-content">
            <s:if test="%{site.images !=null && site.images.size()!=0}">
                <div class="row">
                    <div id="my_carousel" class="carousel slide col-md-offset-1 col-md-10" data-ride="carousel">
                        <!-- Bulles -->
                        <ol class="carousel-indicators">
                            <s:iterator value="site.images" status="status">
                                <s:if test="%{#status.index ==0}">
                                    <li data-target="#my_carousel" data-slide-to="<s:property value="%{#status.index}"/>" class="active"></li>
                                </s:if>
                                <s:else>
                                    <li data-target="#my_carousel" data-slide-to="<s:property value="%{#status.index}"/>"></li>
                                </s:else>
                            </s:iterator>
                        </ol>
                        <!-- Slides -->
                        <div class="carousel-inner">
                            <s:iterator value="site.images" status="status">
                                <s:if test="%{#status.index ==0}">
                                    <div class="item active">
                                        <div class="carousel-page">
                                            <img src="/resources/images/site/<s:property value="path"/>" alt="<s:property value="alt"/>" title="<s:property value="title"/>" class="img-responsive" style="margin:0px auto;" />
                                        </div>
                                    </div>
                                </s:if>
                                <s:else>
                                    <div class="item">
                                        <div class="carousel-page">
                                            <img src="/resources/images/site/<s:property value="path"/>" alt="<s:property value="alt"/>" title="<s:property value="title"/>" class="img-responsive" style="margin:0px auto;" />
                                        </div>
                                    </div>
                                </s:else>
                            </s:iterator>
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
            </s:if>
            <div class="row">
                <div class="col-md-offset-1 col-md-10">
                    <p><s:property value="site.description"/></p>
                    <s:iterator value="site.sectors">
                    <h2>Secteur : <s:property value="name"/></h2>
                    <p><s:property value="description"/></p>
                    <s:iterator value="ways">
                        <h3><s:property value="name"/></h3>
                        <p>Cette voie est composée de <s:property value="pointsNb"/> points et s'étend sur une hauteur de <s:property value="height"/>m. Elle a été enregistré en tant que voie de catégorie <s:property value="quotation.name"/>.</p>
                        <p> Elle comporte <s:property value="lengths.size()"/> <s:if test="lengths.size() > 1">longueurs</s:if><s:else>longueur</s:else> comme suit :
                            <ul>
                                <s:iterator value="lengths">
                                    <li><s:property value="name"/>, longueur constituée de <s:property value="points.size()"/> points</li>
                                </s:iterator>
                            </ul>
                        </p>
                    </s:iterator>
                </s:iterator>
                </div>
            </div>
        </div>
        <div class="col-md-offset-1 col-md-3">
            <aside>
                <h2>Carte d'identité</h2>
                <ul>
                    <li><span class="id-list-title">Localisation : </span><s:property value="site.location"/></li>
                    <li><span class="id-list-title">Région : </span><s:property value="site.departement.region.name"/></li>
                    <li><span class="id-list-title">Département : </span><s:property value="site.departement.name"/></li>
                </ul>
                <p class="aside-footer">Ajouté par <s:property value="site.creator.login"/></p>
            </aside>
            <aside>
                <h2>Topos</h2>
                <s:if test="%{topos !=null && topos.size()!=0}">
                    <%-- Liste des topos disponibles pour le site --%>
                </s:if>
                <s:else>
                    <p>Ce site d'escalade n'est lié à aucun topo</p>
                    <p class="aside-footer"><button class="btn-cliffhangout">Ajouter un topo</button></p>
                </s:else>
            </aside>
        </div>

    </div>
    <div class="row comments-section">
        <div class="col-md-offset-1 col-md-10 ">
            <h2>Commentaires</h2>
            <s:if test="#session.sessionUser!= null">
                <div class="row comments-form">
                    <s:form action="site">
                        <s:textarea name="commentBean.content" id="comment-content" placeholder="Laisser un commentaire"/>
                        <br/><s:submit value="Laisser un commentaire" cssClass="btn-cliffhangout"/>
                        <s:hidden name="idSite" value="%{idSite}"/>
                    </s:form>
                        <!--textarea name="comment-content" id="comment-content" placeholder="Laisser un commentaire"></textarea-->
                        <!--input type="submit" value="Laisser un commentaire" class="btn-cliffhangout"/-->
                </div>
            </s:if>
            <s:if test="%{site.comments !=null && site.comments.size()!=0}">
                <div class="row comments-display">
                    <s:iterator value="site.comments">
                        <div class="row comment">
                            <div class="col-xs-1">
                                <s:if test="%{author.image !=null}">
                                    <div class="author-profile-img">
                                        <img src=/resources/images/user/<s:property value="author.image.path"/>"/>
                                    </div>
                                </s:if>
                                <s:else>
                                    <div class="author-profile-img">
                                        <img src="/resources/images/user/icone-grimpeur.png"/>
                                    </div>
                                </s:else>
                            </div>
                            <div class="col-xs-11 comment-content">
                                <s:property value="author.login"/> a dit : <s:property value="content"/>
                            </div>
                        </div>
                        <s:if test="%{children !=null && children.size()!=0}">
                            <s:iterator value="children">
                                <div class="row comment" style="margin-left:20px;">
                                    <div class="col-xs-1">
                                        <s:if test="%{author.image !=null}">
                                            <div class="author-profile-img">
                                                <img src="${pageContext.request.contextPath}/resources/images/user/<c:out value="${child.author.image.path}"/>"/>
                                            </div>
                                        </s:if>
                                        <s:else>
                                            <div class="author-profile-img">
                                                <img src="${pageContext.request.contextPath}/resources/images/user/icone-grimpeur.png"/>
                                            </div>
                                        </s:else>
                                            <%--
                                            <c:choose>
                                                <c:when test="${!empty child.author.image}">
                                                    <div class="author-profile-img">
                                                        <img src="${pageContext.request.contextPath}/resources/images/user/<c:out value="${child.author.image.path}"/>"/>
                                                    </div>
                                                </c:when>
                                                <c:otherwise>
                                                    <div class="author-profile-img">
                                                        <img src="${pageContext.request.contextPath}/resources/images/user/icone-grimpeur.png"/>
                                                    </div>
                                                </c:otherwise>
                                            </c:choose>--%>
                                    </div>
                                    <div class="col-xs-11 comment-content">
                                        <s:property value="author.login"/> a répondu : <s:property value="content"/>
                                    </div>
                                </div>
                                <s:if test="%{children !=null && children.size()!=0}">
                                    <s:iterator value="children">
                                        <p style="margin-left:20px;"><s:property value="author.login"/> a répondu : <s:property value="content"/></p>
                                    </s:iterator>
                                </s:if>
                                <%--<c:if test="${!empty child.children }">
                                    <c:forEach items="${ child.children }" var="childsChild" varStatus="status">
                                        <p style="margin-left:20px;"><c:out value="${childsChild.author.login}"/> a répondu : <c:out value="${childsChild.content}"/></p>
                                    </c:forEach>
                                </c:if>--%>
                            </s:iterator>
                        </s:if>
                    </s:iterator>
                </div>
            </s:if>
        </div>
    </div>
</div>
