<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.88.1">
    <title>Dashboard Template · Bootstrap v4.6</title>

	<tiles:insertAttribute name="preScript" />

<meta name="theme-color" content="#563d7c">
  </head>
  <body>
    
	<tiles:insertAttribute name="topMenu" />

<div class="container-fluid">
  <div class="row">
    
    <tiles:insertAttribute name="leftMenu" />

    <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
      <tiles:insertAttribute name="content"/>
    </main>
  </div>
</div>
	<tiles:insertAttribute name="postScript" />
  </body>
</html>










    