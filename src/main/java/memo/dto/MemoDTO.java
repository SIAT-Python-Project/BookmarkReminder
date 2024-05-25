package memo.dto;

import java.time.LocalDateTime;

import bookmark.dto.BookmarkDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import memo.entity.Memo;

@NoArgsConstructor
@AllArgsConstructor
@Builder

@Setter
@Getter
public class MemoDTO {
	private Long memoId;
    private String comment;
    private LocalDateTime createdDate;
    private BookmarkDTO bookmark;
    
    public Memo toEntity() {
        return Memo.builder()
                   .memoId(memoId)
                   .comment(comment)
                   .createdDate(createdDate)
                   .bookmark(bookmark.toEntity())
                   .build();
    }
}
