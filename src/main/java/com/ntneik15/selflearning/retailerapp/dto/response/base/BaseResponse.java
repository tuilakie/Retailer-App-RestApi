package com.ntneik15.selflearning.retailerapp.dto.response.base;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@RequiredArgsConstructor

public class BaseResponse {
    private Meta meta;
    private Object data;

    public BaseResponse(Meta meta) {
        this.meta = meta;
    }

    public BaseResponse(Meta meta, Object data) {
        this.meta = meta;
        this.data = data;
    }
}
