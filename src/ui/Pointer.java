package ui;

import framework.Constants;
import framework.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Pointer {
    private BufferedImage pointer;
    private float x, y, width, height, scale, padding;

    public Pointer() {
        scale = 2;
        padding = 10;
        width = Constants.DEFAULT_POINTER_WIDTH * scale;
        height = Constants.DEFAULT_POINTER_HEIGHT * scale;
        pointer = ImageLoader.getSpriteSheet(ImageLoader.POINTER);
    }

    public void render(Graphics g, Button newButton) {
        calculatePointerPosition(newButton);
        g.drawImage(pointer, (int)x, (int)y, (int)width, (int)height, null);
    }

    private void calculatePointerPosition(Button button) {
        x = button.getX() - width - padding;
        y = button.getY() + (button.getHeight() - height) / 2;
    }

    public BufferedImage getPointer() {
        return pointer;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }
}
