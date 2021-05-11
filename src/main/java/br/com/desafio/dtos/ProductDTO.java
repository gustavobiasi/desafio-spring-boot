package br.com.desafio.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class ProductDTO {

    @JsonIgnore
    private String id;

    @Size(max = 100, message = "{name.size}")
    @NotBlank(message = "{name.not.blank}")
    private String name;

    @Size(max = 150, message = "{description.size}")
    @NotBlank(message = "{description.not.blank}")
    private String description;

    @Positive(message = "{price.positive}")
    private BigDecimal price;
}
