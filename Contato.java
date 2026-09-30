public class Contato {

    private String nome;
    private String telefone;
    private String cidade;

    public Contato(String nome, String telefone, String cidade) {
        this.nome = nome;
        this.telefone = telefone;
        this.cidade = cidade;
    }

    public String getNome() { return nome; }
    public String getTelefone() { return telefone; }
    public String getCidade() { return cidade; }

    public String toLinha() {
        return nome + ";" + telefone + ";" + cidade;
    }

    public static Contato fromLinha(String linha) {
        String[] partes = linha.split(";");
        return new Contato(partes[0], partes[1], partes[2]);
    }

    @Override
    public String toString() {
        return nome + " (" + cidade + ") - " + telefone;
    }
}