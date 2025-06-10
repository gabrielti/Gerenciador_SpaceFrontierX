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

    // --- Métodos para Adicionar Recursos ---

    /**
     * Adiciona uma missão à fila de espera.
     * @param missao A missão a ser adicionada.
     */
    public void adicionarMissao(Missao missao) {
        filaDeMissoes.offer(missao); // Adiciona ao final da fila
        System.out.println("Missão '" + missao.getNome() + "' adicionada à fila.");
    }

    /**
     * Adiciona um astronauta aos recursos disponíveis do centro.
     * @param astronauta O astronauta a ser adicionado.
     */
    public void adicionarAstronauta(Astronauta astronauta) {
        astronautasDisponiveis.add(astronauta);
        System.out.println("Astronauta " + astronauta.getNome() + " adicionado aos recursos disponíveis.");
    }

    /**
     * Adiciona um foguete aos recursos disponíveis do centro.
     * @param foguete O foguete a ser adicionado.
     */
    public void adicionarFoguete(Foguete foguete) {
        foguetesDisponiveis.add(foguete);
        System.out.println("Foguete " + foguete.getNome() + " adicionado aos recursos disponíveis.");
    }

    /**
     * Adiciona uma quantidade de combustível ao tanque total do centro.
     * @param quantidade A quantidade de combustível em litros.
     */
    public void adicionarCombustivel(double quantidade) {
        if (quantidade > 0) {
            combustivelTotalDisponivel += quantidade;
            System.out.println("Adicionado " + quantidade + " litros de combustível. Total agora: " + combustivelTotalDisponivel + " litros.");
        } else {
            System.out.println("Quantidade de combustível inválida.");
        }
    }

    // --- Métodos para Gerenciar e Lançar Missões ---

    /**
     * Tenta preparar e iniciar a próxima missão na fila.
     * Verifica a disponibilidade de astronauta, foguete e combustível antes de iniciar o lançamento.
     * @return true se a missão foi preparada e lançada com sucesso, false caso contrário.
     */
    public boolean prepararEIniciarProximaMissao() {
        if (filaDeMissoes.isEmpty()) {
            System.out.println("Nenhuma missão na fila para ser lançada.");
            return false;
        }

        Missao proximaMissao = filaDeMissoes.peek(); // Pega a primeira missão sem removê-la da fila

        System.out.println("\n--- Tentando preparar a missão: '" + proximaMissao.getNome() + "' ---");
        proximaMissao.exibirDetalhes();

        // 1. Verificar Astronauta
        Astronauta astronautaNecessario = proximaMissao.getAstronautaDesignado();
        if (astronautaNecessario == null || !astronautasDisponiveis.contains(astronautaNecessario)) {
            System.out.println("ERRO: Astronauta '" + (astronautaNecessario != null ? astronautaNecessario.getNome() : "Nenhum") + "' não está disponível ou não foi designado para esta missão.");
            return false;
        }

        // 2. Encontrar e verificar Foguete disponível (do tipo ideal)
        Foguete fogueteParaLancamento = null;
        for (Foguete f : foguetesDisponiveis) {
            // Verifica se o foguete é do tipo ideal para a missão E se está pronto para lançamento
            if (f.getClass().equals(proximaMissao.getFogueteIdeal().getClass()) && f.getStatus().equals("Pronto para lançamento")) {
                fogueteParaLancamento = f;
                break;
            }
        }

        if (fogueteParaLancamento == null) {
            System.out.println("ERRO: Nenhum foguete do tipo '" + proximaMissao.getFogueteIdeal().getTipo() + "' está disponível e pronto para lançamento.");
            return false;
        }

        // 3. Verificar Combustível
        if (combustivelTotalDisponivel < proximaMissao.getCombustivelNecessario()) {
            System.out.println("ERRO: Combustível insuficiente para a missão. Necessário: " + proximaMissao.getCombustivelNecessario() + " litros. Disponível: " + combustivelTotalDisponivel + " litros.");
            return false;
        }

        // Se todos os recursos estiverem disponíveis, iniciar a missão
        System.out.println("Todos os recursos para a missão '" + proximaMissao.getNome() + "' estão disponíveis!");

        // Consumir recursos:
        // O foguete é removido dos recursos disponíveis do centro porque ele está sendo usado na missão.
        foguetesDisponiveis.remove(fogueteParaLancamento);
        // O astronauta é removido dos recursos disponíveis do centro porque ele está na missão.
        astronautasDisponiveis.remove(astronautaNecessario);
        // O combustível é consumido.
        combustivelTotalDisponivel -= proximaMissao.getCombustivelNecessario();

        // Atualizar status da missão e do foguete
        proximaMissao.setStatus("Em Andamento");
        fogueteParaLancamento.setStatus("Em Lançamento"); // O método lançar() do foguete irá mudar para "Em voo"

        System.out.println("Iniciando o lançamento da missão " + proximaMissao.getNome() + " com o foguete " + fogueteParaLancamento.getNome() + " e o astronauta " + astronautaNecessario.getNome() + "...");
        fogueteParaLancamento.lançar(); // Chama o método de lançamento específico do foguete (Falcon9 ou Starship)

        // A missão é removida da fila do centro porque o lançamento já começou.
        filaDeMissoes.poll();

        System.out.println("Missão '" + proximaMissao.getNome() + "' iniciada com sucesso!");
        System.out.println("Combustível restante no centro: " + combustivelTotalDisponivel + " litros.");

        return true;
    }

    // --- Métodos para Visualizar o Estado do Centro ---

    /**
     * Lista todas as missões atualmente na fila de espera.
     */
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

    /**
     * Lista todos os recursos (astronautas, foguetes, combustível) disponíveis no centro.
     */
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