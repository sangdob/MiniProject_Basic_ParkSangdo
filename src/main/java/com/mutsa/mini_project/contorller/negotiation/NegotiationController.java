package com.mutsa.mini_project.contorller.negotiation;

import com.mutsa.mini_project.contorller.PageResponse;
import com.mutsa.mini_project.contorller.Response;
import com.mutsa.mini_project.contorller.SuccessCode;
import com.mutsa.mini_project.dto.negotiation.ProposalCreateForm;
import com.mutsa.mini_project.dto.negotiation.ProposalEditForm;
import com.mutsa.mini_project.dto.negotiation.ProposalRes;
import com.mutsa.mini_project.dto.negotiation.WriterInfo;
import com.mutsa.mini_project.models.Negotiation;
import com.mutsa.mini_project.service.negotiation.NegotiationService;
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

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/items/{itemId}/proposals")
public class NegotiationController {
    private final NegotiationService negotiationService;

    @GetMapping
    public ResponseEntity<PageResponse<ProposalRes>> pagesList(@PathVariable("itemId") Long itemId,
                                                               @Valid WriterInfo info,
                                                               @PageableDefault(page = 1,
                                                                       size = 25,
                                                                       sort = "id",
                                                                       direction = Sort.Direction.ASC) Pageable pageable
    ) {
        Page<ProposalRes> pages = negotiationService.findPageList(itemId, info, PageUtils.of(pageable));
        log.info("SUCCESS = {}", pages.getContent());

        return ResponseEntity.status(OK).body(new PageResponse<>(pages));
    }

    @PostMapping
    public ResponseEntity<Response> created(@PathVariable("itemId") Long itemId,
                                            @RequestBody(required = false) @Valid ProposalCreateForm req
    ) {
        ProposalRes negotiation = negotiationService.save(itemId, req);

        log.info("SUCCESS = {}", negotiation.toString());

        Response response = Response.of(SuccessCode.SUCCESS_CREATED_PROPOSAL);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @PutMapping("/{proposalId}")
    public ResponseEntity<Response> modified(@PathVariable("itemId") Long itemId,
                                            @PathVariable("proposalId") Long proposalId,
                                            @RequestBody(required = false) @Valid ProposalEditForm req
    ) {
        SuccessCode successCode = negotiationService.modified(itemId, proposalId, req);
        Response response = Response.of(successCode);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @DeleteMapping("/{proposalId}")
    public ResponseEntity<Response> modified(@PathVariable("itemId") Long itemId,
                                             @PathVariable("proposalId") Long proposalId,
                                             @RequestBody @Valid WriterInfo req) {
        ProposalRes proposal = negotiationService.deleted(itemId, proposalId, req);
        log.info("SUCCESS = {}", proposal.toString());

        Response response = Response.of(SuccessCode.SUCCESS_DELETED_PROPOSAL);
        return new ResponseEntity<>(response, response.getStatus());
    }
}
