package spacex;

public class Astronauta extends Pessoa {
    private String especialidade;

    public Astronauta(String nome, int idade, String especialidade) {
        super(nome, idade);
        this.especialidade = especialidade;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    
    // Método específico de Astronauta para realizar treinamento.
    public void realizarTreinamento() {
        System.out.println("Astronauta " + getNome() 
            + " está realizando treinamento de especialidade: " + especialidade + ".");
    }

    @Override
    public void apresentar() {
        System.out.println("Olá, sou o(a) Astronauta " + getNome() 
            + ", tenho " + getIdade() + " anos e especialidade em " + especialidade + ".");
    }
}

