package br.com.desafio.domain;

import br.com.desafio.dtos.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity @Table(name = "tProduto", schema = "desafioDbg")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String name;
    private String description;
    private BigDecimal price;

    public Product(ProductDTO productDTO) {
        this.id = productDTO.getId();
        this.name = productDTO.getName();
        this.description = productDTO.getDescription();
        this.price = productDTO.getPrice();
    }
}
