package category.dto;

import java.util.List;
import java.util.stream.Collectors;

import bookmarkcategory.dto.BookmarkCategoryDTO;
import category.entity.Category;
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
public class CategoryDTO {
	private Long categoryId;
    private String categoryName;
//  private List<BookmarkCategoryDTO> bookmarkCategories;
    
    public Category toEntity() {
        return Category.builder()
            .categoryId(categoryId)
            .categoryName(categoryName)
//          .bookmarkCategories(bookmarkCategories.stream()
//                                                .map(BookmarkCategoryDTO::toEntity)
//                                                .collect(Collectors.toList()))
            .build();
    }
}
