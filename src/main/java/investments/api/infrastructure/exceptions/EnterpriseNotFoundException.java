package investments.api.infrastructure.exceptions;

public class EnterpriseNotFoundException extends RuntimeException {
    public EnterpriseNotFoundException(Long id) {
        super("Empresa " + id + " não localizada em nosso sistema!");
    }
}

