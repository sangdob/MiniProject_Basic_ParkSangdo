package com.mutsa.mini_project.repository.comment;

import com.mutsa.mini_project.dto.comment.CommentRes;
import com.mutsa.mini_project.models.Comment;
import com.mutsa.mini_project.models.SalesItem;
import com.mutsa.mini_project.models.embedded.RequiredWriter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<CommentRes> findCommentByIdAndSalesItemEquals(Long commentId, SalesItem item);

//    @Query(value = "select c " +
//            "from Comment c " +
//            "join fetch c.salesItem s " +
//            "where c.salesItem.id =:#{item.id}",
//            countQuery = "select count(c) from Comment c " +
//                    "join fetch c.salesItem s " +
//                    "where c.salesItem.id =:#{item.id}"
//    )
    Page<Comment> findCommentsBySalesItemEquals(SalesItem item, Pageable pageable);
    Optional<Comment> findCommentByIdEqualsAndSalesItemEqualsAndRequiredWriterEquals(Long commentId, SalesItem item, RequiredWriter requiredWriter);

    Optional<Comment> findCommentByIdAndRequiredWriterEquals(Long commentId, RequiredWriter requiredWriter);
}
