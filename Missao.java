package spacex;

public class Missao {
    private String nome;
    private String objetivo;
    private Foguete fogueteIdeal; // O TIPO de foguete ideal para a missão (ex: Falcon 9 ou Starship)
    private Astronauta astronautaDesignado; // O astronauta principal para esta missão
    private double combustivelNecessario;
    private String status; // Ex: "Pendente", "Em Andamento", "Concluída", "Cancelada"

    public Missao(String nome, String objetivo, Foguete fogueteIdeal, Astronauta astronautaDesignado, double combustivelNecessario) {
        this.nome = nome;
        this.objetivo = objetivo;
        this.fogueteIdeal = fogueteIdeal;
        this.astronautaDesignado = astronautaDesignado;
        this.combustivelNecessario = combustivelNecessario;
        this.status = "Pendente"; // Status inicial de uma missão
    }

    // Getters
    public String getNome() { return nome; }
    public String getObjetivo() { return objetivo; }
    public Foguete getFogueteIdeal() { return fogueteIdeal; }
    public Astronauta getAstronautaDesignado() { return astronautaDesignado; }
    public double getCombustivelNecessario() { return combustivelNecessario; }
    public String getStatus() { return status; }

    // Setter para o status da missão
    public void setStatus(String status) { this.status = status; }

    public void exibirDetalhes() {
        System.out.println("--- Detalhes da Missão ---");
        System.out.println("Nome: " + nome);
        System.out.println("Objetivo: " + objetivo);
        System.out.println("Foguete Ideal: " + (fogueteIdeal != null ? fogueteIdeal.getNome() : "Nenhum especificado"));
        System.out.println("Astronauta Designado: " + (astronautaDesignado != null ? astronautaDesignado.getNome() : "Nenhum designado"));
        System.out.println("Combustível Necessário: " + combustivelNecessario + " litros");
        System.out.println("Status: " + status);
    }
}