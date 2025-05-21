import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

class SpriteLoader {
    public static BufferedImage load(String path) {
        System.out.println("  (Загрузка спрайта из: " + path + ")");
        return new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
    }
}

class BrickWallType {
    private final int maxHealth;
    private final BufferedImage sprite;

    public BrickWallType(int maxHealth, String spritePath) {
        this.maxHealth = maxHealth;
        this.sprite = SpriteLoader.load(spritePath);
        System.out.println("  Создан новый тип стены: Макс.здоровье=" + maxHealth + ", Спрайт=" + spritePath);
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public BufferedImage getSprite() {
        return sprite;
    }
}

class BrickWallTypeFactory {
    private static final Map<String, BrickWallType> wallTypes = new HashMap<>();

    public static BrickWallType getWallType(String typeKey, int maxHealth, String spritePath) {
        if (!wallTypes.containsKey(typeKey)) {
            wallTypes.put(typeKey, new BrickWallType(maxHealth, spritePath));
        }
        return wallTypes.get(typeKey);
    }
}

class BrickWall {
    private int x, y;
    private int currentHealth;
    private BrickWallType type;

    public BrickWall(int x, int y, String typeKey) {
        this.x = x;
        this.y = y;
        this.type = BrickWallTypeFactory.getWallType(typeKey, 40, "assets/brick_wall_" + typeKey + ".png");
        this.currentHealth = type.getMaxHealth();
        System.out.println("Создана кирпичная стена на (" + x + "," + y + ") с типом '" + typeKey + "'.");
    }

    public void takeDamage(int damage) {
        this.currentHealth -= damage;
        System.out.println("  Стена на (" + x + "," + y + ") получила урон. Здоровье: " + currentHealth);
    }

    public boolean isDestroyed() {
        return currentHealth <= 0;
    }

    public void draw(Graphics g) {
        if (!isDestroyed()) {
            System.out.println("  Отрисована стена на (" + x + "," + y + ") используя общий спрайт.");
        } else {
            System.out.println("  Стена на (" + x + "," + y + ") разрушена.");
        }
    }
}
