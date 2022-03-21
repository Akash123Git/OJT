$(document).ready(function() {

	$("#validate").click(function() {
		var user_Id = Number($("#user_Id").val());
		var password = $("#user_Password").val();
		var user = {
			user_id: user_Id,
			password: password
		}
		function addmsg(msg){
			$("#msg").text(msg+".");
		}
		function addUserDetailsMsg(userDetail){
			$("#msg").text("Id: "+userDetail.user_id+" Name: "+userDetail.user_name+" date-of-birth: "+userDetail.date_of_birth);
		}
		
		if ((user_Id != "") && (password != "")) {
			/*$.ajax({
				type: 'GET',
				url: 'webapi/working/getSingleUserDetails',
				success: function(orders, status) {
					$.each(orders, function(i, order) {
						console.log(order);
					});
				}
			});*/
			$.ajax({
				type: 'POST',
				url: 'webapi/working/postrequest',
				contentType: 'application/json',
				dataType: 'json',
				data:JSON.stringify(user),
				success: function(data) {
					addUserDetailsMsg(data);
				},
				error: function(jqXHR,status,error) {
					addmsg(jqXHR.responseText)
				}
			});
		} else {
			addmsg("enter proper details")

		}
	});

});