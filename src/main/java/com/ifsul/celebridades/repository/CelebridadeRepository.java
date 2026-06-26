package com.ifsul.celebridades.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifsul.celebridades.model.Celebridade;

public interface CelebridadeRepository extends JpaRepository<Celebridade, Long> {

    List<Celebridade> findByMotivoFamaContainingIgnoreCase(String motivoFama);

    List<Celebridade> findByNomeContainingIgnoreCase(String nome);
}
