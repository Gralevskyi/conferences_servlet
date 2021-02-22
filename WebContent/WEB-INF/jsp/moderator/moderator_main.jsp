<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Moderator" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
	<table id="main-container">
		<%@ include file="/WEB-INF/jspf/header.jspf"%>
		<tr>
			<td class="content">
				<h3>Moderator main page</h3>
				<table id="events_table" class="cust_table">
					<thead>
						<tr>
							<td>Name</td>
							<td>Place</td>
							<td>Date</td>
							<td>Time</td>
							<td>Subscribers</td>
							<td>Visitors</td>
						</tr>
					</thead>
					<c:forEach var="event" items="${events}">
						<tr>
							<td><a href="app?command=moderator_event&id=${event.id}"><span>${event.getLocalName(sessionScope.locale)}</span></a></td>
							<td>${event.getLocalPlace(sessionScope.locale)}</td>
							<td>${event.date}</td>
							<td>${event.time}</td>
							<td>${event.subscribersNumber }</td>
							<td>${event.visitors }</td>
						</tr>
					</c:forEach>
				</table>	
			</td>
		</tr>
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</table>
</body>
</html>