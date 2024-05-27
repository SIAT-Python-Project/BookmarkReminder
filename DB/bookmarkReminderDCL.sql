create user bookmarkreminder@localhost identified by 'bookmarkreminder';
create database bookmarkreminder;
grant all privileges on bookmarkreminder.* to bookmarkreminder@localhost with grant option;
commit;