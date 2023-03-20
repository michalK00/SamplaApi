package com.sampla.samplaapi.Research;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResearchRepository extends JpaRepository<Research, Long> {
    Page<Research> findAllByStatus(Research.Status status, Pageable paging);
}
