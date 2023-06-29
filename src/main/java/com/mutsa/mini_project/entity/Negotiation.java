package com.mutsa.mini_project.entity;

import com.mutsa.mini_project.entity.embedded.RequiredWriter;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Negotiation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "negotiation_id")
    private Long id;

    private String suggestedPrice;

    @Embedded
    private RequiredWriter requiredWriter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id",
            foreignKey = @ForeignKey(name = "FK_NEGOTIATION_ITEM"))
    private SalesItem salesItem;


}
