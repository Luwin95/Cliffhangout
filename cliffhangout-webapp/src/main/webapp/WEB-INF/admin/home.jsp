<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<h1>Espace administrateur</h1>
<div class="container">
    <div class="row">
        <ul class="nav nav-tabs">
            <li class="active"><a href="#users" data-toggle="tab">Utilisateurs</a></li>
            <li><a href="#sites" data-toggle="tab">Sites</a></li>
            <li><a href="#topos" data-toggle="tab">Topos</a></li>
            <li><a href="#comments" data-toggle="tab">Commentaires</a></li>
        </ul>
    </div>
    <div class="tab-content row">
        <div class="tab-pane fade in active" id="users">
            <s:if test="%{users!=null && users.size()!=0}">
                <div class="table-responsive col-xs-offset-1 col-xs-10">
                    <table class="table table-hover table-bordered">
                        <thead>
                            <tr>
                                <th>Login</th>
                                <th>Email</th>
                                <th>Rôle</th>
                                <th>Modifier les droits</th>
                                <th>Statut</th>
                                <th>Activer/Désactiver</th>
                            </tr>
                        </thead>
                        <tbody>
                        <s:iterator value="users">
                            <tr>
                                <td><s:property value="login"/></td>
                                <td><s:property value="email"/></td>
                                <td><s:property value="role"/> </td>
                                <s:url var="userRightsUrl" action="editRights" namespace="/admin">
                                    <s:param name="idUser"><s:property value="id"/></s:param>
                                </s:url>
                                <td>
                                    <s:if test='%{role.equals("ADMIN")}'>
                                        <button type="button" class="btn btn-danger" title="Delete" data-toggle="modal" data-target="#userRemoveRightsDialog<s:property value="id"/>">
                                            <span class="glyphicon glyphicon-edit"></span> Supprimer Droits
                                        </button>
                                        <div class="modal fade" id="userRemoveRightsDialog<s:property value="id"/>" tabindex="-1" role="dialog" aria-labelledby="modalUserRemoveRights" aria-hidden="true">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                        <h4 class="modal-title" id="modalUserRemoveRights">Suppression droits d'administrateur de l'utilisateur</h4>
                                                    </div>
                                                    <div class="modal-body">
                                                        Voulez-vous vraiment supprimer les droits d'administrateur de cet utilisateur ?
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
                                                        <a href="${userRightsUrl}" class="btn btn-danger">Confirmer</a>
                                                    </div>
                                                </div><!-- /.modal-content -->
                                            </div><!-- /.modal-dialog -->
                                        </div><!-- /.modal -->
                                    </s:if>
                                    <s:else>
                                        <button type="button" class="btn btn-success" title="Add" data-toggle="modal" data-target="#userAddRightsDialog<s:property value="id"/>">
                                            <span class="glyphicon glyphicon-edit"></span> Ajouter droits
                                        </button>
                                        <div class="modal fade" id="userAddRightsDialog<s:property value="id"/>" tabindex="-1" role="dialog" aria-labelledby="modalUserAddRights" aria-hidden="true">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                        <h4 class="modal-title" id="modalUserAddRights">Ajout droits d'administrateur de l'utilisateur</h4>
                                                    </div>
                                                    <div class="modal-body">
                                                        Voulez-vous vraiment ajouter les droits d'administrateur à cet utilisateur ?
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
                                                        <a href="${userRightsUrl}" class="btn btn-danger">Confirmer</a>
                                                    </div>
                                                </div><!-- /.modal-content -->
                                            </div><!-- /.modal-dialog -->
                                        </div><!-- /.modal -->
                                    </s:else>
                                </td>
                                <td>
                                    <s:if test="%{active == true}">
                                        ACTIF
                                    </s:if>
                                    <s:else>
                                        INACTIF
                                    </s:else>
                                </td>
                                <s:url var="userActivationUrl" action="editUserActivation" namespace="/admin">
                                    <s:param name="idUser"><s:property value="id"/></s:param>
                                </s:url>
                                <td>
                                    <s:if test="%{active == true}">
                                        <button type="button" class="btn btn-danger" title="Delete" data-toggle="modal" data-target="#usersRemoveActivationDialog<s:property value="id"/>">
                                            <span class="glyphicon glyphicon-remove"></span>Désactiver
                                        </button>
                                        <div class="modal fade" id="usersRemoveActivationDialog<s:property value="id"/>" tabindex="-1" role="dialog" aria-labelledby="modalRemoveUsersActivation" aria-hidden="true">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                        <h4 class="modal-title" id="modalRemoveUsersActivation">Désactivation de compte utilisateur</h4>
                                                    </div>
                                                    <div class="modal-body">
                                                        Voulez-vous vraiment désactiver cet utilisateur ?
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
                                                        <a href="${userActivationUrl}" class="btn btn-danger">Confirmer</a>
                                                    </div>
                                                </div><!-- /.modal-content -->
                                            </div><!-- /.modal-dialog -->
                                        </div><!-- /.modal -->
                                    </s:if>
                                    <s:else>
                                        <button type="button" class="btn btn-success" title="Delete" data-toggle="modal" data-target="#usersAddActivationDialog<s:property value="id"/>">
                                            <span class="glyphicon glyphicon-add"></span>Activer
                                        </button>
                                        <div class="modal fade" id="usersAddActivationDialog<s:property value="id"/>" tabindex="-1" role="dialog" aria-labelledby="modalAddUsersActivation" aria-hidden="true">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                        <h4 class="modal-title" id="modalAddUsersActivation">Activation de compte utilisateur</h4>
                                                    </div>
                                                    <div class="modal-body">
                                                        Voulez-vous vraiment activer cet utilisateur ?
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
                                                        <a href="${userActivationUrl}" class="btn btn-danger">Confirmer</a>
                                                    </div>
                                                </div><!-- /.modal-content -->
                                            </div><!-- /.modal-dialog -->
                                        </div><!-- /.modal -->
                                    </s:else>
                                </td>
                            </tr>
                        </s:iterator>
                        </tbody>
                    </table>
                </div>
            </s:if>
        </div>
        <div class="tab-pane fade in" id="sites">
            <s:if test="%{sites!=null && sites.size()!=0}">
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
                        <s:iterator value="sites">
                            <tr>
                                <s:url var="siteUrl" action="site" namespace="/">
                                    <s:param name="idSite"><s:property value="id"/></s:param>
                                </s:url>
                                <td><a href="${siteUrl}"><s:property value="name"/></a></td>
                                <td><s:property value="location"/></td>
                                <td><s:property value="postcode"/> </td>
                                <td><s:property value="%{sectors.size()}"/> </td>
                                <td><s:property value="description"/></td>
                                <s:url var="siteEditUrl" action="editSite" namespace="/subscriber">
                                    <s:param name="idSite"><s:property value="id"/></s:param>
                                </s:url>
                                <td><a href="${siteEditUrl}" class="btn btn-info btn-xs" title="Edit"><span class="glyphicon glyphicon-edit"></span></a></td>
                                <s:url var="siteDeleteUrl" action="removeSite" namespace="/subscriber">
                                    <s:param name="idSite"><s:property value="id"/></s:param>
                                </s:url>
                                <td>
                                    <button type="button" class="btn btn-danger btn-xs" title="Delete" data-toggle="modal" data-target="#siteDialog<s:property value="id"/>">
                                        <span class="glyphicon glyphicon-remove"></span>
                                    </button>
                                    <div class="modal fade" id="siteDialog<s:property value="id"/>" tabindex="-1" role="dialog" aria-labelledby="modalSites" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                    <h4 class="modal-title" id="modalSites">Suppression de site</h4>
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
        <div class="tab-pane fade in" id="topos">
            <s:if test="%{topos!=null && topos.size()!=0}">
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
                        <s:iterator value="topos">
                            <tr>

                                <td><a href="#"><s:property value="name"/></a></td>
                                <td><s:property value="file"/></td>
                                <td><s:property value="description"/> </td>
                                <td><s:if test="%{borrowed==true}">Oui</s:if><s:else>Non</s:else></td>
                                <s:url var="topoEditUrl" action="editTopo" namespace="/subscriber">
                                    <s:param name="idTopo"><s:property value="id"/></s:param>
                                </s:url>
                                <td><a href="${topoEditUrl}" class="btn btn-info btn-xs" title="Edit"><span class="glyphicon glyphicon-edit"></span></a></td>
                                <s:url var="topoDeleteUrl" action="removeTopo" namespace="/subscriber">
                                    <s:param name="idTopo"><s:property value="id"/></s:param>
                                </s:url>
                                <td>
                                    <button type="button" class="btn btn-danger btn-xs" title="Delete" data-toggle="modal" data-target="#topoDialog<s:property value="id"/>">
                                        <span class="glyphicon glyphicon-remove"></span>
                                    </button>
                                    <div class="modal fade" id="topoDialog<s:property value="id"/>" tabindex="-1" role="dialog" aria-labelledby="modalTopos" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                    <h4 class="modal-title" id="modalTopos">Suppression de topo</h4>
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
        <div class="tab-pane fade in" id="comments">
            <s:if test="%{comments!=null && comments.size()!=0}">
                <div class="table-responsive col-xs-offset-1 col-xs-10">
                    <table class="table table-hover table-bordered">
                        <thead>
                            <tr>
                                <th>Commentaire</th>
                                <th>Auteur</th>
                                <th>Retirer Signalement</th>
                                <th>Supprimer</th>
                            </tr>
                        </thead>
                        <tbody>
                        <s:iterator value="comments">
                            <tr>
                                <td><a href="#"><s:property value="content"/></a></td>
                                <td><s:property value="author.login"/></td>
                                <td>
                                    <s:url var="commentReportingUrl" action="deleteReportingOnComment" namespace="/admin">
                                        <s:param name="idComment"><s:property value="id"/></s:param>
                                    </s:url>
                                    <button type="button" class="btn btn-warning btn-xs" title="deleteReporting" data-toggle="modal" data-target="#commentReportingDialog<s:property value="id"/>">
                                        <span class="glyphicon glyphicon-edit"></span>
                                    </button>
                                    <div class="modal fade" id="commentReportingDialog<s:property value="id"/>" tabindex="-1" role="dialog" aria-labelledby="modalCommentsReporting" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                    <h4 class="modal-title" id="modalReporting">Suppression du signalement du commentaire</h4>
                                                </div>
                                                <div class="modal-body">
                                                    Voulez-vous vraiment supprimer le signalement de ce commentaire ?
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
                                                    <a href="${commentReportingUrl}" class="btn btn-danger">Confirmer</a>
                                                </div>
                                            </div><!-- /.modal-content -->
                                        </div><!-- /.modal-dialog -->
                                    </div><!-- /.modal -->
                                </td>
                                <td>
                                    <s:url var="commentDeleteUrl" action="deleteComment" namespace="/admin">
                                        <s:param name="idComment"><s:property value="id"/></s:param>
                                    </s:url>
                                    <button type="button" class="btn btn-danger btn-xs" title="Delete" data-toggle="modal" data-target="#commentDialog<s:property value="id"/>">
                                        <span class="glyphicon glyphicon-remove"></span>
                                    </button>
                                    <div class="modal fade" id="commentDialog<s:property value="id"/>" tabindex="-1" role="dialog" aria-labelledby="modalComments" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                    <h4 class="modal-title" id="modalComments">Suppression de commentaire</h4>
                                                </div>
                                                <div class="modal-body">
                                                    Voulez-vous vraiment supprimer ce commentaire ?
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
                                                    <a href="${commentDeleteUrl}" class="btn btn-danger">Confirmer</a>
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
