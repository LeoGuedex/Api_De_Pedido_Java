package com.leonardoguedex.pedidos.service;

import com.leonardoguedex.pedidos.domain.entity.Categoria;
import com.leonardoguedex.pedidos.domain.repository.CategoriaRepository;
import com.leonardoguedex.pedidos.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria find(Integer id){
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.orElseThrow(()-> new ObjectNotFoundException("Objeto Não Encontrado! Id: " + id + ", tipo: "
                + Categoria.class.getName()));
    }

    public Categoria Insert(Categoria categoria){
        categoria.setId(null);
        return categoriaRepository.save(categoria);
    }

    public Categoria update (Categoria categoria){
        find(categoria.getId()); // evitando de 2 usarem ao msm tempo
        return categoriaRepository.save(categoria);

    }


}
