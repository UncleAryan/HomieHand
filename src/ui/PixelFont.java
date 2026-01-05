package ui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class PixelFont {
    private static final Map<Character, boolean[][]> PIXEL_MATRIX = new HashMap<>();

    static {
        defineCharacter('A', new boolean[][]{
                {false, true, true, true, false},
                {true, false, false, false, true},
                {true, true, true, true, true},
                {true, false, false, false, true},
                {true, false, false, false, true}
        });

        defineCharacter('B', new boolean[][]{
                {true, true, true, true, false},
                {true, false, false, false, true},
                {true, true, true, true, false},
                {true, false, false, false, true},
                {true, true, true, true, false}
        });

        defineCharacter('C', new boolean[][]{
                {false, true, true, true, true},
                {true, false, false, false, false},
                {true, false, false, false, false},
                {true, false, false, false, false},
                {false, true, true, true, true}
        });

        defineCharacter('D', new boolean[][]{
                {true, true, true, true, false},
                {true, false, false, false, true},
                {true, false, false, false, true},
                {true, false, false, false, true},
                {true, true, true, true, false}
        });

        defineCharacter('E', new boolean[][]{
                {true, true, true, true, true},
                {true, false, false, false, false},
                {true, true, true, true, false},
                {true, false, false, false, false},
                {true, true, true, true, true}
        });

        defineCharacter('F', new boolean[][]{
                {true, true, true, true, true},
                {true, false, false, false, false},
                {true, true, true, true, true},
                {true, false, false, false, false},
                {true, false, false, false, false}
        });

        defineCharacter('G', new boolean[][]{
                {false, true, true, true, true},
                {true, false, false, false, false},
                {true, false, true, true, true},
                {true, false, false, false, true},
                {false, true, true, true, false}
        });

        defineCharacter('H', new boolean[][]{
                {true, false, false, false, true},
                {true, false, false, false, true},
                {true, true, true, true, true},
                {true, false, false, false, true},
                {true, false, false, false, true}
        });

        defineCharacter('I', new boolean[][]{
                {true, true, true, true, true},
                {false, false, true, false, false},
                {false, false, true, false, false},
                {false, false, true, false, false},
                {true, true, true, true, true}
        });

        defineCharacter('J', new boolean[][]{
                {true, true, true, true, true},
                {false, false, false, false, true},
                {false, false, false, false, true},
                {true, false, false, false, true},
                {false, true, true, true, false}
        });

        defineCharacter('K', new boolean[][]{
                {true, false, false, true, true},
                {true, false, true, false, false},
                {true, true, false, false, false},
                {true, false, true, false, false},
                {true, false, false, true, true}
        });

        defineCharacter('L', new boolean[][]{
                {true, false, false, false, false},
                {true, false, false, false, false},
                {true, false, false, false, false},
                {true, false, false, false, false},
                {true, true, true, true, true}
        });

        defineCharacter('M', new boolean[][]{
                {false, true, false, true, false},
                {true, false, true, false, true},
                {true, false, true, false, true},
                {true, false, true, false, true},
                {true, false, true, false, true}
        });

        defineCharacter('N', new boolean[][]{
                {true, false, false, false, true},
                {true, true, false, false, true},
                {true, false, true, false, true},
                {true, false, false, true, true},
                {true, false, false, false, true}
        });

        defineCharacter('O', new boolean[][]{
                {false, true, true, true, false},
                {true, false, false, false, true},
                {true, false, false, false, true},
                {true, false, false, false, true},
                {false, true, true, true, false}
        });

        defineCharacter('P', new boolean[][]{
                {true, true, true, true, false},
                {true, false, false, false, true},
                {true, true, true, true, false},
                {true, false, false, false, false},
                {true, false, false, false, false}
        });

        defineCharacter('Q', new boolean[][]{
                {false, true, true, true, false},
                {true, false, false, false, true},
                {true, false, true, false, true},
                {true, false, false, true, false},
                {false, true, true, false, true}
        });

        defineCharacter('R', new boolean[][]{
                {true, true, true, true, false},
                {true, false, false, false, true},
                {true, true, true, true, false},
                {true, false, true, false, false},
                {true, false, false, true, true}
        });

        defineCharacter('S', new boolean[][]{
                {false, true, true, true, true},
                {true, false, false, false, false},
                {false, true, true, true, true},
                {false, false, false, false, true},
                {true, true, true, true, false}
        });

        defineCharacter('T', new boolean[][]{
                {true, true, true, true, true},
                {false, false, true, false, false},
                {false, false, true, false, false},
                {false, false, true, false, false},
                {false, false, true, false, false}
        });

        defineCharacter('U', new boolean[][]{
                {true, false, false, false, true},
                {true, false, false, false, true},
                {true, false, false, false, true},
                {true, false, false, false, true},
                {false, true, true, true, false}
        });

        defineCharacter('V', new boolean[][]{
                {true, false, false, false, true},
                {true, false, false, false, true},
                {false, true, false, true, false},
                {false, true, false, true, false},
                {false, false, true, false, false}
        });

        defineCharacter('W', new boolean[][]{
                {true, false, true, false, true},
                {true, false, true, false, true},
                {true, false, true, false, true},
                {true, false, true, false, true},
                {false, true, false, true, false}
        });

        defineCharacter('X', new boolean[][]{
                {true, false, false, false, true},
                {false, true, false, true, false},
                {false, false, true, false, false},
                {false, true, false, true, false},
                {true, false, false, false, true}
        });

        defineCharacter('X', new boolean[][]{
                {true, false, false, false, true},
                {false, true, false, true, false},
                {false, false, true, false, false},
                {false, true, false, true, false},
                {true, false, false, false, true}
        });

        defineCharacter('Z', new boolean[][]{
                {true, true, true, true, true},
                {false, false, false, true, false},
                {false, false, true, false, false},
                {false, true, false, false, false},
                {true, true, true, true, true}
        });


        defineCharacter(' ', new boolean[][]{
                {false, false, false, false, false},
                {false, false, false, false, false},
                {false, false, false, false, false},
                {false, false, false, false, false},
                {false, false, false, false, false}
        });
    }

    private static void defineCharacter(char c, boolean[][] matrix) {
        PIXEL_MATRIX.put(c, matrix);
    }

    private Color color;
    private int charWidth;
    private int charHeight;
    private int spacing;
    private boolean antialias;

    public PixelFont(Color color, int charWidth, int charHeight) {
        this.color = color;
        this.charWidth = Math.max(1, charWidth);
        this.charHeight = Math.max(1, charHeight);
        this.spacing = 1;
        this.antialias = false;
    }

    public PixelFont(int charWidth, int charHeight) {
        this(Color.WHITE, charWidth, charHeight);
    }

    public void drawText(Graphics2D g, String text, int x, int y) {
        Object originalAntialias = g.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
        Object originalTextAntialias = g.getRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING);

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                antialias ? RenderingHints.VALUE_ANTIALIAS_ON : RenderingHints.VALUE_ANTIALIAS_OFF);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                antialias ? RenderingHints.VALUE_TEXT_ANTIALIAS_ON : RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);

        g.setColor(color);

        int currentX = x;
        text = text.toUpperCase();

        for (char c : text.toCharArray()) {
            drawCharacter(g, c, currentX, y);
            currentX += charWidth + spacing;
        }

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, originalAntialias);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, originalTextAntialias);
    }

    private void drawCharacter(Graphics2D g, char c, int x, int y) {
        boolean[][] matrix = PIXEL_MATRIX.get(c);

        int pixelWidth = Math.max(1, charWidth / 5);
        int pixelHeight = Math.max(1, charHeight / 5);

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                if (matrix[row][col]) {
                    int pixelX = x + col * pixelWidth;
                    int pixelY = y + row * pixelHeight;
                    g.fillRect(pixelX, pixelY, pixelWidth, pixelHeight);
                }
            }
        }
    }

    public void setCharWidth(int charWidth) {
        this.charWidth = charWidth;
    }

    public int calculateTextWidth(String text) {
        return text.length() * (charWidth + spacing) - spacing;
    }

    public int getCharHeight() {
        return charHeight;
    }

    public int getCharWidth() {
        return charWidth;
    }

    public void setSpacing(int spacing) {
        this.spacing = Math.max(0, spacing);
    }

    public int getSpacing() {
        return spacing;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}