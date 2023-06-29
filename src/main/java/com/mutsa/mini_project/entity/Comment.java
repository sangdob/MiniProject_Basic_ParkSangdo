package com.mutsa.mini_project.entity;

import com.mutsa.mini_project.entity.embedded.RequiredWriter;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Embedded
    private RequiredWriter requiredWriter;

    private String content;

    private String reply;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id",
            foreignKey = @ForeignKey(name = "FK_COMMENT_ITEM")
    )
    private SalesItem salesItem;
}
