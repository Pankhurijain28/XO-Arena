import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

        public class TicTacToe extends JFrame implements ActionListener {

            private JButton[][] buttons = new JButton[3][3];
            private boolean isXTurn = true;

            public TicTacToe() {
                setTitle("Tic Tac Toe");
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setSize(300, 300);
                setLayout(new GridLayout(3, 3));
                initializeButtons();
                setVisible(true);
            }

            private void initializeButtons() {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        buttons[i][j] = new JButton();
                        buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
                        buttons[i][j].addActionListener(this);
                        add(buttons[i][j]);
                    }
                }
            }

            public void actionPerformed(ActionEvent e) {
                JButton clickedButton = (JButton) e.getSource();
                if (clickedButton.getText().equals("")) {
                    if (isXTurn) {
                        clickedButton.setText("X");
                    } else {
                        clickedButton.setText("O");
                    }
                    isXTurn = !isXTurn;
                    if (checkForWin() || checkForTie()) {
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                buttons[i][j].setEnabled(false);
                            }
                        }
                    }
                }
            }

            private boolean checkForWin() {
                String[][] field = new String[3][3];
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        field[i][j] = buttons[i][j].getText();
                    }
                }

                // Check rows
                for (int i = 0; i < 3; i++) {
                    if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals("")) {
                        highlightWinningCells(buttons[i][0], buttons[i][1], buttons[i][2]);
                        JOptionPane.showMessageDialog(this, field[i][0] + " wins!");
                        return true;
                    }
                }

                // Check columns
                for (int i = 0; i < 3; i++) {
                    if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals("")) {
                        highlightWinningCells(buttons[0][i], buttons[1][i], buttons[2][i]);
                        JOptionPane.showMessageDialog(this, field[0][i] + " wins!");
                        return true;
                    }
                }

                // Check diagonals
                if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals("")) {
                    highlightWinningCells(buttons[0][0], buttons[1][1], buttons[2][2]);
                    JOptionPane.showMessageDialog(this, field[0][0] + " wins!");
                    return true;
                }

                if (field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals("")) {
                    highlightWinningCells(buttons[0][2], buttons[1][1], buttons[2][0]);
                    JOptionPane.showMessageDialog(this, field[0][2] + " wins!");
                    return true;
                }

                return false;
            }

            private void highlightWinningCells(JButton b1, JButton b2, JButton b3) {
                b1.setBackground(Color.GREEN);
                b2.setBackground(Color.GREEN);
                b3.setBackground(Color.GREEN);
            }

            private boolean checkForTie() {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (buttons[i][j].getText().equals("")) {
                            return false;
                        }
                    }
                }
                JOptionPane.showMessageDialog(this, "It's a tie!");
                return true;
            }

            public static void main(String[] args) {
                new TicTacToe();
            }
        }
