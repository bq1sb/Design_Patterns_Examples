import java.util.List;

interface EnemyBehaviorStrategy {
    void update(EnemyTank enemy, PlayerTank playerTank, List<Wall> walls, int screenWidth, int screenHeight);
}

class AggressiveStrategy implements EnemyBehaviorStrategy {
    @Override
    public void update(EnemyTank enemy, PlayerTank playerTank, List<Wall> walls, int screenWidth, int screenHeight) {
        System.out.println("  Враг: Агрессивно преследует игрока!");
        if (playerTank.x > enemy.getX()) enemy.setX(enemy.getX() + enemy.getSpeed());
        else if (playerTank.x < enemy.getX()) enemy.setX(enemy.getX() - enemy.getSpeed());
        if (playerTank.y > enemy.getY()) enemy.setY(enemy.getY() + enemy.getSpeed());
        else if (playerTank.y < enemy.getY()) enemy.setY(enemy.getY() - enemy.getSpeed());

        enemy.fireDummyBullet();
    }
}


class PatrolStrategy implements EnemyBehaviorStrategy {

    @Override
    public void update(EnemyTank enemy, PlayerTank playerTank, List<Wall> walls, int screenWidth, int screenHeight) {
        enemy.setCurrentSpeed(enemy.getBaseSpeed());
        enemy.moveRandomly(screenWidth, screenHeight, walls);
    }
}

class FleeStrategy implements EnemyBehaviorStrategy {
    @Override
    public void update(EnemyTank enemy, PlayerTank playerTank, List<Wall> walls, int screenWidth, int screenHeight) {
        System.out.println("  Враг: Убегает от игрока!");
        if (playerTank.x > enemy.getX()) enemy.setX(enemy.getX() - enemy.getSpeed());
        else if (playerTank.x < enemy.getX()) enemy.setX(enemy.getX() + enemy.getSpeed());
        if (playerTank.y > enemy.getY()) enemy.setY(enemy.getY() - enemy.getSpeed());
        else if (playerTank.y < enemy.getY()) enemy.setY(enemy.getY() + enemy.getSpeed());
    }
}
class EnemyTank {
    private int x, y;
    private int health = 100;
    private int speed = 3;
    private EnemyBehaviorStrategy currentBehavior;

    private PlayerTank playerTank;
    private List<Wall> walls;

    public EnemyTank(int x, int y, PlayerTank playerTank, List<Wall> walls, EnemyBehaviorStrategy initialStrategy) {
    this.x = x;
    this.y = y;
    this.playerTank = playerTank;
    this.walls = walls;
    this.currentBehavior = initialStrategy;
    System.out.println("Враг создан с начальной стратегией: " + initialStrategy.getClass().getSimpleName());
}

public void setCurrentBehavior(EnemyBehaviorStrategy newStrategy) {
    this.currentBehavior = newStrategy;
    System.out.println("  Враг сменил стратегию на: " + newStrategy.getClass().getSimpleName());
}

public void update(int screenWidth, int screenHeight) {
    currentBehavior.update(this, playerTank, walls, screenWidth, screenHeight);
    if (health < 30 && !(currentBehavior instanceof FleeStrategy)) {
        setCurrentBehavior(new FleeStrategy());
    }
}

public void takeDamage(int amount) {
    health -= amount;
    System.out.println("  Враг получил урон, здоровье: " + health);
}

public int getX() { return x; }
public int getY() { return y; }
public void setX(int x) { this.x = x; }
public void setY(int y) { this.y = y; }
public int getSpeed() { return speed; }
public void fireDummyBullet() { /* Упрощенный выстрел */ }
}

class PlayerTank { int x = 50, y = 50; }
class Wall {}
