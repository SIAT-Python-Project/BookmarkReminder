/* 
	user INERT DML
	password Bcrpt 암호화 
    SHA256 hash -> 이후 Salting과 Key Stretching로 Rainbow table을 활용한 보안 취약점 보완
*/
INSERT INTO user(user_id, login_id, password, email, nickname, user_role, created_date) 
			VALUES(1, 
            'admin', 
            '345/brodrmkmneoReiak/e47524ff01bbe341cf8b85dba3c9174fdbff53206dce52875534557b152f87f0', 
            'admin@email.com',
            'admin',
            'ADMIN',
            now()),
            (2,
            'user',
            '429/iekkdbemaromornR/5cf6fa6d9bad56956ec716b36307ac2c2d7858e4ddd112e6eace2708f68b3bb6',
            'user@email.com',
            'user',
            'USER',
            now()), 
            (3,
            'test',
            '176/roRnearekmbdkiom/28197ef175cd777062883758bcc1ecbd2f37c3c225c5a731a5dd6699c1eb0d0a',
            'test@email.com',
            'test',
            'USER',
            now());
	
SELECT *
FROM user;


INSERT INTO bookmark(bookmark_id, bookmark_name, url, created_date, user_id)
VALUES
    (1, '네이버', 'https://www.naver.com', now(), 2),
    (2, '다음', 'https://www.daum.net', now(), 2),
    (3, '구글', 'https://www.google.com', now(), 2),
    (4, 'Stack Overflow', 'https://stackoverflow.com', now(), 2),
    (5, 'GitHub', 'https://github.com', now(), 2),
    (6, 'Java SE 8 Documentation', 'https://docs.oracle.com/javase/8/docs/', now(), 2),
    (7, 'Java SE 11 Documentation', 'https://docs.oracle.com/en/java/javase/11/', now(), 2),
    (8, 'JPA Specification', 'https://javaee.github.io/javaee-spec/javadocs/javax/persistence/package-summary.html', now(), 2),
    (9, 'JPA 2.2 Specification PDF', 'https://download.oracle.com/otndocs/jcp/persistence-2_2-final-spec/index.html', now(), 2),
    (10, 'JSP Documentation', 'https://javaee.github.io/javaee-spec/javadocs/javax/servlet/jsp/package-summary.html', now(), 2),
    (11, 'Java EE 8 JSP API', 'https://javaee.github.io/javaee-spec/javadocs/javax/servlet/jsp/package-summary.html', now(), 2),
    (12, 'JDBC Documentation', 'https://docs.oracle.com/javase/8/docs/technotes/guides/jdbc/', now(), 2),
    (13, 'JDBC API Specification', 'https://docs.oracle.com/en/java/javase/11/docs/api/java.sql/java/sql/package-summary.html', now(), 2);


SELECT *
FROM bookmark;

INSERT INTO category(category_id, category_name, user_id)
VALUES
	(1, 'Java', 2),
    (2, 'JPA', 2),
    (3, 'JDBC', 2),
    (4, 'JSP', 2);

SELECT *
FROM category;

INSERT INTO bookmarkcategory(bookmark_category_id, bookmark_id, category_id)
VALUES
	(1, 6, 1),
	(2, 7, 1),
	(3, 8, 1),
	(4, 8, 2),
	(5, 9, 1),
	(6, 9, 2),
	(7, 10, 1),
	(8, 10, 4),
	(9, 11, 1),
	(10, 11, 4),
	(11, 12, 1),
	(12, 12, 3),
	(13, 13, 1),
	(14, 13, 3);

SELECT *
FROM bookmarkcategory;

INSERT INTO memo(memo_id, comment, created_date, bookmark_id)
VALUES
	(1, 'git을 잘 사용하는 멋진 사람이 되어봐요.', now(), 5),
    (2, '네이버 취업하고 싶다...', now(), 1),
    (3, '다들 취뽀하길 바래요!!', now(), 1);

SELECT *
FROM memo;