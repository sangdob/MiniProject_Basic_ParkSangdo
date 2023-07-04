package com.mutsa.mini_project.models;

import com.mutsa.mini_project.models.embedded.RequiredWriter;
import com.mutsa.mini_project.repository.base.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.util.StringUtils;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Comment extends BaseTimeEntity {
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

    /* business logic */
    public Comment addItem(SalesItem item) {
        this.salesItem = item;
        return this;
    }

    public void modifyReply(String reply) {
        if (StringUtils.hasText(reply)) {
            this.reply = reply;
        }
    }
}
