<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
    <sec:csrfMetaTags/>
    <link rel="stylesheet" href="../../resources/css/style.css">
    <link rel="stylesheet" href="../../resources/css/monokai.css">
    <script src="../../resources/js/client.js"></script>
    <title>Programmer's chat</title>
</head>
<body style="margin-bottom: 42px;" onload="init();">
<article class="container">
    <div id="messages" class="messages">
            <div class="message info">
                <span class="nick">
                    <a>*</a>
                </span>
                <pre class="text">Users online: <c:set var="total" value="${fn:length(online)}" /><c:forEach items="${online}" var="user" varStatus="counter"><c:out value="${user.login}"/><c:if test="${counter.count < total}">, </c:if></c:forEach></pre>
            </div>
        <c:forEach items="${messages}" var="message">
        <div class="message <c:if test="${message.status=='ME'}">me</c:if>">
            <span class="nick">
                <span class="trip"><fmt:formatDate value="${message.date}" pattern="HH:mm dd-MM-yyy" type="date"/></span>
                <a onclick="directMessage('<c:out value='${message.login}'/>')"><c:out value="${message.login}"/></a>
            </span>
            <pre class="text"><c:out value="${message.text}"/></pre>
        </div>
    </c:forEach>
    </div>
</article>
<footer id ="footer">
    <div class="container">
        <div id="chatform" class="messages">
            <input id="chatinput" type="text" autocomplete="off" autofocus="" style="height: 38px;" onkeydown="sendMessage();"/>
        </div>
    </div>
</footer>
<nav id="sidebar">
    <div id="sidebar-content">
        <p><h4>Actions</h4></p>
        <p><a href="/logout">Logout</a></p>
        <p><button id="clear-messages" onclick="deleteMessages();">Clear messages</button></p>
        <p><h4>Users online</h4></p>
        <ul id="users">
            <c:forEach items="${online}" var="user" varStatus="counter">
                <li id = "<c:out value="${user.login}"/>"><a onclick="directMessage('<c:out value="${user.login}"/>')"><c:out value="${user.login}"/></a></li>
            </c:forEach>
        </ul>
    </div>
</nav>
</body>
</html>