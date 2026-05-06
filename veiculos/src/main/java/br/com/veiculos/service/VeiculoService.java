package br.com.veiculos.service;


import br.com.veiculos.model.Veiculo;
import br.com.veiculos.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository repository;

    public List<Veiculo> listarTodos() {
        return repository.findAll();
    }

    public Veiculo salvar(Veiculo veiculo) {
        return repository.save(veiculo);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    // FILTRO COMPLETO
    public List<Veiculo> filtrar(String marca, String modelo, Integer ano,
                                 Double precoMin, Double precoMax, String status) {

        List<Veiculo> lista = repository.findAll();

        return lista.stream()
                .filter(v -> marca == null || v.getMarca().equalsIgnoreCase(marca))
                .filter(v -> modelo == null || v.getModelo().equalsIgnoreCase(modelo))
                .filter(v -> ano == null || v.getAno() == ano)
                .filter(v -> status == null || v.getStatus().equalsIgnoreCase(status))
                .filter(v -> precoMin == null || v.getPreco() >= precoMin)
                .filter(v -> precoMax == null || v.getPreco() <= precoMax)
                .toList();
    }
}