package net.abdymazhit.dangerzone.customs.events;

/**
 * Представляет собой событие убийства игрока
 *
 * @version   06.11.2021
 * @author    Islam Abdymazhit
 */
public class KillEvent extends Event {

    /** Имя убийцы */
    public String killer;

    /** Цвет команды убийцы */
    public String killerColor;

    /** Имя убитого игрока */
    public String target;

    /** Цвет команды убитого игрока */
    public String targetColor;

    /** Количество здоровья убийцы в момент убийства */
    public String hp;

    /**
     * Инициализирует событие убийства игрока
     * @param image Изображение события
     * @param type Тип события
     * @param time Время события
     * @param killer Имя убийцы
     * @param killerColor Цвет команды убийцы
     * @param target Имя убитого игрока
     * @param targetColor Цвет команды убитого игрока
     * @param hp Количество здоровья убийцы в момент убийства
     */
    public KillEvent(String image, String type, int time, String killer, String killerColor, String target, String targetColor, String hp) {
        super(image, type, time);
        this.killer = killer;
        this.killerColor = killerColor;
        this.target = target;
        this.targetColor = targetColor;
        this.hp = hp;
    }
}
