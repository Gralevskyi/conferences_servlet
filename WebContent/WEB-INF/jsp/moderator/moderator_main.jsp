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
				<ul id="moderator_buttons">
					<li>
						<form action="${pageContext.request.contextPath}/app" method="get">	
							<input type="hidden" name="command" value="moderator"/>
							<input type="hidden" name="order_by" value="e.date"/>   				
		      				<fmt:message key="moderator.main.order.date" bundle="${bundle}" var="orderByDateButton"/>
			    			<input type="submit" value="${orderByDateButton}"/>
						</form>
					</li>
					<li>
						<form action="${pageContext.request.contextPath}/app" method="get">	
							<input type="hidden" name="command" value="moderator"/>
							<input type="hidden" name="order_by" value="reports"/>   				
		      				<fmt:message key="moderator.main.order.reports" bundle="${bundle}" var="orderByReportsButton"/>
			    			<input type="submit" value="${orderByReportsButton}"/>
						</form>
					</li>
					<li>
						<form action="${pageContext.request.contextPath}/app" method="get">	
							<input type="hidden" name="command" value="moderator"/>
							<input type="hidden" name="order_by" value="subscribers"/>   				
		      				<fmt:message key="moderator.main.order.subscr" bundle="${bundle}" var="orderBySubscrButton"/>
			    			<input type="submit" value="${orderBySubscrButton}"/>
						</form>
					</li>
					<li>
						<form action="${pageContext.request.contextPath}/app" method="get">	
							<input type="hidden" name="command" value="moderator"/>
							<input type="hidden" name="order_by" value="visitors"/>   				
		      				<fmt:message key="moderator.main.order.visitors" bundle="${bundle}" var="orderByVisitorsButton"/>
			    			<input type="submit" value="${orderByVisitorsButton}"/>
						</form>
					</li>
				</ul>
			</td>
		</tr>
		<tr>
			<td class="content">
				<form id="login_form" action="${pageContext.request.contextPath}/app" method="post">
					<input type="hidden" name="command" value="moderator"/>
					<input type="hidden" name="act" value="where"/>
					<input type="hidden" name="orderBy" value="${orderBy}"/>
					<ul id="moderator_buttons">
						<li>
							<input type="date" name="date" value="${date}" min="2015-01-01" max="2049-12-31"/><br/>
						</li>
						<li>
							<select name="equality">
								<option value=">"><fmt:message key="moderator.option.newer" bundle="${bundle}"/></option>
								<option value="<="><fmt:message key="moderator.option.older" bundle="${bundle}"/></option>
							</select>
						</li>
						
						<li>
							<fmt:message key="moderator.main.where.date" bundle="${bundle}" var="orderWhereDateButton"/>
			    			<input type="submit" value="${orderWhereDateButton}"/>
						</li>						
					</ul>
				</form>
			</td>
		</tr>
		<tr>
			<td class="content">
				<table id="events_table" class="cust_table">
					<thead>
						<tr>
							<td><fmt:message key="table.name" bundle="${bundle}"/></td>
							<td><fmt:message key="table.place" bundle="${bundle}"/></td>
							<td><fmt:message key="table.date" bundle="${bundle}"/></td>
							<td><fmt:message key="table.time" bundle="${bundle}"/></td>
							<td><fmt:message key="table.reports" bundle="${bundle}"/></td>
							<td><fmt:message key="table.subscribers" bundle="${bundle}"/></td>
							<td><fmt:message key="table.visitors" bundle="${bundle}"/></td>
						</tr>
					</thead>
					<c:forEach var="event" items="${events}">
						<tr>
							<td><a href="app?command=moderator_event&id=${event.id}"><span>${event.getLocalName(sessionScope.locale)}</span></a></td>
							<td>${event.getLocalPlace(sessionScope.locale)}</td>
							<td>${event.date}</td>
							<td>${event.time}</td>
							<td>${event.reportsNumber }</td>
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