<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<ul>
    <li>${site.name}</li>
    <li>${site.description}</li>
    <li>${site.location}</li>
    <li>${site.departement.region.name}</li>
    <li>${site.departement.name}</li>
</ul>