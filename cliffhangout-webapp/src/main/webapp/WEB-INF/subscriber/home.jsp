<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="pageContent">
    <div class="welcome-message">
        <h1>Espace Abonné Cliffhangout</h1>
        <p>Bienvenue sur votre espace dédié aux abonnés de la communauté Cliffhangout.</p>
    </div>
    <div class="container">
        <div class="row content">
            <div class="col-xs-5">
                <h2>Partager votre Spot d'escalade</h2>
                <p>Faites découvrir à la communauté vos emplacements d'escalade préférés !
                    Partagés votre expérience des lieux en notant la difficulté des différentes voies ainsi qu'en décrivant chaque secteur et longueurs.
                </p>
                <a href="<s:url action="addSite"/>" class="btn-cliffhangout col-xs-offset-4 col-xs-4">Créer un site</a>
            </div>
            <div class="col-xs-5 col-xs-offset-2">
                <h2>Espace Topos</h2>
                <p>Réunissez vos expériences de différents sites dans un topos et partager le avec la communauté via un système prêt.
                    Consulter les topos existants et disponible partagé par vos camarades grimpeurs!
                </p>
                <a href="#" class="btn-cliffhangout col-xs-offset-4 col-xs-4">Espace topos</a>
            </div>
        </div>
        <div class="row">
            <s:if test="%{creatorSites!=null && creatorSites.size()!=0}">
                <h2>Mes sites</h2>
                <div class="table-responsive col-xs-offset-1 col-xs-10">
                    <table class="table table-hover table-bordered">
                        <thead>
                            <tr>
                                <th>Nom</th>
                                <th>Localisation</th>
                                <th>Code Postal</th>
                                <th>Nombre de secteurs</th>
                                <th>Description</th>
                                <th>Modifier</th>
                                <th>Supprimer</th>
                            </tr>
                        </thead>
                        <tbody>
                        <s:iterator value="creatorSites">
                            <tr>
                                <s:url var="siteUrl" action="site" namespace="/">
                                    <s:param name="idSite"><s:property value="id"/></s:param>
                                </s:url>
                                <td><a href="${siteUrl}"><s:property value="name"/></a></td>
                                <td><s:property value="location"/></td>
                                <td><s:property value="postcode"/> </td>
                                <td><s:property value="%{sectors.size()}"/> </td>
                                <td><s:property value="description"/></td>
                                <td><a href="" class="btn btn-info btn-xs" title="Edit"><span class="glyphicon glyphicon-edit"></span></a></td>
                                <s:url var="siteDeleteUrl" action="removeSite">
                                    <s:param name="idSite"><s:property value="id"/></s:param>
                                </s:url>
                                <td>
                                    <button type="button" class="btn btn-danger btn-xs" title="Delete" data-toggle="modal" data-target="#siteDialog<s:property value="id"/>">
                                        <span class="glyphicon glyphicon-remove"></span>
                                    </button>
                                    <div class="modal fade" id="siteDialog<s:property value="id"/>" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                    <h4 class="modal-title" id="myModalLabel">Suppression de site</h4>
                                                </div>
                                                <div class="modal-body">
                                                    Voulez-vous vraiment supprimer ce site ?
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
                                                    <a href="${siteDeleteUrl}" class="btn btn-danger">Confirmer</a>
                                                </div>
                                            </div><!-- /.modal-content -->
                                        </div><!-- /.modal-dialog -->
                                    </div><!-- /.modal -->
                                </td>
                            </tr>
                        </s:iterator>
                        </tbody>
                    </table>
                </div>
            </s:if>
        </div>
        <div class="row">
            <s:if test="%{creatorTopos!=null && creatorTopos.size()!=0}">
                <h2>Mes topos</h2>
                <div class="table-responsive col-xs-offset-1 col-xs-10">
                    <table class="table table-hover table-bordered">
                        <thead>
                        <tr>
                            <th>Nom</th>
                            <th>Fichier</th>
                            <th>Description</th>
                            <th>Prêt</th>
                            <th>Modifier</th>
                            <th>Supprimer</th>
                        </tr>
                        </thead>
                        <tbody>
                        <s:iterator value="creatorTopos">
                            <tr>

                                <td><a href="#"><s:property value="name"/></a></td>
                                <td><s:property value="file"/></td>
                                <td><s:property value="description"/> </td>
                                <td><s:if test="%{borrowed==true}">Oui</s:if><s:else>Non</s:else></td>
                                <td><a href="" class="btn btn-info btn-xs" title="Edit"><span class="glyphicon glyphicon-edit"></span></a></td>
                                <s:url var="topoDeleteUrl" action="removeTopo">
                                    <s:param name="idTopo"><s:property value="id"/></s:param>
                                </s:url>
                                <td>
                                    <button type="button" class="btn btn-danger btn-xs" title="Delete" data-toggle="modal" data-target="#topoDialog<s:property value="id"/>">
                                        <span class="glyphicon glyphicon-remove"></span>
                                    </button>
                                    <div class="modal fade" id="topoDialog<s:property value="id"/>" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                    <h4 class="modal-title" id="ModalLabel">Suppression de topo</h4>
                                                </div>
                                                <div class="modal-body">
                                                    Voulez-vous vraiment supprimer ce topo ?
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
                                                    <a href="${topoDeleteUrl}" class="btn btn-danger">Confirmer</a>
                                                </div>
                                            </div><!-- /.modal-content -->
                                        </div><!-- /.modal-dialog -->
                                    </div><!-- /.modal -->
                                </td>
                            </tr>
                        </s:iterator>
                        </tbody>
                    </table>
                </div>
            </s:if>
        </div>
    </div>
</div>