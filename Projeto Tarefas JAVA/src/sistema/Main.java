// INTERFACE GRÁFICA
package sistema;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.*; // PARA ARQUIVO

public class Main extends JFrame {

    ArrayList<Tarefa> lista = new ArrayList<>();

    DefaultListModel<String> model = new DefaultListModel<>();
    JList<String> jList = new JList<>(model);

    JTextField txtDescricao = new JTextField();
    JTextField txtData = new JTextField();

    JButton btnAdd = new JButton("Adicionar");
    JButton btnConcluir = new JButton("Concluir");
    JButton btnDeletar = new JButton("Deletar");

    JLabel lblDesc = new JLabel("Descrição da tarefa:");
    JLabel lblData = new JLabel("Data vencimento:");
    JLabel lblTituloLista = new JLabel("Tarefa | Data | Status"); // NOVO
    JLabel lblQuantidade = new JLabel("Total: 0"); // NOVO

    public Main() {

        setTitle("Sistema de Tarefas      - Criado por: Lucas Ávila");
        setSize(500, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        getContentPane().setBackground(Color.BLACK);

        // ===== LABELS =====
        lblDesc.setBounds(20, 0, 200, 20);
        lblDesc.setForeground(Color.WHITE);
        add(lblDesc);

        lblData.setBounds(240, 0, 150, 20);
        lblData.setForeground(Color.WHITE);
        add(lblData);

        // ===== CAMPOS =====
        txtDescricao.setBounds(20, 20, 200, 30);
        txtDescricao.setBackground(Color.DARK_GRAY);
        txtDescricao.setForeground(Color.WHITE);
        add(txtDescricao);

        txtData.setBounds(240, 20, 100, 30);
        txtData.setBackground(Color.DARK_GRAY);
        txtData.setForeground(Color.WHITE);
        add(txtData);

        // ===== BOTÕES =====
        btnAdd.setBounds(360, 20, 110, 30);
        btnAdd.setBackground(Color.RED);
        btnAdd.setForeground(Color.WHITE);
        add(btnAdd);

        // ===== TITULO LISTA =====
        lblTituloLista.setBounds(20, 55, 300, 20);
        lblTituloLista.setForeground(Color.LIGHT_GRAY);
        add(lblTituloLista);

        // ===== LISTA =====
        jList.setBackground(Color.BLACK);
        jList.setForeground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(jList);
        scroll.setBounds(20, 75, 450, 200);
        add(scroll);

        // ===== BOTÕES INFERIORES =====
        btnConcluir.setBounds(20, 300, 150, 30);
        btnConcluir.setBackground(Color.RED);
        btnConcluir.setForeground(Color.WHITE);
        add(btnConcluir);

        btnDeletar.setBounds(200, 300, 150, 30);
        btnDeletar.setBackground(Color.RED);
        btnDeletar.setForeground(Color.WHITE);
        add(btnDeletar);

        // ===== QUANTIDADE =====
        lblQuantidade.setBounds(20, 340, 200, 20);
        lblQuantidade.setForeground(Color.WHITE);
        add(lblQuantidade);

        // ===== EVENTO ADICIONAR =====
        btnAdd.addActionListener(e -> {
            try {
                String desc = txtDescricao.getText();
                String data = txtData.getText();

                if (desc.isEmpty() || data.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos");
                    return;
                }

                Tarefa t = new Tarefa(desc, data);
                lista.add(t);

                atualizarLista();
                salvarArquivo(); // SALVA

                txtDescricao.setText("");
                txtData.setText("");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao adicionar tarefa");
            }
        });

        // ===== EVENTO CONCLUIR =====
        btnConcluir.addActionListener(e -> {
            try {
                int index = jList.getSelectedIndex();

                if (index != -1) {
                    lista.get(index).concluir();
                    atualizarLista();
                    salvarArquivo(); // SALVA
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao concluir tarefa");
            }
        });

        // ===== EVENTO DELETAR =====
        btnDeletar.addActionListener(e -> {
            try {
                int index = jList.getSelectedIndex();

                if (index != -1) {
                    lista.remove(index);
                    atualizarLista();
                    salvarArquivo(); // SALVA
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao deletar tarefa");
            }
        });

        carregarArquivo(); // CARREGA AO INICIAR
        atualizarLista();
    }

    // ===== ATUALIZAR LISTA =====
    public void atualizarLista() {
        model.clear();

        for (Tarefa t : lista) {
            model.addElement(t.toString());
        }

        lblQuantidade.setText("Total: " + lista.size()); // ATUALIZA CONTADOR
    }

    // ===== SALVAR ARQUIVO =====
    public void salvarArquivo() {
        try {
            FileWriter fw = new FileWriter("tarefas_lista.txt");
            BufferedWriter bw = new BufferedWriter(fw);

            for (Tarefa t : lista) {
                bw.write(t.descricao + ";" + t.data + ";" + t.concluida);
                bw.newLine();
            }

            bw.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar arquivo");
        }
    }

    // ===== CARREGAR ARQUIVO =====
    public void carregarArquivo() {
        try {
            File file = new File("tarefas_lista.txt");

            if (!file.exists()) return;

            BufferedReader br = new BufferedReader(new FileReader(file));

            String linha;

            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");

                Tarefa t = new Tarefa(partes[0], partes[1]);

                if (partes[2].equals("true")) {
                    t.concluir();
                }

                lista.add(t);
            }

            br.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar arquivo");
        }
    }

    public static void main(String[] args) {
        new Main().setVisible(true);
    }
}
// FIM CODE.