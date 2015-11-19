<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Detail Page</title>

		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
		<script src= "http://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
		
		<style>
			*{
				padding:0px;
				margin:0 auto;
				box-sizing: border-box;
			}
			body{
				background-color:#dadada;
			}
			.main-container{
				width: 100%;
			}
			.a-container{
				width: 1080px;
				top:60px;
				position:relative;
			}
			.a-nav{
				min-height: 50px;
				background-color:#43A047;	
				position: fixed;	
				width:100%;
				box-shadow: 0px 0px 3px #43A047;
				z-index: 100000;
			}
			.a-left-side, .a-body, .a-right-side{
				float:left;
				box-shadow: 0.5px 0.5px 5px #A09A9A;
			}
			.a-left-side{
				width: 205px;
				margin-right: 10px;
				background-color:#fff;
				
				/*fixed*/
				position:fixed;
				left:10%;
				top:60px;
				
			}
			.a-body{
				width: 570px;
				margin-right: 10px;
				
				/*fixed */
				position:relative;
				left:20%;
				z-index:4000;
			}
			.a-right-side{
				width: 285px;
				background-color:#fff;
				
				/*fixed*/
				position:fixed;
				right:10%;
				top:60px;
				
			}
			.a-category, .a-popular{
				list-style-type:none;
			}
			ul.a-category li:first-child, ul.a-popular li:first-child{
				background-color:#43A047;
				color: #fff;
				padding: 10px;
			}
			
			ul.a-category li:hover{
				background-color:#dadada;
				cursor:pointer;
			}
			.a-category li:not(first-child){
				padding: 7px 10px 7px 30px;
				border-bottom: 1px solid #dadada;
			}
			
			.article-block{
				width: 100%;
			}
			.article-item{
				background-color:#fff;
				padding: 10px;
				margin-bottom: 10px;
				box-shadow: 0px 0px 3px #A09A9A;
			}
			.article-components{
				border:1px solid #dadada;
			}
			.article-image{
				height: 300px;
				overflow:hidden;
			}
			.article-image img{
				width: 100%;
			}
			.article-desc{
				padding: 10px;
			}
			.article-info{
				margin-bottom: 10px;
			}
			.article-info img{
				width: 50px;
				height: 50px;
				margin-right: 10px;
				float:left;
			}
			.article-info p{
				float:left;
			}
			.article-info img:hover ,.article-info p:hover{
				cursor:pointer;
			}
			.article-info pre{
				margin-top: 7px;
			}
			.article-action{
				margin-top: 10px;
			}
			.clear{
				clear:both;
			}
			.fa{
				padding-right: 5px;
			}
			.a-popular-item{
				max-height: 70px;
				overflow:hidden;
				border-bottom: 1px solid #dadada;
			}
			.a-popular-item img{
				width: 70px;
				height: 70px;
				padding: 5px;
				float:left;
			}
			.a-popular-item p{
				padding: 10px;
			}
			
			/* detail page */
			.a-detail{
				/* min-height:500px; */
			}
			.d-title, .d-body{
				background-color:#fff;
				margin-bottom: 10px;
				padding: 10px;
			}
			.d-body{
				min-height:1000px
			}
			.d-body img{
				width: 100%;
				margin:5px 0px;
			}
			.a-share{
				margin-bottom:10px;
				background-color:#fff;
				padding: 10px;
			}
			
		</style>
		
	</head>
	<body ng-app="myApp" ng-controller="myCtrl">
		
		<div class="main-container">
			<div class="a-nav">
				
			</div>
			<div class="a-container">
			
				<div class="a-row">
					<div class="a-left-side">
						<ul class="a-category">
							<li><i class="fa fa-tags"></i>All Categories</li>
							<li ng-repeat="category in categories" ng-click="articleCategory(category.id)" value="{{category.id}}"><i class="fa fa-angle-down"></i>{{category.name}}</li>
						</ul>
					</div><!--/end a-left-side  -->
					
					<div class="a-body">
						<div class="a-detail">
							<div class="d-title">
								<p>{{article.title}}</p>
								<pre>{{article.postdate |date:'medium'}}</pre>
							</div>	
							<div class="d-body">
								{{article.contents}}
							</div>
							<div class="a-share">
								<pre><span>Facebook</span><span>    Youtube</span><span>   Twitter</span></pre>
							</div>					
						</div>
					</div><!--/end a-body  -->
					
					<div class="a-right-side">
						<ul class="a-popular">
							<li><i class="fa fa-area-chart"></i>Popular Articles</li>
							<li ng-repeat="i in [1,2,3,4,5,6]">
								<div class="a-popular-item">
									<img src="https://cdn1.iconfinder.com/data/icons/user-pictures/100/boy-512.png"/>
									<p>It's not the passion, is the concern, the worry. When I was 12 years old, I went to swim in a lake.</p>
									<div class="clear"></div>
								</div>
							</li>
						</ul>
					</div><!--/end a-right-side  -->
					
				</div><!--/end a-row  -->
				
			</div><!--/end a-container  -->
			
		</div><!--/end main container  -->
		
		
		
		<script>
			var app = angular.module('myApp', []);
			app.controller('myCtrl', function($scope, $http){
				
				$scope.categories = [];
				$scope.populars = [];
				$scope.article = {};
				
				$scope.loadCategories = function(){
					$http.get('../category/listcategory').success(function (response) {
				    	angular.forEach(response.RESPONSE_DATA, function(data, key) {
				    		  $scope.categories.push(data);
				    	});
				    });
				};
				$http.get('../admin/listarticle/${articleid}').success(function (response) {
					var data = response.RESPONSE_DATA;
		    		$scope.article = {
						title: data.title,
						contents: data.contents,
						postdate: data.postdate
					};
			    });

				$scope.loadCategories();
				
				$scope.loadPopulars = function(){
				
				};
			});
		
		</script>
	</body>
</html>