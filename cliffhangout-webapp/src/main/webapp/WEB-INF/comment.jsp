<%--
  Created by IntelliJ IDEA.
  User: Ben
  Date: 08/11/2017
  Time: 08:48
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<s:iterator value="children">
    <div class="row comment">
        <div class="col-xs-1">
            <s:if test="%{author.image !=null}">
                <div class="author-profile-img">
                    <img src="/resources/images/user/<s:property value="author.image.path"/>"/>
                </div>
            </s:if>
            <s:else>
                <div class="author-profile-img">
                    <img src="/resources/images/user/icone-grimpeur.png"/>
                </div>
            </s:else>
        </div>
        <div class="col-xs-8 comment-content">
            <s:property value="author.login"/> a dit : <s:property value="content"/>
        </div>
        <div class="col-xs-3 comment-content">
            <div class="row">
                <s:if test="#session.sessionUser!= null">
                    <s:if test="%{#cpt<2}">
                        <button class="col-xs-5 btn-cliffhangout btn-cliffhangout-lg answer" id="<s:property value="id"/>">Répondre</button>
                        <s:form action="site" >
                            <s:hidden name="idSite" value="%{idSite}"/>
                            <input name="commentToReport" type="hidden" value="<s:property value="id"/>"/>
                            <button type="submit" class="col-xs-offset-2 col-xs-5 btn btn-danger report"><span class="glyphicon glyphicon-alert"></span> Signaler</button>
                        </s:form>
                    </s:if>
                    <s:else>
                        <button class="col-xs-offset-7 col-xs-5 btn btn-danger report"><span class="glyphicon glyphicon-alert"></span> Signaler</button>
                    </s:else>

                </s:if>
                <s:else>
                    <a href="<s:url action="login"/>">Se connecter</a> ou <a href="#">S'inscrire</a>
                </s:else>
            </div>
        </div>
    </div>
    <s:if test="%{children !=null && children.size()!=0}">
        <div style="margin-left:20px;">
            <s:set var="cpt" value="%{#cpt+1}"/>
            <s:include value="comment.jsp"/>
        </div>
    </s:if>
</s:iterator>
