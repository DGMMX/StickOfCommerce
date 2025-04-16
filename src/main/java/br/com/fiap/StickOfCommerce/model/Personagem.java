package br.com.fiap.StickOfCommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Personagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @NotBlank(message = "Campo obrigatório")
    private String nome;
    @NotNull(message = "Campo obrigatório")
    private Class classe;
    @NotNull(message = "Campo obrigatório")
    @Min(value = 1, message = "O nível deve ser maior que 0")
    @Max(value = 99, message = "Você atingiu o nível máximo")
    private Integer nivel;
    @PositiveOrZero(message = "Moeda deve ser igual ou maior que zero")
    private BigDecimal moedas;
}