<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="Email" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
Create a new account
<form:form method="POST" modelAttribute="user"><fieldset>
   Login:<form:input path="login" type="text" id="chatform" class="messages" name="login" style="width:50%;"/>
<font style="color:#f92672"><form:errors path="login" cssClass="error"/></font>

   Email:<form:input path="email" type="text" id="chatform" class="messages" name="email" style="width:50%;"/>
<font style="color:#f92672"><form:errors path="email" cssClass="error"/></font>

Password:<form:input path="password" type="password" id="chatform" class="messages" name="password" style="width:50%;"/>
<font style="color:#f92672"><form:errors path="password" cssClass="error"/></font>

		 <button type="submit" style="width: 15%">Sign up</button>
    </fieldset>
</form:form>
<a href = "/">back to home</a></pre>
        </div>
    </div>
</article>
</body>
</html>