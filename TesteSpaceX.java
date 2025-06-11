package spacex;

public class TesteSpaceX {
    public static void main(String[] args) {
        // 1. Cria o Centro de Lançamento
        CentroDeLancamento centro = new CentroDeLancamento();

        // 2. Cria Astronautas e os adiciona ao centro
        Astronauta yuri = new Astronauta("Yuri Gagarin", 34, "Preparação para voo");
        Astronauta neil = new Astronauta("Neil Armstrong", 38, "Preparação para a Lua");
        Astronauta sally = new Astronauta("Sally Ride", 32, "Pesquisa em órbita");

        centro.adicionarAstronauta(yuri);
        centro.adicionarAstronauta(neil);
        centro.adicionarAstronauta(sally);

        // 3. Cria Foguetes e os adiciona ao centro (com status inicial "Pronto para lançamento")
        Falcon9 falcon001 = new Falcon9("Falcon 9-001", 22800, 7600, 100000, "Pronto para lançamento", 70, 549000, 0, "SpaceX", "Reutilizável");
        Starship starship001 = new Starship("Starship-001", 100000, 17000, 500000, "Pronto para lançamento", 120, 5000000, 0, "SpaceX", "Totalmente Reutilizável");
        Falcon9 falcon002 = new Falcon9("Falcon 9-002", 20000, 7600, 80000, "Manutenção", 70, 549000, 0, "SpaceX", "Reutilizável"); // Este não está pronto
        Starship starship002 = new Starship("Starship-002", 90000, 16000, 450000, "Pronto para lançamento", 120, 4800000, 0, "SpaceX", "Totalmente Reutilizável");


        centro.adicionarFoguete(falcon001);
        centro.adicionarFoguete(starship001);
        centro.adicionarFoguete(falcon002);
        centro.adicionarFoguete(starship002);

        // 4. Adiciona combustível
        centro.adicionarCombustivel(300000.0); // Suficiente para a primeira missão
        centro.adicionarCombustivel(700000.0); // Suficiente para a segunda missão

        centro.listarRecursosDisponiveis();

        // 5. Crie e adiciona Missões à fila
        // Observe que passamos UMA INSTÂNCIA de Foguete como "fogueteIdeal" na Missao.
        // O CentroDeLancamento vai procurar por um foguete do MESMO TIPO e status "Pronto para lançamento".
        Missao missaoSatCom = new Missao("Lançamento de Satélite X", "Colocar satélite de internet em órbita", new Falcon9("temp", 0, 0, 0, "temp", 0, 0, 0, "temp", "temp"), yuri, 150000);
        Missao missaoMarte = new Missao("Missão Tripulada Marte 2040", "Exploração inicial da superfície marciana", new Starship("temp", 0, 0, 0, "temp", 0, 0, 0, "temp", "temp"), neil, 600000);
        Missao missaoAbastecimentoISS = new Missao("Abastecimento da ISS", "Entregar suprimentos e tripulação", new Falcon9("temp", 0, 0, 0, "temp", 0, 0, 0, "temp", "temp"), sally, 80000);
        Missao missaoCombustivelInsuficiente = new Missao("Teste de Combustível (Falha Esperada)", "Teste de falha por falta de combustível", new Starship("temp", 0, 0, 0, "temp", 0, 0, 0, "temp", "temp"), neil, 900000);
        Missao missaoFogueteEmManutencao = new Missao("Teste de Foguete Manutenção (Falha Esperada)", "Teste de falha por foguete em manutenção", new Falcon9("temp", 0, 0, 0, "temp", 0, 0, 0, "temp", "temp"), sally, 50000);


        centro.adicionarMissao(missaoSatCom);
        centro.adicionarMissao(missaoMarte);
        centro.adicionarMissao(missaoAbastecimentoISS);
        centro.adicionarMissao(missaoCombustivelInsuficiente);
        centro.adicionarMissao(missaoFogueteEmManutencao);


        centro.listarFilaDeMissoes();

        // 6. Tenta iniciar as missões
        System.out.println("\n--- Iniciando Operações do Centro de Lançamento ---");

        centro.prepararEIniciarProximaMissao(); // Deve lançar Missão Satélite X (usa Falcon 9-001)
        centro.listarRecursosDisponiveis();
        centro.listarFilaDeMissoes();

        centro.prepararEIniciarProximaMissao(); // Deve lançar Missão Tripulada Marte 2040 (usa Starship-001)
        centro.listarRecursosDisponiveis();
        centro.listarFilaDeMissoes();

        centro.prepararEIniciarProximaMissao(); // Deve lançar Abastecimento da ISS (usa Falcon 9-00X, se houver outro pronto)
        centro.listarRecursosDisponiveis();
        centro.listarFilaDeMissoes();

        centro.prepararEIniciarProximaMissao(); // Deve falhar: combustível insuficiente
        centro.listarRecursosDisponiveis();
        centro.listarFilaDeMissoes();

        centro.prepararEIniciarProximaMissao(); // Deve falhar: foguete em manutenção (Falcon 9-002)
        centro.listarRecursosDisponiveis();
        centro.listarFilaDeMissoes();

        System.out.println("\n--- Fim das Operações Simuladas ---");
    }
}