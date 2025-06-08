import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class interfacePrincipal {
    private JPanel painelPrincipal;
    private JPanel titulo;
    private JPanel funcoes;
    private JPanel campos;
    private JTextField campoTarefa;
    private JButton adicionarTarefaButton;
    ArrayList<JTextField> listaTarefa = new ArrayList<>();
    int i = 1;


    public interfacePrincipal() {
        adicionarTarefaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel tarefa = new JLabel();
                tarefa.setFont(new Font("Arial", Font.PLAIN, 15));
                campos.setLayout(new BoxLayout(campos, BoxLayout.Y_AXIS));

                String tarefaAdicionada = campoTarefa.getText();
                tarefa.setText(i + " - " + tarefaAdicionada);
                tarefa.setAlignmentX(Component.CENTER_ALIGNMENT);

                campos.add(tarefa);
                campos.revalidate();
                campos.repaint();

                i++;
                campoTarefa.setText("");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("interfacePrincipal");
        frame.setContentPane(new interfacePrincipal().painelPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
