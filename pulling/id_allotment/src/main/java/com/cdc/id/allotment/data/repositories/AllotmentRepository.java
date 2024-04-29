package com.cdc.id.allotment.data.repositories;

import com.cdc.id.allotment.data.entities.Allotment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AllotmentRepository extends JpaRepository<Allotment, Long> {

    @Query(value = "SELECT a FROM Allotment a WHERE active = false")
    List<Allotment> fetchInactiveInstance();

    Allotment findByInstanceId(String instanceId);
}
