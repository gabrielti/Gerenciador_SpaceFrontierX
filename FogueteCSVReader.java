package spacex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FogueteCSVReader {

    public static ArrayList<Foguete> readFoguetesFromCSV(String filePath) {
        ArrayList<Foguete> foguetes = new ArrayList<>();
        String line;
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);

                if (data.length >= 10) {
                    String nome = data[0];
                    double carga = Double.parseDouble(data[1]);
                    double empuxo = Double.parseDouble(data[2]);
                    double combustivel = Double.parseDouble(data[3]);
                    String status = data[4];
                    double altura = Double.parseDouble(data[5]);
                    double peso = Double.parseDouble(data[6]);
                    double velocidade = Double.parseDouble(data[7]);
                    String fabricante = data[8];
                    String tipo = data[9];

                    if (tipo.equalsIgnoreCase("Falcon9")) {
                        foguetes.add(new Falcon9(nome, carga, empuxo, combustivel, status, altura, peso, velocidade, fabricante, tipo));
                    } else if (tipo.equalsIgnoreCase("Starship")) {
                        foguetes.add(new Starship(nome, carga, empuxo, combustivel, status, altura, peso, velocidade, fabricante, tipo));
                    } else {
                        System.err.println("Tipo de foguete desconhecido: " + tipo + " na linha: " + line);
                    }
                } else {
                    System.err.println("Linha CSV inválida (dados insuficientes): " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo CSV: " + e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("Erro de formato numérico no arquivo CSV: " + e.getMessage());
            e.printStackTrace();
        }
        return foguetes;
    }
}


