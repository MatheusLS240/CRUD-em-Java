import java.awt.*;
import java.io.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame painelPrincipal = new JFrame("CRUD - Java");
        JPanel title = new JPanel();
        JPanel campoUsuario = new JPanel();
        JPanel resultados = new JPanel();
        JScrollPane scrollPane = new JScrollPane(resultados);

        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        configPainelPrincipal(painelPrincipal);
        configTitlePanel(title);
        configcampoUsuarioPanel(campoUsuario, resultados);
        configResultadosPanel(resultados, scrollPane);
        configGeralPanels(title, campoUsuario, resultados);

        painelPrincipal.add(title, BorderLayout.NORTH);
        painelPrincipal.add(campoUsuario, BorderLayout.CENTER);
        painelPrincipal.add(scrollPane, BorderLayout.SOUTH);

        painelPrincipal.setVisible(true);
    }

    // Configura o painel principal da interface
    private static void configPainelPrincipal(JFrame painelPrincipal) {
        painelPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        painelPrincipal.setSize(500, 500);
        painelPrincipal.setLocationRelativeTo(null);
        painelPrincipal.setLayout(new BorderLayout());
        painelPrincipal.setResizable(false);
        System.out.println("\nPainel principal configurado com sucesso!");
    }

    // Configura o painel de título
    private static void configTitlePanel(JPanel title) {
        JLabel titleLabel = new JLabel("Lista de Tarefas");
        titleLabel.setFont(fontPersonalizada(24f));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 8, 30, 0));
        title.add(titleLabel);
        System.out.println("Painel de título configurado com sucesso!");
    }

    // Configura o painel de campos do usuário
    private static void configcampoUsuarioPanel(JPanel campoUsuario, JPanel resultados) {
        JTextField campoTexto = new JTextField();
        JButton botaoAdicionar = new JButton(new ImageIcon(Main.class.getClassLoader().getResource("icons/add-icon.png")));

        campoTexto.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.BLACK, 2),
            BorderFactory.createEmptyBorder(0, 8, 0, 8)
        ));
        campoTexto.setMaximumSize(new Dimension(410, 48));

        botaoAdicionar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        botaoAdicionar.setForeground(Color.BLACK);
        botaoAdicionar.setBackground(Color.WHITE);
        botaoAdicionar.setMaximumSize(new Dimension(53, 48));
        botaoAdicionar.setFocusPainted(false);

        botaoAdicionar.addActionListener((actionEvent) -> {
            funcaoBotaoAdicionar(campoUsuario, resultados, campoTexto);
        });

        campoUsuario.add(campoTexto);
        campoUsuario.add(Box.createRigidArea(new Dimension(12, 0))); // Espaço horizontal
        campoUsuario.add(botaoAdicionar);
        campoUsuario.setBorder(BorderFactory.createEmptyBorder(0, 8, 30, 8));
        System.out.println("Painel de campos do usuário configurado com sucesso!");
    }

    // Configura o painel de resultados
    private static void configResultadosPanel(JPanel resultados, JScrollPane scrollPane) {
        JSeparator linha = new JSeparator(SwingConstants.HORIZONTAL);
        JLabel mensagemResultado = new JLabel("Seus itens adicionados: ");
        mensagemResultado.setFont(fontPersonalizada(20f));
        mensagemResultado.setBackground(Color.BLACK);
        mensagemResultado.setForeground(Color.BLACK);

        linha.setForeground(Color.BLACK);
        linha.setBackground(Color.BLACK);

        resultados.add(linha);
        resultados.add(Box.createRigidArea(new Dimension(0, 24)));
        resultados.add(mensagemResultado);
        resultados.add(Box.createRigidArea(new Dimension(0, 12)));
        resultados.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));

        scrollPane.setPreferredSize(new Dimension(500, 300));
        System.out.println("Painel de resultados configurado com sucesso!");
    }

    // Configura os layouts dos painéis
    private static void configGeralPanels(JPanel title, JPanel campoUsuario, JPanel resultados) {
        title.setLayout(new BoxLayout(title, BoxLayout.X_AXIS));
        campoUsuario.setLayout(new BoxLayout(campoUsuario, BoxLayout.X_AXIS));
        resultados.setLayout(new BoxLayout(resultados, BoxLayout.Y_AXIS));
        System.out.println("Configurações gerais dos painéis aplicadas com sucesso!");
    }

    // Função para configurar o botão de adicionar
    private static void funcaoBotaoAdicionar(JPanel campoUsuario, JPanel resultados, JTextField campoTexto) {
            String texto = campoTexto.getText();
            if (!texto.isEmpty()) {
                JButton editarBotao = new JButton(new ImageIcon(Main.class.getClassLoader().getResource("icons/edit-icon.png")));
                JButton excluirBotao = new JButton(new ImageIcon(Main.class.getClassLoader().getResource("icons/delete-icon.png")));
                JPanel itemPanel = new JPanel();
                JLabel itemAdicionado = new JLabel(texto);

                excluirBotao.setBackground(Color.WHITE);
                excluirBotao.setBorder(BorderFactory.createEmptyBorder());

                editarBotao.setBackground(Color.WHITE);
                editarBotao.setBorder(BorderFactory.createEmptyBorder());

                itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.X_AXIS));
                itemPanel.setMaximumSize(new Dimension(500, 50));
                itemPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
                itemPanel.setBorder(BorderFactory.createEmptyBorder(12, 0, 12, 0));

                itemPanel.add(itemAdicionado);
                itemPanel.add(Box.createHorizontalGlue());
                itemPanel.add(editarBotao, Component.RIGHT_ALIGNMENT);
                itemPanel.add(excluirBotao);

                itemAdicionado.setFont(fontPersonalizada(16f));
                itemAdicionado.setForeground(Color.BLACK);

                campoTexto.setText("");

                resultados.add(itemPanel);
                resultados.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));
                resultados.revalidate();
                resultados.repaint();

                excluirBotao.addActionListener(e -> {
                    funcaoBotaoRemover(campoUsuario, resultados, itemPanel, itemAdicionado);
                });

                editarBotao.addActionListener(e -> {
                    funcaoBotaoEditar(campoUsuario, itemAdicionado);
                });

            } else {
                JOptionPane.showMessageDialog(
                    campoUsuario,
                    "Por favor, insira um item.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
                );
            }
    }

    private static String getResource(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getResource'");
    }

    // Função do botão de remover
    private static void funcaoBotaoRemover(JPanel campoUsuario, JPanel resultados, JPanel itemPanel, JLabel itemAdicionado) {
        int resposta = JOptionPane.showConfirmDialog(
            campoUsuario,
            "Você tem certeza que deseja excluir este item? (" + itemAdicionado.getText() + ")",
            "Confirmação de Exclusão",
            JOptionPane.YES_NO_OPTION
        );
        if (resposta == JOptionPane.YES_OPTION) {
            resultados.remove(itemPanel);
            resultados.revalidate();
            resultados.repaint();
        }
    }

    // Função do botão de editar
    private static void funcaoBotaoEditar(JPanel campoUsuario, JLabel itemAdicionado) {
        String novoTexto = JOptionPane.showInputDialog(
            campoUsuario,
            "Editar item:",
            itemAdicionado.getText()
        );
        if (novoTexto != null && !novoTexto.trim().isEmpty()) {
            itemAdicionado.setText(novoTexto);
        }
    }

    // Carrega uma fonte personalizada, ou retorna uma padrão se não conseguir
    private static Font fontPersonalizada(float tamanho) {
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("font\\static\\KantumruyPro-Bold.ttf"));
            return font.deriveFont(Font.BOLD, tamanho);
        } catch (FontFormatException | IOException e) {
            System.out.println("Erro ao carregar a fonte personalizada: " + e.getMessage());
            return new Font("SansSerif", Font.BOLD, (int)tamanho);
        }
    }
}
