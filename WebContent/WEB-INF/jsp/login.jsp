<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>


<html>

<c:set var="title" value="Login" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
	<table id="main-container">
		<c:set var = "command" scope = "session" value = "login"/>

		<%@ include file="/WEB-INF/jspf/header.jspf"%>
		<tr>			

			
			<td class="content center">
				<c:if test="${not empty errors}">
				<c:set var="errorMessage">
    				<fmt:message key="login.error" bundle="${bundle}"/>
				</c:set>
        				<c:out value="${errorMessage}"/>
				</c:if>
				<form id="login_form" action="app" method="post">
					<input type="hidden" name="lang" value="${empty param.lang ? 'en' : param.lang}"/>
					<input type="hidden" name="command" value="${sessionScope.command}"/>

					<fieldset>					
						<legend><fmt:message key="login.login" bundle="${bundle}"/></legend>
						<input name="login"/><br/>
					</fieldset><br/>
					<fieldset>
						<legend><fmt:message key="login.password" bundle="${bundle}"/></legend>
						<input type="password" name="password"/>
					</fieldset><br/>
					<fmt:message key="login.button" bundle="${bundle}" var="loginButton"/>
	    			<input type="submit" value="${loginButton}"/>								
				</form>
				</br>
				<form action="${pageContext.request.contextPath}/app" method="get">
    				<fmt:message key="login.register" bundle="${bundle}" var="registerButton"/>
    				<input type="hidden" name="command" value="register"/>
	    			<input type="submit" value="${registerButton}"/>
				</form>

			</td>
		</tr>
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</table>
</body>
</html>