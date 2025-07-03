import java.awt.*;
import java.io.*;
import javax.swing.*;

public class InterfacePrincipal {
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

    // Função para configurar o painel principal da interface, definindo título, tamanho, localização e layout
    private static void configPainelPrincipal(JFrame painelPrincipal) {
        painelPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        painelPrincipal.setSize(500, 500);
        painelPrincipal.setLocationRelativeTo(null);
        painelPrincipal.setLayout(new BorderLayout());
        painelPrincipal.setResizable(false);
        System.out.println("\nPainel principal configurado com sucesso!");
    }

    // Função para configurar o painel de título, onde será exibida uma mensagem de boas-vindas
    private static void configTitlePanel(JPanel title) {
        JLabel titleLabel = new JLabel("Lista de Tarefas");
        titleLabel.setFont(fontPersonalizada(24f));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 8, 30, 0));
        title.add(titleLabel);
        System.out.println("Painel de título configurado com sucesso!");
    }

    // Função para configurar o painel de campos do usuário, onde o usuário pode inserir as tarefas
    private static void configcampoUsuarioPanel(JPanel campoUsuario, JPanel resultados) {
        JTextField campoTexto = new JTextField();
        JButton botaoAdicionar = new JButton("+");

        campoTexto.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 2), 
        BorderFactory.createEmptyBorder(0, 8, 0, 8)));
        campoTexto.setMaximumSize(new Dimension(410, 48));

        botaoAdicionar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        botaoAdicionar.setFont(new Font("SansSerif", Font.PLAIN, 32));
        botaoAdicionar.setForeground(Color.BLACK);
        botaoAdicionar.setMaximumSize(new Dimension(53, 48));
        botaoAdicionar.setBackground(Color.WHITE);
        botaoAdicionar.setFocusPainted(false);
        botaoAdicionar.addActionListener((actionEvent) -> {
            String texto = campoTexto.getText();
            if (!texto.isEmpty()) {
                JLabel itemAdicionado = new JLabel(texto);
                itemAdicionado.setFont(fontPersonalizada(16f));
                itemAdicionado.setForeground(Color.BLACK);
                itemAdicionado.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));
                resultados.add(Box.createRigidArea(new Dimension(0, 24)));
                resultados.add(itemAdicionado);
                resultados.revalidate();
                resultados.repaint();
                campoTexto.setText("");
            } else {
                JOptionPane.showMessageDialog(campoUsuario, "Por favor, insira um item.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        campoUsuario.add(campoTexto);
        campoUsuario.add(Box.createRigidArea(new Dimension(12, 0))); // 12px de espaço horizontal
        campoUsuario.add(botaoAdicionar);
        campoUsuario.setBorder(BorderFactory.createEmptyBorder(0, 8, 30, 8));
        System.out.println("Painel de campos do usuário configurado com sucesso!");
    }

    // Função para configurar o painel de resultados para exibir as tarefas adicionadas pelo usuário
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
        resultados.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));
        
        scrollPane.setPreferredSize(new Dimension(500, 300));

        System.out.println("Painel de resultados configurado com sucesso!");
    }

    // Função para configurar as propriedades gerais dos painéis, como layout e alinhamento
    private static void configGeralPanels(JPanel title, JPanel campoUsuario, JPanel resultados) {
        title.setLayout(new BoxLayout(title, 0));
        campoUsuario.setLayout(new BoxLayout(campoUsuario, BoxLayout.X_AXIS));
        resultados.setLayout(new BoxLayout(resultados, BoxLayout.Y_AXIS));
        System.out.println("Configurações gerais dos painéis aplicadas com sucesso!");
    }

    // Função para carregar uma fonte personalizada, caso não seja possível, retorna uma fonte padrão
    private static Font fontPersonalizada(float tamanho) {
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("font/kantumruy-pro-latin-700-normal.ttf"));
            return font.deriveFont(Font.BOLD, tamanho);
        } catch (FontFormatException | IOException e) {
            System.out.println("Erro ao carregar a fonte personalizada: " + e.getMessage());
            return new Font("SansSerif", Font.BOLD, (int)tamanho);
        }
    }
}
