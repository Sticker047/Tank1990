package sticker.game.level;

import sticker.game.SpriteSheet;
import sticker.utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    private BufferedImage image;
    private TileType type;

    protected Tile(BufferedImage image, int scale, TileType type){
        this.type = type;
        this.image = Utils.resize(image, image.getWidth() * scale, image.getHeight() * scale);
    }

    public void render(Graphics2D g, int x, int y){
        g.drawImage(image, x, y, null);
    }

    protected TileType type(){
        return type;
    }



}
