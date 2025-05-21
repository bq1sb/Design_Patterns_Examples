import java.awt.*;

interface ItemEffectCommand {
    void execute(PlayerTank playerTank);
}

class HealCommand implements ItemEffectCommand {
    private int healAmount;

    public HealCommand(int healAmount) {
        this.healAmount = healAmount;
    }

    @Override
    public void execute(PlayerTank playerTank) {
        playerTank.heal(healAmount);
        System.out.println("  Команда: Игрок вылечен на " + healAmount + " HP.");
    }
}

class SpeedBoostCommand implements ItemEffectCommand {
    private int boostAmount;

    public SpeedBoostCommand(int boostAmount) {
        this.boostAmount = boostAmount;
    }

    @Override
    public void execute(PlayerTank playerTank) {
        playerTank.increaseSpeed(boostAmount);
        System.out.println("  Команда: Скорость игрока увеличена на " + boostAmount + ".");
    }
}

class PlayerTank {
    private int health = 100;
    private int speed = 5;
    public int x, y;

    public void heal(int amount) {
        this.health += amount;
        System.out.println("  Игрок: Здоровье стало " + health + ".");
    }

    public void increaseSpeed(int amount) {
        this.speed += amount;
        System.out.println("  Игрок: Скорость стала " + speed + ".");
    }
}

class Item {
    private int x, y;
    private ItemEffectCommand effectCommand;
    private boolean collected = false;

    public Item(int x, int y, ItemEffectCommand command) {
        this.x = x;
        this.y = y;
        this.effectCommand = command;
        System.out.println("Создан предмет на (" + x + "," + y + ") с эффектом: " + command.getClass().getSimpleName());
    }

    public void onPlayerCollide(PlayerTank player) {
        if (!collected) {
            System.out.println("Предмет на (" + x + "," + y + ") активирован!");
            effectCommand.execute(player);
            collected = true;
        }
    }

    public void draw(Graphics g) {
        if (!collected) {
            System.out.println("  Отрисован предмет на (" + x + "," + y + ")");
        }
    }
}
