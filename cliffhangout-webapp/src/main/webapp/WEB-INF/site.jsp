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
                    <div id="my_carousel" class="carousel slide col-xs-offset-1 col-xs-10" data-ride="carousel">
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
                                            <img src="/uploadCliffhangout/images/site/<s:property value="path"/>" alt="<s:property value="alt"/>" title="<s:property value="title"/>" class="img-responsive" style="margin:0px auto;" />
                                        </div>
                                    </div>
                                </s:if>
                                <s:else>
                                    <div class="item">
                                        <div class="carousel-page">
                                            <img src="/uploadCliffhangout/images/site/<s:property value="path"/>" alt="<s:property value="alt"/>" title="<s:property value="title"/>" class="img-responsive" style="margin:0px auto;" />
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
                    <ul>
                        <s:iterator value="topos">
                            <s:url var="topoBorrow" action="topoBorrowing" namespace="/subscriber">
                                <s:param name="idTopo"><s:property value="id"/></s:param>
                            </s:url>
                            <li><a href="${topoBorrow}"><s:property value="name"/></a></li>
                        </s:iterator>
                    </ul>
                </s:if>
                <s:else>
                    <p>Ce site d'escalade n'est lié à aucun topos</p>
                    <p class="aside-footer"><button class="btn-cliffhangout">Ajouter un topos</button></p>
                </s:else>
            </aside>
        </div>

    </div>
    <div class="row comments-section">
        <div class="col-xs-offset-1 col-xs-10 ">
            <h2>Commentaires</h2>
            <s:if test="#session.sessionUser!= null">
                <div class="row comments-form" id="commentForm">
                    <s:form action="site" >
                        <textarea name="commentBean.content" id="comment-content" placeholder="Laisser un commentaire" required></textarea>
                        <br/><s:submit value="Laisser un commentaire" cssClass="btn-cliffhangout"/>
                        <s:hidden name="idSite" value="%{idSite}"/>
                    </s:form>
                </div>
            </s:if>
            <s:if test="%{site.comments !=null && site.comments.size()!=0}">
                <div class="row comments-display">
                    <s:iterator value="site.comments">
                        <div class="row comment">
                            <div class="col-sm-1 col-xs-2">
                                <s:if test="%{author.image !=null}">
                                    <div class="author-profile-img">
                                        <img src="/uploadCliffhangout/images/user/<s:property value="author.image.path"/>"/>
                                    </div>
                                </s:if>
                                <s:else>
                                    <div class="author-profile-img">
                                        <img src="/resources/images/user/icone-grimpeur.png"/>
                                    </div>
                                </s:else>
                            </div>
                            <div class="col-xs-8 col-xs-10 comment-content">
                                <s:property value="author.login"/> a dit : <s:property value="content"/>
                            </div>
                            <div class="col-sm-3 col-sm-offset-0 col-xs-offset-2 col-xs-10 comment-content">
                                <div class="row">
                                    <s:if test="#session.sessionUser!= null">
                                        <button class="col-xs-5 btn-cliffhangout answer" id="<s:property value="id"/>">Répondre</button>
                                        <s:form action="site" >
                                            <s:hidden name="idSite" value="%{idSite}"/>
                                            <input name="commentToReport" type="hidden" value="<s:property value="id"/>"/>
                                            <button type="submit" class="col-xs-offset-2 col-xs-5 btn btn-danger report"><span class="glyphicon glyphicon-alert"></span> Signaler</button>
                                        </s:form>
                                    </s:if>
                                    <s:else>
                                        <a href="<s:url action="login"/>">Se connecter</a> ou <a href="#">S'inscrire</a>
                                    </s:else>
                                </div>
                            </div>
                        </div>
                        <s:if test="%{children !=null && children.size()!=0}">
                            <s:set var="cpt" value="0"/>
                            <div style="margin-left:20px;">
                                <s:set var="cpt" value="%{#cpt+1}"/>
                                <s:include value="comment.jsp"/>
                            </div>
                        </s:if>
                    </s:iterator>
                </div>
            </s:if>
        </div>
    </div>
</div>
