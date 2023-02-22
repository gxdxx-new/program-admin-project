package com.gxdxx.programadmin.repository;

import com.gxdxx.programadmin.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface PositionRepository extends JpaRepository<Position, Long> {

    ArrayList<Position> findByOrganization_Id(Long organizationId);

}
