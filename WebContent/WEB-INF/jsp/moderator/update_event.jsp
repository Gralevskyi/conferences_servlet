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
						<legend>Name Ukraine:</legend>
						<input name="nameUk" value="${nameUk}"/><br/>
						</br>
						<c:if test="${not empty errors.nameUk}">
		        				<c:out value="${errors.nameUk}" />
						</c:if>
					</fieldset><br/>
					
					<fieldset >
						<legend>Name English:</legend>
						<input name="nameEn" value="${nameEn}"/><br/>
						</br>
						<c:if test="${not empty errors.nameEn}">
		        				<c:out value="${errors.nameEn}" />
						</c:if>
					</fieldset><br/>
					
					<fieldset >
						<legend>Place Ukraine:</legend>
						<input name="placeUk" value="${placeUk}"/><br/>
						</br>
						<c:if test="${not empty errors.placeUk}">
		        				<c:out value="${errors.placeUk}" />
						</c:if>
					</fieldset><br/>
					
					<fieldset >
						<legend>Place English:</legend>
						<input name="placeEn" value="${placeEn}"/><br/>
						</br>
						<c:if test="${not empty errors.placeEn}">
		        				<c:out value="${errors.placeEn}" />
						</c:if>
					</fieldset><br/>
					
					<fieldset >
						<legend>Date:</legend>
						<input type="date" name="date" value="${date}"/><br/>
						</br>
						<c:if test="${not empty errors.date}">
		        				<c:out value="${errors.date}" />
						</c:if>
					</fieldset><br/>
					
					<fieldset >
						<legend>Time:</legend>
						<input type="time" name="time" value="${time}"/><br/>
						</br>
						<c:if test="${not empty errors.time}">
		        				<c:out value="${errors.time}" />
						</c:if>
					</fieldset><br/>
					
					<input type="submit" value="Update event">								
				</form>
			</td>
		</tr>
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</table>
</body>
</html>