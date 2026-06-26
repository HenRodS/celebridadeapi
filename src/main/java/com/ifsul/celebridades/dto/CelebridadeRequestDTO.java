package com.ifsul.celebridades.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CelebridadeRequestDTO {

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 2, max = 150, message = "O nome deve ter entre 2 e 150 caracteres")
    private String nome;

    @NotNull(message = "A idade é obrigatória")
    @Min(value = 0, message = "A idade não pode ser negativa")
    @Max(value = 150, message = "A idade deve ser realista")
    private Integer idade;

    @NotBlank(message = "O motivo da fama é obrigatório")
    @Size(min = 2, max = 200, message = "O motivo da fama deve ter entre 2 e 200 caracteres")
    private String motivoFama;
}
