package com.sampla.samplaapi.Sample;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleRepository extends JpaRepository<Sample, Long> {

    Page<Sample> findAllByResearch_Id(Pageable paging, Long researchId);
}
