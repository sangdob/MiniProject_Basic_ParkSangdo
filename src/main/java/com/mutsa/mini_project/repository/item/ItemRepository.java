package com.mutsa.mini_project.repository.item;

import com.mutsa.mini_project.dto.item.ItemDetailRes;
import com.mutsa.mini_project.dto.item.ItemRes;
import com.mutsa.mini_project.models.SalesItem;
import com.mutsa.mini_project.models.embedded.RequiredWriter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<SalesItem, Long>, ItemRepositoryCustom {

    @Query("select " +
            "new com.mutsa.mini_project.dto.item.ItemRes(s.id, s.title, s.description, s.minPriceWanted, s.imageUrl, s.status) " +
            "from SalesItem s")
    Page<ItemRes> findItemResAll(Pageable pageable);

    @Query("select " +
            "new com.mutsa.mini_project.dto.item.ItemDetailRes(s.title, s.description, s.minPriceWanted, s.imageUrl, s.status) " +
            "from SalesItem as s " +
            "where s.id = :itemId")
    Optional<ItemDetailRes> findDetailItemById(@Param("itemId") Long itemId);

    Optional<SalesItem> findSalesItemByIdEqualsAndRequiredWriter(Long itemId, RequiredWriter requiredWriter);

}
