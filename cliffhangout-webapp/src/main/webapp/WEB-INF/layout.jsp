<%--
  Created by IntelliJ IDEA.
  User: Ben
  Date: 24/10/2017
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Accueil</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
              rel="stylesheet"
              integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
              crossorigin="anonymous">
        <link href="${pageContext.request.contextPath}/resources/css/menu.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/css/footer.css" rel="stylesheet">
        <c:forEach items="${ stylesheets }" var="stylesheet" varStatus="status">
            <link href="${pageContext.request.contextPath}/resources/css/${ stylesheet }" rel="stylesheet">
        </c:forEach>
        <link href="https://fonts.googleapis.com/css?family=Merriweather" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Anton" rel="stylesheet">
    </head>
    <body>
        <%@ include file="menu.jsp" %>
        <div class="page">
            <jsp:include page="${page}" flush="true" />
            <div class="page-cache">
            </div>
            <%@ include file="footer.jsp" %>
        </div>

        <script src="https://code.jquery.com/jquery-3.2.1.min.js"
                integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
                crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
                integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
                crossorigin="anonymous"></script>
        <script src="${pageContext.request.contextPath}/resources/js/menu.js"></script>
        <c:forEach items="${ jsPages }" var="jsPage" varStatus="status">
            <script src="${pageContext.request.contextPath}/resources/js/${ jsPage }"></script>
        </c:forEach>
    </body>
</html>
