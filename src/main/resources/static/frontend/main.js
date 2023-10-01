function checkInformation() {
	const base = "http://localhost:8080/api/verify";

	const type = document.getElementById("card-type").value;
	const num = document.getElementById("card-number").value;
	const date = document.getElementById("card-date").value;
	const cvv = document.getElementById("card-cvv").value;

	// Verify card information with an API call
	const status = document.getElementById("status");
	const api = new XMLHttpRequest();

	api.open("GET", base + "?type=" + type + "&num=" + num + "&date=" + date + "&cvv=" + cvv);
	api.send();
	api.onload = function() {
		const response = JSON.parse(api.responseText);
		const errors = response.errors;

		// Change status text and color according to API return value
		if(!errors) {
			status.innerHTML = "Status: Success!";
			status.style.color = "green";
		} else {
			status.innerHTML = "Status: Failed! Error(s): " + response.activeErrors;
			status.style.color = "red";
		}
	}
}