function checkInformation() {
	const base = "http://localhost:8080/api/verify";

	const type = document.getElementById("card-type").value;
	const num = document.getElementById("card-number").value;
	const date = document.getElementById("card-date").value;
	const cvv = document.getElementById("card-cvv").value;

	// Verify card information with an API call
	const result = document.getElementById("status");
	const api = new XMLHttpRequest();

	api.open("GET", base + "?type=" + type + "&num=" + num + "&date=" + date + "&cvv=" + cvv);
	api.send();
	api.onload = function() {
		alert("Result: " + api.responseText);

		// Change status text and color according to API return value
		if (api.responseText == "true") {
			result.innerHTML = "Status: Success!";
			result.style.color = "green";
		} else {
			result.innerHTML = "Status: Failed!";
			result.style.color = "red";
		}
	}
}