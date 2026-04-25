package pt.uevora.user_management.exeptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("Não existe algum utilizador com id: " + id);
    }
}