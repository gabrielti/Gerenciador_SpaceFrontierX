package spacex;

public class LancamentoAbortadoException extends Exception {

    // Construtor que aceita uma mensagem de erro
    public LancamentoAbortadoException(String message) {
        super(message);
    }

    // Opcional: Construtor que aceita uma mensagem e uma causa (outra exceção)
    public LancamentoAbortadoException(String message, Throwable cause) {
        super(message, cause);
    }
}