package com.mutsa.mini_project.entity;

import com.mutsa.mini_project.entity.embedded.RequiredWriter;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class SalesItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    private String title;

    private String description;

    private String imageUrl;

    private int minPriceWanted;

    @Embedded
    private RequiredWriter requiredWriter;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "salesItem"
    )
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "salesItem"
    )
    private List<Negotiation> negotiations = new ArrayList<>();
}
