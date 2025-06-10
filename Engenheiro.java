package spacex;

public class Engenheiro extends Pessoa {
    private String especialidade; // Adicionei um atributo específico para Engenheiro

    public Engenheiro(String nome, int idade, String especialidade) {
        super(nome, idade);
        this.especialidade = especialidade;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public void projetarComponente() {
        System.out.println(getNome() + " está projetando um componente com sua especialidade em " + especialidade + ".");
    }

    @Override
    public void apresentar() {
        System.out.println("Olá, sou o engenheiro(a) " + getNome() + ", tenho " + getIdade() + " anos e sou especialista em " + especialidade + ".");
    }
}