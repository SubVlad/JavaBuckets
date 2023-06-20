package Logic;

public enum Sprite
{
    voidHex,//0
    redHex,//1
    yellowHex,//2
    greenHex,//3
    blueHex,//4
    Player1,//5
    Player2,//6
    greenCircle;//7

    public Object image;
    int getNumber()
    {
        return this.ordinal();
    }
}
