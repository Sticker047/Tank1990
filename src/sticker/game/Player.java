package sticker.game;

import com.sun.glass.events.KeyEvent;
import sticker.IO.Input;
import sticker.graphics.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;


public class Player extends Entity {

    public static final int SPRITE_SCALE = 16;
    public static final int SPRITE_PER_HEADING = 2;

    private enum Heading {
        NORTH(0 * SPRITE_SCALE, 0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE),
        EAST(6 * SPRITE_SCALE, 0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE),
        SOUTH(4 * SPRITE_SCALE, 0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE),
        WEST(2 * SPRITE_SCALE, 0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE);

        private int x, y, h, w;

        Heading(int x, int y, int h, int w) {
            this.x = x;
            this.y = y;
            this.h = h;
            this.w = w;
        }

        BufferedImage texture(TextureAtlas atlas) {
            return atlas.cut(x, y, w, h);
        }
    }

    private Heading heading;
    private Map<Heading, Sprite> spriteMap;
    private float scale;
    private float speed;

    public Player(float x, float y, float scale, float speed, TextureAtlas atlas) {
        super(EntityType.PLAYER, x, y);

        heading = Heading.NORTH;
        spriteMap = new HashMap<Heading, Sprite>();
        this.scale = scale;
        this.speed = speed;

        for (Heading heading : Heading.values()) {
            SpriteSheet sheet = new SpriteSheet(heading.texture(atlas), SPRITE_PER_HEADING, SPRITE_SCALE);
            Sprite sprite = new Sprite(sheet, scale);
            spriteMap.put(heading, sprite);
        }

    }

    @Override
    protected void update(Input input) {
        float newX = x;
        float newY = y;

        if (input.getKey(KeyEvent.VK_UP)) {
            newY -= speed;
            heading = Heading.NORTH;
        } else if (input.getKey(KeyEvent.VK_DOWN)) {
            newY += speed;
            heading = Heading.SOUTH;
        } else if (input.getKey(KeyEvent.VK_LEFT)) {
            newX -= speed;
            heading = Heading.WEST;
        } else if (input.getKey(KeyEvent.VK_RIGHT)) {
            newX += speed;
            heading = Heading.EAST;
        }

        if (newX < 0) {
            newX = 0;
        } else if (newX > Game.WIDTH - SPRITE_SCALE) {
            newX = Game.WIDTH - SPRITE_SCALE;
        }

        if (newY < 0) {
            newY = 0;
        } else if (newY > Game.HEIGHT - SPRITE_SCALE) {
            newY = Game.HEIGHT - SPRITE_SCALE;
        }

        x = newX;
        y = newY;
    }

    @Override
    protected void render(Graphics2D g) {
        spriteMap.get(heading).render(g, x, y);
    }
}
