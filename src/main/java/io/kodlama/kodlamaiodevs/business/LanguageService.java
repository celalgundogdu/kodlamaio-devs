package io.kodlama.kodlamaiodevs.business;

import io.kodlama.kodlamaiodevs.dto.request.language.CreateLanguageRequest;
import io.kodlama.kodlamaiodevs.dto.request.language.UpdateLanguageRequest;
import io.kodlama.kodlamaiodevs.dto.response.language.GetAllLanguagesResponse;
import io.kodlama.kodlamaiodevs.dto.response.language.GetLanguageByIdResponse;
import io.kodlama.kodlamaiodevs.entity.concretes.Language;
import io.kodlama.kodlamaiodevs.entity.concretes.Technology;
import io.kodlama.kodlamaiodevs.repository.abstracts.LanguageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LanguageService {

    private final LanguageRepository languageRepository;

    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public List<GetAllLanguagesResponse> getAllLanguages() {
        List<GetAllLanguagesResponse> languagesResponse = languageRepository.findAll().stream().map(language -> {
            GetAllLanguagesResponse responseItem = new GetAllLanguagesResponse();
            responseItem.setId(language.getId());
            responseItem.setName(language.getName());
            return responseItem;
        }).collect(Collectors.toList());
        return languagesResponse;
    }

    public GetLanguageByIdResponse getLanguageById(int id) {
        Language language = findLanguageById(id);
        GetLanguageByIdResponse response = new GetLanguageByIdResponse();
        response.setId(language.getId());
        response.setName(language.getName());
        response.setTechnologyNames(language.getTechnologies()
                .stream()
                .map(Technology::getName)
                .collect(Collectors.toList()));
        return response;
    }

    public void createLanguage(CreateLanguageRequest createLanguageRequest) {
        if (isNameInvalid(createLanguageRequest.getName())) {
            throw new RuntimeException("Programming language name may not be blank");
        }
        if (languageRepository.existsLanguageByName(createLanguageRequest.getName())) {
            throw new RuntimeException("Programming language already exists");
        }
        Language language = new Language();
        language.setName(createLanguageRequest.getName());
        languageRepository.save(language);
    }

    public void updateLanguage(int id, UpdateLanguageRequest updateLanguageRequest) {
        Language language = findLanguageById(id);
        if (isNameInvalid(updateLanguageRequest.getName())) {
            throw new RuntimeException("Programming language name may not be blank");
        }
        if (languageRepository.existsLanguageByName(updateLanguageRequest.getName())) {
            throw new RuntimeException("Programming language already exists");
        }
        language.setName(updateLanguageRequest.getName());
        languageRepository.save(language);
    }

    public void deleteLanguage(int id) {
        languageRepository.deleteById(id);
    }

    protected Language findLanguageById(Integer id) {
        Optional<Language> languageOptional = languageRepository.findById(id);
        if (languageOptional.isEmpty()) {
            throw new RuntimeException("Programming language not found");
        }
        return languageOptional.get();
    }

    private boolean isNameInvalid(String name) {
        return name.isBlank();
    }
}
