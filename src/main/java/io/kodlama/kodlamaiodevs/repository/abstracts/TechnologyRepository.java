package io.kodlama.kodlamaiodevs.repository.abstracts;

import io.kodlama.kodlamaiodevs.entity.concretes.Technology;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnologyRepository extends JpaRepository<Technology, Integer> {

    boolean existsTechnologyByName(String name);
}
