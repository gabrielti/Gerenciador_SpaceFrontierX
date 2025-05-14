Repositório: Gerenciador_SpaceFrontierX

Integrantes: Gabriel Takiguchi(GT), Wellington Breno(WELL), Leonardo Gehr(LG)

Contexto do Projeto
Este projeto foi desenvolvido como parte da disciplina de Programação Orientada a Objetos (POO), com o objetivo de aplicar os principais conceitos da programação orientada a objetos em um contexto prático e criativo.

A ideia do projeto é simular o controle de missões espaciais da empresa SpaceX. O sistema permite o gerenciamento de foguetes, astronautas e missões, abordando aspectos como:

    Criação e manutenção de foguetes (como Falcon9 e Starship)

    Alocação de astronautas para as missões

    Planejamento e lançamento de missões

    Verificação de condições para lançamento

    Simulação de exceções durante o processo

    Persistência de dados em arquivos

    Interface gráfica simples para interação com o sistema

O projeto busca representar um cenário inspirado no mundo real, onde há equipes, tecnologia e riscos envolvidos em lançamentos espaciais. Dessa forma, conceitos como herança, polimorfismo, tratamento de exceções, abstração e uso de coleções são colocados em prática de maneira integrada.

O objetivo final é oferecer uma solução simples, porém estruturada, que demonstre domínio da modelagem orientada a objetos e também da persistência de dados e interface gráfica em Java.

Etapas e Pipeline:

Fase 1: Modelagem POO

Todas as tarefas abaixo fazem parte do escopo “Modelagem de classes”.
Tarefas Gerais:

    Criar a classe abstrata Pessoa com os atributos privados nome e idade. - GT

    Implementar os métodos getters e setters na classe Pessoa. - GT

    Criar as subclasses Astronauta (com o método realizarTreinamento()) e Engenheiro (com o método projetarComponente()). - LG

    Criar a classe abstrata Foguete com, no mínimo, cinco atributos: nome, empuxo, combustível, carga e status, além de métodos básicos. - LG

    Criar as subclasses Falcon9 e Starship, sobrescrevendo o método realizarManutencao(). -WELL

    Utilizar coleções (ArrayList<Foguete> e ArrayList<Pessoa>) e modelar a associação de uma missão contendo vários astronautas e um foguete. - WELL

🧠 Fase 2: Regras de Negócio e Persistência

Todas as tarefas desta fase pertencem ao escopo “Lógica de Missões, Exceções e Persistência”.
Tarefas Gerais:

    Implementar a classe Missao com os atributos: nome, data, lista de astronautas e foguete. - GT

    Criar o método executarMissao() utilizando polimorfismo de acordo com o tipo de foguete. -GT

    Implementar a classe CentroDeLancamento para gerenciar a fila de missões e controlar a disponibilidade de lançamento. - LG

    Definir a exceção LancamentoAbortadoException e lançá-la em casos de falha (por exemplo, combustível insuficiente). - LG

    Realizar a leitura e parsing do arquivo foguetes.csv para popular as instâncias de Foguete. - WELL

    Salvar e carregar a lista de missões utilizando serialização (missoes.dat). - WELL

💻 Fase 3: Interface Gráfica e Simulação

As tarefas desta fase abrangem o escopo “GUI e Animação”.
Tarefas Gerais:

    Criar um menu básico com Swing (JFrame ou JOptionPane) para cadastro de foguetes e astronautas. - GT

    Implementar telas para criação de missão e gatilho de execução. - GT

    Exibir feedbacks de sucesso ou erro utilizando diálogos (JOptionPane), como “Lançamento abortado” ou “Missão concluída”. - LG

    Desenvolver um JPanel com animação de contagem regressiva. - LG

    Desenhar um gráfico simples da subida do foguete e atualizar o status da missão em tempo real. - WELL

    Escrever o arquivo README.md com instruções sobre como compilar, executar o projeto e utilizar a interface. - WELL


