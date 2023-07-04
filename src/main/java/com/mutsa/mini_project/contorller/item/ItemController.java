package com.mutsa.mini_project.contorller.item;

import com.mutsa.mini_project.contorller.PageResponse;
import com.mutsa.mini_project.contorller.Response;
import com.mutsa.mini_project.contorller.SuccessCode;
import com.mutsa.mini_project.dto.item.*;
import com.mutsa.mini_project.service.item.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    @GetMapping
    public ResponseEntity<PageResponse<ItemRes>> pageLists(
            @PageableDefault(page = 1,
            size = 25,
            sort = "id",
            direction = Sort.Direction.DESC) Pageable pageable) {
        PageRequest pr = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize(), pageable.getSort());

        return ResponseEntity.status(OK).body(new PageResponse<>(itemService.findAll(pr)));
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<ItemDetailRes> findDetail(@PathVariable(name = "itemId") Long itemId) {
        ItemDetailRes item = itemService.findDetailItemById(itemId);

        return ResponseEntity.status(OK).body(item);
    }

    @PostMapping
    public ResponseEntity<Response> created(@RequestBody(required = false) ItemCreateReq itemCreateReq) {
        itemService.save(itemCreateReq);

        Response response = Response.of(SuccessCode.SUCCESS_CREATED_ITEM);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @PutMapping("{itemId}")
    public ResponseEntity<Response> modified(@PathVariable Long itemId,
                                             @RequestBody @Valid ItemEditForm itemEditReq) {
        itemService.modified(itemId, itemEditReq);
                Response response = Response.of(SuccessCode.SUCCESS_MODIFIED_ITEM);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @PutMapping("{itemId}/image")
    public ResponseEntity<Response> modifiedImage(@PathVariable Long itemId,
                                                  @RequestPart MultipartFile file,
                                                  @ModelAttribute ItemImageForm imageForm) {
        itemService.modifiedImage(itemId, imageForm, file);

        Response response = Response.of(SuccessCode.SUCCESS_MODIFIED_IMAGE);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @DeleteMapping("{itemId}")
    public ResponseEntity<Response> deleted(@PathVariable Long itemId,
                                            @RequestBody @Valid ItemDeleteForm deleteReq) {
        itemService.delete(itemId, deleteReq);
        Response response = Response.of(SuccessCode.SUCCESS_DELETED_ITEM);
        return new ResponseEntity<>(response, response.getStatus());
    }
}
