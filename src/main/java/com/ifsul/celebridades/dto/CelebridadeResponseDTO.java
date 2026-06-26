package com.ifsul.celebridades.dto;

import com.ifsul.celebridades.model.Celebridade;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CelebridadeResponseDTO {

    private Long id;
    private String nome;
    private Integer idade;
    private String motivoFama;

    public static CelebridadeResponseDTO fromEntity(Celebridade celebridade) {
        return CelebridadeResponseDTO.builder()
                .id(celebridade.getId())
                .nome(celebridade.getNome())
                .idade(celebridade.getIdade())
                .motivoFama(celebridade.getMotivoFama())
                .build();
    }
}
