package com.mutsa.mini_project.models;

import com.mutsa.mini_project.models.embedded.RequiredWriter;
import com.mutsa.mini_project.models.status.ItemStatus;
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

    private String suggestedPrice;

    @Enumerated(EnumType.STRING)
    private ItemStatus status;

    @Embedded
    private RequiredWriter requiredWriter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id",
            foreignKey = @ForeignKey(name = "FK_NEGOTIATION_ITEM"))
    private SalesItem salesItem;
}
