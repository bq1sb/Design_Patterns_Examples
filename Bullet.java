import java.awt.*;
abstract class Bullet {
    protected int x, y;
    protected String direction;
    protected Image bulletImage;

    public Bullet(int x, int y, String direction, Image image) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.bulletImage = image;
    }

    public abstract void move();
    public abstract void draw(Graphics g);
}

class SimpleBullet extends Bullet {
    public SimpleBullet(int x, int y, String direction, Image image) {
        super(x, y, direction, image);
        System.out.println("  Создан простой снаряд.");
    }

    @Override
    public void move() {
        if (direction.equals("UP")) y -= 5;
        System.out.println("  Простой снаряд движется к " + direction + ". Позиция: (" + x + "," + y + ")");
    }

    @Override
    public void draw(Graphics g) {
        System.out.println("  Отрисован простой снаряд на (" + x + "," + y + ")");
    }
}

interface BulletFactory {
    Bullet createBullet(int x, int y, String direction);
}

class SimpleBulletFactory implements BulletFactory {
    private Image simpleBulletImage;

    public SimpleBulletFactory(Image image) {
        this.simpleBulletImage = image;
    }

    @Override
    public Bullet createBullet(int x, int y, String direction) {
        return new SimpleBullet(x, y, direction, simpleBulletImage);
    }
}

class PlayerTank {
    private int x, y;
    private BulletFactory bulletFactory;

    public PlayerTank(int x, int y, BulletFactory factory) {
        this.x = x;
        this.y = y;
        this.bulletFactory = factory;
        System.out.println("Игрок-танк создан с фабрикой: " + factory.getClass().getSimpleName());
    }

    public void shoot(String direction) {
        System.out.println("Танк игрока стреляет...");
        Bullet newBullet = bulletFactory.createBullet(this.x + 10, this.y + 10, direction);
        newBullet.move();
    }
}

