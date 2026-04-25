package pt.uevora.user_management.exeptions;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String email) {
        super("Este e-mail já encontra-se registado: " + email);
    }
}