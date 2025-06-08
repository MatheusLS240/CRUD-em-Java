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
    private boolean modoEdicao;

    private final ArrayList<JLabel> listaTarefas = new ArrayList<>();

    public InterfacePrincipal() {
        // Define o layout vertical onde serão listadas as tarefas
        campos.setLayout(new BoxLayout(campos, BoxLayout.Y_AXIS));

        adicionarTarefaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoDigitado = campoTarefa.getText().trim();

                // Verifica se o campos está vazio
                if (textoDigitado.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Digite uma tarefa primeiro!");
                }
                // Caso esteja preenchido faz as intruções abaixo
                else {
                    // Crição do painel onde fica as tarefas e os botões
                    JPanel painelTarefa = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    painelTarefa.setBackground(new Color(255, 177, 113));

                    // Criação do label que armazena as escritas das tarefas
                    JLabel labelTarefa = new JLabel();
                    labelTarefa.setFont(new Font("Arial", Font.PLAIN, 15));

                    // Adiciona e seta o texto digitado ao ArrayList e ao label criado acima
                    listaTarefas.add(labelTarefa);
                    int indice = listaTarefas.indexOf(labelTarefa) + 1;
                    labelTarefa.setText(indice + " - " + textoDigitado);

                    // Ações do botão 'remover'
                    JButton botaoRemover = new JButton("Remover");
                    botaoRemover.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            campos.remove(painelTarefa); // Remove da interface
                            campos.revalidate(); // Recalcula layout
                            campos.repaint(); // Re-renderiza
                            listaTarefas.remove(labelTarefa); // Remove da lista de tarefas
                            atualizarIndices(); // Atualiza os índices após remoção
                        }
                    });

                    // Ações do botão 'editar'
                    JButton editarLabel = new JButton("Editar");

                    // Label com instrução de edição (só visível quando em modo de edição)
                    JLabel editar = new JLabel("Para editar, digite no campo de adicionar tarefa!");
                    editar.setAlignmentX(Component.CENTER_ALIGNMENT);
                    editar.setVisible(false); // Fica invisível até entrar em modo de edição
                    campos.add(editar); // Adiciona o label de instrução no painel principal

                    editarLabel.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            modoEdicao = !modoEdicao; // Alterna o estado do modo edição

                            // Habilita/desabilita os botões dependendo do modo
                            adicionarTarefaButton.setVisible(!modoEdicao);
                            botaoRemover.setVisible(!modoEdicao);
                            editar.setVisible(modoEdicao);

                            if (modoEdicao) {
                                // Editar
                                editarLabel.setText("Enviar Edição");
                            } else {
                                // Enviar edição
                                editarLabel.setText("Editar");
                                String novoTexto = campoTarefa.getText().trim();

                                if (!novoTexto.isEmpty()) {
                                    int indiceAtual = listaTarefas.indexOf(labelTarefa) + 1;
                                    labelTarefa.setText(indiceAtual + " - " + novoTexto);
                                }
                            }
                            campoTarefa.setText(""); // Limpa o campo após edição
                        }
                    });

                    // Adiciona os componentes ao painel principal
                    painelTarefa.add(labelTarefa);
                    painelTarefa.add(botaoRemover);
                    painelTarefa.add(editarLabel);

                    // Adiciona o painel com a tarefa na interface
                    campos.add(painelTarefa);
                    campoTarefa.setText(""); // Limpa o campo de entrada após adicionar
                    campos.revalidate(); // Atualiza layout
                    campos.repaint(); // Atualiza visualmente
                }
            }
        });
    }

    // Atualiza os indíces (FEITO COM AJUDA DA IA)
    private void atualizarIndices() {
        for (int i = 0; i < listaTarefas.size(); i++) {
            JLabel label = listaTarefas.get(i);
            String textoAtual = label.getText();
            String[] partes = textoAtual.split(" - ", 2); // Separa o número do texto

            // Atualiza o índice antes do hífen mantendo o texto da tarefa
            if (partes.length == 2) {
                label.setText((i + 1) + " - " + partes[1]);
            }
        }
    }

    public static void main(String[] args) {
        // Cria a janela da aplicação
        JFrame frame = new JFrame("Lista de Tarefas");

        // Define o painel principal como conteúdo da janela
        frame.setContentPane(new InterfacePrincipal().painelPrincipal);

        // Fecha a aplicação ao clicar no X
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Ajusta o tamanho da janela conforme os componentes
        frame.pack();

        // Exibe a janela
        frame.setVisible(true);
    }
}
