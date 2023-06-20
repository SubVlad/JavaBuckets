package Logic;

public class Player {
    public String name;
    public int lives;
    public boolean myTurn;
    public int x;
    public int y;
    public int z;
    public int spriteNumber;
    public static int SPRITE_SIZEX = 50;
    public static int SPRITE_SIZEY = 50;
    public Form frm = Form.valueOf("window");
    public Player(String name, int lives, int x, int y, int z, int spriteNumber)
    {
        this.name = name;
        this.lives = lives;
        this.myTurn = false;
        this.x = x;
        this.y = y;
        this.z = z;
        this.spriteNumber = spriteNumber;



    }
}
