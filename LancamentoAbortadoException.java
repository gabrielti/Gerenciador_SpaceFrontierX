package spacex;

public class LancamentoAbortadoException extends Exception {

    // Construtor que aceita uma mensagem de erro
    public LancamentoAbortadoException(String message) {
        super(message);
    }

    public LancamentoAbortadoException(String message, Throwable cause) {
        super(message, cause);
    }
}