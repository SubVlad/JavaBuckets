package Logic;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Game {
    public static Board[] BoardsInGame = new Board[2];
    public static GameObject[] GOinGame;
    public static ArrayList<GameObject> gameObjectsCurLoc;
    public static ImageIcon[] iicon = new ImageIcon[Sprite.values().length];
    public static Image[] images = new Image[Sprite.values().length];
    public static Player[] players = new Player[2];
    public static int obInGameQu;
    public static int curobqu;
    public static int curlocation;
    public static int rangeX;
    public static int rangeY;
    public static int startX;
    public static int startY;
    public static int startZ;
    public static int pixelPositionX;
    public static int pixelPositionY;
    public static boolean n1Pressed = false;
    public static int keyNumber = 0;
    public static int turn = 0;
    public static int greenCircleIndex;


    public Game() {

        loadImages();
        Cursor cursor = new Cursor();
        curlocation = 0;
        BoardsInGame[curlocation] = new Board("world", 550, 550, 5);
        GOinGame = new GameObject[5];
        drawPattern(BoardsInGame[curlocation].boardScale);
        /*BoardsInGame[curlocation].setHex("voidHex", 0, 0, 0, 0);
        BoardsInGame[curlocation].setHex("voidHex", -1, 0, 1, 0);
        BoardsInGame[curlocation].setHex("voidHex", 0, -1, 1, 0);
        BoardsInGame[curlocation].setHex("voidHex", 0, 1, -1, 0);
        BoardsInGame[curlocation].setHex("voidHex", 2, -1, -1, 0);*/
        players[0] = new Player("player1", 100, -5, -5, 0, 5);
        players[1] = new Player("player2", 100, 3, -5, 0, 6);
        BoardsInGame[curlocation].setWindow("greenCircle", -5, -4, 0, 7);
        players[0].myTurn = true;

        System.out.println("Player" + (turn + 1));

        changeBoard(curlocation);
    }
    public static void changeTurn(Player[] players)
    {
        //player.myTurn = true;
        for(int i = 0; i < players.length; i ++){
            players[i].myTurn = !players[i].myTurn;
        }
    }
    public static void loadImages()
    {
        for(int i = 0; i < iicon.length; i++){
            String filename = Sprite.values()[i] + ".png";
            iicon[i] = new ImageIcon(filename);
            images[i] = iicon[i].getImage();
        }
    }
    public static void changeBoard(int curlocation){
        gameObjectsCurLoc = new ArrayList<>();
        for(int i = 0; i < BoardsInGame[curlocation].obqu; i++){
            gameObjectsCurLoc.add(new GameObject(BoardsInGame[curlocation].BoardHexes.get(i).name,
                    images[BoardsInGame[curlocation].BoardHexes.get(i).spriteNumber],
                    BoardsInGame[curlocation].BoardHexes.get(i).SPRITE_SIZEX,
                    BoardsInGame[curlocation].BoardHexes.get(i).SPRITE_SIZEY,
                    BoardsInGame[curlocation].BoardHexes.get(i).x,
                    BoardsInGame[curlocation].BoardHexes.get(i).y,
                    BoardsInGame[curlocation].BoardHexes.get(i).z,
                    BoardsInGame[curlocation].BoardHexes.get(i).frm,
                    BoardsInGame[curlocation].centerX,
                    BoardsInGame[curlocation].centerY));
            curobqu = i;
            if(rangeX < BoardsInGame[curlocation].BoardHexes.get(i).x){
                rangeX = BoardsInGame[curlocation].BoardHexes.get(i).x;}
            if(rangeY < BoardsInGame[curlocation].BoardHexes.get(i).y){
                rangeY = BoardsInGame[curlocation].BoardHexes.get(i).y;}
        }
        for(int i = 0; i < players.length; i++){
            gameObjectsCurLoc.add(new GameObject(players[i].name,
                    images[players[i].spriteNumber],
                    players[i].SPRITE_SIZEX,
                    players[i].SPRITE_SIZEY,
                    players[i].x,
                    players[i].y,
                    players[i].z,
                    players[i].frm,
                    BoardsInGame[curlocation].centerX,
                    BoardsInGame[curlocation].centerY));
            curobqu = curobqu + 1;
            /*if(rangeX < BoardsInGame[curlocation].BoardHexes.get(i).x){
                rangeX = BoardsInGame[curlocation].BoardHexes.get(i).x;}
            if(rangeY < BoardsInGame[curlocation].BoardHexes.get(i).y){
                rangeY = BoardsInGame[curlocation].BoardHexes.get(i).y;}*/
        }
        for(int i = 0; i < BoardsInGame[curlocation].BoardWindows.size(); i++){
            gameObjectsCurLoc.add(new GameObject(BoardsInGame[curlocation].BoardWindows.get(i).name,
                    images[BoardsInGame[curlocation].BoardWindows.get(i).spriteNumber],
                    BoardsInGame[curlocation].BoardWindows.get(i).SPRITE_SIZEX,
                    BoardsInGame[curlocation].BoardWindows.get(i).SPRITE_SIZEY,
                    BoardsInGame[curlocation].BoardWindows.get(i).x,
                    BoardsInGame[curlocation].BoardWindows.get(i).y,
                    BoardsInGame[curlocation].BoardWindows.get(i).z,
                    BoardsInGame[curlocation].BoardWindows.get(i).frm,
                    BoardsInGame[curlocation].centerX,
                    BoardsInGame[curlocation].centerY));
            curobqu = curobqu + 1;
            greenCircleIndex = curobqu;
        }

        curobqu++;
    }
    public static void drawOneVoidHex(int i, int k, int l)
    {
        if(!BoardsInGame[curlocation].hexExists(i, k, l)){
            BoardsInGame[curlocation].setHex("voidHex", i, k, l, 0);
        }
    }
    public static void drawPattern(int scale)
    {
        for(int i = -scale; i <= scale; i ++){
            for(int k = -scale; k <= scale; k ++){
                for(int l = -scale; l <= scale; l ++){
                    if(i + k + l == 0) {
                        drawOneVoidHex(i, k, l);
                    }
                }
            }
        }
    }
    public static void changeHexColor()
    {
        GameObject GOinChange = getGameObjectByCoords(Cursor.x, Cursor.y);
        if(     keyNumber > 0 &&
                GOinChange.image != images[keyNumber] &&
                getGameObjectIndex(GOinChange) >= 0){
            Board.Hex hexInChange = BoardsInGame[curlocation].BoardHexes.get(getGameObjectIndex(GOinChange));
            if(BoardsInGame[curlocation].curHex == null){
                hexInChange.spriteNumber = keyNumber;
                GOinChange.setImage(images[hexInChange.spriteNumber]);
                BoardsInGame[curlocation].curHex = hexInChange;
                turn = 1 - turn;
                changeTurn(players);
                changeGreenCircle();
            }else{
                if(     BoardsInGame[curlocation].checkHexesInNeighbours(BoardsInGame[curlocation].findHexNeighboursByCoords(BoardsInGame[curlocation].curHex), hexInChange) // тут проверка - только соседи предыдущего гекса
                        && !BoardsInGame[curlocation].checkHexesAround(BoardsInGame[curlocation].findHexNeighboursByCoords(hexInChange), keyNumber)) { // тут проверка - только если по соседству нет гекса с таким же цветом
                    hexInChange.spriteNumber = keyNumber;
                    GOinChange.setImage(images[hexInChange.spriteNumber]);
                    BoardsInGame[curlocation].curHex = hexInChange;
                    turn = 1 - turn;
                    changeTurn(players);
                    changeGreenCircle();
                }
            }
        }
    }
    public static void changeGreenCircle()
    {
        if(BoardsInGame[curlocation].BoardWindows.get(0).x == -5){
            BoardsInGame[curlocation].BoardWindows.get(0).x = 4;
            gameObjectsCurLoc.get(greenCircleIndex).coordX = 4 * 50 + gameObjectsCurLoc.get(greenCircleIndex).centerX;
/*
            this.coordX = x * this.spriteSizeX + this.centerX;
            this.coordY = y * this.spriteSizeY + this.centerY;*/
        }else{
            BoardsInGame[curlocation].BoardWindows.get(0).x = -5;
            gameObjectsCurLoc.get(greenCircleIndex).coordX = -5 * 50 + gameObjectsCurLoc.get(greenCircleIndex).centerX;
        }
    }
    public static int getGameObjectIndex(GameObject o)
    {
        return gameObjectsCurLoc.indexOf(o);
    }
    public static GameObject getGameObjectByCoords(int x, int y)
    {
        GameObject obj = null;
        GameObject objInCheck = null;
        for(int i = 0; i < gameObjectsCurLoc.size(); i ++){
            if(
                    x > (gameObjectsCurLoc.get(i).getCoordX()) &&
                    x < (gameObjectsCurLoc.get(i).getCoordX() + gameObjectsCurLoc.get(i).spriteSizeX) &&
                    y > (gameObjectsCurLoc.get(i).getCoordY()) &&
                    y < (gameObjectsCurLoc.get(i).getCoordY() + gameObjectsCurLoc.get(i).spriteSizeY)
            ){
                objInCheck = gameObjectsCurLoc.get(i);
                if(hexBodyPixelIsTransparent(objInCheck)){
                    obj = objInCheck;
                }
            }
        }
        return obj;
    }
    public static boolean hexBodyPixelIsTransparent(GameObject obj)
    {
        boolean zeroCheck = true;
        int newX;
        int newY;
        int rgb;
        try{
            obj = gameObjectsCurLoc.get(gameObjectsCurLoc.indexOf(obj));
            String filename = obj.getName() + ".png";;
            BufferedImage bi = ImageIO.read(new File(filename));
            newX = (int) (Cursor.x - obj.getCoordX());
            newY = (int) (Cursor.y - obj.getCoordY());
            rgb = bi.getRGB(newX, newY);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(rgb >= 0){
            zeroCheck = false;
        }
        return zeroCheck;
    }
    public static class Cursor {
        public static int x;
        public static int y;
        public Cursor()
        {
        }
    }
}
