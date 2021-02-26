<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="User" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
	<table id="main-container">
		<%@ include file="/WEB-INF/jspf/header.jspf"%>
		<tr>
			<td class="content">
				<h3><span><fmt:message key="header.conferences" bundle="${bundle}"/></span></h3>
				<table id="events_table" class="cust_table">
					<thead>
						<tr>
							<td><fmt:message key="table.name" bundle="${bundle}"/></td>
							<td><fmt:message key="table.place" bundle="${bundle}"/></td>
							<td><fmt:message key="table.date" bundle="${bundle}"/></td>
							<td><fmt:message key="table.time" bundle="${bundle}"/></td>
						</tr>
					</thead>
					<c:forEach var="event" items="${events}">
						<tr>
							<td><a href="app?command=user_event&id=${event.id}"><span>${event.getLocalName(sessionScope.locale)}</span></a></td>
							<td>${event.getLocalPlace(sessionScope.locale)}</td>
							<td>${event.date}</td>
							<td>${event.time}</td>
						</tr>
					</c:forEach>
				</table>	
			</td>
		</tr>
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</table>
</body>
</html>