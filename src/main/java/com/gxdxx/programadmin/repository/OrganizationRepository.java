package com.gxdxx.programadmin.repository;

import com.gxdxx.programadmin.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    List<Organization> findByOrganizationNameContains(String organizationName);

}
