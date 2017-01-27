<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<spring:url value="/resources/css/style.css" var="mainCss"/>
	<spring:url value="/resources/css/monokai.css" var="monokaiCss"/>
	<link rel="stylesheet" href="${mainCss}"/>
	<link rel="stylesheet" href="${monokaiCss}" />
	<title>Programmer's chat</title>
</head>
<body style="margin-bottom: 42px;">
	<article class="container">
		<div id="messages" class="messages">
			<div class="message">
				<pre class="text">
				<h2>			 Programmer's chat</h2>
Join in chat:
<form method="POST" action="/signin">
   Email:<input type="text" id="chatform" class="messages" name="email" style="width:50%;">

Password:<input type="password" id="chatform" class="messages" name="password" style="width:50%;">

		 <button type="submit" style="width: 15%">Sign in</button>

		 <a href="/signup">sign up</a>		
</form>
				</pre>
			</div>
			<div class="message info">
			<span class="nick">*</span>
			<pre class="text"><c:set var="total" value="${fn:length(online)}" />
User online: <c:forEach items="${online}" var="user" varStatus="counter"><c:out value="${user.login}"/><c:if test="${counter.count < total}">, </c:if></c:forEach>
			</pre>
			</div>
		</div>
	</article>
</body>
</html>