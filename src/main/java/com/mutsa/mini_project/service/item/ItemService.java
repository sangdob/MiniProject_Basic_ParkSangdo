package com.mutsa.mini_project.service.item;

import com.mutsa.mini_project.dto.item.*;
import com.mutsa.mini_project.exceptions.ErrorCode;
import com.mutsa.mini_project.exceptions.exception.NoEntityException;
import com.mutsa.mini_project.models.SalesItem;
import com.mutsa.mini_project.models.embedded.RequiredWriter;
import com.mutsa.mini_project.repository.item.ItemRepository;
import com.mutsa.mini_project.upload.ItemImageManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemImageManager imageManager;

    public List<SalesItem> findAll() {
        return itemRepository.findAll();
    }

    public Page<ItemRes> findAll(Pageable pageable) {
        return itemRepository.findItemResAll(pageable);
    }

    @Transactional
    public Long save(ItemCreateReq itemCreateReq) {
        SalesItem save = itemRepository.save(ItemCreateReq.toEntity(itemCreateReq));
        return save.getId();
    }

    @Transactional
    public void save(List<SalesItem> items) {
        itemRepository.saveAll(items);
    }

    public ItemDetailRes findById(Long itemId) {
        return itemRepository.findDetailItemById(itemId)
                .orElseThrow(() -> new NoEntityException(ErrorCode.NOT_FOUND_ENTITY));
    }

    @Transactional
    public void modified(Long id, ItemEditForm editForm) {
        SalesItem item = itemRepository.findSalesItemByIdEqualsAndRequiredWriter(id,
                        RequiredWriter.of(editForm.getWriter(), editForm.getPassword()))
                .orElseThrow(() -> new NoEntityException(ErrorCode.NOT_FOUND_ENTITY));

        item.updateItem(editForm);
    }

    @Transactional
    public void modifiedImage(Long id, ItemImageForm imageForm , MultipartFile file){
        SalesItem item = itemRepository.findSalesItemByIdEqualsAndRequiredWriter(id,
                        RequiredWriter.of(imageForm.getWriter(),
                        imageForm.getPassword()))
                .orElseThrow(() -> new NoEntityException(ErrorCode.NOT_FOUND_ENTITY));

        if (item.getImageUrl() != null) {
            imageManager.delete(item.getImageUrl());
        }

        String imagePath = imageManager.upload(file);
        item.updateImage(imagePath);
//        itemRepository.save(item);
    }

    @Transactional
    public void delete(Long id, ItemDeleteForm req) {
        SalesItem item = itemRepository.findSalesItemByIdEqualsAndRequiredWriter(id,
                        RequiredWriter.of(req.getWriter(), req.getPassword()))
                .orElseThrow(() -> new NoEntityException(ErrorCode.NOT_FOUND_ENTITY));

        itemRepository.delete(item);
    }
}
