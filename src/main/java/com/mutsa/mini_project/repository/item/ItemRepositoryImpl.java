package com.mutsa.mini_project.repository.item;

import com.mutsa.mini_project.models.SalesItem;
import com.mutsa.mini_project.repository.support.Querydsl4RepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepositoryImpl extends Querydsl4RepositorySupport implements ItemRepositoryCustom{
    public ItemRepositoryImpl() {
        super(SalesItem.class);
    }

//    @Override
//    public List<ItemRes> findAllItems() {
//        return null;
//    }
//
//    @Override
//    public ItemRes findItemById(Long id) {
//        return null;
//    }
}
