package com.ntneik15.selflearning.retailerapp.mapper;

import com.ntneik15.selflearning.retailerapp.dto.OfficeDto;
import com.ntneik15.selflearning.retailerapp.entity.Office;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OfficeMapper {
    public static OfficeDto toDto(Office office) {
        return OfficeDto.builder()
                .officeCode(office.getOfficecode())
                .city(office.getCity())
                .phone(office.getPhone())
                .addressLine1(office.getAddressline1())
                .addressLine2(office.getAddressline2())
                .state(office.getState())
                .country(office.getCountry())
                .postalCode(office.getPostalcode())
                .territory(office.getTerritory())
                .build();
    }

    public static Office toEntity(OfficeDto officeDto) {
        return Office.builder()
                .officecode(officeDto.getOfficeCode())
                .city(officeDto.getCity())
                .phone(officeDto.getPhone())
                .addressline1(officeDto.getAddressLine1())
                .addressline2(officeDto.getAddressLine2())
                .state(officeDto.getState())
                .country(officeDto.getCountry())
                .postalcode(officeDto.getPostalCode())
                .territory(officeDto.getTerritory())
                .build();
    }
}
