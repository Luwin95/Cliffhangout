<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Accueil</title>
    </head>
    <body>
        <h2>Hello World!</h2>
        <p>Ceci est une jsp</p>
        <p>login : ${ user.login } </p>
        <p>password : ${ user.password }</p>
        <p>salt : ${ user.salt }</p>
        <p>email : ${ user.email }</p>
        <p>role : ${ user.role }</p>
        <p>Description Site : <c:out value="${ site.description }" /></p>
        <p>Localisation Site : <c:out value="${ site.location }" /></p>
        <p>Code postale Site: <c:out value="${ site.postcode }" /></p>
        <c:if test="${ !empty site.sectors }">
            <c:forEach items="${ site.sectors }" var="sector" varStatus="status">
                <ul>
                    <li>Sector : <c:out value="${ sector.name }" /></li>
                    <li>Sector Description : <c:out value="${ sector.description }" /></li>
                    <c:if test="${ !empty sector.ways }">
                        <c:forEach items="${ sector.ways }" var="way" varStatus="status">
                            <li>Way : <c:out value="${ way.name }" /></li>
                            <ul>
                                <li>Way Height : <c:out value="${ way.height }" /></li>
                                <li>Way quotation : <c:out value="${ way.quotation }" /></li>
                                <li>Way number of points : <c:out value="${ way.pointsNb }" /></li>
                                <c:if test="${ !empty way.lengths }">
                                    <c:forEach items="${ way.lengths }" var="length" varStatus="status">
                                        <li>Length : <c:out value="${ length.name }" /></li>
                                        <ul>
                                            <li>Length description : <c:out value="${ length.description }" /></li>
                                            <c:if test="${ !empty length.points }">
                                                <c:forEach items="${ length.points }" var="point" varStatus="status">
                                                    <li>Point : <c:out value="${ point.name }" /></li>
                                                    <ul>
                                                        <li>Point description : <c:out value="${ point.description }" /></li>
                                                    </ul>
                                                </c:forEach>
                                            </c:if>
                                        </ul>
                                    </c:forEach>
                                </c:if>
                            </ul>
                        </c:forEach>
                    </c:if>
                </ul>
            </c:forEach>
        </c:if>
        <form method="POST">
            <input type="submit" value="Mettre à jour site"/>
        </form>
    </body>
</html>
