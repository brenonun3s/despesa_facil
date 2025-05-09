package com.example.demo.service;


import com.example.demo.exceptions.DespesaNotFoundException;
import com.example.demo.model.Despesa;
import com.example.demo.repository.DespesaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DespesaService {

    private final DespesaRepository repository;

    @Transactional
    public Despesa registrarDespesa(Despesa despesa) {
        return repository.save(despesa);
    }

    @Transactional
    public void deletar(Integer id) {
        if (!repository.existsById(id)) {
            throw new DespesaNotFoundException("Despesa Não localizada com esse ID");
        }
        repository.deleteById(id);
    }

    @Transactional
    public void atualizar(Despesa despesaAtualizada) {
        repository.findById(despesaAtualizada.getId()).ifPresent(despesaExistente -> {
            despesaExistente.setNome(despesaAtualizada.getNome());
            despesaExistente.setTipo(despesaAtualizada.getTipo());
            despesaExistente.setValor(despesaAtualizada.getValor());
            despesaExistente.setDescricao(despesaAtualizada.getDescricao());
        });
    }

    public List<Despesa> listarTodasAsDespesas() {
        long contagem = repository.count();
        if (contagem == 0) {
            throw new DespesaNotFoundException("Não Existem Despesas Cadastradas");
        }
        return repository.findAll();
    }

    public Double listarTotal() {
        return repository.calcularTotalDespesas();
    }


}


