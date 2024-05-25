package bookmarkcategory.entity;

import bookmark.entity.Bookmark;
import bookmarkcategory.dto.BookmarkCategoryDTO;
import category.entity.Category;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookmarkCategory {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookmark_category_id")
    private Long bookmarkCategoryId;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "bookmark_id")
    private Bookmark bookmark;
    
    public BookmarkCategoryDTO toDTO() {
        return BookmarkCategoryDTO.builder()
                                  .bookmarkCategoryId(bookmarkCategoryId)
                                  .category(category.toDTO()) 
                                  .bookmark(bookmark.toDTO())
                                  .build();
    }
}
