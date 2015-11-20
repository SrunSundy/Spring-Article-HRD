<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<div class="a-nav">
	<input type="text" name="searchkey" placeholder="search here..." ng-model="key"/>
	<button ng-click="searchArticles()">Search</button>
	{{key}}
</div>