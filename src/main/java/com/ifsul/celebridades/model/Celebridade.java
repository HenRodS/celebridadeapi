package com.ifsul.celebridades.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "celebridades")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Celebridade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 2, max = 150, message = "O nome deve ter entre 2 e 150 caracteres")
    @Column(nullable = false, length = 150)
    private String nome;

    @NotNull(message = "A idade é obrigatória")
    @Min(value = 0, message = "A idade não pode ser negativa")
    @Max(value = 150, message = "A idade deve ser realista")
    @Column(nullable = false)
    private Integer idade;

    @NotBlank(message = "O motivo da fama é obrigatório")
    @Size(min = 2, max = 200, message = "O motivo da fama deve ter entre 2 e 200 caracteres")
    @Column(name = "motivo_fama", nullable = false, length = 200)
    private String motivoFama;
}
