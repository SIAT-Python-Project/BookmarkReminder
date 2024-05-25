package bookmark.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import bookmark.entity.Bookmark;
import bookmarkcategory.dto.BookmarkCategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import memo.dto.MemoDTO;
import user.dto.UserDTO;

@NoArgsConstructor
@AllArgsConstructor
@Builder

@Setter
@Getter
@ToString
public class BookmarkDTO {
	private Long bookmarkId;
    private String bookmarkName;
    private String url;
    private LocalDateTime createdDate;
    private UserDTO user;
//  private List<BookmarkCategoryDTO> bookmarkCategories;
//  private List<MemoDTO> memos;
       
    public Bookmark toEntity() {
        return Bookmark.builder()
                       .bookmarkId(bookmarkId)
                       .bookmarkName(bookmarkName)
                       .url(url)
                       .createdDate(createdDate)
                       .user(user.toEntity()) 
//                     .bookmarkCategories(bookmarkCategories.stream()
//                                                           .map(BookmarkCategoryDTO::toEntity)
//                                                           .collect(Collectors.toList()))
//                     .memos(memos.stream()
//                                 .map(MemoDTO::toEntity)
//                                 .collect(Collectors.toList()))
                       .build();
    }
}