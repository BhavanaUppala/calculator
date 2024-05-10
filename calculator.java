package calculator;

import javax.imageio.ImageIO;
import java.io.File;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class calculator extends Frame {
    JFrame f;
    JTextField T1;
    JButton[] b = new JButton[15];
    JButton bequl, clear, on, del, dec,
            bracket;

    JPanel p1, p2;
    double n1, n2, res;
    String opr;

    calculator() {
        f = new JFrame();
        T1 = new JTextField(16);
        bracket = new JButton("()");

        del = new JButton("DEL");
        clear = new JButton("C");
        dec = new JButton(".");
        for (int i = 0; i < 10; i++) {
            b[i] = new JButton(String.valueOf(i));

        }
        b[10] = new JButton("+");
        b[11] = new JButton("-");
        b[12] = new JButton("x");
        b[13] = new JButton("/");
        b[14] = new JButton("%");
        bequl = new JButton("=");

        p2 = new JPanel();

        clear.addActionListener(e -> T1.setText(""));
        ActionListener numberListener = e -> T1.setText(T1.getText() + ((JButton) e.getSource()).getText());
        for (int i = 0; i < 10; i++) {
            b[i].addActionListener(numberListener);

        }
        ActionListener operatorListener = e -> {
            n1 = Double.parseDouble(T1.getText());
            opr = ((JButton) e.getSource()).getText();
            T1.setText(T1.getText() + opr);
        };

        b[10].addActionListener(operatorListener);
        b[11].addActionListener(operatorListener);
        b[12].addActionListener(operatorListener);
        b[13].addActionListener(operatorListener);
        b[14].addActionListener(operatorListener);

        bequl.addActionListener(e -> {

            n2 = Double.parseDouble(T1.getText().substring(T1.getText().indexOf(opr) + 1));
            switch (opr) {
                case "+":
                    res = n1 + n2;

                    break;
                case "-":
                    res = n1 - n2;

                    break;
                case "x":
                    res = n1 * n2;

                    break;
                case "/":
                    try {
                        res = n1 / n2;
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                    break;
                case "%":
                    res = n1 % n2;
                    break;
                default:
                    T1.setText("ERROR");
                    return;

            }

            T1.setText(Double.toString(res));

        });

        del.addActionListener(e -> {
            String backspace = null;
            if (T1.getText().length() > 1) {

                StringBuilder s = new StringBuilder(T1.getText());
                s.deleteCharAt(T1.getText().length() - 1);
                backspace = s.toString();
                T1.setText(backspace);
            }
        });

        dec.addActionListener(e -> {
            T1.setText(T1.getText() + ".");

        });
        bracket.addActionListener(e -> {
            T1.setText(T1.getText() + "()");
        });

        p2.add(bracket);
        p2.add(del);
        p2.add(clear);
        p2.add(dec);
        for (int i = 0; i < 15; i++)
            p2.add(b[i]);
        p2.add(bequl);
        T1.setBounds(50, 200, 200, 30);

        p2.setLayout(new GridLayout(5, 4));
        p1 = new JPanel(new GridBagLayout());
        ImageIcon icon = new ImageIcon(getClass().getResource("logo.png"));

        JLabel l = new JLabel(icon);

        p1.add(l);
        f.add(T1, BorderLayout.NORTH);
        f.add(p1);
        f.add(p2);
        f.setSize(300, 300);
        f.setTitle("SIMPLE CALCULATOR");

        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        new calculator();

    }
}
