<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bookmark Details</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/css/bookmark/bookmarkdetail.css" rel="stylesheet" type="text/css">
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>    
</head>
<%@ include file="/views/layout/bookmarkheader.jsp" %>
<body>
<div class="container mt-5">
    <h1 class="display-4">Bookmark Details</h1>
    <div class="card">
        <div class="card-header">
            <h2 class="card-title">${bookmark.bookmarkName}
                <form action="/bookmark/delete" method="post" style="display: inline;" onsubmit="return confirm('정말 삭제하시겠습니까?');">
                    <input type="hidden" name="bookmarkId" value="${bookmark.bookmarkId}">
                    <button type="submit" class="btn btn-danger btn-sm">북마크 삭제</button>
                </form>
            </h2>
        </div>
        <div class="card-body">
            <p><strong>URL:</strong> <a href="${bookmark.url}" target="_blank">${bookmark.url}</a></p>
            <p><strong>Created Date:</strong> ${bookmark.formattedCreatedDate}</p>
        </div>
    </div>

    <h2 class="mt-4">Add Memo</h2>
    <form id="addMemoForm">
        <div class="form-group">
            <label for="memo">Memo:</label>
            <textarea class="form-control" id="memo" name="memo" rows="3" required></textarea>
        </div>
        <input type="hidden" id="bookmarkId" value="${bookmark.bookmarkId}">
        <button type="submit" class="btn btn-primary">Add Memo</button>
    </form>

    <h2 class="mt-4">Memos</h2>
    <div class="list-group" id="memoList">
        <c:if test="${empty memos}">
            <p class="no-memos">${errorMessage}</p>
        </c:if>
        <c:forEach var="memo" items="${memos}">
            <div class="post-it">
                <p class="comment">${memo.comment}</p>
                <small class="created-date">${memo.formattedCreatedDate}</small>
                <div class="action-buttons">
                    <a href="javascript:void(0);" class="btn btn-primary btn-sm"
                       onclick="showEditModal('${memo.memoId}', '${memo.comment}')">수정</a>
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
                <form id="editMemoForm" action="/memo/update" method="post" onsubmit="return validateEditMemo();">
                    <div class="form-group">
                        <label for="editMemoContent">Memo:</label>
                        <textarea class="form-control" id="editMemoContent" name="memo" rows="3" required></textarea>
                    </div>
                    <input type="hidden" id="editMemoId" name="memoId">
                    <input type="hidden" name="bookmarkId" value="${bookmark.bookmarkId}">
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Save Changes</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    // 새 메모 추가 검증
    function validateMemo() {
        var memoContent = document.getElementById('memo').value.trim();
        if (!memoContent) {
            alert('메모를 입력해주세요.');
            return false;
        }
        return true;
    }

    // 메모 수정 검증
    function validateEditMemo() {
        var editMemoContent = document.getElementById('editMemoContent').value.trim();
        if (!editMemoContent) {
            alert('수정할 메모를 입력해주세요.');
            return false;
        }
        return true;
    }

    function showEditModal(memoId, memoContent) {
        document.getElementById('editMemoContent').value = memoContent.replace(/'/g, "\\'");
        document.getElementById('editMemoId').value = memoId;
        $('#editMemoModal').modal('show');
    }

    function addMemo(event) {
        event.preventDefault();
        if (!validateMemo()) {
            return;
        }
        var memoContent = document.getElementById('memo').value;
        var bookmarkId = document.getElementById('bookmarkId').value;

        axios.post('/memo/insert', {
            memo: memoContent,
            bookmarkId: bookmarkId
        })
        .then(function (response) {
            // 성공적으로 메모 추가 시 페이지를 새로고침하지 않고 UI 업데이트
            var memos = response.data;
            var memoList = document.getElementById('memoList');
            memoList.innerHTML = '';

            if (memos.length === 0) {
                var noMemos = document.createElement('p');
                noMemos.classList.add('no-memos');
                noMemos.textContent = 'No memos available.';
                memoList.appendChild(noMemos);
            } else {
                memos.forEach(function (memo) {
                    var postIt = document.createElement('div');
                    postIt.classList.add('post-it');

                    var comment = document.createElement('p');
                    comment.classList.add('comment');
                    comment.textContent = memo.comment;

                    var createdDate = document.createElement('small');
                    createdDate.classList.add('created-date');
                    createdDate.textContent = memo.formattedCreatedDate;

                    var actionButtons = document.createElement('div');
                    actionButtons.classList.add('action-buttons');

                    var editButton = document.createElement('a');
                    editButton.href = 'javascript:void(0);';
                    editButton.classList.add('btn', 'btn-primary', 'btn-sm');
                    editButton.textContent = '수정';
                    editButton.onclick = function () {
                        showEditModal(memo.memoId, memo.comment);
                    };

                    var deleteForm = document.createElement('form');
                    deleteForm.action = '/memo/delete';
                    deleteForm.method = 'post';
                    deleteForm.onsubmit = function () {
                        return confirm('정말 삭제하시겠습니까?');
                    };
                    deleteForm.style.display = 'inline';

                    var memoIdInput = document.createElement('input');
                    memoIdInput.type = 'hidden';
                    memoIdInput.name = 'memoId';
                    memoIdInput.value = memo.memoId;

                    var bookmarkIdInput = document.createElement('input');
                    bookmarkIdInput.type = 'hidden';
                    bookmarkIdInput.name = 'bookmarkId';
                    bookmarkIdInput.value = bookmarkId;

                    var deleteButton = document.createElement('button');
                    deleteButton.type = 'submit';
                    deleteButton.classList.add('btn', 'btn-danger', 'btn-sm');
                    deleteButton.textContent = '삭제';

                    deleteForm.appendChild(memoIdInput);
                    deleteForm.appendChild(bookmarkIdInput);
                    deleteForm.appendChild(deleteButton);

                    actionButtons.appendChild(editButton);
                    actionButtons.appendChild(deleteForm);

                    postIt.appendChild(comment);
                    postIt.appendChild(createdDate);
                    postIt.appendChild(actionButtons);

                    memoList.appendChild(postIt);
                });
            }
        })
        .catch(function (error) {
            console.error('Error adding memo:', error);
        });
    }

    document.getElementById('addMemoForm').addEventListener('submit', addMemo);
</script>
</body>
</html>
