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
					<input type="hidden" name="command" value="update"/>
					<input type="hidden" name="id" value="${id}"/>
					<fieldset >
						<legend><fmt:message key="event.create.name.ukr" bundle="${bundle}"/></legend>
						<input name="nameUk" value="${nameUk}"/><br/>
						</br>
						<c:if test="${not empty errors.nameUk}">
		        				<c:out value="${errors.nameUk}" />
						</c:if>
					</fieldset><br/>
					
					<fieldset >
						<legend><fmt:message key="event.create.name.eng" bundle="${bundle}"/></legend>
						<input name="nameEn" value="${nameEn}"/><br/>
						</br>
						<c:if test="${not empty errors.nameEn}">
		        				<c:out value="${errors.nameEn}" />
						</c:if>
					</fieldset><br/>
					
					<fieldset >
						<legend><fmt:message key="event.create.place.ukr" bundle="${bundle}"/></legend>
						<input name="placeUk" value="${placeUk}"/><br/>
						</br>
						<c:if test="${not empty errors.placeUk}">
		        				<c:out value="${errors.placeUk}" />
						</c:if>
					</fieldset><br/>
					
					<fieldset >
						<legend><fmt:message key="event.create.place.eng" bundle="${bundle}"/></legend>
						<input name="placeEn" value="${placeEn}"/><br/>
						</br>
						<c:if test="${not empty errors.placeEn}">
		        				<c:out value="${errors.placeEn}" />
						</c:if>
					</fieldset><br/>
					
					<fieldset >
						<legend><fmt:message key="event.create.date" bundle="${bundle}"/></legend>
						<input type="date" name="date" value="${date}"/><br/>
						</br>
						<c:if test="${not empty errors.date}">
		        				<c:out value="${errors.date}" />
						</c:if>
					</fieldset><br/>
					
					<fieldset >
						<legend><fmt:message key="event.create.time" bundle="${bundle}"/></legend>
						<input type="time" name="time" value="${time}"/><br/>
						</br>
						<c:if test="${not empty errors.time}">
		        				<c:out value="${errors.time}" />
						</c:if>
					</fieldset><br/>
					<fmt:message key="event.update.button" bundle="${bundle}" var="updateButton"/>
					<input type="submit" value="${updateButton}">								
				</form>
			</td>
		</tr>
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</table>
</body>
</html>