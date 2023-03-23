package com.ntneik15.selflearning.retailerapp.dto.auth;

import com.ntneik15.selflearning.retailerapp.dto.response.base.BaseResponse;
import com.ntneik15.selflearning.retailerapp.dto.response.base.Meta;
import com.ntneik15.selflearning.retailerapp.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse extends BaseResponse {
    private String AccessToken;

    public AuthResponse(Meta meta, User user, String token) {
        super(meta, user);
        this.AccessToken = token;
    }
    //private String RefreshToken;
}
