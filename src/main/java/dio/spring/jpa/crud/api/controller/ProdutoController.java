package dio.spring.jpa.crud.api.controller;

import dio.spring.jpa.crud.model.Produto;
import dio.spring.jpa.crud.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<Produto> listarTodos(){
        return produtoService.listarTodos();
    }

    @GetMapping("{id}")
    public Produto buscarPorId(@PathVariable Long id){
        return produtoService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto salvar(@Valid @RequestBody Produto produto){
        return produtoService.adicionar(produto);
    }

    @PutMapping("{id}")
    public Produto atualizar(@Valid @PathVariable Long id, @RequestBody Produto produto){
        return produtoService.atualizar(id, produto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id){
        produtoService.excluir(id);
    }


}
