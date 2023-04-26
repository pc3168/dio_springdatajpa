package dio.spring.jpa.crud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PedidoNaoEncontradoException extends RuntimeException{

    public PedidoNaoEncontradoException(Long id) {
        super(String.format("O pedido de código %s não foi encontrado.",id));
    }
}
