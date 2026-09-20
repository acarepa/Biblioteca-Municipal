public class Emprestimo {

    private Livro livro;
    private Utilizador utilizador;
    private boolean devolvido;

    public Emprestimo(Livro livro, Utilizador utilizador) {
        this.livro = livro;
        this.utilizador = utilizador;
        this.devolvido = false;
    }

    public Livro getLivro() {
        return livro;
    }

    public Utilizador getUtilizador() {
        return utilizador;
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    public void marcarDevolvido() {
        this.devolvido = true;
    }

    @Override
    public String toString() {
        return "Livro: " + livro.getTitulo()
                + " | Utilizador: " + utilizador.getNome()
                + " | Estado: " + (devolvido ? "Devolvido" : "Em curso");
    }
}
