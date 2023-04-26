package dio.spring.jpa.crud.service;

import dio.spring.jpa.crud.exception.ClienteEmUsoException;
import dio.spring.jpa.crud.exception.ClienteNaoEncontradoException;
import dio.spring.jpa.crud.model.Cliente;
import dio.spring.jpa.crud.repository.ClienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarTodos(){
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Long id){
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException(id));
    }

    public Cliente adicionar(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public Cliente atualizar(Long id, Cliente cliente){
        Cliente clienteAtual = buscarPorId(id);

        BeanUtils.copyProperties(cliente, clienteAtual, "id");

        return clienteRepository.save(clienteAtual);
    }

    public void excluir(Long id){
        try{
            clienteRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new ClienteNaoEncontradoException(id);
        }catch (DataIntegrityViolationException e){
            throw new ClienteEmUsoException(id);
        }
    }

}
