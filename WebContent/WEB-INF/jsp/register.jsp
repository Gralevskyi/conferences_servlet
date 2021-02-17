<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Login" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>
	
<body>
	<table id="main-container">
		<%@ include file="/WEB-INF/jspf/header.jspf"%>
		<tr>
		<td class="content center">
		<form id="login_form" action="${pageContext.request.contextPath}/app/register" method="post">
			<fieldset >
				<legend>Email:</legend>
				<input name="email" value="${email}"/><br/>
				</br>
				<c:if test="${not empty errors.username}">
        				<c:out value="${errors.username}" />
				</c:if>
			</fieldset><br/>
			<fieldset>
				<legend>Password</legend>
				<input type="password" name="password"/>
				</br>
				<c:if test="${not empty errors.password}">
        				<c:out value="${errors.password}" />
				</c:if>
			</fieldset><br/>
			<fieldset>
				<legend>Password confirmation</legend>
				<input type="password" name="passwordConfirm"/>
			</fieldset><br/>
			
			<input type="submit" value="Register">								
		</form>
				
			<%-- CONTENT --%>

			</td>
		</tr>

		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
		
	</table>
</body>
</html>