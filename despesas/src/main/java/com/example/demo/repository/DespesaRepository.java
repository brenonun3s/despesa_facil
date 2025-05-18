package com.example.demo.repository;

import com.example.demo.model.entity.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Integer> {

    @Query("SELECT SUM(d.valor) FROM Despesa d")
    Double calcularTotalDespesas();

    @Query("SELECT d FROM Despesa d WHERE d.usuario.id = :usuarioId")
    List<Despesa> findByUsuarioId(@Param("usuarioId") UUID usuarioId);


}
