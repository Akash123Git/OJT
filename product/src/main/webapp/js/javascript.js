$(document).ready(function() {

	$("#validate").click(function() {
		var user_Id = Number($("#user_Id").val());
		var password = $("#user_Password").val().trim();

		function addmsg(msg) {
			$("#msg").text(msg + ".");
		}
		function addUserDetailsMsg(userDetail) {
			$("#msg").html("<pre><strong>Id</strong>: " + userDetail.user_id + "\n<strong>full_name:</strong> " + userDetail.full_name +
				"\n <strong>Name:</strong> " + userDetail.user_name + "\n <strong>password:</strong> " + userDetail.password +
				"\n <strong>date_of_birth:</strong> " + userDetail.date_of_birth + "</pre>");
		}
		if (isNaN(user_Id)) {
			addmsg("user id must be Numbers")
		} else {
			if ((user_Id != "") && (password != "")) {
				var user = {
					user_id: user_Id,
					password: password
				}
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
					data: JSON.stringify(user),
					success: function(data) {
						addUserDetailsMsg(data);
					},
					error: function(jqXHR) {
						addmsg(jqXHR.responseText)
					}
				});
			} else {
				addmsg("enter proper details")

			}
		}


	});

});