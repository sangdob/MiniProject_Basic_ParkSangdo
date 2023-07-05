package com.mutsa.mini_project.models;

import com.mutsa.mini_project.dto.item.ItemEditForm;
import com.mutsa.mini_project.models.embedded.RequiredWriter;
import com.mutsa.mini_project.models.status.ItemStatus;
import com.mutsa.mini_project.repository.base.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class SalesItem extends BaseTimeEntity {
    private static final String ITEM_IMAGE_URL = "/images/" + "items/"+ LocalDate.now();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    private String imageUrl;

    @Column(nullable = false)
    private int minPriceWanted;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ItemStatus status;

    @Embedded
    private RequiredWriter requiredWriter;

    @OneToMany(mappedBy = "salesItem",
            cascade = CascadeType.ALL
    )
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "salesItem",
            cascade = CascadeType.ALL
    )
    private List<Negotiation> negotiations = new ArrayList<>();

    /* business logic */
    public SalesItem addComment(Comment comment) {
        comment.addItem(this);
        this.comments.add(comment);
        return this;
    }

    public SalesItem addNegotiation(Negotiation negotiation) {
        negotiation.addItem(this);
        this.negotiations.add(negotiation);
        return this;
    }

    public SalesItem modifyItem(ItemEditForm editForm) {
        this.title = editForm.getTitle();
        this.description = editForm.getDescription();
        this.minPriceWanted = editForm.getMinPriceWanted();
        return this;
    }

    public SalesItem modifyImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public SalesItem modifyStatus(ItemStatus status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return "SalesItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", minPriceWanted=" + minPriceWanted +
                ", status=" + status +
                ", requiredWriter=" + requiredWriter +
                ", comments=" + comments +
                ", negotiations=" + negotiations +
                '}';
    }
}
