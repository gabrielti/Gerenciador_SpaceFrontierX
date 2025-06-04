import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

// Classes básicas
class Foguete {
    String nome;
    public Foguete(String nome) { this.nome = nome; }
    public String toString() { return nome; }
} 

class Astronauta {
    String nome;
    public Astronauta(String nome) { this.nome = nome; }
    public String toString() { return nome; }
}

class Missao {
    String nome;
    Foguete foguete;
    ArrayList<Astronauta> astronautas = new ArrayList<>();

    public Missao(String nome, Foguete foguete, ArrayList<Astronauta> astronautas) {
        this.nome = nome;
        this.foguete = foguete;
        this.astronautas = astronautas;
    }

    public String executar() {
        return "Missão '" + nome + "' iniciada com foguete " + foguete.nome + 
               " e " + astronautas.size() + " astronauta(s).";
    }
}

// Interface principal
public class MenuEspacial extends JFrame {
    private ArrayList<Foguete> foguetes = new ArrayList<>();
    private ArrayList<Astronauta> astronautas = new ArrayList<>();

    public MenuEspacial() {
        setTitle("Sistema Espacial - GT");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton btnCadastrarFoguete = new JButton("Cadastrar Foguete");
        JButton btnCadastrarAstronauta = new JButton("Cadastrar Astronauta");
        JButton btnCriarMissao = new JButton("Criar Missão");

        btnCadastrarFoguete.addActionListener(e -> cadastrarFoguete());
        btnCadastrarAstronauta.addActionListener(e -> cadastrarAstronauta());
        btnCriarMissao.addActionListener(e -> criarMissao());

        setLayout(new GridLayout(3, 1, 10, 10));
        add(btnCadastrarFoguete);
        add(btnCadastrarAstronauta);
        add(btnCriarMissao);

        setVisible(true);
    }

    private void cadastrarFoguete() {
        String nome = JOptionPane.showInputDialog(this, "Nome do foguete:");
        if (nome != null && !nome.isEmpty()) {
            foguetes.add(new Foguete(nome));
            JOptionPane.showMessageDialog(this, "Foguete '" + nome + "' cadastrado com sucesso!");
        }
    }

    private void cadastrarAstronauta() {
        String nome = JOptionPane.showInputDialog(this, "Nome do astronauta:");
        if (nome != null && !nome.isEmpty()) {
            astronautas.add(new Astronauta(nome));
            JOptionPane.showMessageDialog(this, "Astronauta '" + nome + "' cadastrado com sucesso!");
        }
    }

    private void criarMissao() {
        if (foguetes.isEmpty() || astronautas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Cadastre ao menos 1 foguete e 1 astronauta primeiro.");
            return;
        }

        String nomeMissao = JOptionPane.showInputDialog(this, "Nome da missão:");
        if (nomeMissao == null || nomeMissao.isEmpty()) return;

        Foguete fogueteSelecionado = (Foguete) JOptionPane.showInputDialog(
                this,
                "Escolha o foguete:",
                "Selecionar Foguete",
                JOptionPane.QUESTION_MESSAGE,
                null,
                foguetes.toArray(),
                foguetes.get(0)
        );

        ArrayList<Astronauta> selecionados = new ArrayList<>();
        JList<Astronauta> list = new JList<>(astronautas.toArray(new Astronauta[0]));
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        int option = JOptionPane.showConfirmDialog(this, new JScrollPane(list),
                "Selecionar Astronautas", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            for (Astronauta a : list.getSelectedValuesList()) {
                selecionados.add(a);
            }

            Missao missao = new Missao(nomeMissao, fogueteSelecionado, selecionados);
            JOptionPane.showMessageDialog(this, missao.executar());
        }
    }
   
     Criar um menu básico com Swing (JFrame ou JOptionPane) para cadastro de foguetes e astronautas. - GT

Implementar telas para criação de missão e gatilho de execução. - GT
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MenuEspacial::new);
    }
}
