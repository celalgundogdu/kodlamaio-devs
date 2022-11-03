package io.kodlama.kodlamaiodevs.repository.concretes;

import io.kodlama.kodlamaiodevs.entity.concretes.Language;
import io.kodlama.kodlamaiodevs.repository.abstracts.LanguageRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryLanguageRepository implements LanguageRepository {

    private List<Language> languageList;

    public InMemoryLanguageRepository() {
        languageList = new ArrayList<>();
        languageList.add(new Language(1, "C#"));
        languageList.add(new Language(2, "Java"));
        languageList.add(new Language(3, "Python"));
    }

    @Override
    public List<Language> getAll() {
        return languageList;
    }

    @Override
    public Optional<Language> getById(int id) {
        return languageList.stream()
                .filter(language -> language.getId() == id)
                .findFirst();
    }

    @Override
    public void create(Language language) {
        languageList.add(language);
    }

    @Override
    public void update(Language language, int id) {
        Optional<Language> languageOptional = getById(id);
        languageOptional.ifPresent(theLanguage -> language.setName(theLanguage.getName()));
    }

    @Override
    public void delete(int id) {
        Optional<Language> languageOptional = getById(id);
        languageOptional.ifPresent(language -> languageList.remove(language));
    }
}
