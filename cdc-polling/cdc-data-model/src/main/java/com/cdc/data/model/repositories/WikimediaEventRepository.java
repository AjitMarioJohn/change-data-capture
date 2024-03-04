package com.cdc.data.model.repositories;

import com.cdc.data.model.entities.WikimediaEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WikimediaEventRepository extends JpaRepository<WikimediaEvent, Integer> {
}