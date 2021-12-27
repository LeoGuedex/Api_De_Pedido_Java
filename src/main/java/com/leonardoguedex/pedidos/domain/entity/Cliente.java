package com.leonardoguedex.pedidos.domain.entity;

import com.leonardoguedex.pedidos.domain.enums.TipoCliente;

import java.io.Serializable;
import java.util.*;

public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer id;
    private String nome;
    private String cpfCnpj;
    private Integer tipoCliente;
    private List<Endereco> enderecos = new ArrayList<>();
    private Set<String> telefones = new HashSet<>();

    public Cliente() {
    }

    public Cliente(Integer id, String nome, String cpfCnpj, TipoCliente tipoCliente) {
        this.id = id;
        this.nome = nome;
        this.cpfCnpj = cpfCnpj;
        this.tipoCliente = tipoCliente.getCod();
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

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public TipoCliente getTipoCliente() {
        return TipoCliente.toEnum(this.tipoCliente);
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente.getCod();
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public Set<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<String> telefones) {
        this.telefones = telefones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id.equals(cliente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
