package io.kodlama.kodlamaiodevs.repository.abstracts;

import io.kodlama.kodlamaiodevs.entity.concretes.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Integer> {

    boolean existsLanguageByName(String name);
}
