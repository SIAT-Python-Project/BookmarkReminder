package category.entity;

import java.util.List;
import java.util.stream.Collectors;

import bookmarkcategory.entity.BookmarkCategory;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import user.entity.User;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;
	
	@Column(name = "category_name")
    private String categoryName;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<BookmarkCategory> bookmarkCategories;
    
    public void changeCategoryName(String newCategoryName) {
    	this.categoryName = newCategoryName;
    }
    
    public CategoryDTO toDTO() {
        return CategoryDTO.builder()
                          .categoryId(categoryId)
                          .categoryName(categoryName)
//                        .bookmarkCategories(bookmarkCategories.stream()
//                                                .map(BookmarkCategory::toDTO)
//                                                .collect(Collectors.toList()))
            .build();
    }
}
