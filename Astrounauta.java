package spacex;

public class Astronauta extends Pessoa {
    private String missaoAtual; // Adicionei um atributo específico para Astronauta

    public Astronauta(String nome, int idade, String missaoAtual) {
        super(nome, idade);
        this.missaoAtual = missaoAtual;
    }

    public String getMissaoAtual() {
        return missaoAtual;
    }

    public void setMissaoAtual(String missaoAtual) {
        this.missaoAtual = missaoAtual;
    }

    public void realizarTreinamento() {
        System.out.println(getNome() + " está realizando treinamento rigoroso para a missão " + missaoAtual + ".");
    }

    @Override
    public void apresentar() {
        System.out.println("Olá, sou o astronauta " + getNome() + ", tenho " + getIdade() + " anos e estou treinando para a missão " + missaoAtual + ".");
    }
}