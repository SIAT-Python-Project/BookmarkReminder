package bookmarkcategory.dto;

import bookmark.dto.BookmarkDTO;
import bookmarkcategory.entity.BookmarkCategory;
import category.dto.CategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder

@Setter
@Getter
@ToString
public class BookmarkCategoryDTO {
	private Long bookmarkCategoryId;
    private CategoryDTO category;
    private BookmarkDTO bookmark;
    
    public BookmarkCategory toEntity() {
        return BookmarkCategory.builder()
                               .bookmarkCategoryId(bookmarkCategoryId)
                               .category(category.toEntity()) 
                               .bookmark(bookmark.toEntity())
                               .build();
    }
}
