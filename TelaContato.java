import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.IOException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class TelaContato extends JFrame implements ActionListener {

    private AgendaArquivo agenda = new AgendaArquivo("contatos.txt");

    private JTextField campoNome = new JTextField(20);
    private JTextField campoTelefone = new JTextField(20);
    private JTextField campoCidade = new JTextField(20);
    private JButton botaoVerLista = new JButton("Ver contatos");
    private JButton botaoSalvar = new JButton("Salvar");

    private DefaultTableModel modeloTabela = new DefaultTableModel(
            new String[]{"Nome", "Telefone", "Cidade"}, 0) {
        @Override
        public boolean isCellEditable(int linha, int coluna) {
            return false;
        }
    };
    private JTable tabelaLista = new JTable(modeloTabela);

    private static final Color COR_FUNDO = new Color(0x0D0D14);
    private static final Color COR_CAMPO = new Color(0x1A1A28);
    private static final Color COR_TEXTO = new Color(0xE5E7EB);
    private static final Color COR_TEXTO_SUAVE = new Color(0x9CA3AF);
    private static final Color COR_PRINCIPAL = new Color(0x8B5CF6);
    private static final Color COR_DESTAQUE = new Color(0xA78BFA);
    private static final Color COR_BORDA = new Color(0x3B2F63);
    private static final Color COR_LINHA_ALTERNADA = new Color(0x14141F);
    private JPanel painelLista = new JPanel(new BorderLayout());

    public TelaContato() {
        super("Agenda de Contatos");

        JLabel titulo = new JLabel("Novo contato");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        titulo.setForeground(COR_DESTAQUE);

        JPanel formulario = new JPanel(new GridLayout(4, 2, 8, 8));
        formulario.add(rotulo("Nome:"));
        formulario.add(campoNome);
        formulario.add(rotulo("Telefone:"));
        formulario.add(campoTelefone);
        formulario.add(rotulo("Cidade:"));
        formulario.add(campoCidade);
        formulario.add(botaoVerLista);
        formulario.add(botaoSalvar);

        estilizarCampos();
        estilizarBotoes();
        estilizarTabela();
        JScrollPane rolagem = new JScrollPane(tabelaLista);
        rolagem.setPreferredSize(new Dimension(420, 150));
        rolagem.setBorder(BorderFactory.createLineBorder(COR_BORDA));
        rolagem.getViewport().setBackground(COR_FUNDO);
        painelLista.add(rolagem, BorderLayout.CENTER);
        painelLista.setOpaque(false);
        painelLista.setVisible(false);

        formulario.setOpaque(false);

        JPanel painel = new JPanel(new BorderLayout(0, 16));
        painel.setBackground(COR_FUNDO);
        painel.setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));
        painel.add(titulo, BorderLayout.NORTH);
        painel.add(formulario, BorderLayout.CENTER);
        painel.add(painelLista, BorderLayout.SOUTH);

        botaoVerLista.addActionListener(this);
        botaoSalvar.addActionListener(this);

        setContentPane(painel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    private JLabel rotulo(String texto) {
        JLabel l = new JLabel(texto);
        l.setForeground(COR_TEXTO_SUAVE);
        l.setFont(new Font("SansSerif", Font.BOLD, 13));
        return l;
    }

    private void estilizarCampos() {
        for (JTextField c : new JTextField[]{campoNome, campoTelefone, campoCidade}) {
            c.setBackground(COR_CAMPO);
            c.setForeground(COR_TEXTO);
            c.setCaretColor(COR_DESTAQUE);
            c.setFont(new Font("SansSerif", Font.PLAIN, 13));
            c.setBorder(bordaCampo(COR_BORDA));
            c.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    c.setBorder(bordaCampo(COR_PRINCIPAL));
                }

                @Override
                public void focusLost(FocusEvent e) {
                    c.setBorder(bordaCampo(COR_BORDA));
                }
            });
        }
    }

    private javax.swing.border.Border bordaCampo(Color cor) {
        return BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(cor, 1),
                BorderFactory.createEmptyBorder(5, 8, 5, 8));
    }

    private void estilizarBotoes() {
        botaoSalvar.setBackground(COR_PRINCIPAL);
        botaoSalvar.setForeground(Color.WHITE);
        botaoVerLista.setBackground(COR_CAMPO);
        botaoVerLista.setForeground(COR_DESTAQUE);
        for (JButton b : new JButton[]{botaoSalvar, botaoVerLista}) {
            b.setFont(new Font("SansSerif", Font.BOLD, 13));
            b.setFocusPainted(false);
            b.setContentAreaFilled(false);
            b.setOpaque(true);
            b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            b.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(COR_PRINCIPAL),
                    BorderFactory.createEmptyBorder(6, 12, 6, 12)));
        }
    }

    private void estilizarTabela() {
        tabelaLista.setRowHeight(26);
        tabelaLista.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tabelaLista.setBackground(COR_FUNDO);
        tabelaLista.setForeground(COR_TEXTO);
        tabelaLista.setShowGrid(false);
        tabelaLista.setIntercellSpacing(new Dimension(0, 0));
        tabelaLista.setFillsViewportHeight(true);
        tabelaLista.setSelectionBackground(new Color(0x4C3A8A));
        tabelaLista.setSelectionForeground(Color.WHITE);

        JTableHeader cabecalho = tabelaLista.getTableHeader();
        cabecalho.setReorderingAllowed(false);
        cabecalho.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object valor,
                    boolean sel, boolean foco, int linha, int coluna) {
                super.getTableCellRendererComponent(t, valor, sel, foco, linha, coluna);
                setBackground(COR_CAMPO);
                setForeground(COR_DESTAQUE);
                setFont(new Font("SansSerif", Font.BOLD, 13));
                setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(0, 0, 2, 0, COR_PRINCIPAL),
                        BorderFactory.createEmptyBorder(0, 8, 0, 8)));
                return this;
            }
        });

        tabelaLista.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object valor,
                    boolean sel, boolean foco, int linha, int coluna) {
                super.getTableCellRendererComponent(t, valor, sel, foco, linha, coluna);
                if (!sel) {
                    setBackground(linha % 2 == 0 ? COR_FUNDO : COR_LINHA_ALTERNADA);
                    setForeground(COR_TEXTO);
                }
                setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));
                return this;
            }
        });
    }

    private static void configurarTemaEscuro() {
        UIManager.put("OptionPane.background", COR_FUNDO);
        UIManager.put("Panel.background", COR_FUNDO);
        UIManager.put("OptionPane.messageForeground", COR_TEXTO);
        UIManager.put("OptionPane.foreground", COR_TEXTO);
        UIManager.put("Button.background", COR_PRINCIPAL);
        UIManager.put("Button.foreground", Color.WHITE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoVerLista) {
            atualizarLista();
            painelLista.setVisible(!painelLista.isVisible());
            pack();
        } else if (e.getSource() == botaoSalvar) {
            salvar();
        }
    }

    private void salvar() {
        String nome = campoNome.getText().trim();
        String telefone = campoTelefone.getText().trim();
        String cidade = campoCidade.getText().trim();

        if (nome.isEmpty()) {
            avisarCampoVazio("Nome", campoNome);
            return;
        }
        if (telefone.isEmpty()) {
            avisarCampoVazio("Telefone", campoTelefone);
            return;
        }
        if (cidade.isEmpty()) {
            avisarCampoVazio("Cidade", campoCidade);
            return;
        }

        try {
            agenda.adicionar(new Contato(nome, telefone, cidade));
            JOptionPane.showMessageDialog(this, "Contato salvo na agenda.");
            campoNome.setText("");
            campoTelefone.setText("");
            campoCidade.setText("");
            campoNome.requestFocus();
            atualizarLista();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                    "Não foi possível salvar o contato no arquivo contatos.txt.\n"
                    + "Feche o arquivo se ele estiver aberto e tente de novo.",
                    "Erro ao salvar", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void avisarCampoVazio(String nomeDoCampo, JTextField campo) {
        JOptionPane.showMessageDialog(this,
                "O campo " + nomeDoCampo + " está vazio.\n"
                + "Preencha o campo " + nomeDoCampo + " e clique em Salvar de novo.",
                "Campo obrigatório", JOptionPane.WARNING_MESSAGE);
        campo.requestFocus();
    }

    private void atualizarLista() {
        modeloTabela.setRowCount(0);
        for (Contato c : agenda.listarTodos()) {
            modeloTabela.addRow(new Object[]{c.getNome(), c.getTelefone(), c.getCidade()});
        }
    }

    public static void main(String[] args) {
        configurarTemaEscuro();
        new TelaContato().setVisible(true);
    }

    /*
     * Seção 5 - TESTE DE COMPREENSÃO
     *
     * 1) Pasta inexistente:
     *    O FileWriter lança uma IOException (FileNotFoundException), o adicionar
     *    relança e a tela mostra o JOptionPane de erro "Erro ao salvar": "Não foi
     *    possível salvar o contato no arquivo contatos.txt. Feche o arquivo se
     *    ele estiver aberto e tente de novo." Se o adicionar tivesse um catch que
     *    só imprime no console, a tela não saberia do erro: mostraria "Contato
     *    salvo na agenda.", limparia os campos e o usuário acharia que salvou,
     *    mas nada teria sido gravado.
     *
     * 2) Sem botaoSalvar.addActionListener(this):
     *    Nada acontece ao clicar. Implementar ActionListener só garante que a
     *    classe TEM o método actionPerformed; o botão só avisa os ouvintes que
     *    foram cadastrados nele com addActionListener. Sem o cadastro, o botão
     *    não sabe que a tela existe e o clique não chega.
     *
     * 3) Sem pack():
     *    A janela aparece minúscula (só a barra de título, sem mostrar o
     *    conteúdo). O pack() é quem calcula o tamanho da janela a partir do
     *    tamanho preferido dos componentes e do layout; sem ele, ninguém define
     *    o tamanho.
     *
     * 4) Nome "Ana; Maria":
     *    Aparece "Ana (64 99999-1010) -  Maria": o telefone foi parar no campo
     *    cidade e " Maria" ficou no lugar do telefone. A linha gravada tem 4
     *    partes, e o split(";") devolve 4 itens, mas o fromLinha só usa os três
     *    primeiros (partes[0], partes[1], partes[2]) e ignora a cidade real.
     */
}