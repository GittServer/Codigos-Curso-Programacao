package com.mycompany.Janela_jComboBox; // CAMINHO DAS PASTAS
import javax.swing.*;
import java.awt.*;

public class Janela_jComboBox { // Janela_jComboBox = NOME DO ARQUIVO.java
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(200, 200); // TAMANHO DA JANELA
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JComboBox<String> combo = new JComboBox<>();
        combo.addItem("Item 1 ✌"); // EMOJI DEU ERRO NO TERMINAL
        combo.addItem("Item 2 ✌"); // MAS APARECEU NA JANELA.
        combo.addItem("Item 3 ✌");
        combo.addItem("Item 4 ✌");
        combo.addItem("Item 5 ✌");

        JPanel panel = new JPanel();
        panel.add(combo);
        frame.add(panel);

        frame.setVisible(true);
    }
}
// FIM CODE.