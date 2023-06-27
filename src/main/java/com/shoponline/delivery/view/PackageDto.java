package com.shoponline.delivery.view;

import com.shoponline.delivery.entity.Package;
import com.shoponline.delivery.enums.Status;
import lombok.Data;

import java.util.UUID;

@Data
public class PackageDto {

    private UUID id;

    private String description;

    private String status;

    private String phoneNumber;

    public Package fromDto() {
        Package pack = new Package();
        return pack.builder()
                .withDescription(this.description)
                .withStatus(Status.valueOf(this.status))
                .withPhoneNumber(this.phoneNumber)
                .build();
    }
}
