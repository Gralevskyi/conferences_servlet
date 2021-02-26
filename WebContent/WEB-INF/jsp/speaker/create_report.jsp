<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Create_event" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>
	
<body>
	<table id="main-container">
		<%@ include file="/WEB-INF/jspf/header.jspf"%>
		<tr>
			<td class="content center">
				<form id="login_form" action="${pageContext.request.contextPath}/app" method="post">
					<input type="hidden" name="command" value="speaker_create"/>
					<fieldset >
						<legend><fmt:message key="report.topic.ukr" bundle="${bundle}"/></legend>
						<input name="topicUk" value="${topicUk}"/><br/>
						</br>
						<c:if test="${not empty errors.topicUk}">
		        			<fmt:message key="${errors.topicUk}" bundle="${bundle}" var="errors_topicUk"/>
		        			<c:out value="${errors_topicUk}" />
						</c:if>
					</fieldset><br/>
					
					<fieldset >
						<legend><fmt:message key="report.topic.eng" bundle="${bundle}"/></legend>
						<input name="topicEn" value="${topicEn}"/><br/>
						</br>
						<c:if test="${not empty errors.topicEn}">
		        			<fmt:message key="${errors.topicEn}" bundle="${bundle}" var="errors_topicEn"/>
		        			<c:out value="${errors_topicEn}" />
						</c:if>
					</fieldset><br/>
					
					<fmt:message key="report.create.button" bundle="${bundle}" var="createButton"/>
					<input type="submit" value="${createButton}">							
				</form>
			</td>
		</tr>
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</table>
</body>
</html>