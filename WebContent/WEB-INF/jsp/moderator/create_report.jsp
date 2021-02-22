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
					<input type="hidden" name="command" value="moderator_create_report"/>
					<fieldset >
						<legend>Topic Ukraine:</legend>
						<input name="topicUk" value="${topicUk}"/><br/>
						</br>
						<c:if test="${not empty errors.topicUk}">
		        				<c:out value="${errors.topicUk}" />
						</c:if>
					</fieldset><br/>
					
					<fieldset >
						<legend>Topic English:</legend>
						<input name="topicEn" value="${topicEn}"/><br/>
						</br>
						<c:if test="${not empty errors.topicEn}">
		        				<c:out value="${errors.topicEn}" />
						</c:if>
					</fieldset><br/>
					
					<fieldset>
						<legend>Speakers: </legend>
						<select name="speaker_id" value="${speaker.id}">
							<c:forEach var="speaker" items="${speakers}">
								<option value="${speaker.id }"> ${speaker.getLocalName(sessionScope.locale)} </option>
							</c:forEach>
						</select>
					</fieldset></br>
					<input type="submit" value="Create report">								
				</form>
			</td>
		</tr>
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</table>
</body>
</html>