import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.*;
import java.util.Objects;
import javax.swing.*;

public class Program extends JFrame
        implements KeyListener,
        ActionListener
{
    JTextArea displayArea;
    JTextField typingArea;
    static final String newline = System.getProperty("line.separator");

    Bucket buckets[] = new Bucket[2];
    int ig = 0;
    String log;
    int bucketNumber = 0;
    boolean isInteger = true;
    public double taskNumber;
    // ig - номер ведра в массиве вёдер ппри вводе данных в начале эксперимента, начиная с нуля
    // код для окошка пользовательского интерфейса я скопировал из документации к java. англоязычные комментарии - от автора того кода

    public static void main(String[] args) {
        /* Use an appropriate Look and Feel */
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

        //Schedule a job for event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        Program frame = new Program("JavaBuckets");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        frame.addComponentsToPane();


        //Display the window.
        frame.pack();
        frame.setVisible(true);
        frame.typingArea.requestFocusInWindow();
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
        scrollPane.setPreferredSize(new Dimension(475, 325));

        getContentPane().add(typingArea, BorderLayout.CENTER);
        getContentPane().add(scrollPane, BorderLayout.PAGE_START);
        getContentPane().add(button, BorderLayout.PAGE_END);
        String keyString;
        keyString = "Добро пожаловать в JavaBuckets!\nСейчас вам будет предоставлена возможность ввести объёмы вёдер \nи искомый объём воды. \nПо окончанию вычисления будет выдана подробная информация о процедуре. \nобъём первого ведра: ";
        displayArea.append(keyString + newline + "    ");
    }

    public Program(String name) {
        super(name);
    }


    /** Handle the key typed event from the text field. */
    public void keyTyped(KeyEvent e) {
        //displayInfo(e, "KEY TYPED: ");
    }

    /** Handle the key pressed event from the text field. */
    public void keyPressed(KeyEvent e) {
        displayInfo(e, "KEY PRESSED: ");
    }

    /** Handle the key released event from the text field. */
    public void keyReleased(KeyEvent e) {
        //displayInfo(e, "KEY RELEASED: ");
    }

    /** Handle the button click. */
    public void actionPerformed(ActionEvent e) {
        //Clear the text components.
        displayArea.setText("Добро пожаловать в JavaBuckets!\nСейчас вам будет предоставлена возможность ввести объёмы вёдер \nи искомый объём воды. \nПо окончанию вычисления будет выдана подробная информация о процедуре. \nобъём первого ведра: " + newline);
        typingArea.setText("");
        ig = 0;

        //Return the focus to the typing area.
        typingArea.requestFocusInWindow();
    }

    /*
     * We have to jump through some hoops to avoid
     * trying to print non-printing characters
     * such as Shift.  (Not only do they not print,
     * but if you put them in a String, the characters
     * afterward won't show up in the text area.)
     */
    private void displayInfo(KeyEvent e, String keyStatus){

        int key = e.getKeyCode();
        String res = typingArea.getText();
        int vl = 0;
        int find = 0;
        String log2 = "";
        if (key == 10 && !Objects.equals(res, "")){


            log = "Ведро номер " + (ig+1) + " вмещает ";
            try{
                vl = Integer.parseInt(res);
                if(ig < buckets.length - 1){
                    log2 = "А теперь ведро номер " + (ig+2) + newline;
                }else{
                    log2 = "Сейчас - сколько воды вы ищете " + newline;
                }
                log = log + res + " литров" + newline + log2;
            }catch(NumberFormatException w) {
                isInteger = false;
            };
            if(isInteger == true){
                if(vl > 0){
                    if(ig < buckets.length){
                        enterBucket(ig, vl);
                    }else{
                        log = "Вы ищете " + res + " литров" + newline;
                        find = vl;
                        calculate(buckets[0], buckets[1], find);
                    }
                }else{
                    log = "Не реально" + newline;
                }
            }else{
                log = "Не число" + newline;
            }


            displayArea.append(log);
            /*if(ig < buckets.length){
                log = "now the bucket number " + (ig+1) + newline;
            }else{
                log = "now - how much water you seek" + newline;

            }*/

            typingArea.setText("");
            isInteger = true;
        }

        displayArea.setCaretPosition(displayArea.getDocument().getLength());
    }

    public void enterBucket(int bucketNumber, int vl)
    {
            buckets[bucketNumber] = new Bucket(vl);
            ig = ig + 1;

    }

    public String logTry(String res)
    {
        int vl;
        String log;
        try{
            vl = Integer.parseInt(res);
        }catch(NumberFormatException w) {
            vl = 0;
            log = "not an Integer";
        }
        log = "not an Integer" + newline;
        return log;
    }

    public static double getRandom()
    {
        double r = Math.random();
        if(r > 0.6){
            r = getRandom();
        }
        return r;
    }

    public void calculate(Bucket a, Bucket b, int res)
    {
        String lg = new String();
        lg = "Подсказка: левым числам соответсвуют объёмы вёдер, правым числам - объём воды в них\n";
        int st = 0;
        double taskIndex = -1;
        for(int i = 0; i < 2000000; i ++){

            taskNumber = Math.floor(getRandom() * 10);
            if(taskNumber == 0 && a.curvol != a.vol && b.curvol != b.vol && taskIndex != 2){
                a.fillBucket();
                lg = lg + "наполнил первое ведро. В вёдрах: \n" + a.vol + " " + a.curvol + "\n" + b.vol + " " + b.curvol + "\n";

                st ++;
                taskIndex = taskNumber;
            }
            if(taskNumber == 1 && a.curvol != 0  && b.curvol != b.vol && taskIndex != 4){
                a.pourInAB(b);
                lg = lg + "вылил из первого ведра во второе. В вёдрах: \n" + a.vol + " " + a.curvol + "\n" + b.vol + " " + b.curvol + "\n";

                st ++;
                taskIndex = taskNumber;
            }
            if(taskNumber == 2 && a.curvol != 0 && taskIndex != 0){
                a.pourOut();
                lg = lg + "опорожнил первое ведро. В вёдрах: \n" + a.vol + " " + a.curvol + "\n" + b.vol + " " + b.curvol + "\n";

                st ++;
                taskIndex = taskNumber;
            }
            if(taskNumber == 3 && b.curvol != b.vol && a.curvol != a.vol && taskIndex != 5){
                b.fillBucket();
                lg = lg + "наполнил второе ведро. В вёдрах: \n" + a.vol + " " + a.curvol + "\n" + b.vol + " " + b.curvol + "\n";

                st ++;
                taskIndex = taskNumber;
            }
            if(taskNumber == 4 && b.curvol != 0  && a.curvol != a.vol && taskIndex != 1){
                b.pourInAB(a);
                lg = lg + "вылил из второго ведра в первое. В вёдрах: \n" + a.vol + " " + a.curvol + "\n" + b.vol + " " + b.curvol + "\n";

                st ++;
                taskIndex = taskNumber;
            }
            if(taskNumber == 5 && b.curvol != 0 && taskIndex != 3){
                b.pourOut();
                lg = lg + "опорожнил второе ведро. В вёдрах: \n" + a.vol + " " + a.curvol + "\n" + b.vol + " " + b.curvol + "\n";

                st ++;
                taskIndex = taskNumber;
            }
            if(a.curvol == 0 && b.curvol == 0){
                i = 0;
                st = 0;
                lg = "Подсказка: левым числам соответсвуют объёмы вёдер, правым числам - объём воды в них\n";
                taskIndex = -1;
            }
            if(a.curvol == res || b.curvol == res){
                lg = lg + "Победа!!! Задача выполнена за " + st + " шагов\n\nЕсли хотите, можете попробовать снова\n\n";
                System.out.println(lg);
                log = lg;
                taskIndex = -1;
                ig = 0;
                buckets[0] = null;
                buckets[1] = null;
                break;
            }
        }
    }
}

//IndexOutOfBoundException