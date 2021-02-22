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
				<h3>Speaker Cabinet</h3>
				<h2>Accepted reports</h2>
				<table class="cust_table">
					<thead>
						<tr>
							<td>Topic</td>
						</tr>
					</thead>
					<c:forEach var="report" items="${reports}">
						<c:if test="${report.accepted}">
							<tr>
								<td>${report.getLocalTopic(sessionScope.locale)}</td>
							</tr>
						</c:if>
					</c:forEach>
				</table>	
			</td>
		</tr>
		<tr>
			<td class="content">
				<h2>Not accepted reports</h2>
				<table class="cust_table">
					<thead>
						<tr>
							<td>Topic</td>
							<td>Accept</td>
							<td>Refuse</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="report" items="${reports}">
							<c:if test="${not report.accepted}">
								<tr>
									<td>${report.getLocalTopic(sessionScope.locale)}</td>
									<td>
										<form action="${pageContext.request.contextPath}/app" method="post">	    				
						    				<input type="hidden" name="reportId" value="${report.id}"/>
						    				<input type="hidden" name="speakerId" value="${sessionScope.user.id}"/>
						    				<input type="hidden" name="act" value="accept"/>
						    				<input type="hidden" name="command" value="action"/>
						    				<fmt:message key="speaker.cabinet.accept" bundle="${bundle}" var="acceptButton"/>
							    			<input type="submit" value="${acceptButton}"/>
										</form>
									</td>
									<td>
										<form action="${pageContext.request.contextPath}/app" method="post">	    				
						    				<input type="hidden" name="reportId" value="${report.id}"/>
						    				<input type="hidden" name="speakerId" value="${sessionScope.user.id}"/>
						    				<input type="hidden" name="act" value="refuse"/>
						    				<input type="hidden" name="command" value="action"/>
						    				<fmt:message key="speaker.cabinet.refuse" bundle="${bundle}" var="refuseButton"/>
							    			<input type="submit" value="${refuseButton}"/>
										</form>
									</td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</td>
		</tr>
		
		
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</table>
</body>
</html>