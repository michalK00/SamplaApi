package com.sampla.samplaapi.Research;

import com.sampla.samplaapi.Research.ResearchDto.ResearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

@Controller
@RequestMapping("/researches")
public class ResearchController {

    private final ResearchService researchService;

    public ResearchController(ResearchService researchService) {
        this.researchService = researchService;
    }

    @GetMapping("/{id}")
    ResponseEntity<ResearchDto> getResearchById(@PathVariable Long id){
        return researchService.getResearchById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping
    ResponseEntity<Page<ResearchDto>> getResearches(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size,
            @RequestParam(defaultValue = "None") String statusFilter){
        Pageable paging = PageRequest.of(page, size);
        Page<ResearchDto> returnedPage;
        if(statusFilter.equals("None")){
            returnedPage = researchService.getAllResearches(paging);
        } else {
            returnedPage = researchService.getAllResearchesWhereStatusIs(statusFilter, paging);
        }
        return ResponseEntity.ok(returnedPage);
    }
    @PostMapping
    ResponseEntity<ResearchDto> saveResearch(@RequestBody ResearchDto research) {
        ResearchDto savedResearch = researchService.saveResearch(research);
        URI savedResearchURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedResearch.getId())
                .toUri();
        return ResponseEntity.created(savedResearchURI).body(savedResearch);
    }
}
