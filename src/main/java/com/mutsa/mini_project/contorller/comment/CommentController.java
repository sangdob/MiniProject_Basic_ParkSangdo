package com.mutsa.mini_project.contorller.comment;

import com.mutsa.mini_project.contorller.PageResponse;
import com.mutsa.mini_project.contorller.Response;
import com.mutsa.mini_project.contorller.SuccessCode;
import com.mutsa.mini_project.dto.comment.CommentCreatReq;
import com.mutsa.mini_project.dto.comment.CommentEditForm;
import com.mutsa.mini_project.dto.comment.CommentReplyForm;
import com.mutsa.mini_project.dto.comment.CommentRes;
import com.mutsa.mini_project.models.embedded.RequiredWriter;
import com.mutsa.mini_project.service.comment.CommentService;
import com.mutsa.mini_project.utils.PageUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/items/{itemId}/comments")
public class CommentController {
    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<PageResponse<CommentRes>> pageLists(@PathVariable(name = "itemId") Long itemId,
                                                              @PageableDefault(page = 1,
                                                                      size = 25,
                                                                      sort = "id",
                                                                      direction = Sort.Direction.ASC) Pageable pageable
    ) {
        Page<CommentRes> pages = commentService.findPages(itemId, PageUtils.of(pageable));

        return ResponseEntity.status(OK).body(new PageResponse<>(pages));
    }

    @PostMapping
    public ResponseEntity<Response> created(@PathVariable Long itemId,
                                            @RequestBody @Valid CommentCreatReq req
    ) {
        commentService.save(itemId, req);

        Response response = Response.of(SuccessCode.SUCCESS_CREATED_COMMENT);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentRes> detailComment(@PathVariable(name = "itemId") Long itemId,
                                              @PathVariable(name = "commentId") Long commentId
    ) {
        return new ResponseEntity<>(commentService.findDetailCommentById(itemId, commentId), OK);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<Response> pageLists(@PathVariable(name = "itemId") Long itemId,
                                              @PathVariable(name = "commentId") Long commentId,
                                              @RequestBody @Valid CommentEditForm commentEdit
    ) {
        commentService.modifiedComment(itemId, commentId, commentEdit);

        Response response = Response.of(SuccessCode.SUCCESS_MODIFIED_COMMENT);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Response> deleted(@PathVariable(name = "itemId") Long itemId,
                                            @PathVariable(name = "commentId") Long commentId,
                                            @RequestBody @Valid RequiredWriter requiredWriter
    ) {
        commentService.deletedComment(itemId, commentId, requiredWriter);

        Response response = Response.of(SuccessCode.SUCCESS_DELETED_COMMENT);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @PutMapping("/{commentId}/reply")
    public ResponseEntity<Response> modifiedReply(@PathVariable(name = "itemId") Long itemId,
                                                  @PathVariable(name = "commentId") Long commentId,
                                                  @RequestBody @Valid CommentReplyForm form
    ) {
        commentService.modifiedReply(itemId, commentId, form);

        Response response = Response.of(SuccessCode.SUCCESS_MODIFIED_REPLY);
        return new ResponseEntity<>(response, response.getStatus());
    }
}
