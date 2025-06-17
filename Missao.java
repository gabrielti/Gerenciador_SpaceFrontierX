package spacex;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Missao {
    private String nome;
    private LocalDate data;
    private String objetivo;
    private Foguete fogueteIdeal;
    private List<Astronauta> astronautasDesignados;
    private double combustivelNecessario;
    private String status;

    public Missao(String nome, LocalDate data, String objetivo, Foguete fogueteIdeal, List<Astronauta> astronautasDesignados, double combustivelNecessario) {
        this.nome = nome;
        this.data = data;
        this.objetivo = objetivo;
        this.fogueteIdeal = fogueteIdeal;
        this.astronautasDesignados = new ArrayList<>(astronautasDesignados);
        this.combustivelNecessario = combustivelNecessario;
        this.status = "Pendente";
    }

    // --- Getters e Setters (como mostrado anteriormente) ---
    public String getNome() { return nome; }
    public LocalDate getData() { return data; }
    public String getObjetivo() { return objetivo; }
    public Foguete getFogueteIdeal() { return fogueteIdeal; }
    public List<Astronauta> getAstronautasDesignados() { return astronautasDesignados; }
    public double getCombustivelNecessario() { return combustivelNecessario; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public void setFogueteIdeal(Foguete fogueteIdeal) { this.fogueteIdeal = fogueteIdeal; }
    public void setCombustivelNecessario(double combustivelNecessario) { this.combustivelNecessario = combustivelNecessario; }
    public void setObjetivo(String objetivo) { this.objetivo = objetivo; }
    public void setData(LocalDate data) { this.data = data; }
    public void adicionarAstronauta(Astronauta astronauta) {
        if (this.astronautasDesignados == null) {
            this.astronautasDesignados = new ArrayList<>();
        }
        this.astronautasDesignados.add(astronauta);
    }

    public void exibirDetalhes() {
        System.out.println("--- Detalhes da Missão ---");
        System.out.println("Nome: " + nome);
        System.out.println("Data: " + data);
        System.out.println("Objetivo: " + objetivo);
        System.out.println("Foguete Ideal: " + (fogueteIdeal != null ? fogueteIdeal.getNome() : "Nenhum especificado"));
        System.out.println("Astronautas Designados:");
        if (astronautasDesignados != null && !astronautasDesignados.isEmpty()) {
            for (Astronauta a : astronautasDesignados) {
                System.out.println("  - " + a.getNome());
            }
        } else {
            System.out.println("  Nenhum astronauta designado.");
        }
        System.out.println("Combustível Necessário: " + combustivelNecessario + " litros");
        System.out.println("Status: " + status);
    }

    // --- Método executarMissao() com polimorfismo e tratamento de exceção ---
    public void executarMissao() throws LancamentoAbortadoException {
        System.out.println("\n--- Iniciando Procedimentos da Missão: " + this.nome + " ---");
        this.setStatus("Em Andamento");

        // 1. Verificações preliminares
        if (this.fogueteIdeal == null) {
            throw new LancamentoAbortadoException("Missão não pode ser executada: Foguete ideal não especificado.");
        }
        if (this.astronautasDesignados == null || this.astronautasDesignados.isEmpty()) {
            throw new LancamentoAbortadoException("Missão '" + this.nome + "' abortada: Nenhum astronauta designado.");
        }

        // Verifica o status do foguete (conforme a lógica que você tem no CentroDeLancamento)
        // Note que o abastecimento e a verificação do combustível do foguete devem ocorrer no CentroDeLancamento
        // antes de chamar este método, como discutimos.
        if (!"Pronto".equalsIgnoreCase(this.fogueteIdeal.getStatus())) {
            throw new LancamentoAbortadoException("Foguete " + this.fogueteIdeal.getNome() + " não está pronto para lançamento. Status atual: " + this.fogueteIdeal.getStatus());
        }

        // Verifica o combustível que o foguete *já tem a bordo* (assumindo que o Centro o abasteceu)
        if (this.fogueteIdeal.getCombustivel() < this.combustivelNecessario) {
            throw new LancamentoAbortadoException("Combustível a bordo do foguete " + this.fogueteIdeal.getNome() + " insuficiente para a missão '" + this.nome + "'. Necessário: " + this.combustivelNecessario + " litros. A bordo: " + this.fogueteIdeal.getCombustivel() + " litros.");
        }


        System.out.println("Verificações pré-lançamento concluídas para o " + this.fogueteIdeal.getNome() + ".");
        System.out.println("Tripulação da missão '" + this.nome + "':");
        for (Astronauta a : astronautasDesignados) {
            System.out.println("  - " + a.getNome());
            a.realizarTreinamento(); // Chama o método de treinamento de cada astronauta
        }

        // 2. Acionar o lançamento do foguete (Polimorfismo!)
        // O método 'lançar()' será chamado na instância concreta de Foguete (Falcon9 ou Starship).
        // O comportamento específico de 'lançar()' é definido na subclasse.
        System.out.println("Iniciando sequência de lançamento do " + this.fogueteIdeal.getNome() + " para a missão '" + this.nome + "'...");
        try {
            this.fogueteIdeal.lançar(); // Chama o método polimórfico de lançamento do foguete

            // Se o lançamento foi bem-sucedido, o foguete consumiu parte do seu combustível.
            // Aqui, simulamos o consumo restante da missão (se o `lançar()` do foguete não consumir tudo)
            // Ou simplesmente atualizamos o status do foguete e da missão.
            this.fogueteIdeal.setCombustivel(this.fogueteIdeal.getCombustivel() - this.combustivelNecessario); // Consome do foguete o que a missão precisa
            System.out.println("Combustível restante a bordo do " + this.fogueteIdeal.getNome() + ": " + this.fogueteIdeal.getCombustivel() + " litros.");

            this.fogueteIdeal.setStatus("Em Órbita"); // Atualiza o status do foguete após um lançamento bem-sucedido
            this.setStatus("Concluída"); // A missão é marcada como concluída

            System.out.println("Missão '" + this.nome + "' concluída com sucesso!");

        } catch (LancamentoAbortadoException e) {
            // Se o próprio método lançar() do foguete lançar uma exceção
            this.fogueteIdeal.setStatus("Falha no Lançamento");
            this.setStatus("Cancelada");
            System.err.println("❌ Lançamento da Missão '" + this.nome + "' ABORTADO: " + e.getMessage());
            throw e; // Relança a exceção para que o CentroDeLancamento possa tratá-la
        } catch (Exception e) {
            // Captura qualquer outra exceção inesperada durante o lançamento
            this.fogueteIdeal.setStatus("Falha Crítica");
            this.setStatus("Cancelada");
            System.err.println("❌ Erro inesperado durante o lançamento da Missão '" + this.nome + "': " + e.getMessage());
            throw new LancamentoAbortadoException("Erro inesperado na missão '" + this.nome + "': " + e.getMessage(), e);
        }
    }
}