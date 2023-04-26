package dio.spring.jpa.crud.service;

import dio.spring.jpa.crud.exception.ProdutoEmUsoException;
import dio.spring.jpa.crud.exception.ProdutoNaoEncontradoException;
import dio.spring.jpa.crud.model.Produto;
import dio.spring.jpa.crud.repository.ProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> listarTodos(){
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(Long id){
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));
    }

    public Produto adicionar(Produto produto){
        return produtoRepository.save(produto);
    }

    public Produto atualizar(Long id, Produto produto){
        Produto produtoAtual = buscarPorId(id);

        BeanUtils.copyProperties(produto, produtoAtual, "id");

        return produtoRepository.save(produtoAtual);
    }

    public void excluir(Long id){
        try{
            produtoRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new ProdutoNaoEncontradoException(id);
        }catch (DataIntegrityViolationException e){
            throw new ProdutoEmUsoException(id);
        }
    }

}
