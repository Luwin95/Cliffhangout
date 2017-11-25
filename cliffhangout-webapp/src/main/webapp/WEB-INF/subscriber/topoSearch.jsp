<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<h1>Liste des topos à emprunter</h1>
<div class="container">
    <s:if test="%{topos!=null}">
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

</div>
