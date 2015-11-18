<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrape/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/simple-sidebar.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/fixedbar.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/fontawesome/css/font-awesome.min.css">


<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/bootstrape/js/bootstrap.min.js"></script>

<title>Insert title here</title>
</head>
<body>
	<%@include file="../fixedbar/topmenu.jsp"%>
	


<div id="wrapper" style="margin-top: 54px;">
<%@include file="../fixedbar/leftbar.jsp"%>
	
	<h1> WELCOME CATEGORY PAGE! </h1>
</div>
 <script>
		//insertArticle();
		//deleteArticle();
		//updateArticle();
		
		function updateArticle(){
			json={
					id : 5,
					title : "Hellosssssssssssssasdsfsfsaaaaaaaaaaaaasss",
	            	description : "This is first",
	            	image : "Tiger.jpg",
	            	contents : "Shit just got real",
	            	user : {
	            		uid : 1
	            	},
	            	category : {
	            		id : 1
	            	}      	
			};
			$.ajax({
				type: "POST",
	            url: "updatearticle",
	            contentType: "application/json",
	            dataType: 'json',
	            data : JSON.stringify(json),
	            success: function(data){ 
	            	alert(data.MESSAGE);
	            },
	            error: function(data){
	            	alert("Unsuccess" + data);
	            	console.log("ERROR..." + data);
	            }
			});
		}
		function deleteArticle(){
			$.ajax({
				type: "POST",
	            url: "deletearticle",
	            data :{
	            	articleid : 2
	            },
	            success: function(data){ 
	            	alert(data.MESSAGE);
	            },
	            error: function(data){
	            	alert("Unsuccess" + data);
	            	console.log("ERROR..." + data);
	            }
			});
		}
		function listArticle(){
			$.ajax({
				type: "GET",
	            url: "listarticle",
	            data :{
	            	articleid : 1
	            },
	            success: function(data){ 
	            	alert(data.RESPONSE_DATA);
	            },
	            error: function(data){
	            	alert("Unsuccess" + data);
	            	console.log("ERROR..." + data);
	            }
			});
		}
		function listArticles(){
			$.ajax({
				type: "GET",
	            url: "listarticles", 
	            success: function(data){ 
	            	alert(data.RESPONSE_DATA);
	            },
	            error: function(data){
	            	alert("Unsuccess" + data);
	            	console.log("ERROR..." + data);
	            }
			});
		}
		function insertArticle(){
			json={
					title : "Hello",
	            	description : "This is first",
	            	image : "Tiger.jpg",
	            	contents : "Shit just got real",
	            	user : {
	            		uid : 1
	            	},
	            	category : {
	            		id : 1
	            	}      	
			};
			$.ajax({
				type: "POST",
				contentType: "application/json",
	            url: "insertarticle", 
	            dataType: 'json',
	            data : JSON.stringify(json),
	            success: function(data){ 
	            	alert(data.MESSAGE);
	            },
	            error: function(data){
	            	alert("Unsuccess" + data);
	            	console.log("ERROR..." + data);
	            }
			});
				
		}
	</script>
</body>
</html>