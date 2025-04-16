package br.com.fiap.StickOfCommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Campo é obrigatório")
    private String nome;

    @NotNull(message = "Campo é obrigatório")
    @Enumerated(EnumType.STRING)
    private TipoItem tipo;

    @NotNull(message = "Campo é obrigatória")
    @Enumerated(EnumType.STRING)
    private Raridade raridade;

    @NotNull(message = "Campo é obrigatório")
    @Positive(message = "Preço deve ser maior que zero")
    private BigDecimal preco;

    @NotNull(message = "Campo é obrigatório")
    @ManyToOne
    private Personagem dono;
}