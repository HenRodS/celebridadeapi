package com.ifsul.celebridades.repository;

import com.ifsul.celebridades.model.Celebridade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CelebridadeRepository extends JpaRepository<Celebridade, Long> {

    List<Celebridade> findByMotivoFamaContainingIgnoreCase(String motivoFama);

    List<Celebridade> findByNomeContainingIgnoreCase(String nome);
}
