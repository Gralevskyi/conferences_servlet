<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Register" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>
	
<body>
	<table id="main-container">
		<%@ include file="/WEB-INF/jspf/header.jspf"%>
		<tr>
		<td class="content center">
		<form id="login_form" action="${pageContext.request.contextPath}/app" method="post">
			<input type="hidden" name="command" value="register"/>
			<fieldset >
				<legend><fmt:message key="register.email" bundle="${bundle}"/></legend>
				<input name="email" value="${email}"/><br/>
				</br>
				<c:if test="${not empty errors.username}">
						<fmt:message key="${errors.username}" bundle="${bundle}" var="errors_username"/>
        				<c:out value="${errors_username}" />
				</c:if>
			</fieldset><br/>
			<fieldset>
				<legend><fmt:message key="login.password" bundle="${bundle}"/></legend>
				<input type="password" name="password"/>
				</br>
				<c:if test="${not empty errors.password}">
        				<fmt:message key="${errors.password}" bundle="${bundle}" var="errors_password"/>
        				<c:out value="${errors_password}" />
				</c:if>
			</fieldset><br/>
			<fieldset>
				<legend><fmt:message key="register.passwordconfirm" bundle="${bundle}"/></legend>
				<input type="password" name="passwordConfirm"/>
			</fieldset><br/>
			<fmt:message key="register.registerbut" bundle="${bundle}" var="registerButton"/>
			<input type="submit" value="${registerButton}"/>								
		</form>
				
			<%-- CONTENT --%>

			</td>
		</tr>

		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
		
	</table>
</body>
</html>