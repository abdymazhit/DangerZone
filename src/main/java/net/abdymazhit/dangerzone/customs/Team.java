package net.abdymazhit.dangerzone.customs;

import net.abdymazhit.dangerzone.Utils;

/**
 * Представляет собой команду
 *
 * @version   23.10.2021
 * @author    Islam Abdymazhit
 */
public class Team {

    /** Место команды */
    public Integer place;

    /** Имя команды */
    public String name;

    /** Очки команды */
    public Integer points;

    /** Изображение лиги команды */
    public String image;

    /**
     * Инициализирует команду
     * @param place Место команды
     * @param name Имя команды
     * @param points Очки команды
     */
    public Team(int place, String name, int points) {
        this.place = place;
        this.name = name;
        this.points = points;
        this.image = Utils.getLeagueImage(points);
    }
}
