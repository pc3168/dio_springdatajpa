package dio.spring.jpa.crud.api.controller;

import dio.spring.jpa.crud.model.Pedido;
import dio.spring.jpa.crud.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public List<Pedido> listarTodos(){
        return pedidoService.listarTodos();
    }

    @GetMapping("{id}")
    public Pedido buscarPorId(@PathVariable Long id){
        return pedidoService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido salvar(@RequestBody Pedido pedido){
        return pedidoService.adicionar(pedido);
    }

    @PutMapping("{id}")
    public Pedido atualizar(@PathVariable Long id, @RequestBody Pedido pedido){
        return pedidoService.atualizar(id, pedido);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id){
        pedidoService.excluir(id);
    }


}
