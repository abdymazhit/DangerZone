package net.abdymazhit.mixmethod.customs;

import net.abdymazhit.mixmethod.Utils;

/**
 * Представляет собой игрока
 *
 * @version   07.10.2021
 * @author    Islam Abdymazhit
 */
public class Player {

    /** Место игрока */
    public Integer place;

    /** Имя игрока */
    public String name;

    /** Очки игрока */
    public Integer points;

    /** Изображение лиги игрока */
    public String image;

    /**
     * Инициализирует игрока
     * @param place Место игрока
     * @param name Имя игрока
     * @param points Очки игрока
     */
    public Player(int place, String name, int points) {
        this.place = place;
        this.name = name;
        this.points = points;
        this.image = Utils.getLeagueImage(points);
    }
}
