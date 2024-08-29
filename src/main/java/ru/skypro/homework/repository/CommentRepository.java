package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.Comments;

import java.util.List;

/**
 * Репозиторий для получения методов для работы с базой данных комментариев.
 */

@Repository
public interface CommentRepository extends JpaRepository<Comments, Integer> {

    @Query(value = "SELECT * FROM comments " +
            "WHERE ad_id = :AdId",
            nativeQuery = true)
    List<Comments> findAllCommentsByAdId(Integer AdId);

    @Query(value = "SELECT COUNT(comment_id) FROM comments " +
            "WHERE ad_id = :AdId",
            nativeQuery = true)
    Integer countCommentsByAdId(Integer AdId);

    @Query(value = "SELECT MAX(comment_id) FROM comments " +
            "WHERE ad_id = :AdId",
            nativeQuery = true)
    Integer findLastCommentId(Integer AdId);

    @Query(value = "SELECT COUNT(ad_id) FROM ads",
            nativeQuery = true)
    Integer countAdId();

    @Query(value = "SELECT MAX(ad_id) FROM ads",
            nativeQuery = true)
    Integer findLastAdId();
}
