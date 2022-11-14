package io.kodlama.kodlamaiodevs.dto.request.technology;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTechnologyRequest {
    private String name;
    private Integer languageId;
}
