import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InterfacePrincipal {
    private JPanel painelPrincipal;
    private JPanel titulo;
    private JPanel funcoes;
    private JPanel campos;
    private JTextField campoTarefa;
    private JButton adicionarTarefaButton;

    private ArrayList<JLabel> listaTarefas = new ArrayList<>();

    public InterfacePrincipal() {
        campos.setLayout(new BoxLayout(campos, BoxLayout.Y_AXIS));

        adicionarTarefaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoDigitado = campoTarefa.getText().trim();

                if (textoDigitado.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Digite uma tarefa primeiro!");
                } else {
                    JPanel painelTarefa = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    painelTarefa.setBackground(new Color(255, 177, 113));

                    JLabel labelTarefa = new JLabel();
                    labelTarefa.setFont(new Font("Arial", Font.PLAIN, 15));

                    listaTarefas.add(labelTarefa);
                    int indice = listaTarefas.indexOf(labelTarefa) + 1;
                    labelTarefa.setText(indice + " - " + textoDigitado);

                    JButton botaoRemover = new JButton("Remover");

                    botaoRemover.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            campos.remove(painelTarefa);
                            campos.revalidate();
                            campos.repaint();
                            listaTarefas.remove(labelTarefa);
                        }
                    });

                    painelTarefa.add(labelTarefa);
                    painelTarefa.add(botaoRemover);

                    campos.add(painelTarefa);
                    campoTarefa.setText("");

                    campos.revalidate();
                    campos.repaint();
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Lista de Tarefas");
        frame.setContentPane(new InterfacePrincipal().painelPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
