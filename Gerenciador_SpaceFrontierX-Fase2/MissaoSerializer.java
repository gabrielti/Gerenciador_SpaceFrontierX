package spacex;

import java.io.*;
import java.util.ArrayList;

public class MissaoSerializer implements Serializable {

    public static void salvarMissoes(ArrayList<Missao> missoes, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(missoes);
            System.out.println("Missões salvas com sucesso em: " + filePath);
        } catch (IOException e) {
            System.err.println("Erro ao salvar missões: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static ArrayList<Missao> carregarMissoes(String filePath) {
        ArrayList<Missao> missoes = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            missoes = (ArrayList<Missao>) ois.readObject();
            System.out.println("Missões carregadas com sucesso de: " + filePath);
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de missões não encontrado. Criando uma nova lista.");
            missoes = new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar missões: " + e.getMessage());
            e.printStackTrace();
        }
        return missoes;
    }
}


