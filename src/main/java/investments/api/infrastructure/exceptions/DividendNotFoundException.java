package investments.api.infrastructure.exceptions;

public class DividendNotFoundException extends RuntimeException {
    public DividendNotFoundException(Long id) {
        super("Dividendo de id " + id + " não encontrado em nosso sistema!");
    }
}

