package br.com.fiap.StickOfCommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.StickOfCommerce.model.Item;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByNomeContainingIgnoreCase(String nome);

    List<Item> findByTipo(String tipo);

    List<Item> findByPrecoBetween(double precoMinimo, double precoMaximo);

    List<Item> findByRaridade(String raridade);
}