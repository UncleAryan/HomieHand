package framework;

public enum BlockType {
    GRASS(0,  102, 0,  0),
    DIRT (64, 64,  64, 1),
    STONE(0,  51,  0,  2),
    LAVA (51, 0,   0,  3),
    ICE  (0,  0,   51, 4);

    public final int r, g, b;
    public final int spriteColumn;

    BlockType(int r, int g, int b, int spriteColumn) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.spriteColumn = spriteColumn;
    }

    public static BlockType fromRGB(int r, int g, int b) {
        for (BlockType type : values()) {
            if (type.r == r && type.g == g && type.b == b) return type;
        }
        return null;
    }
}
