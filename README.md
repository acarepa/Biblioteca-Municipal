# Biblioteca Municipal

Sistema de gestão de uma biblioteca, em linguagem Java, executado na consola. Permite registar livros, consultar o catálogo, gerir utilizadores e efectuar empréstimos e devoluções. Os dados ficam guardados em memória (listas `ArrayList`) enquanto o programa está a correr.

Trabalho da disciplina de **Introdução a Algoritmos e Programação**, Engenharia Informática, UNISCED.

**Autor:** Ancha Waite Saide Carepa Estudante do curso de Engenharia Informática 

## Funcionalidades

O programa apresenta um menu com sete opções:

| Opção | Nome | O que faz |
|-------|------|-----------|
| 1 | Adicionar Livro | Pede título, autor, ano e quantidade de exemplares. O identificador é atribuído automaticamente, a partir de 1. |
| 2 | Listar Livros | Lista todos os livros ou pesquisa por título ou por autor (basta parte do texto, sem distinguir maiúsculas de minúsculas). |
| 3 | Emprestar Livro | Pede o ID do livro e o ID do utilizador. Diminui uma unidade na quantidade disponível e regista o empréstimo. |
| 4 | Devolver Livro | Pede o ID do livro e o ID do utilizador. Aumenta uma unidade na quantidade disponível e marca o empréstimo como devolvido. |
| 5 | Excluir Livro | Remove o livro do catálogo. Recusa a exclusão se houver exemplares emprestados. |
| 6 | Gerir Utilizadores | Submenu para registar utilizadores, listá-los e ver o histórico de empréstimos. |
| 7 | Encerrar Programa | Termina a execução. |

## Requisitos

- **JDK 17 ou superior** (o projecto foi escrito e executado com o JDK 26). O programa usa apenas a biblioteca padrão do Java, por isso **não tem dependências externas**.
- **Git**, para clonar o repositório.
- Opcional: **IntelliJ IDEA** (ou outro IDE) para abrir e executar o projecto.

Para confirmar que o JDK está instalado, escreva no terminal:

```
java -version
javac -version
```

Se algum dos comandos não for reconhecido, instale um JDK, por exemplo o OpenJDK, em https://openjdk.org/ ou o Eclipse Temurin, em https://adoptium.net/.

## Estrutura do projecto

```
biblioteca-municipal/
├── src/
│   ├── Biblioteca.java   (método main, menu e ciclo principal)
│   ├── Servicos.java     (leitura de dados e operações do sistema)
│   ├── Livro.java        (dados e regras de um livro)
│   ├── Utilizador.java   (dados de um utilizador)
│   └── Emprestimo.java   (liga um livro a um utilizador e guarda o estado)
├── .gitignore
└── README.md
```

## Configuração

1. Clone o repositório:

   ```
   git clone https://github.com/SEU-UTILIZADOR/biblioteca-municipal.git
   cd biblioteca-municipal
   ```

2. Não é necessário instalar mais nada: não há ficheiros de configuração nem bibliotecas a descarregar.

## Execução

### Opção A: pelo terminal

Na pasta `biblioteca-municipal`, compile e execute:

```
javac -d out src/Biblioteca.java src/Servicos.java src/Livro.java src/Utilizador.java src/Emprestimo.java
java -cp out Biblioteca
```

O primeiro comando compila as cinco classes para a pasta `out`. O segundo executa a classe `Biblioteca`, que contém o método `main`.

### Opção B: pelo IntelliJ IDEA

1. Abra o IntelliJ IDEA e escolha **File > Open**.
2. Seleccione a pasta `biblioteca-municipal`.
3. Se o IntelliJ pedir, defina o JDK em **File > Project Structure > Project > SDK**.
4. Marque a pasta `src` como **Sources Root** (clique com o botão direito em `src` > **Mark Directory as** > **Sources Root**).
5. Abra `Biblioteca.java` e clique no botão verde de execução ao lado do método `main`.

## Exemplo de utilização

Ao iniciar, o programa mostra o menu:

```
Digite a opcao desejada:
[1] Adicionar Livro
[2] Listar Livros
[3] Emprestar Livro
[4] Devolver Livro
[5] Excluir Livro
[6] Gerir Utilizadores
[7] Encerrar Programa
```

Um percurso típico:

1. Escolher **6**, depois **1**, e registar um utilizador (recebe o ID 1).
2. Escolher **1** e adicionar um livro (recebe o ID 1).
3. Escolher **3** e indicar o ID do livro e o ID do utilizador para efectuar o empréstimo.
4. Escolher **6** e depois **3** para ver o histórico, onde o empréstimo aparece como "Em curso".
5. Escolher **4** para devolver o livro; no histórico passa a "Devolvido".
6. Escolher **7** para sair.

## Notas e limitações

- Os dados ficam apenas em memória e perdem-se quando o programa termina.
- O programa valida as entradas numéricas: se for escrito texto onde se espera um número, a pergunta é repetida.
- Os textos do menu não têm acentos, para evitar problemas de codificação na consola.
- Melhorias possíveis: gravar os dados em ficheiro e permitir editar e excluir utilizadores.
