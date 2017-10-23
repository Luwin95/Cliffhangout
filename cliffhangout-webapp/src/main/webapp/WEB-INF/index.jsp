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
            <ul>
                <li>Description Site : <c:out value="${ site.description }" /></li>
                <li>Localisation Site : <c:out value="${ site.location }" /></li>
                <li>Code postale Site: <c:out value="${ site.postcode }" /></li>
                <c:if test="${ !empty sectors }">
                    <c:forEach items="${ sectors }" var="sector" varStatus="status">
                        <li>Sector : <c:out value="${ sector.name }" /></li>
                            <ul>
                                <li>Sector Description : <c:out value="${ sector.description }" /></li>
                                    <c:forEach items="${ ways }" var="way" varStatus="status">
                                        <c:if test="${ way.sector.id == sector.id }">
                                            <li>Way : <c:out value="${ way.name }" /></li>
                                            <ul>
                                                <li>Way Height : <c:out value="${ way.height }" /></li>
                                                <li>Way quotation : <c:out value="${ way.quotation }" /></li>
                                                <li>Way number of points : <c:out value="${ way.pointsNb }" /></li>
                                                    <c:forEach items="${ lengths }" var="length" varStatus="status">
                                                        <c:if test="${ length.way.id == way.id }">
                                                            <li>Length description : <c:out value="${ length.description }" /></li>
                                                            <ul>
                                                                    <c:forEach items="${ points }" var="point" varStatus="status">
                                                                        <c:if test="${ point.length.id == length.id }">
                                                                            <li>Point : <c:out value="${ point.name }" /></li>
                                                                            <ul>
                                                                                <li>Point description : <c:out value="${ point.description }" /></li>
                                                                            </ul>
                                                                        </c:if>
                                                                    </c:forEach>
                                                            </ul>
                                                        </c:if>
                                                    </c:forEach>
                                            </ul>
                                        </c:if>
                                    </c:forEach>
                            </ul>
                    </c:forEach>
                </c:if>

        <form method="POST">
            <input type="submit" value="Mettre à jour site"/>
        </form>
    </body>
</html>
