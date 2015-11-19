<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<%@include file="include/header.jsp"%>

<style>
table tr.tbheader {
	color: white;
	background: #757575;
	font-family: "Times New Roman", Georgia, Serif;
}

span.searchresult {
	color: #757575;
	font-style: italic;
	font-family: "Times New Roman", Georgia, Serif;
}

.user-profile {
	border-radius: 50%;
	width: 5%;
}
</style>
<title>List User</title>
</head>
<body>
	<%@include file="include/topmenu.jsp"%>



	<div id="wrapper" style="margin-top: 54px;">
		<%@include file="include/leftbar.jsp"%>
		<div style="height: 20px;" class="col-sm-12"></div>

		<div id="content" class="col-sm-12">
			<fieldset>
				<legend>
					<span style="font-weight: bold; font-size: 20px;">User</span><span
						style="font-size: 14px;"> List information</span>
				</legend>
				<div class="col-sm-12">
					<div class="col-sm-9 ">
						<div class="row">
							<div class="row">
								<select id="rowset"
									style="height: 32px; border: 1px solid #E0E0E0; border-radius: 5px;">
									<option value="10">10</option>
									<option value="20">20</option>
									<option value="50">50</option>
									<option value="100">100</option>
								</select> <span class="searchresult " style="">Result : <span
									id="rowshow"></span>/<span id="recordresult"></span></span>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="row">
							<div class="col-sm-3">

								<input type="search" onkeyup="displayPage(1); getArticleRow();"
									id="searcharticle" class="form-control" placeholder="Searching"
									style="margin: 0 0 0 15px; height: 32px; border: 1px solid #E0E0E0; border-radius: 5px;" />

							</div>
						</div>
					</div>
				</div>
				<div id="listuserresult"></div>
				<div id="content"></div>
				<div id="demo4_top" class="demo4_top"></div>
			</fieldset>

		</div>


	</div>
	<%@include file="include/footer.jsp"%>
	<script>
		var dbrow = 0;
		var numofpage = 0;
		var rowshow = 0;
		listUser();
		getUserRow();
		//list user show as table
		function listUserTb(data) {
			if (data.RESPONSE_DATA != null) {
				jsonUser = data.RESPONSE_DATA;
				if (jsonUser.length != 0) {
					var tb = "<table class='table'>";
					tb += "<tr class='tbheader'>";
					tb += "<th>ID</th>";
					tb += "<th>Name</th>";
					tb += "<th>Password</th>";
					tb += "<th>Email</th>";
					tb += "<th>Gender</th>";
					tb += "<th>Type</th>";
					tb += "<th>Status</th>";
					tb += "<th>Image</th>";
					tb += "</tr>";
					$
							.each(
									jsonUser,
									function(i, b) {
										tb += "<tr>";
										tb += "<td>" + b.uid + "</td>";
										tb += "<td>" + b.uname + "</td>";
										tb += "<td>" + b.upassword + "</td>";
										tb += "<td>" + b.uemail + "</td>";
										tb += "<td>" + b.ugender + "</td>";
										tb += "<td>" + b.utype + "</td>";
										tb += "<td>" + b.ustatus + "</td>";
										tb += "<td><img class='user-profile' src='${pageContext.request.contextPath}/upload/"+ b.uimage +"'/></td>";
										tb += "</tr>";
									});
					tb += "</table>";
					return tb;
				}
			}

		}
		//list user
		function listUser() {

			var data = {
				"page" : "1",
				"key" : ""
			}

			$.ajax({
				url : "${pageContext.request.contextPath}/user/getalluser",
				type : 'POST',
				contentType : 'application/json', // type of data
				data : JSON.stringify(data),
				dataType : 'JSON',
				success : function(data) {
					$("#listuserresult").html(listUserTb(data));
				},
				error : function(data) {
					alert("Unsuccess" + data);
					console.log("ERROR..." + data);
				}
			});
		}
		function getUserRow() {

			$.ajax({
				method : "POST",
				url : "${pageContext.request.contextPath}/user/usercount",
				data : {
					acontent : "s"
				},
				success : function(data) {

					dbrow = data.RESPONSE_DATA;

					var npage;
					var nps = dbrow / 10;

					if (!(dbrow % rowshow == 0)) {
						npage = Math.floor(nps);
						npage += 1;
					} else {
						npage = nps;
					}
					numofpage = npage;

					$("#recordresult").html(dbrow);

					$("#rowshow").html($("#rowset").val());
					loadPagination();
				}
			});
		}
		function loadPagination() {

			$('.demo4_top').bootpag({
				total : numofpage,

				maxVisible : 5,
				leaps : true,
				firstLastUse : true,
				first : '&#8592;',
				last : '&#8594;',
				wrapClass : 'pagination',
				activeClass : 'active',
				disabledClass : 'disabled',
				nextClass : 'next',
				prevClass : 'prev',
				lastClass : 'last',
				firstClass : 'first'
			}).on("page", function(event, num) {

				displayPage(num);
			});
		}
		function displayPage(mypage) {

			$.ajax({
				method : "POST",
				url : "displayarticlepage",
				data : {
					acontent : $("#searcharticle").val(),
					page : mypage

				},
				success : function(data) {

					$("#listarticleresult").html(listTb(data.RESPONSE_DATA));

				}
			});
		}
	</script>
</body>
</html>