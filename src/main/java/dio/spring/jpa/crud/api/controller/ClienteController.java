package dio.spring.jpa.crud.api.controller;

import dio.spring.jpa.crud.model.Cliente;
import dio.spring.jpa.crud.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> listarTodos(){
        return clienteService.listarTodos();
    }

    @GetMapping("{id}")
    public Cliente buscarPorId(@PathVariable Long id){
        return clienteService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar(@Valid  @RequestBody Cliente cliente){
        return clienteService.adicionar(cliente);
    }

    @PutMapping("{id}")
    public Cliente atualizar(@Valid @PathVariable Long id, @RequestBody Cliente cliente){
        return clienteService.atualizar(id, cliente);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id){
        clienteService.excluir(id);
    }


}
