<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
</head>
<body>
<spring:url value="${pageContext.request.contextPath}/topic/add" var="add"/>
<spring:url value="${pageContext.request.contextPath}/topic/edit" var="edit"/>

<div class="container-fluid" style="margin-top:40px">
    <sf:form modelAttribute="topic" action="${topic.topicId eq null ? add : edit}" method="post" class="form-horizontal" role="form">
        <sf:input path="topicId" type="hidden"/>
        <div class="form-group">
            <label class="control-label col-sm-4">Тема:</label>
            <sf:errors cssClass="control-label" path="subject"/>
            <div class="col-xs-3">
                <sf:input path="subject" class="form-control" placeholder="Обязательное поле"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-4">Сообщение:</label>
            <sf:errors cssClass="control-label" path="message"/>
            <div class="col-xs-3">
                <sf:input path="message" class="form-control" placeholder="Обязательное поле"/>
            </div>
        </div>
        <div class="form-group" style="margin-left: 420px">
            <div class="col-sm-offset-2 col-sm-12">
                <a href="/" class="btn btn-danger">Отмена</a>
                <button type="submit" class="btn btn-primary">Отправить</button>
            </div>
        </div>
    </sf:form>
</div>
</body>
</html>
