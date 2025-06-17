package spacex;
public class Falcon9 extends Foguete {
    private String fabricante;
    private int numeroDeMotores;

    public Falcon9(String nome, double empuxo, double combustivel, double carga, String status) {
        super(nome, empuxo, combustivel, carga, status);
        this.fabricante = "SpaceX";
        this.numeroDeMotores = 9;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public int getNumeroDeMotores() {
        return numeroDeMotores;
    }

    public void setNumeroDeMotores(int numeroDeMotores) {
        this.numeroDeMotores = numeroDeMotores;
    }

    @Override
    public void realizarManutencao() {
        System.out.println("Falcon 9 (" + getNome() + "):");
        System.out.println("- Verificando todos os " + numeroDeMotores + " motores Merlin.");
        System.out.println("- Conferindo sistema de combustível (" + getCombustivel() + " unidades).");
        System.out.println("- Inspecionando estrutura do primeiro estágio.");
        System.out.println("Falcon 9 pronto para lançamento!\n");
    }
}
