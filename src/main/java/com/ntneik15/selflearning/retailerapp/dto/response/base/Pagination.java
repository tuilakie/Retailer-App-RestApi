package com.ntneik15.selflearning.retailerapp.dto.response.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Data
@AllArgsConstructor
@Builder
@Slf4j
public class Pagination {
    private int total;
    private int count;
    private int per_page;
    private int current_page;
    private int total_pages;
    private Links links;

    public Pagination(int total, int count, int per_page, int current_page, int total_pages) {
        this.total = total;
        this.count = count;
        this.per_page = per_page;
        this.current_page = current_page;
        this.total_pages = total_pages;
        this.links = createLinksPagination();
    }
    public Links createLinksPagination(){
        boolean isLastPage = current_page == total_pages;
        boolean isFirstPage = current_page == 1;
        boolean isNextPage = current_page < total_pages && current_page >= 1;
        boolean isPrevPage = current_page > 1 && current_page <= total_pages;
//        get current URI ignore query params
        String uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri().toString().split("\\?")[0];
        // ignore query params
        log.info("current uri: {}", uri);
         Links links = Links.builder()
                 .first(isFirstPage ? null : uri + "?page=1&size=" + per_page)
                    .last(isLastPage ? null : uri + "?page=" + total_pages + "&size=" + per_page)
                    .next(isNextPage ? uri + "?page=" + (current_page + 1) + "&size=" + per_page : null)
                    .prev(isPrevPage ? uri + "?page=" + (current_page - 1) + "&size=" + per_page : null)
                    .self(uri + "?page=" + current_page + "&size=" + per_page)
                    .build();
         return links;
    }

    public static Pagination createPaginationWithPageRequest(Page pageRequest, int page, int size){
        return new Pagination(
                (int)pageRequest.getTotalElements(),
                pageRequest.getNumberOfElements(),
                size, page,
                pageRequest.getTotalPages());
    }

}
