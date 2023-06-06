import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
// этот класс пока не используется. сделан заране, потому что я хотел отделить класс пользователского интерфейса от главного исполнительного класса

public class UI extends JPanel implements KeyListener, ActionListener
{

    private JPanel ui;
    private Timer timer;
    public Session session;
    JTextArea displayArea;
    JTextField typingArea;

    public UI(){
        //setBackground(Color.black);
        Session session = new Session();
        //initUI();
        setFocusable(true);
        timer = new Timer(250,this);
        timer.start();
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //createAndShowGUI();
            }
        });

    }


    public void initUI()
    {
        ui = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                }
            };
        }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private void addComponentsToPane() {

        JButton button = new JButton("Clear");
        button.addActionListener(this);

        typingArea = new JTextField(20);
        typingArea.addKeyListener(this);

        //Uncomment this if you wish to turn off focus
        //traversal.  The focus subsystem consumes
        //focus traversal keys, such as Tab and Shift Tab.
        //If you uncomment the following line of code, this
        //disables focus traversal and the Tab events will
        //become available to the key event listener.
        //typingArea.setFocusTraversalKeysEnabled(false);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setPreferredSize(new Dimension(375, 125));

    }

    @Override
    public void keyTyped(KeyEvent e) {
        keyReader(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyReader(e);

    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyReader(e);

    }

    void keyReader(KeyEvent e)
    {

        int id = e.getID();

    }
}

