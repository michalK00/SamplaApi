package com.sampla.samplaapi.repository;

import com.sampla.samplaapi.entity.Sample;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SampleRepository extends JpaRepository<Sample, Long> {

    Page<Sample> findAllByResearch_Id(Long researchId, Pageable paging);
    List<Sample> findAllByResearch_Id(Long researchId);
    void deleteAllByResearch_Id(Long researchId);
}
