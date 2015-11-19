<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<%@include file="include/header.jsp" %>

<title>Insert title here</title>

<style>
	table tr.tbheader{
		color: white;
		background: #757575;
		font-family: "Times New Roman", Georgia, Serif;
	}
	span.searchresult{
		color: #757575;
		font-style: italic;
		font-family: "Times New Roman", Georgia, Serif;
	}
</style>
</head>
<body>
      	
	<%@include file="include/topmenu.jsp"%>

<div id="wrapper" style="margin-top: 54px;">
<%@include file="include/leftbar.jsp"%>
		<div style="height:20px;"  class="col-sm-12"></div>
		
		<div id="content" class="col-sm-12">
			<fieldset>
				<legend><span style="font-weight:bold;font-size:20px;">ARTICLE</span><span style="font-size:14px;"> Listing</span></legend>
				<div class="col-sm-12">
					<div class="col-sm-9 ">
						<div class="row">
						<div class="row">
							<select id="rowset" style="height:32px;border:1px solid #E0E0E0;border-radius:5px;">
								<option value="10">10</option>
								<option value="20">20</option>
								<option value="50">50</option>
								<option value="100">100</option>
							</select>
							<span class="searchresult "  style="">Result : <span id="rowshow"></span>/<span id="recordresult"></span></span>
						</div>
						</div>
					</div>
					<div class="row">
					<div class="row">
					<div class="col-sm-3" >
						
						<input type="search" onkeyup="displayPage(1); getArticleRow();" id="searcharticle" class="form-control" placeholder="Searching" style="margin:0 0 0 15px;height:32px;border:1px solid #E0E0E0;border-radius:5px;"/>
						
					</div>
					</div>
					</div>
				</div>
				<div id="listarticleresult">
					
				</div>
				<div id="content"></div>
    	<div id="demo4_top" class="demo4_top"></div>
			</fieldset>
			
		</div>
		
	
</div>
<%@include file="include/footer.jsp" %>
 <script>
		//insertArticle();
		//deleteArticle();
		//updateArticle();
		var dbrow=0;
		var numofpage=0;
		var rowshow=0;
		listArticles();
		getArticleRow();
		function getArticleRow(){
			
			$.ajax({
				method : "POST",
				url : "getarticlerow",
				data:{
					acontent : $("#searcharticle").val()
				},
				success : function(data){
					
					
					 dbrow=data.RESPONSE_DATA;
					
					var npage;
					var nps=dbrow/10;
					
					if(!(dbrow % rowshow==0)){
						npage = Math.floor(nps);
						npage+=1;		
					}else{	
						npage=nps;
					}
					numofpage=npage; 
					
				    $("#recordresult").html(dbrow);
				 
				    $("#rowshow").html($("#rowset").val()); 
					loadPagination();
				}
			});
		}
		
		function loadPagination(){
			
			$('.demo4_top').bootpag({
		        total: numofpage,
		        
		        maxVisible: 5,
		        leaps: true,
		        firstLastUse: true,
		        first: '&#8592;',
		        last: '&#8594;',
		        wrapClass: 'pagination',
		        activeClass: 'active',
		        disabledClass: 'disabled',
		        nextClass: 'next',
		        prevClass: 'prev',
		        lastClass: 'last',
		        firstClass: 'first'
		    }).on("page", function(event, num){
		    	
		   		 displayPage(num);
		    }); 
		}
		function displayPage(mypage){
			
			$.ajax({
				method : "POST",
				url : "displayarticlepage",
				data : {
					acontent : $("#searcharticle").val(),
					page : mypage
					
				},
				success : function(data){
					
					$("#listarticleresult").html(listTb(data.RESPONSE_DATA));
					
					 
				}
			});
		} 
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
		function listTb(data){
			
			var tb="<table class='table'>";
			tb+="<tr class='tbheader'>";
			tb+="<th>Image</th>";
			tb+="<th>Title</th>";
			tb+="<th>PostedDate</th>";
			tb+="<th>Category</th>";
			tb+="<th>Writer</th>";
			tb+="<th>Status</th></tr>";
			for(var i=0;i<data.length;i++){
				tb+="<tr>";
				tb+="<td>"+data[i].image+"</td>";
				tb+="<td>"+data[i].title+"</td>";
				tb+="<td>"+data[i].postdate+"</td>";
				tb+="<td>"+data[i].category.id+"</td>";
				tb+="<td>"+data[i].user.uid+"</td>";
				tb+="<td>"+data[i].status+"</td>";
				tb+="</tr>";
				
			}
			tb+="</table>";
			return tb;
			
		
		}
		function listArticles(){
			$.ajax({
				type: "GET",
	            url: "listarticles", 
	            success: function(data){ 
	            	
	            	$("#listarticleresult").html(listTb(data.RESPONSE_DATA));
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