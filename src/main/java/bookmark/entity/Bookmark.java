package bookmark.entity;

import java.time.LocalDateTime;
import java.util.List;

import bookmarkcategory.entity.BookmarkCategory;
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
import memo.entity.Memo;
import user.entity.User;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bookmark {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookmark_id")
    private Long bookmarkId;

    @Column(name = "bookmark_name")
    private String bookmarkName;

    private String url;
    
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "bookmark")
    private List<BookmarkCategory> bookmarkCategories;

    @OneToMany(mappedBy = "bookmark")
    private List<Memo> memos;
}
