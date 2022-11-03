package io.kodlama.kodlamaiodevs.repository.abstracts;

import io.kodlama.kodlamaiodevs.entity.concretes.Language;

import java.util.List;
import java.util.Optional;

public interface LanguageRepository {

    List<Language> getAll();
    Optional<Language> getById(int id);
    void create(Language language);
    void update(Language language, int id);
    void delete(int id);
}
