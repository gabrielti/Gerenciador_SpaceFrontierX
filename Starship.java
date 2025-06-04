package spacex;

/**
 * Subclasse de Foguete que representa o foguete Starship.
 * Implementa o método realizarManutencao().
 */
public class Starship extends Foguete {
    private String fabricante;   // Ex.: "SpaceX"
    private String destino;      // Ex.: "Marte"

    public Starship(String nome, double empuxo, double combustivel, double carga, String status, String destino) {
        super(nome, empuxo, combustivel, carga, status);
        this.fabricante = "SpaceX";
        this.destino = destino;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    /**
     * Implementação concreta de realizarManutencao() para Starship.
     */
    @Override
    public void realizarManutencao() {
        System.out.println("Starship (" + getNome() + "):");
        System.out.println("- Inspeção completa do casco térmico.");
        System.out.println("- Teste de todos os motores Raptor (" + getEmpuxo() + " N).");
        System.out.println("- Verificação do sistema de suporte de vida para missão a " + destino + ".");
        System.out.println("Starship pronta para lançamento interplanetário!\n");
    }
}
