package dio.spring.jpa.crud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ProdutoEmUsoException extends RuntimeException {
    public ProdutoEmUsoException(Long id) {
        super(String.format("O produto de código %s não pode ser removido pois está em uso.",id));
    }
}
