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
				<h3>Reports and speakers</h3>
				<table class="cust_table">
					<thead>
						<tr>
							<th> Id </th>
							<th> Topic </th>
							<th> Creator </th>
							<th> Is Accepted </th>
							<th> Speaker </th>
							<th> Set Accepted </th>
							<th> Set new Speaker </th>
							<th> Update </th>
						</tr>
					</thead>
					<c:forEach var="report" items="${reports}">
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
									<button onclick="updateReport(this)">Update</button>								
								</div>
							</td>						
						</tr>
					</c:forEach>
				</table>	
			</td>
		</tr>
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</table>
	<script src="js/custom.js"></script>
</body>
</html>