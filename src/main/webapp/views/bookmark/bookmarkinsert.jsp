<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Bookmark</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<%@ include file="/views/layout/bookmarkheader.jsp" %>
<body>
<div class="container mt-5">
    <h1 class="display-4">Add Bookmark</h1>
    <div class="card">
        <div class="card-header">
            <h2 class="card-title">Bookmark Details</h2>
        </div>
        <div class="card-body">
            <form action="/bookmark/insert" method="post">
                <div class="form-group">
                    <label for="categoryName">Category Name:</label>
                    <input type="text" class="form-control" id="categoryName" name="categoryName">
                </div>
                <div class="form-group">
                    <label for="bookmarkName">Bookmark Name:</label>
                    <input type="text" class="form-control" id="bookmarkName" name="bookmarkName" required>
                </div>
                <div class="form-group">
                    <label for="url">URL:</label>
                    <input type="url" class="form-control" id="url" name="url" required>
                </div>
                <button type="submit" class="btn btn-primary">Add Bookmark</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
