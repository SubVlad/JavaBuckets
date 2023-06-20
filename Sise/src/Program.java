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


public class Program extends JFrame implements ActionListener
{
    private JLabel label;
    private Image image;
    private JPanel ui;
    private Timer timer;
    public boolean ePressed = false;

    public static void main(String[] args)
    {
        Program mw = new Program();
    }

    public Program() {
        setBackground(Color.black);
        Game game = new Game();
        initUI();
        addKeyListener(new GameObjectKeyListener());
        setFocusable(true);
        timer = new Timer(250, this);
        timer.start();
        initFrame();
    }

    public void initFrame()
    {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sise");
        setResizable(true);
        setVisible(true);
        setSize(565,593);
        setLocationRelativeTo(null);
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
       // System.out.println(getMousePosition());
        Game.Cursor.x = getMousePosition().x - 8;
        Game.Cursor.y = getMousePosition().y - 30;
        Game.changeHexColor();
        repaint();
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
            if(key == KeyEvent.VK_1){
                Game.keyNumber = key-48;
            }
            if(key == KeyEvent.VK_2){
                Game.keyNumber = key-48;
            }
            if(key == KeyEvent.VK_3){
                Game.keyNumber = key-48;
            }
            if(key == KeyEvent.VK_4){
                Game.keyNumber = key-48;
            }



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

        public void keyReleased(KeyEvent e){
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_1){
                Game.keyNumber = 0;
            }
            if(key == KeyEvent.VK_2){
                Game.keyNumber = 0;
            }
            if(key == KeyEvent.VK_3){
                Game.keyNumber = 0;
            }
            if(key == KeyEvent.VK_4){
                Game.keyNumber = 0;
            }

        }


    }

    /*public static class Cursor
    {
        public static int x;
        public static int y;
        public Cursor()
        {
        }
        *//*public static void setX(int x) {
            this.x = x;
        }
        public static void setY(int y) {
            this.y = y;
        }*//*
        *//*public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }*//*
    }*/
}