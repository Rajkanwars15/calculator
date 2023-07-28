import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

class Calculator extends JFrame {
    private final Font BIGGER_FONT = new Font("Times New Roman",Font.BOLD, 20);
    private JTextField textfield;
    private boolean number = true;
    private String equalOp = "=";
    private CalculatorOp op = new CalculatorOp();

    public Calculator() {
        textfield = new JTextField("", 12);
        textfield.setHorizontalAlignment(JTextField.RIGHT);
        textfield.setFont(BIGGER_FONT);
        textfield.setForeground(new Color(128, 0, 0));
        ActionListener numberListener = new NumberListener();
        String buttonOrder = "9876543210. ";
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 4, 4));
        for (int i = 0; i < buttonOrder.length(); i++) {
            String key = buttonOrder.substring(i, i+1);
            if (key.equals(" ")) {
                buttonPanel.add(new JLabel(""));
            } else {
                final JButton button = new JButton(key);
                button.addActionListener(numberListener);
                button.setFont(BIGGER_FONT);
                button.setBackground(Color.WHITE);
                button.setForeground(new Color(128, 0, 0));
                button.addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent arg0) {
                        button.setBackground(new Color(0, 0, 128));
                        button.setForeground(Color.WHITE);
                    }
                    public void mouseReleased(MouseEvent arg0) {
                        button.setBackground(Color.WHITE);
                        button.setForeground(new Color(128, 0, 0));
                    }
                });
                buttonPanel.add(button);
            }
        }
        ActionListener operatorListener = new OperatorListener();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3, 3, 3));
        String[] opOrder = {"AC","+","=","-", "\u00D7", "\u00F7","sin","cos","log"};
        for (int i = 0; i < opOrder.length; i++) {
            final JButton button = new JButton(opOrder[i]);
            button.addActionListener(operatorListener);
            button.setFont(BIGGER_FONT);
            button.setBackground(Color.WHITE);
            button.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent arg0) {
                    button.setForeground(new Color(0, 0, 128));
                }
            });
            button.setForeground(new Color(128, 0, 0));
            panel.add(button);
        }
        JPanel pan = new JPanel();
        pan.setLayout(new BorderLayout(4, 4));
        pan.add(textfield, BorderLayout.NORTH );
        pan.add(buttonPanel , BorderLayout.CENTER);
        pan.add(panel , BorderLayout.WEST);
        this.setContentPane(pan);
        this.pack();
        this.setTitle("21BCE7873-CALCULATOR");
        this.setResizable(false);
    }

    private void action() {
        number = true;
        textfield.setText("");
        equalOp = "=";
        op.setTotal("");
    }
    class OperatorListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String displayText = textfield.getText();
            if (e.getActionCommand().equals("sin"))
            {
                textfield.setText("" + Math.sin(Double.valueOf(displayText).doubleValue()));

            }else
            if (e.getActionCommand().equals("cos"))
            {
                textfield.setText("" + Math.cos(Double.valueOf(displayText).doubleValue()));

            }
            else
            if (e.getActionCommand().equals("log"))
            {
                textfield.setText("" + Math.log(Double.valueOf(displayText).doubleValue()));

            }
            else if (e.getActionCommand().equals("C"))
            {
                textfield.setText("");
            }

            else
            {
                if (number)
                {

                    action();
                    textfield.setText("");

                }
                else
                {
                    number = true;
                    if (equalOp.equals("="))
                    {
                        op.setTotal(displayText);
                    }else
                    if (equalOp.equals("+"))
                    {
                        op.add(displayText);
                    }
                    else if (equalOp.equals("-"))
                    {
                        op.subtract(displayText);
                    }
                    else if (equalOp.equals("\u00D7"))
                    {
                        op.multiply(displayText);
                    }
                    else if (equalOp.equals("\u00F7"))
                    {
                        op.divide(displayText);
                    }

                    textfield.setText("" + op.getTotalString());
                    equalOp = e.getActionCommand();
                }
            }
        }
    }
    class NumberListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String digit = event.getActionCommand();
            if (number) {
                textfield.setText(digit);
                number = false;
            } else {
                textfield.setText(textfield.getText() + digit);
            }
        }
    }
    public class CalculatorOp {
        private int total;
        public CalculatorOp() {
            total = 0;
        }
        public String getTotalString() {
            return ""+total;
        }
        public void setTotal(String n) {
            total = convertToNumber(n);
        }
        public void add(String n) {
            total += convertToNumber(n);
        }
        public void subtract(String n) {
            total -= convertToNumber(n);
        }
        public void multiply(String n) {
            total *= convertToNumber(n);
        }
        public void divide(String n) {
            total /= convertToNumber(n);
        }
        private int convertToNumber(String n) {
            return Integer.parseInt(n);
        }
    }
}