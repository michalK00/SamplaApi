package com.sampla.samplaapi.rest;

import com.sampla.samplaapi.dto.base.ResearchDto;
import com.sampla.samplaapi.dto.brief.ResearchBriefDto;
import com.sampla.samplaapi.dto.create.CreateResearchDto;
import com.sampla.samplaapi.dto.update.UpdateResearchDto;
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
    ResponseEntity<List<ResearchBriefDto>> getResearches(){
        List<ResearchBriefDto> returnedList = researchService.getAllResearchesBrief();
        return ResponseEntity.ok(returnedList);
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

    @PutMapping("/{researchId}")
    ResponseEntity<?> updateResearch(@PathVariable Long researchId, @RequestBody UpdateResearchDto updateResearchDto){
        researchService.updateResearch(updateResearchDto, researchId);
        return  ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteResearch(@PathVariable Long id){
        researchService.deleteResearch(id);
        return ResponseEntity.noContent().build();
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
//    @PatchMapping("/{id}")
//    ResponseEntity<?> updateResearch(@PathVariable Long id, @RequestBody JsonMergePatch patch){
//        try {
//            ResearchDto researchDto = researchService.getResearchById(id).orElseThrow();
//            ResearchDto researchPatched = applyPatch(researchDto, patch);
//            researchService.updateResearch(researchPatched);
//        } catch (JsonPatchException | JsonProcessingException e) {
//            return ResponseEntity.internalServerError().build();
//        } catch (NoSuchElementException e ) {
//            return ResponseEntity.notFound().build();
//        }
//        return  ResponseEntity.noContent().build();
//    }
//
//    private ResearchDto applyPatch(ResearchDto researchDto, JsonMergePatch patch) throws JsonPatchException, JsonProcessingException {
//        JsonNode researchNode = objectMapper.valueToTree(researchDto);
//        JsonNode researchPatchedNode = patch.apply(researchNode);
//        return objectMapper.treeToValue(researchPatchedNode, ResearchDto.class);
//    }


}
