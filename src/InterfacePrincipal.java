import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

public class InterfacePrincipal {
    public static void main(String[] args) {
        JFrame painelPrincipal = new JFrame("CRUD - Java");
        JPanel title = new JPanel();
        JPanel camposUsuario = new JPanel();
        JPanel resultados = new JPanel();

        configPainelPrincipal(painelPrincipal);
        configTitlePanel(title);
        configCamposUsuarioPanel(camposUsuario);
        configResultadosPanel(resultados);
        backgroundPanels(title, camposUsuario, resultados);


        painelPrincipal.add(title, BorderLayout.NORTH);
        painelPrincipal.add(camposUsuario, BorderLayout.CENTER);
        painelPrincipal.add(resultados, BorderLayout.SOUTH);

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
        JLabel titleLabel = new JLabel("Lista de Compras    ");
        
        titleLabel.setFont(fontPersonalizada(24f));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 8, 30, 8));

        title.setLayout(new FlowLayout(FlowLayout.LEFT));
        title.add(titleLabel);
    }

    // Função para configurar o painel de campos do usuário, onde o usuário pode inserir as tarefas
    private static void configCamposUsuarioPanel(JPanel camposUsuario) {

    }

    // Função para configurar o painel de resultados para exibir as tarefas adicionadas pelo usuário
    private static void configResultadosPanel(JPanel resultados) {

    }

    // Função para definir a cor de fundo dos painéis
    private static void backgroundPanels(JPanel title, JPanel campoUsuario, JPanel resultados) {

    }

    private static Font fontPersonalizada (float tamanho) {
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("font/kantumruy-pro-latin-700-normal.ttf"));
            return font.deriveFont(Font.BOLD, tamanho);
        } catch (FontFormatException | IOException e) {
            System.out.println("Erro ao carregar a fonte personalizada: " + e.getMessage());
            return new Font("SansSerif", Font.BOLD, (int)tamanho);
        }
    }
}
