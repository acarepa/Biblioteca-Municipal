import java.util.ArrayList;
import java.util.Scanner;

public class Servicos {

    private Scanner scanner = new Scanner(System.in);

    // Le um numero inteiro; repete a pergunta ate o utilizador escrever um valor valido.
    public int lerInteiro(String mensagem) {
        while (true) {
            System.out.println(mensagem);
            String texto = scanner.nextLine().trim();
            try {
                return Integer.parseInt(texto);
            } catch (NumberFormatException e) {
                System.out.println("Valor invalido. Escreva um numero inteiro.");
            }
        }
    }

    public String lerTexto(String mensagem) {
        System.out.println(mensagem);
        return scanner.nextLine().trim();
    }

    public int opcao() {
        System.out.println("Digite a opcao desejada: ");
        System.out.println("[1] Adicionar Livro");
        System.out.println("[2] Listar Livros");
        System.out.println("[3] Emprestar Livro");
        System.out.println("[4] Devolver Livro");
        System.out.println("[5] Excluir Livro");
        System.out.println("[6] Gerir Utilizadores");
        System.out.println("[7] Encerrar Programa");
        return lerInteiro("");
    }

    public Livro adicionarLivro(int id) {
        String titulo = lerTexto("Digite o titulo do livro: ");
        String autor = lerTexto("Digite o nome do autor: ");
        int ano = lerInteiro("Digite o ano de publicacao: ");
        int quantidade = lerInteiro("Digite a quantidade de exemplares: ");
        while (quantidade < 1) {
            quantidade = lerInteiro("A quantidade deve ser pelo menos 1. Digite novamente: ");
        }
        Livro livro = new Livro(id, titulo, autor, ano, quantidade);
        System.out.println();
        System.out.println("Livro adicionado com sucesso. ID atribuido: " + id);
        System.out.println();
        return livro;
    }

    // Lista todos os livros ou pesquisa por titulo ou por autor.
    public void listarLivros(ArrayList<Livro> livros) {
        if (livros.isEmpty()) {
            System.out.println();
            System.out.println("Nenhum livro cadastrado");
            System.out.println();
            return;
        }
        System.out.println("[1] Listar todos");
        System.out.println("[2] Pesquisar por titulo");
        System.out.println("[3] Pesquisar por autor");
        int tipo = lerInteiro("");
        String termo = "";
        if (tipo == 2) {
            termo = lerTexto("Digite o titulo (ou parte dele): ").toLowerCase();
        } else if (tipo == 3) {
            termo = lerTexto("Digite o autor (ou parte do nome): ").toLowerCase();
        } else if (tipo != 1) {
            System.out.println("Opcao invalida.");
            return;
        }

        System.out.println();
        System.out.println("Lista de Livros: ");
        int encontrados = 0;
        for (int i = 0; i < livros.size(); i++) {
            Livro livro = livros.get(i);
            boolean corresponde = tipo == 1
                    || (tipo == 2 && livro.getTitulo().toLowerCase().contains(termo))
                    || (tipo == 3 && livro.getAutor().toLowerCase().contains(termo));
            if (corresponde) {
                System.out.println(livro.toString());
                encontrados++;
            }
        }
        if (encontrados == 0) {
            System.out.println("Nenhum livro encontrado.");
        }
        System.out.println();
    }

    private Livro procurarLivro(ArrayList<Livro> livros, int id) {
        for (Livro livro : livros) {
            if (livro.getId() == id) {
                return livro;
            }
        }
        return null;
    }

    private Utilizador procurarUtilizador(ArrayList<Utilizador> utilizadores, int id) {
        for (Utilizador utilizador : utilizadores) {
            if (utilizador.getId() == id) {
                return utilizador;
            }
        }
        return null;
    }

    public void emprestarLivro(ArrayList<Livro> livros, ArrayList<Utilizador> utilizadores,
                               ArrayList<Emprestimo> emprestimos, int idLivro, int idUtilizador) {
        System.out.println();
        Livro livro = procurarLivro(livros, idLivro);
        if (livro == null) {
            System.out.println("Livro com ID " + idLivro + " nao encontrado.");
            System.out.println();
            return;
        }
        Utilizador utilizador = procurarUtilizador(utilizadores, idUtilizador);
        if (utilizador == null) {
            System.out.println("Utilizador com ID " + idUtilizador + " nao encontrado.");
            System.out.println();
            return;
        }
        if (livro.emprestar()) {
            emprestimos.add(new Emprestimo(livro, utilizador));
            System.out.println("Livro emprestado com sucesso: " + livro.getTitulo()
                    + " a " + utilizador.getNome()
                    + " (restam " + livro.getQuantidadeDisponivel() + ")");
        } else {
            System.out.println("Nao ha exemplares disponiveis de: " + livro.getTitulo());
        }
        System.out.println();
    }

    public void devolverLivro(ArrayList<Livro> livros, ArrayList<Utilizador> utilizadores,
                              ArrayList<Emprestimo> emprestimos, int idLivro, int idUtilizador) {
        System.out.println();
        for (Emprestimo emprestimo : emprestimos) {
            if (!emprestimo.isDevolvido()
                    && emprestimo.getLivro().getId() == idLivro
                    && emprestimo.getUtilizador().getId() == idUtilizador) {
                emprestimo.marcarDevolvido();
                emprestimo.getLivro().devolver();
                System.out.println("Livro devolvido com sucesso: " + emprestimo.getLivro().getTitulo()
                        + " (disponiveis " + emprestimo.getLivro().getQuantidadeDisponivel() + ")");
                System.out.println();
                return;
            }
        }
        System.out.println("Nao existe emprestimo em curso deste livro para este utilizador.");
        System.out.println();
    }

    public void excluirLivro(ArrayList<Livro> livros, ArrayList<Emprestimo> emprestimos, int id) {
        System.out.println();
        Livro livro = procurarLivro(livros, id);
        if (livro == null) {
            System.out.println("Livro com ID " + id + " nao encontrado.");
            System.out.println();
            return;
        }
        for (Emprestimo emprestimo : emprestimos) {
            if (!emprestimo.isDevolvido() && emprestimo.getLivro().getId() == id) {
                System.out.println("Nao e possivel excluir: existem exemplares emprestados de " + livro.getTitulo());
                System.out.println();
                return;
            }
        }
        livros.remove(livro);
        System.out.println("Livro removido com sucesso: " + livro.getTitulo());
        System.out.println();
    }

    // Submenu de utilizadores: registar, listar e consultar o historico de emprestimos.
    public void gerirUtilizadores(ArrayList<Utilizador> utilizadores, ArrayList<Emprestimo> emprestimos) {
        System.out.println("[1] Registar utilizador");
        System.out.println("[2] Listar utilizadores");
        System.out.println("[3] Ver historico de emprestimos");
        int tipo = lerInteiro("");
        System.out.println();
        if (tipo == 1) {
            String nome = lerTexto("Digite o nome do utilizador: ");
            while (nome.isEmpty()) {
                nome = lerTexto("O nome nao pode ficar vazio. Digite novamente: ");
            }
            int id = utilizadores.size() + 1;
            utilizadores.add(new Utilizador(id, nome));
            System.out.println("Utilizador registado com sucesso. ID atribuido: " + id);
        } else if (tipo == 2) {
            if (utilizadores.isEmpty()) {
                System.out.println("Nenhum utilizador registado.");
            } else {
                System.out.println("Lista de Utilizadores: ");
                for (int i = 0; i < utilizadores.size(); i++) {
                    System.out.println(utilizadores.get(i).toString());
                }
            }
        } else if (tipo == 3) {
            if (emprestimos.isEmpty()) {
                System.out.println("Ainda nao ha emprestimos.");
            } else {
                System.out.println("Historico de Emprestimos: ");
                for (int i = 0; i < emprestimos.size(); i++) {
                    System.out.println(emprestimos.get(i).toString());
                }
            }
        } else {
            System.out.println("Opcao invalida.");
        }
        System.out.println();
    }
}
