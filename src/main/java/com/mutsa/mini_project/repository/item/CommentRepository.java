package com.mutsa.mini_project.repository.item;

import com.mutsa.mini_project.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
