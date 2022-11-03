package io.kodlama.kodlamaiodevs.controller;

import io.kodlama.kodlamaiodevs.business.abstracts.LanguageService;
import io.kodlama.kodlamaiodevs.entity.concretes.Language;
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
    public List<Language> getAll() {
        return languageService.getAll();
    }

    @GetMapping("/{id}")
    public Language getById(@PathVariable int id) {
        return languageService.getById(id);
    }

    @PostMapping
    public void create(@RequestBody Language language) {
        languageService.create(language);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody Language language, @PathVariable int id) {
        languageService.update(language, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        languageService.delete(id);
    }
}
