package io.kodlama.kodlamaiodevs.business.concretes;

import io.kodlama.kodlamaiodevs.business.abstracts.LanguageService;
import io.kodlama.kodlamaiodevs.entity.concretes.Language;
import io.kodlama.kodlamaiodevs.repository.abstracts.LanguageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageManager implements LanguageService {

    private final LanguageRepository languageRepository;

    public LanguageManager(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public List<Language> getAll() {
        return languageRepository.getAll();
    }

    @Override
    public Language getById(int id) {
        Optional<Language> languageOptional = languageRepository.getById(id);
        if (languageOptional.isEmpty()) {
            throw new RuntimeException("Programming language not found");
        }
        return languageOptional.get();
    }

    @Override
    public void create(Language language) {
        if (isNameInvalid(language.getName())) {
            throw new RuntimeException("Programming language name may not be blank");
        }
        if (isExists(language.getName())) {
            throw new RuntimeException("Programming language already exists");
        }
        languageRepository.create(language);
    }

    @Override
    public void update(Language language, int id) {
        if (languageRepository.getById(id).isEmpty()){
            throw new RuntimeException("Programming language not found");
        }
        if (isNameInvalid(language.getName())) {
            throw new RuntimeException("Programming language name may not be blank");
        }
        if (isExists(language.getName())) {
            throw new RuntimeException("Programming language already exists");
        }
        languageRepository.update(language, id);
    }

    @Override
    public void delete(int id) {
        languageRepository.delete(id);
    }

    private boolean isExists(String name) {
        return languageRepository.getAll().stream()
                .anyMatch(language -> language.getName().equals(name));
    }

    private boolean isNameInvalid(String name) {
        return name.isBlank();
    }
}
