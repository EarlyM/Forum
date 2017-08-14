<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
</head>
<body>
<div class="container" style="margin-top: 10px">

    <c:if test="${pageContext.request.userPrincipal eq null}">
        <form role="form" action="/login" method="post" style="margin-top: 20px">
            <div class="form-group">
                <div class="col-sm-4">
                    <input class="form-control" type="text" placeholder="Username" name="j_username">
                </div>
                <div class="col-sm-4">
                    <input class="form-control" type="text" placeholder="Password" name="j_password">
                </div>
                <div class="col-sm-2">
                    <input value="Войти" type="submit" class="btn btn-primary">
                </div>
            </div>
        </form>
    </c:if>
    <c:if test="${pageContext.request.userPrincipal ne null}">
        <span style="float: right"><c:out value="${pageContext.request.userPrincipal.name}"/> <a href="/logout" class="a">Выйти</a></span>
    </c:if>
    <br/>
    <div style="padding-top: 15px">
    <h3>${topic.topicSubject}</h3>
    <p>${topic.text}</p>
    </div>

    <table class="table table-border">
        <thead>
        <tr>
            <th>Пользователь</th>
            <th>Сообщение</th>
            <th>Дата</th>
        <tbody>
        <c:forEach items="${topic.messageList}" var="message">
            <c:choose>
                <c:when test="${message.massageId == message.parentId}">
                    <tr>
                        <td>${message.user}</td>
                        <td>${message.message}</td>
                        <td>${message.createData}</td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <tr style="margin-left: 50px">
                        <td><b style="margin-left: 15px">${message.user}</b></td>
                        <td>${message.message}</td>
                        <td>${message.createData}</td>
                    </tr>
                </c:otherwise>
            </c:choose>
            <sec:authorize access="hasRole('ROLE_ADMINISTRATOR')">
            <td><a href="/topic/${topic.topicId}/edit/${message.massageId}"><button>Редактировать</button></a></td>
            <td><a href="/topic/${topic.topicId}/delete/${message.massageId}"><button>Удалить</button></a></td>
            </sec:authorize>
        </c:forEach>
        </tbody>
        </tr>
        </thead>
    </table>


    <sec:authorize access="hasRole('ROLE_USER')">
<sf:form modelAttribute="message" action="${pageContext.request.contextPath}/topic/${topic.topicId}/add" method="post">
    <sf:input id="parent" path="parent" type="hidden"/>
    <sf:input path="text"/>
    <input type="submit" value="Отправить">
</sf:form>
        </sec:authorize>
</div>
</body>
</html>
