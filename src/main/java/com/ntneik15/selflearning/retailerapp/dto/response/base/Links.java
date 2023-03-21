package com.ntneik15.selflearning.retailerapp.dto.response.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Links {
    private String first;
    private String last;
    private String prev;
    private String next;
    private String self;

    // builder
    public static class LinksBuilder {
        private String first;
        private String last;
        private String prev;
        private String next;
        private String self;
    }
}
