<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
  </head>
  <body>
  <div class="container" style="margin-top: 10px">

    <c:if test="${pageContext.request.userPrincipal eq null}">
      <form role="form" action="/login" method="post" style="margin-top: 50px">
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

    <table class="table table-border">
      <thead>
      <tr>
        <th>Топик</th>
        <th>Дата</th>
      <tbody>
    <c:forEach items="${topicList}" var="topic">
      <tr>
        <td><a href="/topic/${topic.topicId}">${topic.topicSubject}</a></td>
        <td><p>Дата создания: ${topic.createdDate}</p></td>
        <sec:authorize access="hasRole('ROLE_ADMINISTRATOR')">
        <td><a href="${pageContext.request.contextPath}/topic/edit/${topic.topicId}"><button>Редактировать</button></a></td>
        <td><a href="${pageContext.request.contextPath}/topic/delete/${topic.topicId}"><button>Редактировать</button></a></td>
        </sec:authorize>
      </tr>
    </c:forEach>
      </tbody>
      </tr>
      </thead>
    </table>
    <sec:authorize access="hasRole('ROLE_USER')">
      <div class="col-sm-2">
        <a href="/topic/add"><button class="btn btn-primary">Создать топик</button></a>
      </div>
    </sec:authorize>
    </div>
  </body>
</html>
