package io.kodlama.kodlamaiodevs.dto.response.language;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetLanguageByIdResponse {
    private Integer id;
    private String name;
    private List<String> technologyNames;
}
