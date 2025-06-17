package spacex;

import java.util.ArrayList;

// Gerenciamento de astronautas e foguetes
public class DemoColecoes {
    public static void main(String[] args) {
        // --- 1) Cria lista de astronautas (ArrayList<Pessoa>) ---
        ArrayList<Pessoa> listaAstronautas = new ArrayList<>();

        // Instancia dois astronautas qualquer
        Astronauta astro1 = new Astronauta("Neil Armstrong", 38, "Comandante Lunar");
        Astronauta astro2 = new Astronauta("Buzz Aldrin", 39, "Piloto de Módulo Lunar");

        // Adiciona na lista
        listaAstronautas.add(astro1);
        listaAstronautas.add(astro2);

        // --- 2) Cria lista de foguetes (ArrayList<Foguete>) ---
        ArrayList<Foguete> listaFoguetes = new ArrayList<>();

        // Instancia um foguete Falcon 9 como exemplo
        Falcon9 falcon9 = new Falcon9(
            "Falcon 9 Block 5",   // nome
            7607000.0,            // empuxo (Newton)
            500000.0,             // combustivel (unidade arbitrária)
            22800.0,              // carga (kg)
            "Pronto para lançamento"
        );

        // Adiciona na lista de foguetes
        listaFoguetes.add(falcon9);

        // --- 3) “Missão” simples: exibe dados dos astronautas e do foguete ---
        System.out.println("----- Missão Apollo Simulada -----\n");

        System.out.println("Astronautas escalados para esta missão:");
        for (Pessoa p : listaAstronautas) {
            p.apresentar();  // chama o método polimórfico de cada Astronauta
        }

        System.out.println("\nFoguetes disponíveis para a missão:");
        for (Foguete f : listaFoguetes) {
            System.out.println(f.toString()); // usa toString() definido em Foguete
        }

        // Exemplo de chamar manutenção no foguete selecionado
        System.out.println("\nExecutando rotina de manutenção no foguete:");
        falcon9.realizarManutencao();
    }
}
