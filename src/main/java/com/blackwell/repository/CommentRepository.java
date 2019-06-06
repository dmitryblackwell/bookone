package com.blackwell.repository;

import com.blackwell.entity.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {
    List<Comment> findAllByUsername(String username);
    List<Comment> findAllByIsbn(long isbn);

    @Query("SELECT AVG(score) FROM Comment c WHERE c.isbn=:isbn")
    Float getAvgScoreByIsbn(long isbn);

}
