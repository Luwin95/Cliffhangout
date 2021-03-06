<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="welcome">
    <h1>Cliffhangout</h1>
    <p>Le premier site communautaire dédié à l'escalade</p>
</div>
<a href="#site-elements">
    <div class="discover">
        <p class="discover-text">Découvrir <br/><span class="glyphicon glyphicon-chevron-down"></span></p>
    </div>
</a>
<div class="container site-elements" id="site-elements">
    <div class="row ">
        <div class ="col-sm-3 col-xs-12 element">
            <div class="img-container">
                <img src="/images/background.jpg"/>
            </div>
            <h2>Retrouvez et découvrez les meilleurs sites d'escalade recensés par la communauté Cliffhangout</h2>
            <a href="<s:url action='search'/>" class="btn-cliffhangout">Rechercher un site</a>
        </div>
        <div class="col-sm-offset-1 col-sm-4 col-xs-12 element">
            <div class="img-container">
                <img src="/images/climber.jpg"/>
            </div>

            <h2>Partager votre expérience de la grimpe en décrivant vos sites favoris</h2>
            <a href="#" class="btn-cliffhangout">Partager un site</a>
        </div>
        <div class="col-sm-offset-1 col-sm-3 col-xs-12 element">
            <div class="img-container">
                <img src="/images/lenses-ridge.jpg"/>
            </div>

            <h2>Accédez à notre espace de partage de topos pour faciliter vos prochaines journées grimpe</h2>
            <a href="#" class="btn-cliffhangout">Espace prêt de topos</a>
        </div>
    </div>
</div>