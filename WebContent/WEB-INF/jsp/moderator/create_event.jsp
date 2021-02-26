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
					<input type="hidden" name="command" value="create_event"/>
					<fieldset >
						<legend><fmt:message key="event.create.name.ukr" bundle="${bundle}"/></legend>
						<input name="nameUk" value="${nameUk}"/><br/>
						</br>
						<c:if test="${not empty errors.nameUk}">
							<fmt:message key="${errors.nameUk}" bundle="${bundle}" var="errors_nameUk"/>
		        			<c:out value="${errors_nameUk}" />
						</c:if>
					</fieldset><br/>
					
					<fieldset >
						<legend><fmt:message key="event.create.name.eng" bundle="${bundle}"/></legend>
						<input name="nameEn" value="${nameEn}"/><br/>
						</br>
						<c:if test="${not empty errors.nameEn}">
		        			<fmt:message key="${errors.nameEn}" bundle="${bundle}" var="errors_nameEn"/>
		        			<c:out value="${errors_nameEn}" />
						</c:if>
					</fieldset><br/>
					
					<fieldset >
						<legend><fmt:message key="event.create.place.ukr" bundle="${bundle}"/></legend>
						<input name="placeUk" value="${placeUk}"/><br/>
						</br>
						<c:if test="${not empty errors.placeUk}">
		        			<fmt:message key="${errors.placeUk}" bundle="${bundle}" var="errors_placeUk"/>
		        			<c:out value="${errors_placeUk}" />
						</c:if>
					</fieldset><br/>
					
					<fieldset >
						<legend><fmt:message key="event.create.place.eng" bundle="${bundle}"/></legend>
						<input name="placeEn" value="${placeEn}"/><br/>
						</br>
						<c:if test="${not empty errors.placeEn}">
		        			<fmt:message key="${errors.placeEn}" bundle="${bundle}" var="errors_placeEn"/>
		        			<c:out value="${errors_placeEn}" />
						</c:if>
					</fieldset><br/>
					
					<fieldset >
						<legend><fmt:message key="event.create.date" bundle="${bundle}"/></legend>
						<input type="date" name="date" value="${date}"/><br/>
						</br>
						<c:if test="${not empty errors.date}">
		        			<fmt:message key="${errors.date}" bundle="${bundle}" var="errors_date"/>
		        			<c:out value="${errors_date}" />
						</c:if>
					</fieldset><br/>
					
					<fieldset >
						<legend><fmt:message key="event.create.time" bundle="${bundle}"/></legend>
						<input type="time" name="time" value="${time}"/><br/>
						</br>
						<c:if test="${not empty errors.time}">
		        			<fmt:message key="${errors.time}" bundle="${bundle}" var="errors_time"/>
		        			<c:out value="${errors_time}" />
						</c:if>
					</fieldset><br/>
					
					<fmt:message key="event.create.button" bundle="${bundle}" var="createButton"/>
					<input type="submit" value="${createButton}">								
				</form>
			</td>
		</tr>
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</table>
</body>
</html>