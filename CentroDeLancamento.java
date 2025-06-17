package spacex;

import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.List;

public class CentroDeLancamento {
    private Queue<Missao> filaDeMissoes;
    private List<Astronauta> astronautasDisponiveis;
    private List<Foguete> foguetesDisponiveis;
    private double combustivelTotalDisponivel; // Em litros

    public CentroDeLancamento() {
        this.filaDeMissoes = new LinkedList<>();
        this.astronautasDisponiveis = new ArrayList<>();
        this.foguetesDisponiveis = new ArrayList<>();
        this.combustivelTotalDisponivel = 0;
    }

    public void adicionarMissao(Missao missao) {
        filaDeMissoes.offer(missao); // Adiciona no final da fila
        System.out.println("Missão '" + missao.getNome() + "' adicionada à fila.");
    }

    public void adicionarAstronauta(Astronauta astronauta) {
        astronautasDisponiveis.add(astronauta);
        System.out.println("Astronauta " + astronauta.getNome() + " adicionado aos recursos disponíveis.");
    }

    public void adicionarFoguete(Foguete foguete) {
        foguetesDisponiveis.add(foguete);
        System.out.println("Foguete " + foguete.getNome() + " adicionado aos recursos disponíveis.");
    }

    public void adicionarCombustivel(double quantidade) {
        if (quantidade > 0) {
            combustivelTotalDisponivel += quantidade;
            System.out.println("Adicionado " + quantidade + " litros de combustível. Total agora: " + combustivelTotalDisponivel + " litros.");
        } else {
            System.out.println("Quantidade de combustível inválida.");
        }
    }

    // Tenta preparar e iniciar a próxima missão na fila.
    // Verifica a disponibilidade de astronauta, foguete e combustível antes de iniciar o lançamento.
    public void prepararEIniciarProximaMissao() throws LancamentoAbortadoException {
        if (filaDeMissoes.isEmpty()) {
            throw new LancamentoAbortadoException("Nenhuma missão na fila para ser lançada.");
        }

        Missao proximaMissao = filaDeMissoes.peek(); // Pega a primeira missão sem removê-la da fila

        System.out.println("\n--- Tentando preparar a missão: '" + proximaMissao.getNome() + "' ---");
        proximaMissao.exibirDetalhes();

        // Verifica Astronautas
        List<Astronauta> astronautasNecessarios = proximaMissao.getAstronautasDesignados();
        if (astronautasNecessarios == null || astronautasNecessarios.isEmpty()) {
            throw new LancamentoAbortadoException("Missão '" + proximaMissao.getNome() + "': Nenhum astronauta designado.");
        }

        for (Astronauta a : astronautasNecessarios) {
            if (!astronautasDisponiveis.contains(a)) {
                throw new LancamentoAbortadoException("Missão '" + proximaMissao.getNome() + "': Astronauta '" + a.getNome() + "' não está disponível no centro.");
            }
        }
        System.out.println("Todos os astronautas designados estão disponíveis.");

        // Encontra e verifica Foguete disponível (do tipo ideal)
        Foguete fogueteParaLancamento = null;
        for (Foguete f : foguetesDisponiveis) {
            // Verifica se o foguete é do tipo ideal para a missão E se está pronto para lançamento
            if (f.getTipo().equalsIgnoreCase(proximaMissao.getFogueteIdeal().getTipo())) && f.getStatus().equalsIgnoreCase("Pronto para lançamento")) {
                fogueteParaLancamento = f;
                break;
            }
        }

        if (fogueteParaLancamento == null) {
            throw new LancamentoAbortadoException("Nenhum foguete do tipo '" + proximaMissao.getFogueteIdeal().getTipo() + "' está disponível e pronto para lançamento para a missão '" + proximaMissao.getNome() + "'.");
        }

        // 3. Verifica Combustível
        if (combustivelTotalDisponivel < proximaMissao.getCombustivelNecessario()) {
            throw new LancamentoAbortadoException("Combustível insuficiente no centro para a missão '" + proximaMissao.getNome() + "'. Necessário: " + proximaMissao.getCombustivelNecessario() + " litros. Disponível: " + combustivelTotalDisponivel + " litros.");
        }

        fogueteParaLancamento.setCombustivel(fogueteParaLancamento.getCombustivel() + proximaMissao.getCombustivelNecessario());
        combustivelTotalDisponivel -= proximaMissao.getCombustivelNecessario();
        System.out.println("Foguete " + fogueteParaLancamento.getNome() + " abastecido com " + proximaMissao.getCombustivelNecessario() + " litros.");
        System.out.println("Combustível restante no centro: " + String.format("%.2f", combustivelTotalDisponivel) + " litros.");

        System.out.println("Todos os recursos para a missão '" + proximaMissao.getNome() + "' estão disponíveis!");

        for (Astronauta a : astronautasNecessarios) {
            astronautasDisponiveis.remove(a);
        }
        foguetesDisponiveis.remove(fogueteParaLancamento);
        astronautasDisponiveis.remove(astronautaNecessario);
        combustivelTotalDisponivel -= proximaMissao.getCombustivelNecessario();

        // Atualizar status da missão e do foguete
        proximaMissao.setStatus("Em Andamento");
        fogueteParaLancamento.setStatus("Em Lançamento"); // O método lançar() do foguete irá mudar para "Em voo"

        System.out.println("Iniciando o lançamento da missão " + proximaMissao.getNome() + " com o foguete " + fogueteParaLancamento.getNome() + " e o astronauta " + astronautaNecessario.getNome() + "...");
        fogueteParaLancamento.lançar(); // Chama o método de lançamento específico do Falcon9 ou do Starship

        // A missão é removida da fila do centro porque o lançamento já começou.
        filaDeMissoes.poll();

        System.out.println("Missão '" + proximaMissao.getNome() + "' iniciada com sucesso!");
        System.out.println("Combustível restante no centro: " + String.format("%.2f", combustivelTotalDisponivel) + " litros.");

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.err.println("A missão foi interrompida.");
            Thread.currentThread().interrupt();
        }
        proximaMissao.setStatus("Concluída");
    }

    // Lista todas as missões atualmente na fila de espera.
    public void listarFilaDeMissoes() {
        if (filaDeMissoes.isEmpty()) {
            System.out.println("\nA fila de missões está vazia.");
            return;
        }
        System.out.println("\n--- Fila de Missões Atuais ---");
        int i = 1;
        for (Missao missao : filaDeMissoes) {
            System.out.println(i++ + ". " + missao.getNome() + " (Objetivo: " + missao.getObjetivo() + ", Status: " + missao.getStatus() + ")");
        }
    }

    // Lista todos os recursos (astronautas, foguetes, combustível) disponíveis no centro.
    public void listarRecursosDisponiveis() {
        System.out.println("\n--- Recursos Disponíveis no Centro de Lançamento ---");
        System.out.println("Combustível Total: " + String.format("%.2f", combustivelTotalDisponivel) + " litros.");

        System.out.println("\nAstronautas Disponíveis:");
        if (astronautasDisponiveis.isEmpty()) {
            System.out.println("  Nenhum astronauta disponível.");
        } else {
            for (Astronauta a : astronautasDisponiveis) {
                System.out.println("  - " + a.getNome() + " (Idade: " + a.getIdade() + ", Missão Atual: " + a.getMissaoAtual() + ")");
            }
        }

        System.out.println("\nFoguetes Disponíveis:");
        if (foguetesDisponiveis.isEmpty()) {
            System.out.println("  Nenhum foguete disponível.");
        } else {
            for (Foguete f : foguetesDisponiveis) {
                System.out.println("  - " + f.getNome() + " (Tipo: " + f.getTipo() + ", Status: " + f.getStatus() + ", Combustível a bordo: " + f.getCombustivel() + " litros)");
            }
        }
    }
}