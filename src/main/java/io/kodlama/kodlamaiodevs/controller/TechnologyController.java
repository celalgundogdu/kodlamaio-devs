package io.kodlama.kodlamaiodevs.controller;

import io.kodlama.kodlamaiodevs.business.TechnologyService;
import io.kodlama.kodlamaiodevs.dto.request.technology.CreateTechnologyRequest;
import io.kodlama.kodlamaiodevs.dto.request.technology.UpdateTechnologyRequest;
import io.kodlama.kodlamaiodevs.dto.response.technology.GetAllTechnologiesResponse;
import io.kodlama.kodlamaiodevs.dto.response.technology.GetTechnologyByIdResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/technologies")
public class TechnologyController {

    private final TechnologyService technologyService;

    public TechnologyController(TechnologyService technologyService) {
        this.technologyService = technologyService;
    }

    @GetMapping
    public List<GetAllTechnologiesResponse> getAllTechnologies() {
        return technologyService.getAllTechnologies();
    }

    @GetMapping("/{id}")
    public GetTechnologyByIdResponse getTechnologyById(@PathVariable Integer id) {
        return technologyService.getTechnologyById(id);
    }

    @PostMapping
    public void createTechnology(@RequestBody CreateTechnologyRequest createTechnologyRequest) {
        technologyService.createTechnology(createTechnologyRequest);
    }

    @PutMapping("/{id}")
    public void updateTechnology(@PathVariable Integer id,
                                 @RequestBody UpdateTechnologyRequest updateTechnologyRequest) {
        technologyService.updateTechnology(id, updateTechnologyRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteTechnology(@PathVariable Integer id) {
        technologyService.deleteTechnology(id);
    }
}
