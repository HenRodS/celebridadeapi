package com.ifsul.celebridades.service;

import java.util.List;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.ifsul.celebridades.dto.CelebridadeRequestDTO;
import com.ifsul.celebridades.dto.CelebridadeResponseDTO;
import com.ifsul.celebridades.exception.CelebridadeNotFoundException;
import com.ifsul.celebridades.model.Celebridade;
import com.ifsul.celebridades.repository.CelebridadeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CelebridadeService {

    private final CelebridadeRepository repository;

    public List<CelebridadeResponseDTO> listarTodas() {
        return repository.findAll()
                .stream()
                .map(CelebridadeResponseDTO::fromEntity)
                .toList();
    }

    public CelebridadeResponseDTO buscarPorId(@NonNull Long id) {
        Celebridade celebridade = repository.findById(id)
                .orElseThrow(() -> new CelebridadeNotFoundException(id));
        return CelebridadeResponseDTO.fromEntity(celebridade);
    }

    public List<CelebridadeResponseDTO> buscarPorMotivoFama(String motivoFama) {
        return repository.findByMotivoFamaContainingIgnoreCase(motivoFama)
                .stream()
                .map(CelebridadeResponseDTO::fromEntity)
                .toList();
    }

    public List<CelebridadeResponseDTO> buscarPorNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(CelebridadeResponseDTO::fromEntity)
                .toList();
    }

    public CelebridadeResponseDTO cadastrar(CelebridadeRequestDTO dto) {
        Celebridade celebridade = Celebridade.builder()
                .nome(dto.getNome())
                .idade(dto.getIdade())
                .motivoFama(dto.getMotivoFama())
                .build();
        Celebridade salvo = repository.save(celebridade);
        return CelebridadeResponseDTO.fromEntity(salvo);
    }

    public CelebridadeResponseDTO atualizar(Long id, CelebridadeRequestDTO dto) {
        Celebridade celebridade = repository.findById(id)
                .orElseThrow(() -> new CelebridadeNotFoundException(id));

        celebridade.setNome(dto.getNome());
        celebridade.setIdade(dto.getIdade());
        celebridade.setMotivoFama(dto.getMotivoFama());

        Celebridade atualizado = repository.save(celebridade);
        return CelebridadeResponseDTO.fromEntity(atualizado);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new CelebridadeNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
