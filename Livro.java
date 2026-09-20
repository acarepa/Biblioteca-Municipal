public class Livro {

    private int id;
    private String titulo;
    private String autor;
    private int ano;
    private int quantidadeTotal;
    private int quantidadeDisponivel;

    public Livro(int id, String titulo, String autor, int ano, int quantidade) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.quantidadeTotal = quantidade;
        this.quantidadeDisponivel = quantidade;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAno() {
        return ano;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    // Diminui uma unidade; devolve false se nao houver exemplares.
    public boolean emprestar() {
        if (quantidadeDisponivel > 0) {
            quantidadeDisponivel--;
            return true;
        }
        return false;
    }

    // Aumenta uma unidade; devolve false se todos os exemplares ja estao na biblioteca.
    public boolean devolver() {
        if (quantidadeDisponivel < quantidadeTotal) {
            quantidadeDisponivel++;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "ID: " + id
                + " | Titulo: " + titulo
                + " | Autor: " + autor
                + " | Ano: " + ano
                + " | Disponiveis: " + quantidadeDisponivel + "/" + quantidadeTotal;
    }
}
