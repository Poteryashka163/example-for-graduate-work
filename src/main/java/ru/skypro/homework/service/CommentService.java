package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.comments.CommentDto;
import ru.skypro.homework.dto.comments.CommentsDto;
import ru.skypro.homework.dto.comments.CreateOrUpdateCommentDto;

/**
 * Интерфейс с методами получения, добавления, изменения, удаления комментариев к объявлениям.
 */
public interface CommentService {
    CommentsDto getComments(Integer adId, Authentication authentication);

    CommentDto addComment(Integer id, CreateOrUpdateCommentDto createOrUpdateComment, Authentication authentication);

    void deleteComment(Integer adId, Integer commentId, Authentication authentication);

    CommentDto updateComment(Integer adId, Integer commentId, CreateOrUpdateCommentDto createOrUpdateComment, Authentication authentication);
}
