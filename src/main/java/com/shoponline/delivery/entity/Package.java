package com.shoponline.delivery.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shoponline.delivery.enums.Status;
import com.shoponline.delivery.view.PackageDto;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "package")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder(setterPrefix = "with")
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@EqualsAndHashCode
public class Package {

    @Id
    @GeneratedValue(generator = "custom-uuid")
    @GenericGenerator(
            name = "custom-uuid",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    private UUID id;

    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "phone_number")
    private String phoneNumber;

    public PackageDto getDto() {
        PackageDto packageDto = new PackageDto();
        packageDto.setId(this.id);
        packageDto.setDescription(this.description);
        packageDto.setStatus(this.status.getName());
        packageDto.setPhoneNumber(this.phoneNumber);
        return packageDto;
    }
}
