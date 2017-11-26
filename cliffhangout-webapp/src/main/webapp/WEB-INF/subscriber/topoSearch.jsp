<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<h1>Espace prêt de topos</h1>
<div class="container">
    <s:if test="%{topos!=null && topos.size()>0}">
        <h2>Liste des topos à emprunter</h2>
        <div class="table-responsive">
            <table class="table table-hover table-bordered">
                <thead>
                <tr>
                    <th>Nom</th>
                    <th>Description</th>
                    <th>Propriétaire</th>
                    <th>Sites</th>
                    <th>Emprunter</th>
                </tr>
                </thead>
                <tbody>
                <s:iterator value="topos">
                    <tr>
                        <td><s:property value="name"/></td>
                        <td><s:property value="description"/></td>
                        <td><s:property value="owner.login"/></td>
                        <td>
                            <ul>
                                <s:iterator value="sites">
                                    <s:url var="siteUrl" action="site" namespace="/">
                                        <s:param name="idSite"><s:property value="id"/></s:param>
                                    </s:url>
                                    <li><a href="${siteUrl}"><s:property value="name"/></a> (Departement : <s:property value="departement.name"/>)</li>
                                </s:iterator>
                            </ul>
                        </td>
                        <s:url var="topoUrl" action="topoBorrowing">
                            <s:param name="idTopo"><s:property value="id"/></s:param>
                        </s:url>
                        <td><a href="${topoUrl}" class="btn btn-info">Emprunter ce topo</a></td>
                    </tr>
                </s:iterator>
                </tbody>
            </table>
        </div>
    </s:if>
    <s:else>
        <p>Aucun topo à emprunter pour le moment</p>
    </s:else>
    <s:if test="%{borrows !=null && borrows.size()>0}">
        <h2>Mes emprunts</h2>
        <div class="table-responsive">
            <table class="table table-hover table-bordered">
                <thead>
                <tr>
                    <th>Nom</th>
                    <th>Description</th>
                    <th>Propriétaire</th>
                    <th>Sites</th>
                    <th>Début Prêt</th>
                    <th>Fin Prêt</th>
                    <th>Télécharger fichier</th>
                </tr>
                </thead>
                <tbody>
                <s:iterator value="borrows">
                    <tr>
                        <td><s:property value="topo.name"/></td>
                        <td><s:property value="topo.description"/></td>
                        <td><s:property value="topo.owner.login"/></td>
                        <td>
                            <ul>
                                <s:iterator value="topo.sites">
                                    <s:url var="siteUrl" action="site" namespace="/">
                                        <s:param name="idSite"><s:property value="id"/></s:param>
                                    </s:url>
                                    <li><a href="${siteUrl}"><s:property value="name"/></a> (Departement : <s:property value="departement.name"/>)</li>
                                </s:iterator>
                            </ul>
                        </td>
                        <td><s:property value="startDate"/> </td>
                        <td><s:property value="endDate"/> </td>
                        <s:url var="topoDownload" action="topoSearch">
                            <s:param name="fileName"><s:property value="topo.file"/></s:param>
                        </s:url>
                        <td>
                            <s:if test="%{!startDate.equals(now)}">
                                <s:if test="%{!startDate.before(now) || !endDate.after(now)}">
                                    <span style="color:red;">Topo non disponible (prêt non commencé ou terminé)</span>
                                </s:if>
                            </s:if>
                            <s:else>
                                <a href="${topoDownload}" class="btn btn-info">Télécharger topo</a>
                            </s:else>
                        </td>
                    </tr>
                </s:iterator>
                </tbody>
            </table>
        </div>

    </s:if>

</div>
