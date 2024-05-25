//package test.sm;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//import bookmark.entity.Bookmark;
//import bookmark.service.BookmarkService;
//import user.entity.User;
//import user.service.UserService;
//
//public class SMBookmarkServiceTest {
//	
//	private static List<User> users = new ArrayList<>();
//    private static List<Bookmark> bookmarks = new ArrayList<>();
//    
//    static {
//        for (int i = 1; i <= 10; i++) {
//            users.add(User.builder()
//            		.id((long) i)
//                    .createdDate(LocalDateTime.now())
//                    .email("user" + i + "@email.com")
//                    .loginId("user" + i)
//                    .password("user" + i)
//                    .nickname("user" + i)
//                    .build());
//        }
//        
//        for (int i = 1; i <= 10; i++) {
//        	bookmarks.add(Bookmark.builder()
//                .bookmarkName("Bookmark " + i)
//                .url("http://example.com/" + i)
//                .createdDate(LocalDateTime.now())
//                .user(users.get(i - 1))
//                .build());           
//        }
//    }
//    
//    private static void settings() {
//        users.forEach(user -> {
//            UserService.signup(user.getLoginId(),
//                    user.getPassword(),
//                    user.getNickname(),
//                    user.getEmail());
//        });
//        
//
//        bookmarks.forEach(bookmark -> {
//            bookmark = BookmarkService.addBookmark(
//                    bookmark.getBookmarkName(),
//                    bookmark.getUrl(),
//                    users.get(0)
//            );
//        });
//               
//    }
//    
//    private static void addBookmark(String bookmarkName, String url, User user) {
//        try {
//            Bookmark bookmark = BookmarkService.addBookmark(bookmarkName, url, user);
//            System.out.println("Bookmark insert 성공: " + bookmark.getBookmarkName());
//        } catch (IllegalArgumentException e) {
//            System.err.println(e.getMessage());
//        }
//    }
//
//    private static void getBookmark(Long id) {
//        try {
//            Bookmark bookmark = BookmarkService.getBookmark(id);
//            System.out.println("찾은 bookmark 데이터: " + bookmark.getBookmarkName());
//        } catch (IllegalArgumentException e) {
//            System.err.println(e.getMessage());
//        }
//    }
//
//    private static void getAllBookmarks() {
//        try {
//            List<Bookmark> bookmarkList = BookmarkService.getAllBookmarks();
//            bookmarkList.forEach(bookmark -> {
//                System.out.println("Bookmark All 데이터: " + bookmark.getBookmarkName());
//            });
//        } catch (IllegalArgumentException e) {
//            System.err.println(e.getMessage());
//        }
//    }
//
//    private static void deleteBookmark(Long id) {
//        try {
//            BookmarkService.deleteBookmark(id);
//            System.out.println("북마크 삭제 성공!" + id);
//        } catch (IllegalArgumentException e) {
//            System.err.println(e.getMessage());
//        }
//    }
//
//    public static void main(String[] args) {
//        // Initialize the database with users
//        settings();
//
//        // Test addBookmark
////      addBookmark("북마크 서비스 테스트", "http://example.com/new", users.get(0));
//
//        // Test getBookmark
////        getBookmark(1L);
//
//        // Test getAllBookmarks
////       getAllBookmarks();
//
//        // Test deleteBookmark
////       deleteBookmark(1L);
//    }
//    
//
//}