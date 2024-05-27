## 장희권이 한 일
### User
- 회원가입과 관련된 모든 로직
  - url: /signup.do
  - 단방향 DB 암호화
- 로그인과 관련된 모든 로직
  - url: /login.do
  - session 관리
  - 비동기 처리
- 비밀번호 수정과 관련된 모든 로직
  - url: /passwordUpdate.do
- 정보 조회와 관련된 모든 로직
  - url: /user.do
- User Role과 관련된 모든 로직
  - ADMIN: User가 생성한 페이지 열람 가능
  - USER: 자기가 생성한 것만 열람 가능
### filter
  - LoginFilter: session에 존재에 따라 조회가 가능한 페이지가 나뉨
  - EncodingFilter: HttpRequest와 HttpResponse의 encoding 설정
  - AdminFilter: Role에 따라 조회가 가능한 페이지가 나뉨
### Category
- MainPage Controller 및 JSP 구현
  - /mainPage.do
  - 로그인 후 처음 나오는 페이지
  - 혜진님이 구현하신 Service 사용
- Category 생성 Controller 및 JSP 구현
  - /categoryInsert.do
  - 혜진님이 구현하신 Service 사용
