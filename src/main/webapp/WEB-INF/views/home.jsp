<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="../../resources/css/style.css">
	<link rel="stylesheet" href="../../resources/css/monokai.css">
	<title>Programmer's chat</title>
</head>
<body style="margin-bottom: 42px;">
	<article class="container">
		<div id="messages" class="messages">
			<div class="message">
				<pre class="text">
				<h2>			 Programmer's chat</h2>
Welcome <font style="color: #ae81ff;"><c:out value="${login}"/></font> to programmer's chat, a minimal, distraction-free chat application.

<form method="POST" action="/chat">
Create chanel:<input type="text" id="chatform" class="messages" name="newChanel" style="width:50%;">     <button type="submit" style="width: 15%">Create</button>
</form>
Chanel list:
				</pre>
			</div>
		</div>
	</article>
</body>
</html>