package br.com.desafio.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ApiError implements Serializable {
    private static final long serialVersionUID = 1L;

    private int status_code;
    private String message;
}