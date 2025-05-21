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
