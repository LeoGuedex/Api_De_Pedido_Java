package com.leonardoguedex.pedidos.rest.dto;
import com.leonardoguedex.pedidos.domain.entity.Categoria;

import java.io.Serializable;

public class CategoriaDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;






    public CategoriaDto() {
    }

    public CategoriaDto(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public CategoriaDto(Categoria categoria) {
        this.id = id;
        this.nome = nome;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}