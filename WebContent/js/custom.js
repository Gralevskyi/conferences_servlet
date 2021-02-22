function updateReport(button) {
	var row = button.closest('td').closest('tr');
	var report = row.cells[0].textContent;
	var id = row.cells[0].innerText;
	var newAccepted = row.cells[5].children[0].value;
	var newSpeakerId = row.cells[6].children[0].value;
	if(newSpeakerId == 1 && newAccepted == 'true'){
		alert("You can not save accepted true on report without speaker");
	} else {
		container = {
				report: report, 
				id: id,
				newSpeakerId: newSpeakerId,
				newAccepted: newAccepted
		}
		
		sendUpdateRequest(container);
	}	
}

function sendUpdateRequest(contrainer){
	
	var xhttp = new XMLHttpRequest();
	xhttp.onload = function() {
		if (this.readyState == 4 && this.status == 200) {
			document.location.reload();
		} else {
			alert(`network error`);
		}
	  };		
	xhttp.onerror = function() { 
		alert(`network error`);
	};
	report = container.id,
	speakerId= container.newSpeakerId, 
	accepted=container.newAccepted == 'true'
	url = "http://localhost:8080/conferences-servlet/app?command=moderator_speakers&reportId="+report+"&speakerId="+speakerId+"&accepted="+accepted;
	xhttp.open("POST", url, true);
	console.log(report);
	xhttp.setRequestHeader("Content-type", "application/json");
	xhttp.send();
}
