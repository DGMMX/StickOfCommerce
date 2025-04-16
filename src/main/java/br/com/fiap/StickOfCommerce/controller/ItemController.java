package br.com.fiap.StickOfCommerce.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

import br.com.fiap.StickOfCommerce.model.Item;
import br.com.fiap.StickOfCommerce.repository.ItemRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/itens")
@Slf4j
public class ItemController {

    @Autowired
    private ItemRepository repository;

    @GetMapping
    @Cacheable("itens")
    @Operation(description = "Listar todos os itens", tags = "itens", summary = "Lista de itens")
    public List<Item> index() {
        log.info("Buscando todos os itens");
        return repository.findAll();
    }

    @PostMapping
    @CacheEvict(value = "itens", allEntries = true)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
        summary = "Cadastrar um item",
        responses = {
            @ApiResponse(responseCode = "201", description = "Item criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Falha na validação")
        }
    )
    public Item create(@RequestBody @Valid Item item) {
        log.info("Cadastrando item: " + item.getNome());
        return repository.save(item);
    }

    @GetMapping("{id}")
    public Item get(@PathVariable Long id) {
        log.info("Buscando item com ID: " + id);
        return getItem(id);
    }

    @PutMapping("{id}")
    @CacheEvict(value = "itens", allEntries = true)
    public Item update(@PathVariable Long id, @RequestBody @Valid Item item) {
        log.info("Atualizando item " + id + " -> " + item);
        getItem(id);
        item.setId(id);
        return repository.save(item);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(value = "itens", allEntries = true)
    public void destroy(@PathVariable Long id) {
        log.info("Removendo item com ID: " + id);
        repository.delete(getItem(id));
    }

    private Item getItem(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Item não encontrado"
            ));
    }
}
