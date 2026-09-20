import java.util.ArrayList;

public class Biblioteca {

    public static void main(String[] args) {

        ArrayList<Livro> livros = new ArrayList<>();
        ArrayList<Utilizador> utilizadores = new ArrayList<>();
        ArrayList<Emprestimo> emprestimos = new ArrayList<>();

        Servicos servicos = new Servicos();
        boolean parar = false;
        int idLivro = 1;
        int id;
        int idUtilizador;

        do {
            int opcao = servicos.opcao();
            switch (opcao) {
                case 1:
                    livros.add(servicos.adicionarLivro(idLivro));
                    idLivro++;
                    break;
                case 2:
                    servicos.listarLivros(livros);
                    break;
                case 3:
                    id = servicos.lerInteiro("Digite o ID do livro que deseja emprestar: ");
                    idUtilizador = servicos.lerInteiro("Digite o ID do utilizador: ");
                    servicos.emprestarLivro(livros, utilizadores, emprestimos, id, idUtilizador);
                    break;
                case 4:
                    id = servicos.lerInteiro("Digite o ID do livro que deseja devolver: ");
                    idUtilizador = servicos.lerInteiro("Digite o ID do utilizador: ");
                    servicos.devolverLivro(livros, utilizadores, emprestimos, id, idUtilizador);
                    break;
                case 5:
                    id = servicos.lerInteiro("Digite o ID do livro que deseja remover: ");
                    servicos.excluirLivro(livros, emprestimos, id);
                    break;
                case 6:
                    servicos.gerirUtilizadores(utilizadores, emprestimos);
                    break;
                case 7:
                    parar = true;
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opcao invalida. Escolha um numero de 1 a 7.");
                    System.out.println();
            }
        } while (!parar);
    }
}
