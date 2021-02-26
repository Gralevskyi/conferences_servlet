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
				<h3><fmt:message key="moderator.reports.speakers" bundle="${bundle}"/></h3>
				<table class="cust_table">
					<thead>
						<tr>
							<th> <fmt:message key="moderator.report.id" bundle="${bundle}"/> </th>
							<th> <fmt:message key="table.topic" bundle="${bundle}"/> </th>
							<th> <fmt:message key="moderator.creator" bundle="${bundle}"/> </th>
							<th> <fmt:message key="moderator.accepted" bundle="${bundle}"/> </th>
							<th> <fmt:message key="table.speaker" bundle="${bundle}"/> </th>
							<th> <fmt:message key="moderator.set.accepted" bundle="${bundle}"/> </th>
							<th> <fmt:message key="moderator.set.speaker" bundle="${bundle}"/> </th>
							<th> <fmt:message key="event.update.button" bundle="${bundle}"/> </th>
						</tr>
					</thead>
					<c:forEach var="report" items="${reports}">
						<c:if test="${not report.suggested }">
							<tr>
								<td>${report.id}</td>
								<td>${report.getLocalTopic(sessionScope.locale)}</td>
								<td>${report.author.username}</td>
								<td>${report.accepted}</td>
								<td>${report.speaker.getLocalName(sessionScope.locale)}</td>
								<td>
									<select name="newAccepted">
										<option>${report.accepted}</option>
										<option>${not report.accepted}</option>
									</select>
								</td>
								<td>
									<select name="speaker_id" value="${speaker.id}">
										<c:forEach var="speaker" items="${speakers}">
											<option value="${speaker.id }"> ${speaker.getLocalName(sessionScope.locale)} </option>
										</c:forEach>
									</select>
								</td>
								<td>
									<div>
										<button onclick="updateReport(this)"><fmt:message key="moderator.event.update" bundle="${bundle}"/></button>								
									</div>
								</td>						
							</tr>
						</c:if>
					</c:forEach>
				</table>	
			</td>
		</tr>
		<tr>
			<td class="content">
				<h3><fmt:message key="moderator.suggested.speakers" bundle="${bundle}"/></h3>
				<table class="cust_table">
					<thead>
						<tr>
							<th> <fmt:message key="moderator.report.id" bundle="${bundle}"/> </th>
							<th> <fmt:message key="table.topic" bundle="${bundle}"/> </th>
							<th> <fmt:message key="moderator.creator" bundle="${bundle}"/> </th>
							<th> <fmt:message key="table.speaker" bundle="${bundle}"/> </th>
							<th> <fmt:message key="speaker.cabinet.accept" bundle="${bundle}"/> </th>
							<th> <fmt:message key="speaker.cabinet.refuse" bundle="${bundle}"/> </th>
						</tr>
					</thead>
					<c:forEach var="report" items="${reports}">
						<c:if test="${report.suggested }">
							<tr>
								<td>${report.id}</td>
								<td>${report.getLocalTopic(sessionScope.locale)}</td>
								<td>${report.author.username}</td>
								<td>${report.speaker.getLocalName(sessionScope.locale)}</td>
								<td>
									<form id="accept_form" action="${pageContext.request.contextPath}/app" method="post">
										<input type="hidden" name="command" value="suggested"/>
										<input type="hidden" name="act" value="accept"/>
										<input type="hidden" name="report_id" value="${report.id}"/>
										<input type="hidden" name="author_id" value="${report.author.id}"/>
										<input type="hidden" name="speaker_id" value="${report.speaker.id}"/>
										<fmt:message key="speaker.cabinet.accept" bundle="${bundle}" var="acceptButton"/>
										<input type="submit" value="${acceptButton}">
									</form>
								</td>
								<td>
									<form id="refuse_form" action="${pageContext.request.contextPath}/app" method="post">
										<input type="hidden" name="command" value="suggested"/>
										<input type="hidden" name="act" value="refuse"/>
										<input type="hidden" name="report_id" value="${report.id}"/>
										<input type="hidden" name="author_id" value="${report.author.id}"/>
										<input type="hidden" name="speaker_id" value="${report.speaker.id}"/>
										<fmt:message key="speaker.cabinet.refuse" bundle="${bundle}" var="refuseButton"/>
										<input type="submit" value="${refuseButton}">
									</form>
								</td>
							</tr>
						</c:if>
					</c:forEach>
				</table>	
			</td>
		</tr>
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</table>
	<script src="js/custom.js"></script>
</body>
</html>