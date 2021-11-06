package net.abdymazhit.dangerzone.customs.events;

/**
 * Представляет собой событие разрушения кровати
 *
 * @version   06.11.2021
 * @author    Islam Abdymazhit
 */
public class BedEvent extends Event {

    /** Имя разрушающего */
    public String player;

    /** Цвет команды разрушающего */
    public String playerColor;

    /** Название команды разрушающего */
    public String team;

    /** Цвет команды разрушающего */
    public String teamColor;

    /**
     * Инициализирует событие разрушения кровати
     * @param image Изображение события
     * @param type Тип события
     * @param time Время события
     * @param player Имя разрушающего
     * @param playerColor Цвет команды разрушающего
     * @param team Название команды разрушающего
     * @param teamColor Цвет команды разрушающего
     */
    public BedEvent(String image, String type, int time, String player, String playerColor, String team, String teamColor) {
        super(image, type, time);
        this.player = player;
        this.playerColor = playerColor;
        this.team = team;
        this.teamColor = teamColor;
    }
}