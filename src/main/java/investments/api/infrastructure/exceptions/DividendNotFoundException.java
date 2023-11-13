package investments.api.infrastructure.exceptions;

public class DividendNotFoundException extends RuntimeException {
    public DividendNotFoundException(String date) {
        super("Não existe dividendos para data informada!");
    }
}

