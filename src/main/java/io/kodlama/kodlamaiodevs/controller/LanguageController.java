package io.kodlama.kodlamaiodevs.controller;

import io.kodlama.kodlamaiodevs.business.LanguageService;
import io.kodlama.kodlamaiodevs.dto.request.language.CreateLanguageRequest;
import io.kodlama.kodlamaiodevs.dto.request.language.UpdateLanguageRequest;
import io.kodlama.kodlamaiodevs.dto.response.language.GetAllLanguagesResponse;
import io.kodlama.kodlamaiodevs.dto.response.language.GetLanguageByIdResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/languages")
public class LanguageController {

    private final LanguageService languageService;

    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping
    public List<GetAllLanguagesResponse> getAllLanguages() {
        return languageService.getAllLanguages();
    }

    @GetMapping("/{id}")
    public GetLanguageByIdResponse getLanguageById(@PathVariable Integer id) {
        return languageService.getLanguageById(id);
    }

    @PostMapping
    public void createLanguage(@RequestBody CreateLanguageRequest createLanguageRequest) {
        languageService.createLanguage(createLanguageRequest);
    }

    @PutMapping("/{id}")
    public void updateLanguage(@PathVariable Integer id, @RequestBody UpdateLanguageRequest updateLanguageRequest) {
        languageService.updateLanguage(id, updateLanguageRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteLanguage(@PathVariable Integer id) {
        languageService.deleteLanguage(id);
    }
}
