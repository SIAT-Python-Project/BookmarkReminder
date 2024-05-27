# 혜진 담당 부분

### Category
- Category Repository 구현
	- CURD
	
- Category Service 구현
	- 카테고리 이름 검증 로직
	- CRUD 서비스 구현(+예외 처리)
	
- Category 상세 페이지 Controller 및 jsp 구현
	- 특정 카테고리 조회 시 출력되는 페이지(+북마크 리스트)
	- /category.do
	- categorydetail.jsp
	- categorydetail.css

- Category Update Controller 구현
	- /updateCategoryForm.do
	- /updateCategory.do

- Category delete Controller 구현
	- /deleteCategory.do
	- 카테고리 삭제 시 연관 관계 및 cascade 설정에 따라 카테고리에 포함된 북마크도 삭제
	- 단, 북마크가 여러 카테고리에 포함된 경우는 연관 관계만 삭제되도록 로직 구현


### BookmarkCategory
- BookmarkCategory Repository 구현
	- CRD
	
- BookmarkCategory Service 구현
	- 카테고리에 포함되지 않은 북마크 조회 서비스 구현
	- 카테고리 Id로 카테고리에 포함된 북마크 조회 서비스 구현
	- 북마크카테고리 관계 테이블 데이터 추가 서비스 구현
	- 카테고리 삭제 시 카테고리에 포함된 북마크 삭제 서비스 구현
	- 예외 처리