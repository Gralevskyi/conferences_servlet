<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Event" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
	<table id="main-container">
		<%@ include file="/WEB-INF/jspf/header.jspf"%>
		<tr>
			<td class="content">
				<h3><fmt:message key="user.event.details" bundle="${bundle}"/></h3>
				<table>
					<thead>
						<tr>
							<td><fmt:message key="table.name" bundle="${bundle}"/></td>
							<td><fmt:message key="table.place" bundle="${bundle}"/></td>
							<td><fmt:message key="table.date" bundle="${bundle}"/></td>
							<td><fmt:message key="table.time" bundle="${bundle}"/></td>
						</tr>
					</thead>
						<tr>
							<td>${event.getLocalName(sessionScope.locale)}</td>
							<td>${event.getLocalPlace(sessionScope.locale)}</td>
							<td>${event.date}</td>
							<td>${event.time}</td>
						</tr>
				</table>	
				
			</td>
		</tr>
		<tr>
			<td class="content">
				<table class="cust_table">
					<thead>
						<tr>
							<td><fmt:message key="table.topic" bundle="${bundle}"/></td>
							<td><fmt:message key="table.speaker" bundle="${bundle}"/></td>
						</tr>
					</thead>
					<c:forEach var="report" items="${event.reports}">
						<tr>
							<td>${report.getLocalTopic(sessionScope.locale)}</td>
							<td>${report.speaker.getLocalName(sessionScope.locale)}</td>
						</tr>
					</c:forEach>
				</table>	
				</br>				
				<c:if test="${userRole.name == 'user' and not subscribed}">
					<form action="${pageContext.request.contextPath}/app" method="post">	    				
	    				<input type="hidden" name="id" value="${event.id}"/>
	    				<input type="hidden" name="command" value="subscribe"/>
	    				<fmt:message key="user.event.subscribe" bundle="${bundle}" var="subscribeButton"/>
		    			<input type="submit" value="${subscribeButton}"/>
					</form>
				</c:if>

			</td>
		</tr>
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</table>
</body>
</html>