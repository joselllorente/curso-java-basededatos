package curso.java.junit.excepciones;

public class DineroInsuficienteException extends RuntimeException{
    public DineroInsuficienteException(String message) {
        super(message);
    }
}
