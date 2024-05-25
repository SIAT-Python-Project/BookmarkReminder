<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bookmark Details</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .post-it {
            background-color: #fffbcc;
            border: 1px solid #f0e68c;
            border-radius: 10px;
            box-shadow: 3px 3px 5px rgba(0, 0, 0, 0.2);
            padding: 15px;
            margin: 10px 0;
            position: relative;
        }
        .post-it::before {
            content: "";
            position: absolute;
            top: -10px;
            left: 50%;
            transform: translateX(-50%);
            width: 30px;
            height: 10px;
            background-color: #fffbcc;
            border-top: 10px solid #f0e68c;
            border-radius: 10px 10px 0 0;
        }
        .post-it .comment {
            font-size: 16px;
            color: #333;
        }
        .post-it .created-date {
            font-size: 12px;
            color: #666;
            text-align: right;
        }
        .post-it .action-buttons {
            margin-top: 10px;
        }
    </style>
</head>
<body>
<div class="container mt-5">
	
    <h1 class="display-4">Bookmark Details</h1>
    <div class="card">
        <div class="card-header">
            <h2 class="card-title">
            ${bookmark.bookmarkName}
            <!-- Add a Delete Bookmark Button here -->
                <form action="/bookmark/delete" method="post" style="display: inline;" onsubmit="return confirm('정말 삭제하시겠습니까?');">
                    <input type="hidden" name="bookmarkId" value="${bookmark.bookmarkId}">
                    <button type="submit" class="btn btn-danger btn-sm">북마크 삭제</button>
                </form>
            </h2>
        </div>
        <div class="card-body">
            <p><strong>URL:</strong> <a href="${bookmark.url}" target="_blank">${bookmark.url}</a></p>
            <p><strong>Created Date:</strong> ${bookmark.createdDate}</p>
        </div>
        
    <h2 class="mt-4">Add Memo</h2>
    <form action="/memo/insert" method="post">
        <div class="form-group">
            <label for="memo">Memo:</label>
            <textarea class="form-control" id="memo" name="memo" rows="3"></textarea>
        </div>
        <input type="hidden" name="bookmarkId" value="${bookmark.bookmarkId}">
        <button type="submit" class="btn btn-primary">Add Memo</button>
    </form>   
    </div>
    <h2 class="mt-4">Memos</h2>
    <div class="list-group">
        <c:forEach var="memo" items="${memos}">
            <div class="post-it">
                <p class="comment">${memo.comment}</p>
                <small class="created-date">${memo.createdDate}</small>
                <div class="action-buttons">
                <!-- Edit button -->
				<a href="javascript:void(0);" class="btn btn-primary btn-sm" 
                   onclick="showEditModal('${memo.memoId}', '${memo.comment}')">수정</a>
                <!-- Delete button -->
				<%-- <a href="/memo/delete?memoId=${memo.memoId}" class="btn btn-danger btn-sm" onclick="return confirm('정말 삭제하시겠습니까?');">삭제</a> --%> 
				<form action="/memo/delete" method="post" onsubmit="return confirm('정말 삭제하시겠습니까?');" style="display: inline;">
	                <input type="hidden" name="memoId" value="${memo.memoId}">
	                <input type="hidden" name="bookmarkId" value="${bookmark.bookmarkId}">
	                <button type="submit" class="btn btn-danger btn-sm">삭제</button>
            	</form>                   
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<!-- Memo Edit Modal -->
<div class="modal fade" id="editMemoModal" tabindex="-1" role="dialog" aria-labelledby="editMemoModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editMemoModalLabel">Edit Memo</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="editMemoForm" action="/memo/update" method="post">
                    <div class="form-group">
                        <label for="editMemoContent">Memo:</label>
                        <textarea class="form-control" id="editMemoContent" name="memo" rows="3"></textarea>
                    </div>
                    <input type="hidden" id="editMemoId" name="memoId">
                    <input type="hidden" name="bookmarkId" value="${bookmark.bookmarkId}">
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="submit" class="btn btn-primary" form="editMemoForm">Save Changes</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    function showEditModal(memoId, memoContent) {
        document.getElementById('editMemoContent').value = memoContent.replace(/'/g, "\\'");
        document.getElementById('editMemoId').value = memoId;

        // Bootstrap 4 modal display
        $('#editMemoModal').modal('show');
    }
</script>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
