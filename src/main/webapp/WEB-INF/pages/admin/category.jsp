<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<%@include file="include/header.jsp" %>
<title>Insert title here</title>
</head>
<body>
	<%@include file="include/topmenu.jsp"%>
	


<div id="wrapper" style="margin-top: 54px;">
<%@include file="include/leftbar.jsp"%>
	
	<h1> WELCOME CATEGORY PAGE! </h1>
</div>
<%@include file="include/footer.jsp" %>
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