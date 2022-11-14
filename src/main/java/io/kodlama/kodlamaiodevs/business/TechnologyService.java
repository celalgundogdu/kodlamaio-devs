package io.kodlama.kodlamaiodevs.business;

import io.kodlama.kodlamaiodevs.dto.request.technology.CreateTechnologyRequest;
import io.kodlama.kodlamaiodevs.dto.request.technology.UpdateTechnologyRequest;
import io.kodlama.kodlamaiodevs.dto.response.technology.GetAllTechnologiesResponse;
import io.kodlama.kodlamaiodevs.dto.response.technology.GetTechnologyByIdResponse;
import io.kodlama.kodlamaiodevs.entity.concretes.Technology;
import io.kodlama.kodlamaiodevs.repository.abstracts.TechnologyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TechnologyService {

    private final TechnologyRepository technologyRepository;
    private final LanguageService languageService;

    public TechnologyService(TechnologyRepository technologyRepository, LanguageService languageService) {
        this.technologyRepository = technologyRepository;
        this.languageService = languageService;
    }

    public List<GetAllTechnologiesResponse> getAllTechnologies() {
        List<GetAllTechnologiesResponse> technologiesResponse = technologyRepository.findAll().stream().map(technology -> {
            GetAllTechnologiesResponse response = new GetAllTechnologiesResponse();
            response.setName(technology.getName());
            return response;
        }).collect(Collectors.toList());
        return technologiesResponse;
    }

    public GetTechnologyByIdResponse getTechnologyById(int id) {
        Technology technology = findTechnologyById(id);
        GetTechnologyByIdResponse response = new GetTechnologyByIdResponse();
        response.setName(technology.getName());
        response.setLanguageName(technology.getLanguage().getName());
        return response;
    }

    public void createTechnology(CreateTechnologyRequest createTechnologyRequest) {
        if (isNameInvalid(createTechnologyRequest.getName())) {
            throw new RuntimeException("Technology name must be at least two characters");
        }
        if (createTechnologyRequest.getLanguageId() == null) {
            throw new RuntimeException("Language id may not be null");
        }
        if (technologyRepository.existsTechnologyByName(createTechnologyRequest.getName())) {
            throw new RuntimeException("Technology already exists");
        }
        Technology technology = new Technology();
        technology.setName(createTechnologyRequest.getName());
        technology.setLanguage(languageService.findLanguageById(createTechnologyRequest.getLanguageId()));
        technologyRepository.save(technology);
    }

    public void updateTechnology(int id, UpdateTechnologyRequest updateTechnologyRequest) {
        Technology technology = findTechnologyById(id);
        if (isNameInvalid(updateTechnologyRequest.getName())) {
            throw new RuntimeException("Technology name must be at least two characters");
        }
        if (technologyRepository.existsTechnologyByName(updateTechnologyRequest.getName())) {
            throw new RuntimeException("Technology already exists");
        }
        if (updateTechnologyRequest.getLanguageId() != null) {
            technology.setLanguage(languageService.findLanguageById(updateTechnologyRequest.getLanguageId()));
        }
        technology.setName(updateTechnologyRequest.getName());
        technologyRepository.save(technology);
    }

    public void deleteTechnology(int id) {
        technologyRepository.deleteById(id);
    }

    private Technology findTechnologyById(int id) {
        Optional<Technology> technologyOptional = technologyRepository.findById(id);
        if (technologyOptional.isEmpty()) {
            throw new RuntimeException("Technology not found");
        }
        return technologyOptional.get();
    }

    private boolean isNameInvalid(String name) {
        return name.trim().length() < 2;
    }
}
