package investments.api.infrastructure.exceptions;

public class EnterpriseAlreadyRegisteredException extends RuntimeException {
    public EnterpriseAlreadyRegisteredException(Long id) {
        super("Empresa do id " + id + " já consta em nosso sistema!");
    }
}
