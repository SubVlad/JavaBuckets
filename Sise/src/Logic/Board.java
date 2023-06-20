package Logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Board implements Collection<Board.Hex> {
    public String name;
    public int boardWidth;
    public int boardLength;
    public int boardScale;
    public ArrayList<Hex> BoardHexes;
    public ArrayList<Window> BoardWindows;
    public int obqu;
    public int centerX;
    public int centerY;
    public ArrayList<Hex> neighbours;
    public Hex curHex;



    Board (String BoardName, int boardWidth, int boardLength, int boardScale)
    {
        this.name = BoardName;
        this.BoardHexes = new ArrayList<>();
        this.BoardWindows = new ArrayList<>();
        this.boardWidth = boardWidth;
        this.boardLength = boardLength;
        this.boardScale = boardScale;
        this.centerX = (this.boardWidth / 2);
        this.centerY = (this.boardLength / 2);

    }

    public void setHex(String name, int x, int y, int z, int spriteNumber) {
        this.BoardHexes.add(new Hex(name, x, y, z, spriteNumber));
        this.obqu++;
        Game.obInGameQu++;
    }
    public void setWindow(String name, int x, int y, int z, int spriteNumber) {
        this.BoardWindows.add(new Window(name, x, y, z, spriteNumber));
        //this.obqu++;
        Game.obInGameQu++;
    }

    public Hex getHex(int index)
    {
        return this.BoardHexes.get(index);
    }


    public boolean hexExists(int i, int k, int l)
    {
        boolean answer = false;
        for(int f = 0; f < this.BoardHexes.size(); f ++){
            if(this.BoardHexes.get(f).x == i && this.BoardHexes.get(f).y == k && this.BoardHexes.get(f).z == l){
                answer = true;
                break;
            }else{
                answer = false;
            }
        }
        return answer;
    }
    public ArrayList<Hex> findHexNeighboursByCoords(Hex hex){
        ArrayList<Hex> neighbours = new ArrayList<>();
        for(int i = 0; i < this.BoardHexes.size(); i ++){
            if(
                    Math.abs(hex.x - this.BoardHexes.get(i).x) +
                    Math.abs(hex.y - this.BoardHexes.get(i).y) +
                    Math.abs(hex.z - this.BoardHexes.get(i).z) == 2
            ){
                    neighbours.add(this.BoardHexes.get(i));
            }
        }
        return neighbours;
    }
    public boolean checkHexesAround(ArrayList<Hex> neighbours, int keyNumber)
    {
        boolean answer = false;
        for(int i = 0; i < neighbours.size(); i ++){
            if(neighbours.get(i).spriteNumber == keyNumber){
                answer = true;
                break;
            }
        }
        return answer;
    }
    public boolean checkHexesInNeighbours(ArrayList<Hex> neighbours, Hex hexInCheck)
    {
        boolean answer = false;
        for(int i = 0; i < neighbours.size(); i ++){
            if(neighbours.get(i) == hexInCheck){
                answer = true;
                break;
            }
        }
        return answer;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<Hex> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(Hex Hex) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Hex> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }
    public class Hex
    {
        public String name;
        public int x;
        public int y;
        public int z;
        public int spriteNumber;
        public static int SPRITE_SIZEX = 50;
        public static int SPRITE_SIZEY = 50;
        public Form frm = Form.valueOf("hex");
        public Hex(String hexName, int x, int y, int z, int spriteNumber)
        {
            this.name = hexName;
            this.x = x;
            this.y = y;
            this.z = z;
            this.spriteNumber = spriteNumber;
        }

        public int getHexX()
        {
            return x;
        }
        public int getHexY()
        {
            return y;
        }
        public int getHexZ()
        {
            return z;
        }
    }
    public class Window
    {
        public String name;
        public int x;
        public int y;
        public int z;
        public int spriteNumber;
        public static int SPRITE_SIZEX = 50;
        public static int SPRITE_SIZEY = 50;
        public int status = 1;
        public Form frm = Form.valueOf("window");
        public Window(String windowName, int x, int y, int z, int spriteNumber)
        {
            this.name = windowName;
            this.x = x;
            this.y = y;
            this.z = z;
            this.spriteNumber = spriteNumber;
        }
    }
}