package memo.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import bookmark.entity.Bookmark;
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
import memo.dto.MemoDTO;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Memo {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memo_id")
    private Long memoId;

    private String comment;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "bookmark_id")
    private Bookmark bookmark;
    
    public void updateComment(String newComment) {
        this.comment = newComment;
    }
    
    public MemoDTO toDTO() {    	
    	
        return MemoDTO.builder()
                      .memoId(memoId)
                      .comment(comment)
                      .createdDate(createdDate)
                      .formattedCreatedDate(createdDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                      .bookmark(bookmark.toDTO())
                      .build();
    }
}
