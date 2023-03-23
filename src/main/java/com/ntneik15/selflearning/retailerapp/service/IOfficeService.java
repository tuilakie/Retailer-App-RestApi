package com.ntneik15.selflearning.retailerapp.service;

import com.ntneik15.selflearning.retailerapp.dto.office.OfficeDto;

import java.util.List;

public interface IOfficeService {
    public List<OfficeDto> getAllOffices();
    public OfficeDto getOfficeByOfficeCode(String officeCode);

}
