package com.mutsa.mini_project.models;

import com.mutsa.mini_project.models.embedded.RequiredWriter;
import com.mutsa.mini_project.models.status.ProposalStatus;
import com.mutsa.mini_project.repository.base.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Negotiation extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "negotiation_id")
    private Long id;

    @Column(nullable = false)
    private int suggestedPrice;

    @Embedded
    private RequiredWriter requiredWriter;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProposalStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private SalesItem salesItem;

    /* Business Logic */

    public Negotiation addItem(SalesItem item) {
        this.salesItem = item;
        return this;
    }

    public Negotiation modifyPrice(int suggestedPrice) {
        if (suggestedPrice > 0) {
            this.suggestedPrice = suggestedPrice;
        }
        return this;
    }

    public Negotiation modifyStatus(ProposalStatus status) {
        this.status = status;
        return this;
    }

    public boolean isWriterAndPassword(String writer, String password) {
        return this.requiredWriter.getWriter().equals(writer) && this.requiredWriter.getPassword().equals(password);
    }

    @Override
    public String toString() {
        return "Negotiation{" +
                "id=" + id +
                ", suggestedPrice=" + suggestedPrice +
                ", requiredWriter=" + requiredWriter +
                ", salesItem=" + salesItem.getId() +
                '}';
    }
}
