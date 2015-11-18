<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Article Page</title>

		<script src= "http://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
		
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
				width: 1280px;
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
				width: 245px;
				margin-right: 10px;
				background-color:#fff;
			}
			.a-body{
				width: 770px;
				margin-right: 10px;
			}
			.a-right-side{
				width: 245px;
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
			.a-category li{
				padding: 10px;
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
				height: 400px;
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
				margin-top: 10px;
			}
			.article-action{
				margin-top: 10px;
			}
			.clear{
				clear:both;
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
							<li>All Categories</li>
							<li ng-repeat="category in categories" value="{{category.id}}">{{category.name}}</li>
						</ul>
					</div>
					
					<div class="a-body">
						<div class="article-block">
							<div class="article-item" ng-repeat="i in [1,2,3,4,5,6,7,8,9,10]">
								<div class="article-info">
									<img src="http://www.keenthemes.com/preview/metronic/theme/assets/global/plugins/jcrop/demos/demo_files/image1.jpg"/>
									<p>PHEARUN</p><br>
									<pre>Sep 12 2015, 05:12 PM</pre>	
									<div class="clear"></div>								
								</div>
								<div class="article-components">
									<div class="article-image">
										<img src="http://www.keenthemes.com/preview/metronic/theme/assets/global/plugins/jcrop/demos/demo_files/image1.jpg"/>
									</div>
									<div class="article-desc">
										<h3>CSS Selector Reference</h3>
										<p>In CSS, selectors are patterns used to select the element(s) you want to style.
											Use our CSS Selector Tester to demonstrate the different selectors.
											The "CSS" column indicates in which CSS version the property is defined (CSS1, CSS2, or CSS3).
										</p>
									</div>
								</div>
								<div class="article-action">
									<div class="action">
										<pre><span>1002k Likes</span><span>       500k Comments</span><span>       100k Shares</span></pre>
									</div>
								</div>
							</div>
						</div>
					</div>
					
					<div class="a-right-side">
						<ul class="a-category">
							<li>Popular Articles</li>
							<li ng-repeat="category in categories" value="{{category.id}}">{{category.name}}</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		
		<script>
			var app = angular.module('myApp', []);
			app.controller('myCtrl', function($scope){
				
				$scope.categories = [{"id":"c01","name":"Technology"},
				                     {"id":"c02","name":"Health"},
				                     {"id":"c03","name":"Sports"},
				                     {"id":"c04","name":"Life"},
				                     {"id":"c03","name":"Sports"},
				                     {"id":"c04","name":"Life"},
				                     {"id":"c03","name":"Sports"},
				                     {"id":"c04","name":"Life"},
				                     {"id":"c03","name":"Sports"},
				                     {"id":"c04","name":"Life"}
									];
				$scope.articles = [  {"title":"John", "description":"Doe"}, 
						             {"title":"Anna", "description":"Smith"}, 
						             {"title":"Peter","description":"Jones"}
						          ];	
			});
			
		</script>
	</body>
</html>