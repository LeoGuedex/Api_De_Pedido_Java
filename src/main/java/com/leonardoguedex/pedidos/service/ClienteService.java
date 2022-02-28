package com.leonardoguedex.pedidos.service;

import com.leonardoguedex.pedidos.domain.entity.Categoria;
import com.leonardoguedex.pedidos.domain.entity.Cidade;
import com.leonardoguedex.pedidos.domain.entity.Cliente;
import com.leonardoguedex.pedidos.domain.entity.Endereco;
import com.leonardoguedex.pedidos.domain.enums.TipoCliente;
import com.leonardoguedex.pedidos.domain.repository.ClienteRepository;
import com.leonardoguedex.pedidos.domain.repository.EnderecoRepository;
import com.leonardoguedex.pedidos.exception.DataIntegratyException;
import com.leonardoguedex.pedidos.exception.ObjectNotFoundException;
import com.leonardoguedex.pedidos.rest.dto.ClienteDto;
import com.leonardoguedex.pedidos.rest.dto.ClienteNewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente find(Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(() -> new ObjectNotFoundException("Objeto Não Encontrado! Id: " + id + ", tipo: "
                + Cliente.class.getName()));
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return clienteRepository.findAll(pageRequest);
    }

    @Transactional
    public Cliente insert(Cliente cliente) {
        cliente.setId(null);
        Cliente clienteSaved = clienteRepository.save(cliente);
        enderecoRepository.save(clienteSaved.getEnderecos().get(0));
        return clienteSaved;
    }

    public Cliente update(Cliente cliente) {
        Cliente clienteToUpdate = find(cliente.getId());
        updateData(clienteToUpdate, cliente);
        return clienteRepository.save(clienteToUpdate);
    }

    public Cliente fromDto(ClienteDto clienteDto) {
        return new Cliente(clienteDto.getId(), clienteDto.getNome(), clienteDto.getEmail(), null, null, null);
    }

    public Cliente fromDto(ClienteNewDto clienteNewDto) {
        Cliente cliente = new Cliente(null,
                clienteNewDto.getNome(),
                clienteNewDto.getEmail(),
                clienteNewDto.getCpfCnpj(),
                TipoCliente.toEnum(clienteNewDto.getTipo()),
                bCryptPasswordEncoder.encode(clienteNewDto.getSenha()));

        Cidade cidade = new Cidade(clienteNewDto.getCidadeId(), null, null);

        Endereco endereco = new Endereco(null,
                clienteNewDto.getLogradouro(),
                clienteNewDto.getNumero(),
                clienteNewDto.getComplemento(),
                clienteNewDto.getBairro(),
                clienteNewDto.getCep(),
                cliente, cidade);

        cliente.getEnderecos().add(endereco);

        cliente.getTelefones().add(clienteNewDto.getTelefone1());

        if (clienteNewDto.getTelefone2() != null) {
            cliente.getTelefones().add(clienteNewDto.getTelefone2());
        }
        if (clienteNewDto.getTelefone3() != null) {
            cliente.getTelefones().add(clienteNewDto.getTelefone3());
        }

        return cliente;
    }


        public void delete (Integer id){
            find(id);
            try {
                clienteRepository.deleteById(id);
            } catch (DataIntegrityViolationException e) {
                throw new DataIntegratyException("Não é possivel excluir um cliente que possui dados vinculados.");
            }
        }

        public void updateData (Cliente clienteToUpdate, Cliente cliente){
            clienteToUpdate.setNome(cliente.getNome());
            clienteToUpdate.setEmail(cliente.getEmail());
        }


    }
