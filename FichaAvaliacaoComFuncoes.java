import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FichaAvaliacao extends JFrame {
    private JTextField campoCodigo, campoNome;
    private JRadioButton radioFeminino, radioMasculino;
    private JTextArea areaCV;
    private JComboBox<String> comboInteresse, comboAtuacao;

    public FichaAvaliacao() {
        setTitle("Ficha de Avaliação");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        criarMenu();
        criarInterface();

        setVisible(true);
    }

    private void criarMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuArquivo = new JMenu("Arquivo");
        JMenu menuEditar = new JMenu("Editar");

        JMenu menuEnviar = new JMenu("Enviar");
        JMenuItem enviarEmail = new JMenuItem("email");
        JMenuItem enviarImpressora = new JMenuItem("impressora");

        enviarEmail.addActionListener(e -> JOptionPane.showMessageDialog(this, "Email enviado!"));
        enviarImpressora.addActionListener(e -> JOptionPane.showMessageDialog(this, "Enviado para impressora!"));

        menuEnviar.add(enviarEmail);
        menuEnviar.add(enviarImpressora);

        JMenuItem itemSalvar = new JMenuItem("Salvar");
        itemSalvar.addActionListener(e -> salvarDados());

        JMenuItem itemSair = new JMenuItem("Sair");
        itemSair.addActionListener(e -> System.exit(0));

        menuArquivo.add(menuEnviar);
        menuArquivo.add(itemSalvar);
        menuArquivo.add(itemSair);

        menuBar.add(menuArquivo);
        menuBar.add(menuEditar);
        setJMenuBar(menuBar);
    }

    private void criarInterface() {
        JPanel painelPrincipal = new JPanel(new BorderLayout());

        
        JPanel painelTopo = new JPanel(new GridLayout(3, 2, 5, 5));
        painelTopo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        campoCodigo = new JTextField();
        campoNome = new JTextField();
        radioFeminino = new JRadioButton("Feminino");
        radioMasculino = new JRadioButton("Masculino");
        ButtonGroup grupoSexo = new ButtonGroup();
        grupoSexo.add(radioFeminino);
        grupoSexo.add(radioMasculino);

        painelTopo.add(new JLabel("Código:"));
        painelTopo.add(campoCodigo);

        painelTopo.add(new JLabel("Nome:"));
        painelTopo.add(campoNome);

        painelTopo.add(new JLabel("Sexo:"));
        JPanel painelSexo = new JPanel();
        painelSexo.add(radioFeminino);
        painelSexo.add(radioMasculino);
        painelTopo.add(painelSexo);

        
        JPanel painelCV = new JPanel(new BorderLayout());
        painelCV.setBorder(BorderFactory.createTitledBorder("Curriculum Vitae"));
        areaCV = new JTextArea(6, 40);
        painelCV.add(new JScrollPane(areaCV), BorderLayout.CENTER);

        
        JPanel painelAreas = new JPanel(new GridLayout(2, 2, 10, 10));
        painelAreas.setBorder(BorderFactory.createTitledBorder("Áreas"));

        comboInteresse = new JComboBox<>(new String[]{"Desenvolvedor"});
        comboAtuacao = new JComboBox<>(new String[]{"Programação"});

        painelAreas.add(new JLabel("Interesse:"));
        painelAreas.add(comboInteresse);
        painelAreas.add(new JLabel("Atuação:"));
        painelAreas.add(comboAtuacao);

        
        JPanel painelBotoes = new JPanel();
        painelBotoes.setBackground(Color.GREEN);

        String[] nomesBotoes = {"Salvar", "Anterior", "Proximo", "Novo", "Cancelar"};
        for (String nome : nomesBotoes) {
            JButton botao = new JButton(nome);
            painelBotoes.add(botao);

            switch (nome) {
                case "Salvar" -> botao.addActionListener(e -> salvarDados());
                case "Cancelar" -> botao.addActionListener(e -> limparCampos());
                case "Novo" -> botao.addActionListener(e -> JOptionPane.showMessageDialog(this, "Novo cadastro iniciado!"));
                
            }
        }

        painelPrincipal.add(painelTopo, BorderLayout.NORTH);
        painelPrincipal.add(painelCV, BorderLayout.CENTER);
        painelPrincipal.add(painelAreas, BorderLayout.SOUTH);

        getContentPane().add(painelPrincipal, BorderLayout.CENTER);
        getContentPane().add(painelBotoes, BorderLayout.SOUTH);
    }

    private void salvarDados() {
        String sexo = radioFeminino.isSelected() ? "Feminino" : radioMasculino.isSelected() ? "Masculino" : "Não selecionado";
        String msg = "Código: " + campoCodigo.getText() +
                     "\nNome: " + campoNome.getText() +
                     "\nSexo: " + sexo +
                     "\nCurrículo: " + areaCV.getText() +
                     "\nInteresse: " + comboInteresse.getSelectedItem() +
                     "\nAtuação: " + comboAtuacao.getSelectedItem();
        JOptionPane.showMessageDialog(this, msg, "Dados salvos", JOptionPane.INFORMATION_MESSAGE);
    }

    private void limparCampos() {
        campoCodigo.setText("");
        campoNome.setText("");
        areaCV.setText("");
        radioFeminino.setSelected(false);
        radioMasculino.setSelected(false);
        comboInteresse.setSelectedIndex(0);
        comboAtuacao.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FichaAvaliacao::new);
    }
}
