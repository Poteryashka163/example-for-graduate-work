package ru.skypro.homework.dto.comments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.model.Comments;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Класс-обертка для получения комментария к объявлению.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private int author;
    private int pk;
    private LocalDateTime createdAt;
    private String authorImage;
    private String authorFirstName;
    private String text;

    public static CommentDto fromComment(Comments comments) {
        CommentDto commentDto = new CommentDto();
        commentDto.setPk(comments.getPkComment());
        commentDto.setAuthor(comments.getUser().getId());
        Optional.ofNullable(comments.getUser().getImageUser()).ifPresent(image -> commentDto.setAuthorImage(
                "/users/" + comments.getUser().getImageUser().getId() + "/image"));
        commentDto.setAuthorFirstName(comments.getUser().getFirstName());
        commentDto.setCreatedAt(comments.getCreatedAt());
        commentDto.setText(comments.getText());
        return commentDto;
    }

    public Comments toComment() {
        Comments comment = new Comments();
        comment.setPkComment(this.getPk());
        comment.setCreatedAt(this.getCreatedAt());
        comment.setText(this.getText());

        return comment;
    }
}
