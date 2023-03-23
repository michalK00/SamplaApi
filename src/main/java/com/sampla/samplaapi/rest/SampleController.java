package com.sampla.samplaapi.rest;

import com.sampla.samplaapi.dto.brief.SampleBriefDto;
import com.sampla.samplaapi.dto.base.SampleDto;
import com.sampla.samplaapi.dto.create.CreateSampleDto;
import com.sampla.samplaapi.dto.update.UpdateSampleDto;
import com.sampla.samplaapi.service.SampleService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.util.List;
import java.net.URI;


@RestController
@RequestMapping("/researches")
public class SampleController {

    private final SampleService sampleService;

    public SampleController(SampleService sampleService) {
        this.sampleService = sampleService;
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
    @PostMapping("/{researchId}")
    ResponseEntity<SampleDto> createSample(@PathVariable Long researchId,@Valid @RequestBody CreateSampleDto sample){
        SampleDto savedSample = sampleService.createSample(sample, researchId);
        URI savedSampleUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(savedSample.getId())
                .toUri();
        return ResponseEntity.created(savedSampleUri).body(savedSample);
    }

    @PutMapping("/samples/{sampleId}")
    ResponseEntity<?> updateSample(@PathVariable Long sampleId, @Valid @RequestBody UpdateSampleDto sample){
        sampleService.updateSample(sample, sampleId);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/samples/{sampleId}")
    ResponseEntity<?> deleteSample(@PathVariable Long sampleId){
        sampleService.deleteSample(sampleId);
        return ResponseEntity.noContent().build();
    }
    @Transactional
    @DeleteMapping("/{researchId}/samples")
    ResponseEntity<?> deleteAllSamplesOfOneResearch(@PathVariable Long researchId){
        sampleService.deleteAllSamples(researchId);
        return ResponseEntity.noContent().build();
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
//    @PatchMapping("/samples/{sampleId}")
//    ResponseEntity<?> updateSample(@PathVariable Long sampleId, @RequestBody JsonMergePatch patch) {
//        try {
//            UpdateSampleDto sampleDto = mapping.mapNormalToUpdate(sampleService.getSampleById(sampleId).orElseThrow());
//            UpdateSampleDto samplePatched = applyPatch(sampleDto, patch);
//
//            sampleService.updateSample(samplePatched);
//        } catch (JsonPatchException | JsonProcessingException e) {
//            return ResponseEntity.internalServerError().build();
//        }
//        return ResponseEntity.noContent().build();
//    }


//    private UpdateSampleDto applyPatch(UpdateSampleDto sample, JsonMergePatch patch) throws JsonPatchException, JsonProcessingException {
//        JsonNode sampleNode = objectMapper.valueToTree(sample);
//        JsonNode samplePatchedNode = patch.apply(sampleNode);
//        return objectMapper.treeToValue(samplePatchedNode, UpdateSampleDto.class);
//    }




}
