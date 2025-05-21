public class Game {
    private static Game instance;

    private boolean isSoundOn = true;
    private float volume = 0.8f;

    private Game() {
        System.out.println("Игра запущена: Создан главный менеджер игры (Game).");
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public void toggleSound() {
        isSoundOn = !isSoundOn;
        System.out.println("Звук " + (isSoundOn ? "включен" : "выключен"));
    }

    public boolean isSoundOn() {
        return isSoundOn;
    }

    public void showMainMenu() {
        System.out.println("Показать главное меню игры.");
    }
}

