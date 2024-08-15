package Java.Hot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class txtH extends JFrame {
    private JLabel labelHora;
    private JButton botaoMostrarDataHora;

    public txtH() {
        super("Relógio com Botão Swing");
        labelHora = new JLabel();
        labelHora.setHorizontalAlignment(JLabel.CENTER);
        labelHora.setFont(new Font("Arial", Font.PLAIN, 24));

        botaoMostrarDataHora = new JButton("Mostrar Data e Hora");
        botaoMostrarDataHora.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarDataHora();
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(labelHora, BorderLayout.CENTER);
        panel.add(botaoMostrarDataHora, BorderLayout.SOUTH);
        add(panel);

        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        atualizarHora();
    }

    private void atualizarHora() {
        Thread thread = new Thread(() -> {
            while (true) {
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
                String horaAtual = formatoHora.format(cal.getTime());
                labelHora.setText(horaAtual);
                try {
                    Thread.sleep(1000); // Atualizar a cada segundo
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    private void mostrarDataHora() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatoDataHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dataHoraAtual = formatoDataHora.format(cal.getTime());
        JOptionPane.showMessageDialog(this, "Data e Hora Atuais:\n" + dataHoraAtual, "Data e Hora", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new txtH());
    }
}
