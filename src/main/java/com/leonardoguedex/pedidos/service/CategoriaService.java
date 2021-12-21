package com.leonardoguedex.pedidos.service;

import com.leonardoguedex.pedidos.domain.entity.Categoria;
import com.leonardoguedex.pedidos.domain.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria buscar(Integer id){
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.orElse(null);
    }
}
