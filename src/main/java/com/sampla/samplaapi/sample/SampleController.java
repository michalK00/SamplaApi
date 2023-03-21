package com.sampla.samplaapi.sample;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.sampla.samplaapi.sample.sampleDto.SampleBriefDto;
import com.sampla.samplaapi.sample.sampleDto.SampleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonpatch.JsonPatchException;

import java.util.List;
import java.util.NoSuchElementException;
import java.net.URI;


@RestController
@RequestMapping("/researches")
public class SampleController {

    private final SampleService sampleService;
    private final ObjectMapper objectMapper;

    public SampleController(SampleService sampleService, ObjectMapper objectMapper) {
        this.sampleService = sampleService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/samples/{sampleId}")
    ResponseEntity<SampleDto> getSample(@PathVariable Long sampleId){
        return sampleService.getSampleById(sampleId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/{researchId}/samples")
    ResponseEntity<List<SampleBriefDto>> getResearches(@PathVariable Long researchId){
        List<SampleBriefDto> returnedList = sampleService.getSampleBriefs(researchId);
        return ResponseEntity.ok(returnedList);
    }
    @GetMapping("/{researchId}/samples/paging")
    ResponseEntity<Page<SampleBriefDto>> getSampleBriefs(@PathVariable Long researchId,
                                                         @RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "8") int size,
                                                         @RequestParam(defaultValue = "sampleCode") String sortBy){

        Pageable paging = PageRequest.of(page, size, Sort.by(sortBy));
        Page<SampleBriefDto> returnedPage = sampleService.getSampleBriefs(paging, researchId);
        return ResponseEntity.ok(returnedPage);
    }
    @PostMapping("/{researchId}")
    ResponseEntity<SampleDto> addSample(@PathVariable Long researchId, @RequestBody SampleDto sample){
        sample.setResearchId(researchId);
        SampleDto savedSample = sampleService.saveSample(sample);
        URI savedSampleUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(savedSample.getId())
                .toUri();
        return ResponseEntity.created(savedSampleUri).body(savedSample);
    }
    @PatchMapping("/samples/{sampleId}")
    ResponseEntity<?> updateSample(@PathVariable Long sampleId, @RequestBody JsonMergePatch patch) {
        try {
            SampleDto sampleDto = sampleService.getSampleById(sampleId).orElseThrow();
            SampleDto samplePatched = applyPatch(sampleDto, patch);
            sampleService.updateSample(samplePatched);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.internalServerError().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    private SampleDto applyPatch(SampleDto sample, JsonMergePatch patch) throws JsonPatchException, JsonProcessingException {
        JsonNode sampleNode = objectMapper.valueToTree(sample);
        JsonNode samplePatchedNode = patch.apply(sampleNode);
        return objectMapper.treeToValue(samplePatchedNode, SampleDto.class);
    }

    @DeleteMapping("/samples/{sampleId}")
    ResponseEntity<?> deleteResearch(@PathVariable Long sampleId){
        sampleService.deleteSample(sampleId);
        return ResponseEntity.noContent().build();
    }
}
