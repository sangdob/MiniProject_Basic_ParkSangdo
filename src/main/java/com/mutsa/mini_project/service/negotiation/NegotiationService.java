package com.mutsa.mini_project.service.negotiation;

import com.mutsa.mini_project.contorller.SuccessCode;
import com.mutsa.mini_project.dto.negotiation.ProposalCreateForm;
import com.mutsa.mini_project.dto.negotiation.ProposalEditForm;
import com.mutsa.mini_project.dto.negotiation.ProposalRes;
import com.mutsa.mini_project.dto.negotiation.WriterInfo;
import com.mutsa.mini_project.exceptions.ErrorCode;
import com.mutsa.mini_project.exceptions.exception.InvalidInputException;
import com.mutsa.mini_project.exceptions.exception.NoEntityException;
import com.mutsa.mini_project.exceptions.exception.NotMatchException;
import com.mutsa.mini_project.models.Negotiation;
import com.mutsa.mini_project.models.SalesItem;
import com.mutsa.mini_project.models.embedded.RequiredWriter;
import com.mutsa.mini_project.models.status.ItemStatus;
import com.mutsa.mini_project.models.status.ProposalStatus;
import com.mutsa.mini_project.repository.item.ItemRepository;
import com.mutsa.mini_project.repository.negotiation.NegotiationRepository;
import com.mutsa.mini_project.utils.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NegotiationService {
    private final ItemRepository itemRepository;
    private final NegotiationRepository negotiationRepository;

    @Transactional
    public ProposalRes save(Long itemId, ProposalCreateForm form) {
        SalesItem item = findItemById(itemId);

        Negotiation proposal = ProposalCreateForm.toEntity(form);
        proposal.addItem(item);

        return ProposalRes.of(negotiationRepository.save(proposal));
    }

    public Page<ProposalRes> findPageList(Long itemId, WriterInfo info, Pageable pageable) {
        if (PageUtils.isOutOfRange(pageable)) {
            throw new InvalidInputException(ErrorCode.INVALID_INPUT_VALUE);
        }

        SalesItem item = findItemById(itemId);

        if (itemRepository.existsSalesItemByIdAndRequiredWriterEquals(itemId, RequiredWriter.of(info.getWriter(), info.getPassword()))) {
            return negotiationRepository.findNegotiationsBySalesItemEquals(item, pageable)
                    .map(ProposalRes::of);
        }

        return negotiationRepository.findNegotiationsBySalesItemEqualsAndRequiredWriter(item,
                        RequiredWriter.of(info.getWriter(), info.getPassword()),
                        pageable)
                .map(ProposalRes::of);
    }

    @Transactional
    public SuccessCode modified(Long itemId, Long proposalId, ProposalEditForm form) {
        // 제안 가격 변경 로직
        if (hasPrice(form)) {
            SalesItem item = findItemById(itemId);

            Negotiation negotiation = findNegotiationByIdEqualsAndSalesItemEqualsAndRequiredWriterEquals(proposalId,
                    RequiredWriter.of(form.getWriter(), form.getPassword()),
                    item);
            negotiation.modifyPrice(form.getSuggestedPrice());

            return SuccessCode.SUCCESS_MODIFIED_PROPOSAL;
        }

        // 판매자 수정
        if (!isStatusConfirm(form) && StringUtils.hasText(form.getStatus())) {
            SalesItem item = findSalesItemByIdEqualsAndRequiredWriterEquals(itemId, form);

            Negotiation negotiation = negotiationRepository.findBySalesItemEqualsAndId(item, proposalId)
                    .orElseThrow(() -> new NoEntityException(ErrorCode.NOT_FOUND_ENTITY));

            ProposalStatus status = ProposalStatus.valueOfName(form.getStatus());
            negotiation.addItem(item);
            negotiation.modifyStatus(status);

            return SuccessCode.SUCCESS_MODIFIED_SALLER;
        }

        // 제안 확정 로직 
        if (isStatusConfirm(form)) {
            SalesItem item = findItemById(itemId);
            List<Negotiation> negosList = negotiationRepository.findNegotiationsBySalesItem(item);

            Negotiation validateProposal = negosList.stream()
                    .filter(n -> n.getStatus() == ProposalStatus.ACCEPT)
                    .findAny().orElseThrow(() -> new NoEntityException(ErrorCode.NOT_FOUND_ENTITY));

            if (!validateProposal.isWriterAndPassword(form.getWriter(), form.getPassword())) {
                throw new NotMatchException(ErrorCode.NOT_MATCH_WRITER);
            }

            for (Negotiation nego : negosList) {
                nego.modifyStatus(nego.getStatus() == ProposalStatus.ACCEPT ? ProposalStatus.CONFIRM : ProposalStatus.DECLINE);
            }
            item.modifyStatus(ItemStatus.SOLD_OUT);

            return SuccessCode.SUCCESS_COMPLETED_SALLER;
        }

        return SuccessCode.INTERNAL_SERVER_ERROR;
    }

    @Transactional
    public ProposalRes deleted(Long itemId, Long proposalId, WriterInfo req) {
        SalesItem item = findItemById(itemId);

        Negotiation findProposal = findNegotiationByIdEqualsAndSalesItemEqualsAndRequiredWriterEquals(proposalId, RequiredWriter.of(req.getWriter(), req.getPassword()), item);

        negotiationRepository.delete(findProposal);

        return ProposalRes.of(findProposal);
    }

    private Negotiation findNegotiationByIdEqualsAndSalesItemEqualsAndRequiredWriterEquals(Long proposalId, RequiredWriter rw, SalesItem item) {
        return negotiationRepository.findNegotiationByIdEqualsAndSalesItemEqualsAndRequiredWriterEquals(proposalId, item, rw)
                .orElseThrow(() -> new NotMatchException(ErrorCode.NOT_MATCH_WRITER));
    }

    private SalesItem findItemById(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new NoEntityException(ErrorCode.NOT_FOUND_ENTITY));
    }

    private SalesItem findSalesItemByIdEqualsAndRequiredWriterEquals(Long itemId, ProposalEditForm form) {
        return itemRepository.findSalesItemByIdEqualsAndRequiredWriterEquals(itemId, RequiredWriter.of(form.getWriter(), form.getPassword()))
                .orElseThrow(() -> new NotMatchException(ErrorCode.NOT_MATCH_WRITER));
    }

    private static boolean isStatusConfirm(ProposalEditForm form) {
        return form.getStatus().equals(ProposalStatus.CONFIRM.getName());
    }

    private static boolean hasPrice(ProposalEditForm form) {
        return form.getSuggestedPrice() != null;
    }
}
