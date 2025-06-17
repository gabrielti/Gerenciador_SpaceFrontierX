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

    public void executarMissao() throws LancamentoAbortadoException {
        System.out.println("\n--- Iniciando Procedimentos da Missão: " + this.nome + " ---");
        this.setStatus("Em Andamento");

        if (this.fogueteIdeal == null) {
            throw new LancamentoAbortadoException("Missão não pode ser executada: Foguete ideal não especificado.");
        }
        if (this.astronautasDesignados == null || this.astronautasDesignados.isEmpty()) {
            throw new LancamentoAbortadoException("Missão '" + this.nome + "' abortada: Nenhum astronauta designado.");
        }

        if (!"Pronto".equalsIgnoreCase(this.fogueteIdeal.getStatus())) {
            throw new LancamentoAbortadoException("Foguete " + this.fogueteIdeal.getNome() + " não está pronto para lançamento. Status atual: " + this.fogueteIdeal.getStatus());
        }

        if (this.fogueteIdeal.getCombustivel() < this.combustivelNecessario) {
            throw new LancamentoAbortadoException("Combustível a bordo do foguete " + this.fogueteIdeal.getNome() + " insuficiente para a missão '" + this.nome + "'. Necessário: " + this.combustivelNecessario + " litros. A bordo: " + this.fogueteIdeal.getCombustivel() + " litros.");
        }


        System.out.println("Verificações pré-lançamento concluídas para o " + this.fogueteIdeal.getNome() + ".");
        System.out.println("Tripulação da missão '" + this.nome + "':");
        for (Astronauta a : astronautasDesignados) {
            System.out.println("  - " + a.getNome());
            a.realizarTreinamento();
        }

        System.out.println("Iniciando sequência de lançamento do " + this.fogueteIdeal.getNome() + " para a missão '" + this.nome + "'...");
        try {
            this.fogueteIdeal.lançar(); // polimorfismo
            this.fogueteIdeal.setCombustivel(this.fogueteIdeal.getCombustivel() - this.combustivelNecessario);
            System.out.println("Combustível restante a bordo do " + this.fogueteIdeal.getNome() + ": " + this.fogueteIdeal.getCombustivel() + " litros.");

            this.fogueteIdeal.setStatus("Em Órbita");
            this.setStatus("Concluída");

            System.out.println("Missão '" + this.nome + "' concluída com sucesso!");

        } catch (LancamentoAbortadoException e) {
            this.fogueteIdeal.setStatus("Falha no Lançamento");
            this.setStatus("Cancelada");
            System.err.println("❌ Lançamento da Missão '" + this.nome + "' ABORTADO: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            this.fogueteIdeal.setStatus("Falha Crítica");
            this.setStatus("Cancelada");
            System.err.println("❌ Erro inesperado durante o lançamento da Missão '" + this.nome + "': " + e.getMessage());
            throw new LancamentoAbortadoException("Erro inesperado na missão '" + this.nome + "': " + e.getMessage(), e);
        }
    }
}