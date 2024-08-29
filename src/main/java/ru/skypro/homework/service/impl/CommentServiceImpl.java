package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.comments.CommentDto;
import ru.skypro.homework.dto.comments.CommentsDto;
import ru.skypro.homework.dto.comments.CreateOrUpdateCommentDto;
import ru.skypro.homework.exception.AccessErrorException;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.exception.CommentNotFoundException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comments;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;

import java.time.LocalDateTime;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс, содержащий методы получения, добавления, изменения, удаления комментариев к объявлениям.
 */
@Service
public class CommentServiceImpl implements CommentService {
    private static final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);
    private final CommentRepository commentRepository;
    private final AdsRepository adsRepository;
    private final UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, AdsRepository adsRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.adsRepository = adsRepository;
        this.userRepository = userRepository;
    }

    /**
     * Внутренний метод класса, проверяющий право пользователя на изменение или удаление комментария.
     */
    private boolean isAdminOrOwnerComment(Authentication authentication, String ownerComment) {
        boolean isAdmin = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList())
                .contains("ROLE_ADMIN");

        boolean isOwnerComment = authentication.getName().equals(ownerComment);

        return isAdmin || isOwnerComment;

    }

    /**
     * Публичный метод получения всех комментариев объявления и их количества.
     */
    @Override
    public CommentsDto getComments(Integer adId, Authentication authentication) {
        if (authentication.isAuthenticated()) {
            List<CommentDto> commentDtoList = commentRepository.findAllCommentsByAdId(adId)
                    .stream()
                    .map(CommentDto::fromComment)
                    .collect(Collectors.toList());
            return new CommentsDto(commentDtoList.size(), commentDtoList);
        } else {
            throw new AccessErrorException();
        }

    }

    /**
     * Публичный метод добавления комментария к объявлению.
     */
    @Override
    public CommentDto addComment(Integer id, CreateOrUpdateCommentDto createOrUpdateCommentDto, Authentication authentication) {

        if (authentication.isAuthenticated()) {
            String username = authentication.getName();

            Ad getAd = adsRepository.findAdByPk(id).orElseThrow(AdNotFoundException::new);
            User meUser = userRepository.findByUsername(username)
                    .orElseThrow(UserNotFoundException::new);
            Comments newComments = new Comments();

            newComments.setUser(meUser);
            newComments.setAd(getAd);
            newComments.setText(createOrUpdateCommentDto.getTextCreateOrUpdateCommentDto());
            newComments.setCreatedAt(LocalDateTime.now());

            CommentDto commentDTO = CommentDto.fromComment(commentRepository.save(newComments));

            return commentDTO;
        } else {
            throw new AccessErrorException();
        }
    }

    /**
     * Публичный метод удаления комментария к объявлению, доступен только автору комментария и администратору.
     */
    @Override
    public void deleteComment(Integer adId, Integer commentId, Authentication authentication) {
        if (authentication.isAuthenticated()) {
            Comments findComment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
            if (!adId.equals(findComment.getAd().getPkAd())) {
                throw new CommentNotFoundException();
            } else {
                if (isAdminOrOwnerComment(authentication, findComment.getUser().getEmail())) {
                    commentRepository.delete(findComment);
                } else {
                    throw new AccessErrorException();
                }
            }
        } else {
            throw new AccessErrorException();
        }
    }

    /**
     * Публичный метод изменения комментария к объявлению, доступен только автору комментария и администратору.
     */
    @Override
    public CommentDto updateComment(Integer adId, Integer commentId, CreateOrUpdateCommentDto createOrUpdateCommentDto, Authentication authentication) {
        if (authentication.isAuthenticated()) {
            Comments findComment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
            if (!adId.equals(findComment.getAd().getPkAd())) {
                throw new CommentNotFoundException();
            } else {

                if (isAdminOrOwnerComment(authentication, findComment.getUser().getEmail())) {
                    findComment.setText(createOrUpdateCommentDto.getTextCreateOrUpdateCommentDto());
                    findComment.setCreatedAt(LocalDateTime.now());

                    CommentDto commentDto = CommentDto.fromComment(commentRepository.save(findComment));
                    return commentDto;
                } else {
                    throw new AccessErrorException();
                }

            }
        } else {
            throw new AccessErrorException();
        }
    }
}
