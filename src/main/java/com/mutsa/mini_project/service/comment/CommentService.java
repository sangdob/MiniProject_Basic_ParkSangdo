package com.mutsa.mini_project.service.comment;

import com.mutsa.mini_project.repository.item.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
}
