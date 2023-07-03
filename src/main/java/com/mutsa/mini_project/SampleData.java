package com.mutsa.mini_project;

import com.mutsa.mini_project.models.SalesItem;
import com.mutsa.mini_project.models.embedded.RequiredWriter;
import com.mutsa.mini_project.models.status.ItemStatus;
import com.mutsa.mini_project.service.comment.CommentService;
import com.mutsa.mini_project.service.item.ItemService;
import com.mutsa.mini_project.service.negotiation.NegotiationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SampleData {
    private final ItemService itemService;
    private final CommentService commentService;
    private final NegotiationService negotiationService;

    //    @PostConstruct
    public void sampleData() {
        List<SalesItem> items = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            String title = "";
            String description = "";
            int random = (int) (Math.random() * 5) + 1;
            int price = (int) (Math.random() * 5000000) + 1;
            switch (random) {
                case 1:
                    title = "test" + i;
                    description = "test" + i;
                    break;
                case 2:
                    title = "mutsa" + i;
                    description = "mutsa" + i;
                    break;
                case 3:
                    title = "techit" + i;
                    description = "techit" + i;
                    break;
                case 4:
                    title = "man" + i;
                    description = "man" + i;
                    break;
                case 5:
                    title = "woman" + i;
                    description = "woman" + i;
                    break;
            }
            items.add(SalesItem.builder()
                    .title(title)
                    .description(description)
                    .minPriceWanted(price)
                    .imageUrl(null)
                    .requiredWriter(RequiredWriter.of(Integer.valueOf(i).toString(), Integer.valueOf(i).toString()))
                    .status(ItemStatus.SALE)
                    .build());
        }
        itemService.save(items);
    }
}
