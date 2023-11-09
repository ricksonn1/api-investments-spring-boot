package investments.api.infrastructure.exceptions;

public class DividendAlreadyRegisteredException extends RuntimeException {
    public DividendAlreadyRegisteredException(Long id) {
        super("Dividendo de id " + id + " já cadastrado em nosso sistema!");
    }
}
