package dio.spring.jpa.crud.service;


import dio.spring.jpa.crud.exception.PedidoEmUsoException;
import dio.spring.jpa.crud.exception.PedidoNaoEncontradoException;
import dio.spring.jpa.crud.model.Cliente;
import dio.spring.jpa.crud.model.Pedido;
import dio.spring.jpa.crud.model.Produto;
import dio.spring.jpa.crud.repository.PedidoRepository;
import dio.spring.jpa.crud.repository.ProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ClienteService clienteService;

    public List<Pedido> listarTodos(){
        return pedidoRepository.findAll();
    }

    public Pedido buscarPorId(Long id){
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNaoEncontradoException(id));
    }

    public Pedido adicionar(Pedido pedido){
        List<Produto> produtos = pedido.getProdutos();
        Long clienteId = pedido.getCliente().getId();

        Cliente cliente = clienteService.buscarPorId(clienteId);

        double total = totalProdutos(produtos);
        List<Produto> produtosAtuais = atualizaListaProdutos(produtos);

        pedido.setTotal(new BigDecimal(total));
        pedido.setCliente(cliente);
        pedido.setProdutos(produtosAtuais);
        return pedidoRepository.save(pedido);
    }

    private List<Produto> atualizaListaProdutos(List<Produto> produtos) {
        return produtos.stream()
                .map(e -> produtoService.buscarPorId(e.getId()))
                .collect(Collectors.toList());
    }

    private double totalProdutos(List<Produto> produtos) {
        return produtos.stream()
                .mapToDouble(e -> {
                    Produto produto = produtoService.buscarPorId(e.getId());
                    return produto.getPreco().doubleValue();
                })
                .sum();
    }

    public Pedido atualizar(Long id, Pedido pedido){
        Pedido pedidoAtual = buscarPorId(id);

        BeanUtils.copyProperties(pedido, pedidoAtual, "id");

        return adicionar(pedidoAtual);
    }

    public void excluir(Long id){
        try{
            pedidoRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new PedidoNaoEncontradoException(id);
        }catch (DataIntegrityViolationException e){
            throw new PedidoEmUsoException(id);
        }
    }

}
