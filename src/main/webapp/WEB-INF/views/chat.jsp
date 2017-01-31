<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
    <c:forEach items="${messages}" var="message">
        <div class="message">
            <span class="nick">
                <span class="trip"><c:out value="${message.date}"/></span>
                <a><c:out value="${message.login}"/></a>
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
</body>
</html>