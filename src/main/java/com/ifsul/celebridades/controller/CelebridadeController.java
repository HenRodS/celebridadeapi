package com.ifsul.celebridades.controller;

import com.ifsul.celebridades.dto.CelebridadeRequestDTO;
import com.ifsul.celebridades.dto.CelebridadeResponseDTO;
import com.ifsul.celebridades.service.CelebridadeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/celebridades")
@RequiredArgsConstructor
public class CelebridadeController {

    private final CelebridadeService service;

    // GET /api/celebridades - Lista todas as celebridades
    @GetMapping
    public ResponseEntity<List<CelebridadeResponseDTO>> listarTodas(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String motivoFama) {

        if (nome != null && !nome.isBlank()) {
            return ResponseEntity.ok(service.buscarPorNome(nome));
        }
        if (motivoFama != null && !motivoFama.isBlank()) {
            return ResponseEntity.ok(service.buscarPorMotivoFama(motivoFama));
        }
        return ResponseEntity.ok(service.listarTodas());
    }

    // GET /api/celebridades/{id} - Busca celebridade por ID
    @GetMapping("/{id}")
    public ResponseEntity<CelebridadeResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    // POST /api/celebridades - Cadastra nova celebridade
    @PostMapping
    public ResponseEntity<CelebridadeResponseDTO> cadastrar(@Valid @RequestBody CelebridadeRequestDTO dto) {
        CelebridadeResponseDTO criado = service.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    // PUT /api/celebridades/{id} - Atualiza celebridade existente
    @PutMapping("/{id}")
    public ResponseEntity<CelebridadeResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody CelebridadeRequestDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    // DELETE /api/celebridades/{id} - Remove celebridade
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
