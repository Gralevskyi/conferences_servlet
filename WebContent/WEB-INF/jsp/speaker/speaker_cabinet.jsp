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
				<h3><fmt:message key="speaker.accepted.reports" bundle="${bundle}"/></h3>
				<table class="cust_table">
					<thead>
						<tr>
							<td><fmt:message key="table.topic" bundle="${bundle}"/></td>
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
				<h3><fmt:message key="speaker.suggested.reports" bundle="${bundle}"/></h3>
				<table class="cust_table">
					<thead>
						<tr>
							<td><fmt:message key="table.topic" bundle="${bundle}"/></td>
						</tr>
					</thead>
					<c:forEach var="report" items="${reports}">
						<c:if test="${report.suggested}">
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
				<h3><fmt:message key="speaker.not.accepted.reports" bundle="${bundle}"/></h3>
				<table class="cust_table">
					<thead>
						<tr>
							<td><fmt:message key="table.topic" bundle="${bundle}"/></td>
							<td><fmt:message key="speaker.cabinet.accept" bundle="${bundle}"/></td>
							<td><fmt:message key="speaker.cabinet.refuse" bundle="${bundle}"/></td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="report" items="${reports}">
							<c:if test="${not report.accepted and not report.suggested}">
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