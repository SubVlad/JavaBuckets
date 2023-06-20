import Logic.GameObject;
import Logic.Game;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static java.awt.MouseInfo.getPointerInfo;


public class UI extends JPanel implements ActionListener {

    private JPanel ui;
    private Timer timer;
    public boolean ePressed = false;

    public UI() {
        setBackground(Color.black);
        Game game = new Game();
        initUI();
        addKeyListener(new GameObjectKeyListener());
        setFocusable(true);
        timer = new Timer(250, this);
        timer.start();
    }


    public void initUI() {
        ui = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (int i = 0; i < Game.curobqu; i++) {
                    g.drawImage(Game.gameObjectsCurLoc.get(i).getImage(),
                            (int) Game.gameObjectsCurLoc.get(i).getCoordX(),
                            (int) Game.gameObjectsCurLoc.get(i).getCoordY(), this);
                }
            }

            ;
        };

        ui.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                if (e.getButton() == MouseEvent.BUTTON1)
                    pressLeftButton();

                ui.repaint();
            }
        });

        ui.setPreferredSize(new Dimension(Game.BoardsInGame[0].boardWidth, Game.BoardsInGame[0].boardLength));

        add(ui);
    }

    public void pressLeftButton() {
        System.out.println(getMousePosition());
    }

    public void move() {

        repaint();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }



    class GameObjectKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();



            /*if (key == KeyEvent.VK_A && Game.gameObjectsCurLoc.get(Game.curobqu - 1).getCoordX() != 0) {
                Game.gameObjectsCurLoc.get(Game.curobqu - 1).setCoordX(Game.gameObjectsCurLoc.get(Game.curobqu - 1).getCoordX() - 1);
                move();
            }
            if (key == KeyEvent.VK_D && Game.gameObjectsCurLoc.get(Game.curobqu - 1).getCoordX() != Game.rangeX) {
                Game.gameObjectsCurLoc.get(Game.curobqu - 1).setCoordX(Game.gameObjectsCurLoc.get(Game.curobqu - 1).getCoordX() + 1);
                move();
            }
            if (key == KeyEvent.VK_W && Game.gameObjectsCurLoc.get(Game.curobqu - 1).getCoordY() != 0) {
                Game.gameObjectsCurLoc.get(Game.curobqu - 1).setCoordY(Game.gameObjectsCurLoc.get(Game.curobqu - 1).getCoordY() - 1);
                move();
            }
            if (key == KeyEvent.VK_S && Game.gameObjectsCurLoc.get(Game.curobqu - 1).getCoordY() != Game.rangeY) {
                Game.gameObjectsCurLoc.get(Game.curobqu - 1).setCoordY(Game.gameObjectsCurLoc.get(Game.curobqu - 1).getCoordY() + 1);
                move();
            }
            if (key == KeyEvent.VK_E && Game.gameObjectsCurLoc.get(Game.curobqu - 1).getCoordX() == 0 &&
                    Game.gameObjectsCurLoc.get(Game.curobqu - 1).getCoordY() == 0) {

                System.out.println("curloc = " + Game.curloc);
                System.out.println("gameObjectsCurLoc - " + Game.gameObjectsCurLoc.get(0).getName());
                Game.curloc = 1;
                Game.gameObjectsCurLoc.get(Game.curobqu - 1).setCoordX(1);
                Game.gameObjectsCurLoc.get(Game.curobqu - 1).setCoordY(1);
                Game.changeBoard();
                move();
            }*/
        }
    }
}