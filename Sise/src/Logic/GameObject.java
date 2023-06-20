package Logic;

import java.awt.*;

public class GameObject {
    private String name;

    public Image image;
    public double coordX;
    public double coordY;
    public int spriteSizeX;
    public int spriteSizeY;
    public int centerX;
    public int centerY;

    public GameObject (String name, Image image, int spriteSizeX, int spriteSizeY, int x, int y, int z, Form frm, int centerX, int centerY)
    {
        this.name = name;
        this.image = image;
        this.spriteSizeX = spriteSizeX;
        this.spriteSizeY = spriteSizeY;
        this.centerX = centerX;
        this.centerY = centerY;
        this.coordX = x * this.spriteSizeX + this.centerX;
        this.coordY = y * this.spriteSizeY + this.centerY;
        if(frm == Form.valueOf("hex")){
            this.coordX = this.coordX
                    - (this.spriteSizeX * 0.5)
                    - ((this.spriteSizeX * 0.01) * x)
                    + ((this.spriteSizeX * 0.5) * y)
                    - ((this.spriteSizeX * 0.005) * z);
            this.coordY = this.coordY
                    - (this.spriteSizeY * 0.5)
                    //- (this.spriteSizeY * 0.1)
                    - ((this.spriteSizeY * 0.01) * x)
                    - ((this.spriteSizeY * 1.76) * y)
                    - ((this.spriteSizeY * 0.0024) * z);
        }
        if(frm == Form.valueOf("window")){

        }
        //	sprite = this.sprite;
    }

    public GameObject (int coordX, int coordY)
    {
        this.coordX = coordX;
        this.coordY = coordY;
    }


    public Image getImage()
    {
        return image;
    }
    public void setImage(Image image)
    {
        this.image = image;
    }
    public double getCoordX()
    {
        return coordX;
    }
    public void setCoordX(double coordX)
    {
        this.coordX = coordX;
    }
    public double getCoordY()
    {
        return coordY;
    }
    public void setCoordY(double coordY)
    {
        this.coordY = coordY;
    }
    public String getName()
    {
        return name;
    }
    public void setName(int name)
    {
        this.name = String.valueOf(name);
    }



}
