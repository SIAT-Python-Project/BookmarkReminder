<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Category Details</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1>카테고리 상세페이지 : JSP</h1>
        
        <a href="/bookmark/detail?bookmarkId=2">북마크 이름</a>
        
        <!-- Trigger/Add Bookmark Modal Button -->
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addBookmarkModal">
            북마크 추가
        </button>

        <!-- Add Bookmark Modal -->
        <div class="modal fade" id="addBookmarkModal" tabindex="-1" role="dialog" aria-labelledby="addBookmarkModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addBookmarkModalLabel">북마크 추가</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form action="/bookmark/insert" method="post">
                            <div class="form-group">
                                <label for="bookmarkName">북마크 이름:</label>
                                <input type="text" class="form-control" id="bookmarkName" name="bookmarkName" required>
                            </div>
                            <div class="form-group">
                                <label for="bookmarkUrl">URL:</label>
                                <input type="url" class="form-control" id="bookmarkUrl" name="url" required>
                            </div>
                            <input type="hidden" name="categoryId" value="2"> <!-- 테스트 용 -->
                            <%-- <input type="hidden" name="categoryId" value="${categoryId}"> --%>
                            <button type="submit" class="btn btn-primary">저장</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap and jQuery scripts -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
