<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
 <%@ taglib prefix="frm" uri="http://www.springframework.org/tags/form"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<%@include file="include/header.jsp" %>
<title>category</title>

</head>
<body>
	<%@include file="include/topmenu.jsp"%>

<div id="wrapper" style="margin-top: 54px;">

<%@include file="include/leftbar.jsp"%>

	<div class="container" ng-app="myApp" ng-controller="myCtrl" ng-init="sok='dara'">
	    
		<h1> WELCOME CATEGORY PAGE! {{sok}}</h1>
		<button type="button" class="btn btn-info" data-toggle="modal" data-target="#myAdd" ng-click="viewCategory(cat.id)">Add</button>
		<table class="table table-border">
			    <tr>
			    	<th> ID </th>
			    	<th> NAME </th>
			    	<th> DESCRIPTION </th>
			    	<th> STATUS </th>
			    	<th> ACTION </th>
			    </tr>
				<tr ng-repeat="cat in category.RESPONSE_DATA" >
					<td>{{ cat.id }} <input type="hidden" value="{{ cat.id }}"/></td>
				    <td>{{ cat.name }} </td>
				    <td>{{ cat.description }} </td>
				    <td>
				       <div>
			               <input type="checkbox" value={{cat.status}} ng-click="statusCategory(cat.id,cat.status)" checked ng-if="cat.status == 1 "/>
			               <input type="checkbox" value={{cat.status}} ng-click="statusCategory(cat.id,cat.status)" ng-if="cat.status == 0" />
    	 
				       </div>
				    	
				   
				    	
				    </td>
				    <td>
				    	<button type="button" class="btn btn-info" data-toggle="modal" data-target="#myView" ng-click="viewCategory(cat.id)">view</button>
				    	<button type="button" class="btn btn-info" data-toggle="modal" data-target="#myUpdate" ng-click="viewCategory(cat.id)">edit</button>
				     </td>
				</tr>
			</table>
			
			<!-- Modal View -->
		  <div class="modal fade" id="myView" role="dialog" data-keyboard="false" data-backdrop="static">
		    <div class="modal-dialog">
		    
		      <!-- Modal content-->
		      <div class="modal-content">
		        <div class="modal-header">
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		          <h4 class="modal-title">Modal Header</h4>
		        </div>
		        <div class="modal-body">
		          <table class="table table-border">
		            <tr>
		          		<th>ID</th>
		          		<td> {{ id }}</td>
		          	</tr>
		          	<tr>
		          		<th>NAME</th>
		          		<td>{{ name }}</td>
		          	</tr>
		          	<tr>
		          		<th>Description</th>
		          		<td>{{ description }}</td>
		          	</tr>
		            <tr>
		          		<th>Status</th>
		          		<td>{{ status }}</td>
		          	</tr>
		          </table>
		        </div>
		        <div class="modal-footer">
		          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		        </div>
		      </div>
		      
		    </div>
		  </div>
		  
		  			<!-- Modal Update -->
		  <div class="modal fade" id="myUpdate" role="dialog" data-keyboard="false" data-backdrop="static">
		    <div class="modal-dialog">
		    
		      <!-- Modal content-->
		      <div class="modal-content">
		        <div class="modal-header">
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		          <h4 class="modal-title">Update Category</h4>
		        </div>
		        <div class="modal-body">
		        <frm:form id="frmedit" class="form-horizontal" modelAttribute="category" ng-submit="updateCategory($event)">
					<table class="table table-border">
		          	<tr>
		          		<input type="hidden" value="{{id}}" id="id" name="id"/>
		          		<th>NAME</th>
		          		<td><input type="text" value="{{name}}" name="name" required id="name"/></td>
		          	</tr>
		          	<tr>
		          		<th>Description</th>
		          		<td><input type="text" value="{{description}}" name="description" id="description" /></td>
		          	</tr>
		          	<tr>
		          		<th></th>
		          		<td>
		          			<button type="submit" class="btn btn-success"><i class="fa fa-plus"></i>SUBMIT</button>
		          		</td>
		          	</tr>
		          </table>
				 </frm:form>

		          
		        </div>
		        <div class="modal-footer">
		          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		        </div>
		      </div>
		      
		    </div>
		  </div>
		  
		  <!-- Modal Add -->
		  <div class="modal fade" id="myAdd" role="dialog" data-keyboard="false" data-backdrop="static">
		    <div class="modal-dialog">
		    
		      <!-- Modal content-->
		      <div class="modal-content">
		        <div class="modal-header">
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		          <h4 class="modal-title">Update Category</h4>
		        </div>
		        <div class="modal-body">
		        <frm:form id="frmadd" class="form-horizontal" modelAttribute="category" ng-submit="addCategory($event)">
					<table class="table table-border">
		          	<tr>
		          		<input type="hidden" value="{{id}}" id="id" name="id"/>
		          		<th>NAME</th>
		          		<td><input type="text" value="{{name}}" name="name" required id="name"/></td>
		          	</tr>
		          	<tr>
		          		<th>Description</th>
		          		<td><input type="text" value="{{description}}" name="description" id="description" /></td>
		          	</tr>
		          	<tr>
		          		<th></th>
		          		<td>
		          			<button type="submit" class="btn btn-success"><i class="fa fa-plus"></i>SUBMIT</button>
		          		</td>
		          	</tr>
		          </table>
				 </frm:form>

		        </div>
		        <div class="modal-footer">
		          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		        </div>
		      </div>
		      
		    </div>
		  </div>
	
	</div>
	
	 <script>
	 	var app = angular.module('myApp',[]);
		app.controller('myCtrl',function($scope,$http){
			   
			   $scope.category={};
			   
			   // Load list category			   
			   $scope.categoryfunc = function(){
				  $http.get('http://localhost:8080/Spring-Article-HRD/category/listcategory')
				  .success(function(data, status, headers, config) {
					  
				      $scope.category = data;
				      console.log($scope.category);
				      
				    }).
				    error(function(data, status, headers, config) {
				    	console.log('load data error...');
				    });
			   }
			   
			   // call list funciton
			   $scope.categoryfunc();
			   
			   //view category by id
			 	$scope.viewCategory = function(id){
					$http.get('http://localhost:8080/Spring-Article-HRD/category/getcategory/'+id).
				    success(function(data, status, headers, config) {

				      console.log(data);

				      $scope.id = data.RESPONSE_DATA.id;
				      $scope.name = data.RESPONSE_DATA.name;
				      $scope.description = data.RESPONSE_DATA.description;
				      $scope.status = data.RESPONSE_DATA.status;
				      
				    }).
				    error(function(data, status, headers, config) {
				    	console.log('load data error...');
				    });
				}
			   
			   
			   //update category path object
			 	$scope.updateCategory = function($event){				   
				   $event.preventDefault();
					console.log( angular.element("#frmedit input[name='description']").val() );
					
					var dataObj = {
							"id" : angular.element("#frmedit input[name='id']").val(),
							"name" : angular.element("#frmedit input[name='name']").val(),
							"description" : angular.element("#frmedit input[name='description']").val()
					};	
					$http.put('http://localhost:8080/Spring-Article-HRD/category/updatecategory',dataObj).
				    success(function(data, status, headers, config) {
				      console.log(data);
				      $scope.categoryfunc();
				      
				    }).
				    error(function(data, status, headers, config) {
				    	console.log('load data error...');
				    });
					alert("UPDATE");
				}
			   
			   
				   //add category path object
			 	$scope.addCategory = function($event){				   
				   $event.preventDefault();
					console.log( angular.element("#frmadd input[name='description']").val() );
					
					var dataObj = {
							"name" : angular.element("#frmadd input[name='name']").val(),
							"description" : angular.element("#frmadd input[name='description']").val()
					};	
					$http.post('http://localhost:8080/Spring-Article-HRD/category/addcategory',dataObj).
				    success(function(data, status, headers, config) {
				      console.log(data);
				      $scope.categoryfunc();
				    }).
				    error(function(data, status, headers, config) {
				    	console.log('load data error...');
				    });
					alert("Add");
				}
				   
				   
			 	$scope.statusCategory = function(id,status){
					//alert("status:"+ status);
					
					$http.put('http://localhost:8080/Spring-Article-HRD/category/statuscategory/'+id+'/'+status).
				    success(function(data, status, headers, config) {
				      console.log(data);
				      
				    }).
				    error(function(data, status, headers, config) {
				    	console.log('load data error...');
				    });
					alert("status");
				}
			   
		});
	</script>
</div>
<%@include file="include/footer.jsp" %>


</body>
</html>