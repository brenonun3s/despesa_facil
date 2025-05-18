package com.example.demo.service;


import com.example.demo.exceptions.DespesaNotFoundException;
import com.example.demo.model.entity.Despesa;
import com.example.demo.repository.DespesaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DespesaService {

    private final DespesaRepository despesaRepository;

    @Transactional
    public Despesa registrarDespesa(Despesa despesa) {
        return despesaRepository.save(despesa);
    }

    @Transactional
    public void deletar(Integer id) {
        if (!despesaRepository.existsById(id)) {
            throw new DespesaNotFoundException("Despesa Não localizada com esse ID");
        }
        despesaRepository.deleteById(id);
    }

    @Transactional
    public void atualizar(Despesa despesaAtualizada) {
        despesaRepository.findById(despesaAtualizada.getId()).ifPresent(despesaExistente -> {
            despesaExistente.setNome(despesaAtualizada.getNome());
            despesaExistente.setTipo(despesaAtualizada.getTipo());
            despesaExistente.setValor(despesaAtualizada.getValor());
            despesaExistente.setDescricao(despesaAtualizada.getDescricao());
        });
    }

//    public List<Despesa> listarTodasAsDespesas() {
//        long contagem = despesaRepository.count();
//        if (contagem == 0) {
//            throw new DespesaNotFoundException("Não Existem Despesas Cadastradas");
//        }
//        return despesaRepository.findAll();
//    }
//
//    public Double listarTotal() {
//        return despesaRepository.calcularTotalDespesas();
//    }

    public List<Despesa> listarDespesasPorUsuario(UUID usuarioId) {
        return despesaRepository.findByUsuarioId(usuarioId);
    }

    public Double calcularTotalPorUsuario(UUID usuarioId) {
        List<Despesa> despesas = listarDespesasPorUsuario(usuarioId);
        return despesas.stream()
                .mapToDouble(Despesa::getValor)
                .sum();
    }


}


