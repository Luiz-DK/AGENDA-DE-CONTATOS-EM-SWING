import java.io.*;
import java.util.ArrayList;

public class AgendaArquivo {

    private String arquivo;

    public AgendaArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    public void adicionar(Contato c) throws IOException {
        try (PrintWriter escritor = new PrintWriter(new FileWriter(arquivo, true))) {
            escritor.println(c.toLinha());
        }
    }

    public ArrayList<Contato> listarTodos() {
        ArrayList<Contato> contatos = new ArrayList<>();
        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            String linha = leitor.readLine();
            while (linha != null) {
                contatos.add(Contato.fromLinha(linha));
                linha = leitor.readLine();
            }
        } catch (IOException e) {
        }
        return contatos;
    }

    public ArrayList<Contato> buscarPorCidade(String cidade) {
        ArrayList<Contato> achados = new ArrayList<>();
        for (Contato c : listarTodos()) {
            if (c.getCidade().equalsIgnoreCase(cidade)) {
                achados.add(c);
            }
        }
        return achados;
    }
}