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
				<h2><fmt:message key="event.add.reports" bundle="${bundle}"/> ${name} </h2>
				
			</td>
		</tr>
		<tr>
			<td class="content">
				<h3><fmt:message key="event.add.list" bundle="${bundle}"/></h3>
				<form action="${pageContext.request.contextPath}/app" method="post">				
					<input type="hidden" name="command" value="add_reports"/>
					<input type="hidden" name="id" value="${id}"/> 
      				<fmt:message key="moderator.event.addselected" bundle="${bundle}" var="addSelectedButton"/>
      				<ul>
      					<c:forEach var="report" items="${reports}">
      						<li>
     							<input type="checkbox" name="selected_reports" value="${report.id}">${report.getLocalTopic(sessionScope.locale)}<br>
      						</li>
      					</c:forEach>
      				</ul>
      				
	    			<input type="submit" value="${addSelectedButton}"/>
				</form>
			</td>
		</tr>
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</table>
</body>
</html>