package com.ntneik15.selflearning.retailerapp.dto.response.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Meta {
    private Boolean Success;
    private String Message;
    private HttpStatus Status;

}
