<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <title>404 NotFound</title>
    <link href="/resources/css/404.css" rel="stylesheet">
</head>
<body>
    <div class="content">
        <img src="/resources/images/404.gif"/>
        <h1>Oups quelque chose s'est mal passé!</h1>
        <p>Retourné à <a href="<s:url action='home'/>">l'accueil</a> </p>
    </div>
</body>
</html>
