# 성민 담당 부분

### Bookmark
- Bookmark Repository 구현
  - CRD

- BookmarkService 구현
  - 북마크를 추가할 때 3가지 경우의 수로 나누었습니다.

  - 첫번째는 카테고리 이름을 입력하고 db에 카테고리가 없는 경우에 카테고리를 먼저 생성한 다음에 
    북마크를 추가하는 방식으로 진행했고 이미 존재하는 카테고리인 경우에는 북마크만 추가하는 방법으로 진행했습니다.
  
  - 두번째는 북마크를 추가할 떄 카테고리 없이 북마크를 추가하는 경우도 있기 때문에 그런 경우에는 메인 페이지에서 보시면 이 북마크 목록 아래에는 카테고리가 없는 북마크 목록을 출력하고 있습니다.
  
  - 세번쨰는 여러 개의 카테고리 이름을 입력받는 경우에 대한 경우 카테고리 이름을 이런식으로 콤마를 기준으로 나누어서 
    해당 북마크를 여러개의 카테고리에서 가질수 있도록 처리하였습니다. 메인 페이지에서 확인 해보시면 각각의 카테고리가 같은 북마크를 갖고 있습니다.

  - 북마크 삭제 :@OneToMany에 캐스케이드 속성에 remove 값을 줘서 연관관계를 맺는 메모와 북마크카테고리 다대다 테이블에 대해 같이 제거가 되게끔 처리

- Bookmark Controller 구현
  - BookmarkInsertFormController url : /bookmark/insert/form (북마크 입력 폼 페이지 이동)
  - BookmarkInsertController url : /bookmark/insert (북마크 상세페이지로 이동)
  - BookmarkDeleteController url : /bookmark/delete (북마크 상세페이지로 이동)
  - BookmarkDetailController url : /bookmark/detail (북마크 상세페이지로 이동)

### Memo
- Memo Repository 구현
  - CURD

- Memo Service 구현
  - CRUD 서비스 구현(+예외 처리)

- Memo Controller 구현
  - MemoInsertController url : /memo/insert (메모 추가 시 비동기 처리)
  - MemoUpdateController url : /memo/update (동기 처리 후 북마크 상세페이지 이동) 
  - MemoDeleteController url : /memo/delete (동기 처리 후 북마크 상세페이지 이동)
