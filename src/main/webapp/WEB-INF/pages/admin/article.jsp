<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="include/header.jsp"%>
<title>Insert title here</title>
</head>
<body>
      	
	<%@include file="include/topmenu.jsp"%>

<div id="wrapper" style="margin-top: 54px;">
<%@include file="include/leftbar.jsp"%>
		<div style="height:20px;"  class="col-sm-12"></div>
		<div id="content" class="col-sm-12">
			<fieldset>
				<legend><span style="font-weight:bold;font-size:20px;">ARTICLE</span><span style="font-size:14px;"> Management</span></legend>
			
			<form role="form" method="POST" id="formarticle">
			
				<div class="col-sm-6">
					<div class="form-group">
					  <label for="cat">News-Cat</label>
					  <select class="form-control" id="category" name="category">
					    <option>1</option>
					    <option>2</option>
					    <option>3</option>
					    <option>4</option>
					  </select>
					</div>
				</div>
				<div class="col-sm-6">
					 <div class="form-group">
			    		<label for="title">Title:</label>
			   			 <input type="text" class="form-control" id="title" name="title">
			  		</div>
			  	</div>
			  	<div class="col-sm-6">
				  	<div class="form-group">

							<div class="fileinput fileinput-new" data-provides="fileinput">
								<div class="fileinput-preview thumbnail"
									data-trigger="fileinput" id='disimage'
									style="width: 400px; height: 200px;"></div>
								<div>
									<span class="btn btn-default btn-file"><span
										class="fileinput-new">Select image</span><span
										class="fileinput-exists">Change</span><input id='image'
										type="file" name="image"></span> <a href="#"
										class="btn btn-default fileinput-exists"
										data-dismiss="fileinput">Remove</a>
								</div>
							</div>
						</div>
			 
			  <!-- <button type="submit" class="btn btn-default">Submit</button> -->
				
				</div>
				<div class="col-sm-6">
					<div class="form-group">
							<label for="comment">Description:</label>
							<textarea class="form-control" rows="5" id="description" name="description"></textarea>
						</div>
				</div>
				
				<div class="col-sm-12">
								<textarea name="contents" id="editor1">&lt;p&gt;Initial editor content.&lt;/p&gt;</textarea>

								<script>
									CKEDITOR
											.replace(
													'contents',
													{
														filebrowserBrowseUrl : 'plugin/Filemanager-master/index.html',

													});
								</script>
				</div>
			 	
			 	<input type="submit" value="Insert" id="insertarticle" />
			</form>	
			</fieldset>
			
		</div>
		
	
</div>
<%@include file="include/footer.jsp"%>
 <script>
		//insertArticle();
		//deleteArticle();
		//updateArticle();
		$("#formarticle").submit(function(event){
			event.preventDefault();
			
			var filename = $("#image").val().split('\\').pop();
			json={
					title : $("#title").val(),
	            	description : $("#description").val(),
	            	image : filename,
	            	contents : $("#contents").val(),
	            	user : { 
	            		uid : 1
	            	},
	            	category : {
	            		id : $("#category").val()
	            	}      	
			}; 
			
			data1 = new FormData();
			 data1.append('file', $('#image')[0].files[0]);
			 data1.append('articledata',json);
			
			$.ajax({
				type: "POST",
				contentType: "application/json",
	            url: "insertarticle", 
	            dataType: 'json',
	            data : JSON.stringify(data1),
	            success: function(data){ 
	            	alert(data.MESSAGE);
	            },
	            error: function(data){
	            	alert("Unsuccess" + data);
	            	console.log("ERROR..." + data);
	            }
			});
		});
		function insertArticle(){
			
			
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
		
		
	</script>
</body>
</html>