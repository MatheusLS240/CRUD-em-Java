import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class InterfacePrincipal {
    public static void main(String[] args) {
        JFrame painelPrincipal = new JFrame("CRUD - Java");
        JPanel title = new JPanel();
        JPanel camposUsuario = new JPanel();
        JPanel resultados = new JPanel();

        configPainelPrincipal(painelPrincipal);
        configTitlePanel(title);
        configCamposUsuarioPanel(camposUsuario);
        painelPrincipal.add(title, BorderLayout.NORTH);
        painelPrincipal.add(camposUsuario, BorderLayout.CENTER);
        painelPrincipal.add(resultados, BorderLayout.SOUTH);

        
    }

    private static void configPainelPrincipal(JFrame painelPrincipal) {
        painelPrincipal.setVisible(true);
        painelPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        painelPrincipal.setSize(500, 500);
        painelPrincipal.setLocationRelativeTo(null);
        painelPrincipal.setLayout(new BorderLayout());
    }

    private static void configTitlePanel(JPanel title) {
        Font font = new Font("Arial", Font.BOLD, 24);
        JLabel label = new JLabel("Seja bem-vindo ao CRUD - Java");

        label.setFont(font);

        title.setLayout(new FlowLayout(FlowLayout.CENTER));
        title.add(label);
        title.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.BLACK, 2),
            new EmptyBorder(10, 10, 10, 10)
        ));
        title.setSize(500, 100);
    }

    private static void configCamposUsuarioPanel(JPanel camposUsuario) {
        JTextField txtNome = new JTextField(20);
        JButton btnAdicionar = new JButton("Adicionar");
        
        camposUsuario.setLayout(new FlowLayout());
        camposUsuario.add(txtNome);
        camposUsuario.add(btnAdicionar);
    }
}
