package ui;

import framework.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Button {
    private BufferedImage buttonImage, button;
    private float x, y, width, height, scale;
    private UIType type;
    private PixelFont pixelFont;
    private boolean isHovered;
    private Rectangle bounds;

    public Button(float x, float y, float width, float height, float scale, UIType type) {
        isHovered = false;
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.width = width * scale;
        this.height = height * scale;
        bounds = new Rectangle((int)x, (int)y, (int)this.width, (int)this.height);
        this.type = type;
        buttonImage = ImageLoader.getSpriteSheet(ImageLoader.BUTTON);
        pixelFont = new PixelFont(16, 16);
        button = createButtonWithCenteredText(buttonImage, getButtonText(type), pixelFont);
    }

    public void render(Graphics g) {
        g.drawImage(button, (int)x, (int)y, (int)width, (int)height, null);
    }

    public boolean contains(int mouseX, int mouseY) {
        return bounds.contains(mouseX, mouseY);
    }

    public boolean isHovered() {
        return isHovered;
    }

    public void setHovered(boolean hovered) {
        this.isHovered = hovered;
    }

    private BufferedImage createButtonWithCenteredText(BufferedImage buttonImage, String text, PixelFont pixelFont) {
        int textWidth = pixelFont.calculateTextWidth(text);
        int fontHeight = pixelFont.getCharHeight();

        int padding = 10;
        int minWidth = textWidth + padding * 2;
        int minHeight = fontHeight + padding * 2;

        int finalWidth = Math.max(buttonImage.getWidth(), minWidth);
        int finalHeight = Math.max(buttonImage.getHeight(), minHeight);

        BufferedImage result = new BufferedImage(
                finalWidth,
                finalHeight,
                BufferedImage.TYPE_INT_ARGB
        );

        Graphics2D g = result.createGraphics();

        scaleBaseButton(g, buttonImage, finalWidth, finalHeight);

        int x = (finalWidth - textWidth) / 2;
        int y = (finalHeight - fontHeight) / 2;

        pixelFont.drawText(g, text, x, y);

        g.dispose();
        return result;
    }

    private void scaleBaseButton(Graphics2D g, BufferedImage buttonImage, int finalWidth, int finalHeight) {
        if (finalWidth > buttonImage.getWidth() || finalHeight > buttonImage.getHeight()) {
            g.drawImage(buttonImage, 0, 0, finalWidth, finalHeight, null);
        } else {
            g.drawImage(buttonImage, 0, 0, null);
        }
    }


    private String getButtonText(UIType type) {
        switch (type) {
            case START_BUTTON: return "START";
            case SETTINGS_BUTTON: return "SETTINGS";
            case EXIT_BUTTON: return "EXIT";
            default: return "";
        }
    }

    public UIType getType() {
        return type;
    }

    public void setType(UIType type) {
        this.type = type;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public BufferedImage getButton() {
        return button;
    }

    public void setButton(BufferedImage button) {
        this.button = button;
    }
}
