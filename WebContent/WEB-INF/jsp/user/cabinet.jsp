<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Event" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
	<table id="main-container">
		<%@ include file="/WEB-INF/jspf/header.jspf"%>
		<c:forEach var="event" items="${events}">
			<tr>
			<td class="content">
				<h3>${event.getLocalName(sessionScope.locale)} event details</h3>
				<table>
					<thead>
						<tr>
							<td>Name</td>
							<td>Place</td>
							<td>Date</td>
							<td>Time</td>
							<c:if test="${not event.visited}">
								<td>visited</td>
							</c:if>
						</tr>
					</thead>
						<tr>
							<td>${event.getLocalName(sessionScope.locale)}</td>
							<td>${event.getLocalPlace(sessionScope.locale)}</td>
							<td>${event.date}</td>
							<td>${event.time}</td>
								<c:if test="${not event.visited}">
									<td>
										<form action="${pageContext.request.contextPath}/app" method="post">	    				
						    				<input type="hidden" name="id" value="${event.id}"/>
						    				<input type="hidden" name="command" value="visit"/>
						    				<fmt:message key="user.event.visit" bundle="${bundle}" var="visitButton"/>
							    			<input type="submit" value="${visitButton}"/>
										</form>
									</td>
								</c:if>
							
						</tr>
				</table>	
				
			</td>
		</tr>
		<tr>
			<td class="content">
				<table class="cust_table">
					<thead>
						<tr>
							<td>Topic</td>
							<td>Speaker</td>
						</tr>
					</thead>
					<c:forEach var="report" items="${event.reports}">
						<tr>
							<td>${report.getLocalTopic(sessionScope.locale)}</td>
							<td>${report.speaker.getLocalName(sessionScope.locale)}</td>
						</tr>
					</c:forEach>
				</table>	
			</td>
		</tr>
		</br>
		</c:forEach>
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</table>
</body>
</html>