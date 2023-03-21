package com.ntneik15.selflearning.retailerapp.dto.response.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
public class PaginationResponse extends BaseResponse{
    public PaginationResponse(Meta meta, Object data, Pagination pagination) {
        super(meta, data);
        this.pagination = pagination;
    }
    private
    Pagination pagination;
}
