package com.ntneik15.selflearning.retailerapp.service.impl;

import com.ntneik15.selflearning.retailerapp.dto.office.OfficeDto;
import com.ntneik15.selflearning.retailerapp.entity.Office;
import com.ntneik15.selflearning.retailerapp.exception.ConflictException;
import com.ntneik15.selflearning.retailerapp.mapper.OfficeMapper;
import com.ntneik15.selflearning.retailerapp.repository.IOfficeRepository;
import com.ntneik15.selflearning.retailerapp.service.IOfficeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class OfficeServiceImpl implements IOfficeService {
    public final IOfficeRepository officeRepository;
    @Override
    public List<OfficeDto> getAllOffices() {
        return officeRepository.findAll().stream().map(OfficeMapper::toDto).toList();
    }

    @Override
    public OfficeDto getOfficeByOfficeCode(String officeCode) {
        Office foundOffice = officeRepository.findByOfficecode(officeCode);
        if (foundOffice == null) {
         throw new ConflictException("Office with code " + officeCode + " not found");
        }
        return OfficeMapper.toDto(foundOffice);
    }
}
