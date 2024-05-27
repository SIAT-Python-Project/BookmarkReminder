# 📝BookMark Reminder
~ Bookmark Reminder ~<br/>
오직 나만을 위한 참고 사이트를 만들어 보세요!



## About The Project
인터넷 상에는 다양한 정보를 제공하는 여러 사이트들이 있다. 이러한 사이트들 중 일부는 다시 참고하려는 목적으로 재방문하는 데에 용이하도록 브라우저의 '즐겨찾기' 기능을 이용하여 저장한다. 그러나 '즐겨찾기' 기능의 경우, 추가하는 사이트가 많아질수록 관리하기 어려운 점이 있다. 그에 따라 본 프로젝트에서는 북마크(참고 사이트)를 관리하는 웹 페이지(이하 북마크 리마인더)를 제작하여 이러한 단점을 해결하고자 한다.

북마크 리마인더는 사용자별로 북마크 관리 페이지를 제공하여 카테고리 기능을 통해 사용자가 원하는 기준에 따라 북마크를 분류하여 저장할 수 있도록 한다. 또한 북마크별로 메모 기능을 통해 사용자가 북마크와 관련하고 기록하고 싶은 내용을 저장할 수 있도록 한다.

***


+ ### Languages, libraries, frameworks and tools used
- Language
	* Java

- Library
	* lombok
		* version : 1.18.30

	* mysql-connector
		* version : 8.0.33
		
	* JSTL
		* version : 1.2

- Framwork
	* hibernate
		* version : 6.5.0
***

### getting start
1. Clone the repo
```
git clone https://github.com/SIAT-Python-Project/BookmarkReminder.git
```

2. Execute /DB/bookmarkReminder.mwb

3. Add /src/main/resources/persistence.xml and fill your DBMS information(driver, jdbc url, user, password)
```
<?xml version="1.0" encoding="UTF-8"?>

<persistence xmlns="https://jakarta.ee/xml/ns/persistence"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="https://jakarta.ee/xml/ns/persistence
                https://jakarta.ee/xml/ns/persistence/persistence_3_0.xsd"
             version="3.0">

    <persistence-unit name="bookmarkreminder">
        <properties>
            <property name="jakarta.persistence.jdbc.driver" value="{driver}"/> // driver 정보 넣기
            <property name="jakarta.persistence.jdbc.url"
                      value="{dbms url}"/> // dbms url 넣기
            <property name="jakarta.persistence.jdbc.user" value="{user}"/> // user 정보 넣기
            <property name="jakarta.persistence.jdbc.password" value="{password}"/> // password 정보 넣기

            <property name="hibernate.hikari.poolName" value="pool"/>
            <property name="hibernate.hikari.maximumPoolSize" value="10"/>
            <property name="hibernate.hikari.minimumIdle" value="10"/>
            <property name="hibernate.hikari.connectionTimeout" value="1000"/>
            
            <property name="hibernate.dialect" value="org.hibernate.dialect.MySQLDialect"/>
       			<property name="hibernate.show_sql" value="true"/>
    			  <property name="hibernate.format_sql" value="true"/>
    			  <property name="hibernate.use_sql_comments" value="true"/>
    			  <property name="hibernate.hbm2ddl.auto" value="none"/>
        </properties>
    </persistence-unit>
</persistence>
```

4. Setting Tomcat Server (Tomcat 9.0)

- Eclipse > Server > click new server > Apache > Tomcat 9.0 > Next
![image](https://github.com/SIAT-Python-Project/BookmarkReminder/assets/163812603/7ddc6ae9-7969-4f94-a16c-08c10cba3876)


- Tomcat 설치 디렉터리 설정 > Finish <br>
![image](https://github.com/SIAT-Python-Project/BookmarkReminder/assets/163812603/cebcfd87-aec6-4728-bb2e-75af36b51de4)


- Build Path > Configure build path > Web project Settings > Context root: / 설정 > Apply and Close
![image](https://github.com/SIAT-Python-Project/BookmarkReminder/assets/163812603/01ac0937-5246-4518-a707-8f2d74bca823)


5. Run project on Tomcat Server (/views/user/login.jsp)



***

### Feature
- to login, to logout : 로그인, 로그아웃 기능
- to sign up : 회원가입 기능
- to change user's password : 패스워드 변경 기능
- to show user's categories and bookmarks : 사용자별로 생성한 카테고리 및 북마크 조회 기능
- to get/create/update/delete category : 카테고리 조회/생성/수정/삭제 기능
- to get/create/delete bookmark : 북마크 조회/생성/삭제 기능
- to get/create/update/delete memo : 메모 조회/생성/수정/삭제 기능

***

### Road Map
1. Sign up
2. Login
3. Searching all categories and bookmarks that created by user
4. Showing these categories and bookmarks in main page
  
5. Creating new category or bookmark<br>
  5-1) category
    - Showing new category in main page

    5-2) bookmark
    - Showing the bookmark information and memos of the bookmark

6. Selecting one of the categories or bookmarks<br>
  6-1) category
    - Showing the category and all bookmarks included category(bookmark name, url)
    - Selecting one of the bookmarks
    - Showing the bookmark information and memos of the bookmark
    - Creating new bookmark of the category -> Showing new bookmark information
  
    6-2) bookmark
    - Showing the bookmark information and memos of the bookmark

***

### Contact

👷 이혜진
* GitHub: <https://github.com/hj613>
* e-mail: <jesuisambitieux@gmail.com>

👷 장희권
* GitHub: <https://github.com/jang010505>
* e-mail: <hgyellow0505@gmail.com>

👷 조성민
* GitHub: <https://github.com/EnjoyTime18>
* e-mail: <ggbb2956@gmail.com>
