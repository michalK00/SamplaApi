package com.sampla.samplaapi.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.sampla.samplaapi.dto.base.ResearchDto;
import com.sampla.samplaapi.dto.brief.ResearchBriefDto;
import com.sampla.samplaapi.dto.create.CreateResearchDto;
import com.sampla.samplaapi.rest.exceptions.ResearchDeletionException;
import com.sampla.samplaapi.service.ResearchService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/researches")
public class ResearchController {

    private final ResearchService researchService;
    private final ObjectMapper objectMapper;

    public ResearchController(ResearchService researchService, ObjectMapper objectMapper) {
        this.researchService = researchService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/{id}")
    ResponseEntity<ResearchDto> getResearchById(@PathVariable Long id){
        return researchService.getResearchById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping
    ResponseEntity<List<ResearchBriefDto>> getResearches(){
        List<ResearchBriefDto> returnedList = researchService.getAllResearchesBrief();
        return ResponseEntity.ok(returnedList);
    }
    @GetMapping("/paging")
    ResponseEntity<Page<ResearchBriefDto>> getResearches(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size,
            @RequestParam(defaultValue = "None") String statusFilter){
        Pageable paging = PageRequest.of(page, size);
        Page<ResearchBriefDto> returnedPage;

        if(statusFilter.equals("None")){
            returnedPage = researchService.getAllResearchesBrief(paging);
        } else {
            returnedPage = researchService.getAllResearchesBriefWhereStatusIs(statusFilter, paging);
        }

        return ResponseEntity.ok(returnedPage);
    }
    @PostMapping
    ResponseEntity<ResearchBriefDto> saveResearch(@Valid @RequestBody CreateResearchDto research) {

        ResearchBriefDto savedResearch = researchService.createResearch(research);

        URI savedResearchURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedResearch.getId())
                .toUri();
        return ResponseEntity.created(savedResearchURI).body(savedResearch);
    }
    @PatchMapping("/{id}")
    ResponseEntity<?> updateResearch(@PathVariable Long id, @RequestBody JsonMergePatch patch){
        try {
            ResearchDto researchDto = researchService.getResearchById(id).orElseThrow();
            ResearchDto researchPatched = applyPatch(researchDto, patch);
            researchService.updateResearch(researchPatched);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.internalServerError().build();
        } catch (NoSuchElementException e ) {
            return ResponseEntity.notFound().build();
        }
        return  ResponseEntity.noContent().build();
    }

    private ResearchDto applyPatch(ResearchDto researchDto, JsonMergePatch patch) throws JsonPatchException, JsonProcessingException {
        JsonNode researchNode = objectMapper.valueToTree(researchDto);
        JsonNode researchPatchedNode = patch.apply(researchNode);
        return objectMapper.treeToValue(researchPatchedNode, ResearchDto.class);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteResearch(@PathVariable Long id){
        try {
            ResearchDto researchDto = researchService.getResearchById(id).orElseThrow();
            if (!researchDto.getSampleList().isEmpty()) {
                throw new ResearchDeletionException("Cannot delete research with samples");
            }
            researchService.deleteResearch(id);
        }catch (NoSuchElementException e ) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.noContent().build();
    }


}
