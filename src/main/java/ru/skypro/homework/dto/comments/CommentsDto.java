package ru.skypro.homework.dto.comments;

import lombok.AllArgsConstructor;
import lombok.Data;


import java.util.List;

@Data
@AllArgsConstructor
public class CommentsDto {
    private int count;
    private List<CommentDto> results;
}
