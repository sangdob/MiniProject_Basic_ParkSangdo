package com.mutsa.mini_project.service.comment;

import com.mutsa.mini_project.dto.comment.CommentCreatReq;
import com.mutsa.mini_project.dto.comment.CommentEditForm;
import com.mutsa.mini_project.dto.comment.CommentReplyForm;
import com.mutsa.mini_project.dto.comment.CommentRes;
import com.mutsa.mini_project.exceptions.ErrorCode;
import com.mutsa.mini_project.exceptions.exception.InvalidInputException;
import com.mutsa.mini_project.exceptions.exception.NoEntityException;
import com.mutsa.mini_project.exceptions.exception.NotMatchException;
import com.mutsa.mini_project.models.Comment;
import com.mutsa.mini_project.models.SalesItem;
import com.mutsa.mini_project.models.embedded.RequiredWriter;
import com.mutsa.mini_project.repository.comment.CommentRepository;
import com.mutsa.mini_project.repository.item.ItemRepository;
import com.mutsa.mini_project.utils.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
    private final CommentRepository commentRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public void save(Long itemId, CommentCreatReq req) {
        Comment comment = CommentCreatReq.toEntity(req);
        comment.addItem(findItemById(itemId));

        commentRepository.save(comment);
    }
    public Page<CommentRes> findPages(Long itemId, Pageable pageable) {
        if (PageUtils.isOutOfRange(pageable)) {
            throw new InvalidInputException(ErrorCode.INVALID_INPUT_VALUE);
        }

        SalesItem item = findItemById(itemId);

        return commentRepository.findCommentsBySalesItemEquals(item, PageUtils.of(pageable))
                .map(CommentRes::of);
    }

    public CommentRes findDetailCommentById(Long itemId, Long commentId) {
        SalesItem item = findItemById(itemId);
        return commentRepository.findCommentResByIdAndSalesItemEquals(commentId, item)
                .orElseThrow(() -> new NoEntityException(ErrorCode.NOT_FOUND_ENTITY));
    }

    @Transactional
    public void modifiedComment(Long itemId, Long commentId, CommentEditForm commentEdit) {
        SalesItem item = findItemById(itemId);

        Comment comment = commentRepository.findCommentByIdEqualsAndSalesItemEqualsAndRequiredWriterEquals(commentId,
                        item,
                        RequiredWriter.of(commentEdit.getWriter(), commentEdit.getPassword()))
                .orElseThrow(() -> new NoEntityException(ErrorCode.NOT_MATCH_WRITER));

        comment.modify(commentEdit.getContent());
    }

    @Transactional
    public void deletedComment(Long itemId, Long commentId, RequiredWriter requiredWriter) {
        SalesItem item = findItemById(itemId);

        Comment comment = commentRepository.findCommentByIdAndRequiredWriterEquals(commentId, requiredWriter)
                .orElseThrow(() -> new NotMatchException(ErrorCode.NOT_MATCH_WRITER));
        comment.addItem(item);

        commentRepository.delete(comment);
    }

    @Transactional
    public void modifiedReply(Long itemId, Long commentId, CommentReplyForm form) {
        SalesItem item = findSalesItemByIdEqualsAndRequiredWriterEquals(itemId, form);
        Comment comment = commentRepository.findCommentByIdAndSalesItemEquals(commentId, item)
                .orElseThrow(() -> new NotMatchException(ErrorCode.NOT_MATCH_WRITER));

        comment.addItem(item);
        comment.modifyReply(form.getReply());
    }

    private SalesItem findSalesItemByIdEqualsAndRequiredWriterEquals(Long itemId, CommentReplyForm form) {
        return itemRepository.findSalesItemByIdEqualsAndRequiredWriterEquals(itemId,
                        RequiredWriter.of(form.getWriter(), form.getPassword()))
                .orElseThrow(() -> new NotMatchException(ErrorCode.NOT_MATCH_WRITER));
    }

    private SalesItem findItemById(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new NoEntityException(ErrorCode.NOT_FOUND_ENTITY));
    }
}
