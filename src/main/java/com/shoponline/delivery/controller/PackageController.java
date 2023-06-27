package com.shoponline.delivery.controller;

import com.shoponline.delivery.service.PackageService;
import com.shoponline.delivery.view.PackageDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/package")
@Slf4j
@AllArgsConstructor
@Tag(name = "Сервис отслеживания посылок")
public class PackageController {

    private PackageService packageService;

    @Operation(summary = "Отследить посылку по номеру телефона")
    @GetMapping(value = "/{phoneNumber}")
    @ResponseStatus(HttpStatus.FOUND)
    public PackageDto findById(@RequestParam String phoneNumber) {
        return packageService.findByPhoneNumber(phoneNumber);
    }
}
