package assets;

import framework.BlockType;
import framework.EntityType;
import framework.GameObject;
import framework.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Block extends GameObject {
    private BufferedImage sprite;

    public Block(float x, float y, float width, float height, float scale, BlockType blockType) {
        super(x, y, width, height, scale, EntityType.BLOCK);
        sprite = ImageLoader.getSpriteSheet(ImageLoader.BLOCK_SPRITESHEET)
                .getSubimage(blockType.spriteColumn * (int) width, 0, (int) width, (int) height);
    }

    @Override
    public void tick(LinkedList<GameObject> gameObjects) { }

    @Override
    public void render(Graphics g) {
        g.drawImage(sprite, (int) x, (int) y, (int) scaledWidth, (int) scaledHeight, null);
    }
}
