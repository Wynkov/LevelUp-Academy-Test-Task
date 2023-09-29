function checkInformation() {
	var api = Java.type("backend.BackendAPI()");
	alert("API loaded");

	var type = document.getElementById("card-type").value;
	var num = document.getElementById("card-number").value;
	var date = document.getElementById("card-date").value;
	var cvv = document.getElementById("card-cvv").value;

	// Change status text and color according to API return value
	if (api.checkInformation(type, num, date, cvv) == true) {
		document.getElementById("status").style.color = "green";
		document.getElementById("status").innerHTML = "Status: Success!";
	} else {
		document.getElementById("status").style.color = "red";
		document.getElementById("status").innerHTML = "Status: Failed!";
	}

	alert("API function called");
}