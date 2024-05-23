package category.entity;

import java.util.List;

import bookmarkcategory.entity.BookmarkCategory;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor
public class Category {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;
	
	@Column(name = "category_name")
    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<BookmarkCategory> bookmarkCategories;
    
    public void changeCategoryName(String newCategoryName) {
    	this.categoryName = newCategoryName;
    }

	public Category(String categoryName, List<BookmarkCategory> bookmarkCategories) {
		super();
		this.categoryName = categoryName;
		this.bookmarkCategories = bookmarkCategories;
	}
}
