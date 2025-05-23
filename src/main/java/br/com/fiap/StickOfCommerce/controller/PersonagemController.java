package br.com.fiap.StickOfCommerce.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.StickOfCommerce.model.Personagem;
import br.com.fiap.StickOfCommerce.repository.PersonagemRepository;

import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/personagens")
@Slf4j
public class PersonagemController {
    
    @Autowired
    private PersonagemRepository repository;

    @GetMapping
    @Cacheable("personagens")
    @Operation(description = "Listar todos os personagens", tags = "personagens", summary = "Lista de personagens")
    public List<Personagem> index() {
        log.info("Buscando todos os personagens");
        return repository.findAll();
    }
    
    @PostMapping
    @CacheEvict(value = "personagens", allEntries = true)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(responses =  {
            @ApiResponse(responseCode = "400", description = "Falha na validação")
    })
    public Personagem create(@RequestBody @Valid Personagem personagem) {
        log.info("Cadastrando personagem " + personagem.getNome());
        return repository.save(personagem);
    }
    
    @GetMapping("{id}")
    public Personagem get(@PathVariable Long id) {
        log.info("Buscando personagem " + id);
        return getPersonagem(id);
    }
    
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(value = "personagens", allEntries = true)
    public void destroy(@PathVariable Long id) {
        log.info("Apagando personagem " + id);
        repository.delete(getPersonagem(id));
    }
    
    @PutMapping("{id}")
    @CacheEvict(value = "personagens", allEntries = true)
    public Personagem update(@PathVariable Long id, @RequestBody @Valid Personagem personagem) {
        log.info("Atualizando personagem " + id + " " + personagem);
        getPersonagem(id);
        personagem.setId(id);
        return repository.save(personagem);
    }
    
    private Personagem getPersonagem(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Personagem não encontrado"));
    }


}