package spacex;

/**
 * Subclasse de Pessoa que representa um Engenheiro.
 * Adiciona o método projetarComponente().
 */
public class Engenheiro extends Pessoa {
    private String areaAtuacao; // Exemplo de atributo extra (opcional)

    public Engenheiro(String nome, int idade, String areaAtuacao) {
        super(nome, idade);
        this.areaAtuacao = areaAtuacao;
    }

    public String getAreaAtuacao() {
        return areaAtuacao;
    }

    public void setAreaAtuacao(String areaAtuacao) {
        this.areaAtuacao = areaAtuacao;
    }

    /**
     * Método específico de Engenheiro para projetar componentes.
     */
    public void projetarComponente() {
        System.out.println("Engenheiro " + getNome() 
            + " está projetando um componente na área de " + areaAtuacao + ".");
    }

    @Override
    public void apresentar() {
        System.out.println("Olá, sou o(a) Engenheiro(a) " + getNome() 
            + ", tenho " + getIdade() + " anos e atuo na área de " + areaAtuacao + ".");
    }
}
