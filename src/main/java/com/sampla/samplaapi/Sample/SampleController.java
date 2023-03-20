package com.sampla.samplaapi.Sample;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.sampla.samplaapi.Sample.SampleDto.SampleDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonpatch.JsonPatchException;
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
