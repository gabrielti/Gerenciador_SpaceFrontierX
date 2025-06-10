package spacex;

public class TesteSpaceX {
    public static void main(String[] args) {
        Astronauta yuri = new Astronauta("Yuri Gagarin", 34, "Primeiro voo espacial");
        Engenheiro sergei = new Engenheiro("Sergei Korolev", 59, "Foguetes");

        System.out.println("--- Astronauta ---");
        yuri.apresentar();
        yuri.realizarTreinamento();
        System.out.println("Nome do astronauta: " + yuri.getNome());
        System.out.println("Missão do astronauta: " + yuri.getMissaoAtual());

        System.out.println("\n--- Engenheiro ---");
        sergei.apresentar();
        sergei.projetarComponente();
        System.out.println("Nome do engenheiro: " + sergei.getNome());
        System.out.println("Especialidade do engenheiro: " + sergei.getEspecialidade());
    }
}