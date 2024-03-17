package project;

/*
 * Written By:
 * Ezzat Boukhary, Patrick Hansen, Diego Cortes
 * Final Project
 * 12/02/2023
 */

import java.util.Random;
import javax.swing.*;
import java.util.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

interface GUI {
    public void menuoutput();

    public void Boardoutput();
}

abstract class CreateRandom {
    // create random function for initialization class
    public Random rand;

    public CreateRandom() {
        rand = new Random();
    }

    public int getRandomNumber(int bound) {
        return rand.nextInt(bound);
    }

    // unique random function for initialization class
    public Random rando = new Random();
    private Set<Integer> generatedNumbers = new HashSet<>();
    private Random rand1 = new Random();

    public int getUniqueRandomNumber(int bound) {
        if (bound <= generatedNumbers.size()) {
            throw new IllegalArgumentException("All numbers in the range have already been generated.");
        }

        int num;
        do {
            num = rand.nextInt(bound);
        } while (generatedNumbers.contains(num));

        generatedNumbers.add(num);
        return num;
    }

    public int getUniqueRandomNumber2(int bound) {
        int num2 = rando.nextInt(bound) == 0 ? 0 : 31;
        return num2;
    }
}

class initialization extends CreateRandom {
    int[][] board = new int[32][32];

    public void initialization() {
        // initialize board to make everything random
        int p;
        int f = 0;

        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 32; j++) {
                board[i][j] = 0;
                // makes every int in board 0
            }
        }

        for (int i = 0; i < 31; i++) {
            board[0][i] = 11;
            board[i][0] = 11;
            board[31][i] = 11;
            board[i][31] = 11;
        }
        board[31][31] = 11;
        board[31][0] = 11;
        board[0][31] = 11;
        board[0][0] = 11;
        // makes all border walls equal to 11
        p = getRandomNumber(2);
        if (p == 0) {
            p = getUniqueRandomNumber2(2);
            int h = getRandomNumber(32);
            board[p][h] = 18;
            p = getUniqueRandomNumber2(2);
            h = getRandomNumber(32);
            board[p][h] = 19;
            // creates enter cell (18) and exit cell (19)
        } else if (p == 1) {
            p = getRandomNumber(32);
            int h = getUniqueRandomNumber2(2);
            board[h][p] = 18;
            p = getRandomNumber(32);
            h = getUniqueRandomNumber2(2);
            board[h][p] = 19;
            // does same if p == 1
        }

        while (f < 8) {
            f++;
            int l = 0;
            while (l < 32) {
                int rando1 = getRandomNumber(4);
                int rando2 = getRandomNumber(32);
                int rando3 = getRandomNumber(32);
                if (board[rando2][rando3] == 0) {
                    board[rando2][rando3] = rando1 + 1;
                    l++;
                    // creates all wall 256 wall cells at random 1 = right wall 2 = left wall 3 =
                    // down wall 4 = up wall

                }
            }
        }

        p = 5;
        f = 0;
        while (f < 80) {
            int rando2 = getRandomNumber(32);
            int rando3 = getRandomNumber(32);
            if (board[rando3][rando2] == 0) {
                board[rando3][rando2] = p;
                f++;
                p++;
                if (p == 10) {
                    p = 5;
                    // creates all random object 5 = icecream 6 = coin 7 = elixir 8 = troll 9 = cave
                    // dweller
                }
            }
        }
    }
}

abstract class jframeblueprint extends initialization {
    public JLabel image(String input, int x, int y, int l, int w, JFrame panel) {
        ImageIcon imageIcon = new ImageIcon(getClass().getResource(input));
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(x, y, l, w);
        imageLabel.setIcon(imageIcon);
        panel.add(imageLabel);
        return imageLabel;
    }
}

class BoardGame extends jframeblueprint {
    private int boardSize = 32;
    private JPanel[][] squares;
    int length = 32;
    int height = 32;
    boolean newBoard = true; // flag for checking load/new board
    JFrame frame = new JFrame("Board");

    public void BoardGame() {
        // creates grid layout and print all cells

        // check if we're creating a new board from scratch or not
        if (newBoard)

            initialization();

        squares = new JPanel[32][32];
        JPanel ContainerBoardPanel = new JPanel();
        ContainerBoardPanel.setLayout(new GridLayout(height, length));

        frame.setLayout(new FlowLayout());
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Paint the board
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                squares[i][j] = new JPanel();

                if (board[i][j] == 19) {

                    squares[i][j].setBackground(Color.lightGray);

                } else if (board[i][j] == 18) {
                    squares[i][j].setBackground(Color.darkGray);
                } else if (board[i][j] == 1) {
                    squares[i][j] = new LeftHalfColoredPanel(Color.blue, Color.WHITE);

                } else if (board[i][j] == 2) {
                    squares[i][j] = new RightHalfColoredPanel(Color.blue, Color.WHITE);
                } else if (board[i][j] == 3) {
                    squares[i][j] = new BottomHalfColoredPanel(Color.blue, Color.WHITE);
                } else if (board[i][j] == 4) {
                    squares[i][j] = new TopHalfColoredPanel(Color.blue, Color.WHITE);
                } else if (board[i][j] == 5) {
                    squares[i][j].setBackground(Color.yellow);

                } else if (board[i][j] == 6) {
                    squares[i][j].setBackground(Color.cyan);
                } else if (board[i][j] == 7) {
                    squares[i][j].setBackground(Color.green);
                } else if (board[i][j] == 8) {
                    squares[i][j].setBackground(Color.red);
                } else if (board[i][j] == 9) {
                    squares[i][j].setBackground(Color.magenta);
                } else if (board[i][j] == 10) {
                    squares[i][j].setBackground(Color.orange);
                } else if (board[i][j] == 11) {
                    squares[i][j].setBackground(Color.BLACK);
                } else if (board[i][j] == 12) {
                    squares[i][j].setBackground(Color.green);
                } else if (board[i][j] == 13) {
                    squares[i][j].setBackground(Color.darkGray);
                } else if (board[i][j] == 14) {
                    squares[i][j].setBackground(Color.yellow);
                } else {
                    squares[i][j].setBackground(Color.white);
                }

                ContainerBoardPanel.add(squares[i][j]);
            }
        }

        // Save Board Button + Behavior
        JButton button = new JButton("SAVE BOARD");
        button.setBounds(400, 400, 200, 50);
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // Attempt to save board data
                try {
                    ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("board.ser"));
                    outputStream.writeObject(board);
                    outputStream.close();

                } catch (Exception ex) {
                    System.err.println(ex.getMessage());
                    System.exit(1);
                }
            }
        });

        // Add components to frame

        frame.add(ContainerBoardPanel);
        frame.add(button);

        try {
            image("KEY.jpg", 0, 0, 600, 600, frame);
        } catch (NullPointerException e) {

        }
        frame.setVisible(true);
    }

    class TopHalfColoredPanel extends JPanel {
        private Color topColor;
        private Color bottomColor;

        public TopHalfColoredPanel(Color topColor, Color bottomColor) {
            this.topColor = topColor;
            this.bottomColor = bottomColor;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int height = getHeight();
            int width = getWidth();

            g.setColor(topColor);
            g.fillRect(0, 0, width, height / 4);

            g.setColor(bottomColor);
            g.fillRect(0, height / 4, width, height);
        }
    }

    class BottomHalfColoredPanel extends JPanel {
        private Color topColor;
        private Color bottomColor;

        public BottomHalfColoredPanel(Color topColor, Color bottomColor) {
            this.topColor = topColor;
            this.bottomColor = bottomColor;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int height = getHeight();
            int width = getWidth();

            g.setColor(bottomColor);
            g.fillRect(0, 0, width, height);

            g.setColor(topColor);
            g.fillRect(0, height, width, height / 4);
        }
    }

    class LeftHalfColoredPanel extends JPanel {
        private Color leftColor;
        private Color rightColor;

        public LeftHalfColoredPanel(Color leftColor, Color rightColor) {
            this.leftColor = leftColor;
            this.rightColor = rightColor;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int height = getHeight();
            int width = getWidth();

            g.setColor(leftColor);
            g.fillRect(0, 0, width, height);

            g.setColor(rightColor);
            g.fillRect(width / 4, 0, width, height);
        }
    }

    class RightHalfColoredPanel extends JPanel {
        private Color leftColor;
        private Color rightColor;

        public RightHalfColoredPanel(Color leftColor, Color rightColor) {
            this.leftColor = leftColor;
            this.rightColor = rightColor;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int height = getHeight();
            int width = getWidth();

            g.setColor(rightColor);
            g.fillRect(0, 0, width, height);

            g.setColor(leftColor);
            g.fillRect(width, 0, width / 4, height);
        }
    }

}

class jframe extends BoardGame {
    // creates all pop ups

    public void menu() {
        // creates menu
        JFrame window;
        JLayeredPane layeredPane;
        JPanel picture;
        JButton button;
        window = new JFrame();
        window.setSize(1280, 720);
        // creates jframe
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 1280, 720);
        // creates panel
        window.add(layeredPane);
        picture = new JPanel();
        // creates new panel for buttons
        picture.setBounds(0, 0, 1280, 720);
        picture.setLayout(null);

        try {

            picture.add(image("Slide1.jpg", 0, 0, 1280, 720, window));
        } catch (NullPointerException e) {

        }
        // Initialize theJButton and set its properties
        button = new JButton("DESIGN RANDOM BOARD");
        button.setBounds(70, 400, 200, 50);

        picture.add(button); // Add the button to the JPanel
        layeredPane.add(picture, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(button, JLayeredPane.PALETTE_LAYER);

        // Load Button
        JButton loadButton = new JButton("LOAD BOARD");
        loadButton.setBounds(70, 500, 200, 50);
        picture.add(loadButton);

        // Design button click behavior.
        button.addActionListener(new ActionListener() {
            // waits for button click and prints out the board if design is clicked
            @Override
            public void actionPerformed(ActionEvent e) {

                // Create a new frame with a new board
                jframe instance = new jframe();
                instance.BoardGame();
                window.setVisible(false);

            }
        });

        // Load Board Button Click Behavior
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Attempt to read board data from file
                try {
                    ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("board.ser"));

                    // Create new frame with board data
                    jframe instance = new jframe();
                    instance.board = (int[][]) inputStream.readObject();

                    instance.newBoard = false; // set newBoard flag to false
                    instance.BoardGame();

                    window.setVisible(false);
                    inputStream.close();

                } catch (Exception ex) {
                    System.err.println(ex.getMessage());
                    System.exit(1);
                }
            }
        });

        window.setVisible(true);
    }
}

class MyGui extends jframe implements GUI {
    public void menuoutput() {
        menu();

    }

    public void Boardoutput() {
        BoardGame();

    }
}

public class testclass1 {
    public static void main(String[] args) {
        MyGui finals = new MyGui();
        // calls menu method in jframe class
        finals.menu();
    }
}
