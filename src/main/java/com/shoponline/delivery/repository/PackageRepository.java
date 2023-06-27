package com.shoponline.delivery.repository;

import com.shoponline.delivery.entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PackageRepository extends JpaRepository<Package, UUID>, JpaSpecificationExecutor<Package> {

    Optional<Package> findByPhoneNumber(String phoneNumber);
}
