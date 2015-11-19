<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Article Page</title>

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
			}
			.a-body{
				width: 570px;
				margin-right: 10px;
			}
			.a-right-side{
				width: 285px;
				background-color:#fff;
			}
			.a-category{
				list-style-type:none;
			}
			ul.a-category li:first-child{
				background-color:#43A047;
				color: #fff;
				padding: 10px;
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
							<li ng-repeat="category in categories" value="{{category.id}}"><i class="fa fa-angle-down"></i>{{category.name}}</li>
						</ul>
					</div><!--/end a-left-side  -->
					
					<div class="a-body">
						<div class="article-block">
							<div class="article-item" ng-repeat="article in articles">
								<div class="article-info">
									<img src="upload/{{article.user.uimage}}"/>
									<p>{{article.user.uname}}</p><br>
									<pre>{{article.postdate | date:'medium'}}</pre>	
									<div class="clear"></div>								
								</div>
								<div class="article-components">
									<div class="article-image">
										<a href="admin/listarticle/{{article.id}}" target="_blank"><img src="{{article.image}}"/></a>
									</div>
									<div class="article-desc">
											<h3><a href="admin/listarticle/{{article.id}}" target="_blank">{{article.title}}</a></h3>
											<p><a href="admin/listarticle/{{article.id}}" target="_blank">{{article.description}}</a></p>
									</div>
								</div>
								<div class="article-action">
									<div class="action">
										<pre><span>1002K Likes</span><span>       500K Comments</span><span>       100K Shares</span></pre>
									</div>
								</div>
							</div>
						</div>
					</div><!--/end a-body  -->
					
					<div class="a-right-side">
						<ul class="a-category">
							<li>Popular Articles</li>
							<li ng-repeat="category in categories" value="{{category.id}}">{{category.name}}</li>
						</ul>
					</div><!--/end a-right-side  -->
					
				</div><!--/end a-row  -->
				
			</div><!--/end a-container  -->
			
		</div><!--/end main container  -->
		
		
		
		<script>
			var app = angular.module('myApp', []);
			app.controller('myCtrl', function($scope, $window, $http){
				
				$scope.articles = [];
				$scope.categories = [];
				$scope.page = 0;
				
				$scope.loadCategories = function(){
					$http.get('category/listcategory').success(function (response) {
				    	angular.forEach(response.RESPONSE_DATA, function(data, key) {
				    		  $scope.categories.push(data);
				    	});
				    });
				};
				
				$scope.loadArticles = function(){
					$scope.page += 1;
					$http({
                        method: "POST",
                        url: "admin/displayarticlepage",
                        params: {
                            acontent: "",
                            page:$scope.page
                        }
                    })
                    .success(function (response) {
                    	angular.forEach(response.RESPONSE_DATA, function(data, key) {
				    		  $scope.articles.push(data);
				    	});
				    });
				};
					
				$scope.loadCategories();
				$scope.loadArticles();
				
				angular.element($window).bind("scroll", function() {
                    var windowHeight = "innerHeight" in window ? window.innerHeight: document.documentElement.offsetHeight;
                    var body = document.body, html = document.documentElement;
                    var docHeight = Math.max(body.scrollHeight,body.offsetHeight, html.clientHeight,html.scrollHeight, html.offsetHeight);
                    windowBottom = windowHeight + window.pageYOffset;

                    if (windowBottom >= docHeight) {
                    	$scope.$apply($scope.loadArticles());
                    }
     	        });
     	        
			});
		
		</script>
	</body>
</html>