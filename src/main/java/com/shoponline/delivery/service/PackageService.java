package com.shoponline.delivery.service;

import com.shoponline.delivery.exception.EntityNotExistException;
import com.shoponline.delivery.repository.PackageRepository;
import com.shoponline.delivery.view.PackageDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class PackageService {

    private PackageRepository packageRepository;

    public PackageDto findByPhoneNumber(String phoneNumber) {
        var packageDto = packageRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new EntityNotExistException(
                        "Package with phone number: '%s' not found.", phoneNumber)).getDto();
        log.info("Package found");
        return packageDto;
    }
}
