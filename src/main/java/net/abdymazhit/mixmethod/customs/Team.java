package net.abdymazhit.mixmethod.customs;

import net.abdymazhit.mixmethod.Utils;

/**
 * Представляет собой команду
 *
 * @version   07.10.2021
 * @author    Islam Abdymazhit
 */
public class Team {

    /** Место команды */
    public Integer place;

    /** Имя команды */
    public String name;

    /** Имя лидера команды */
    public String leaderName;

    /** Очки команды */
    public Integer points;

    /** Изображение лиги команды */
    public String image;

    /**
     * Инициализирует команду
     * @param place Место команды
     * @param name Имя команды
     * @param leaderName Имя лидера команды
     * @param points Очки команды
     */
    public Team(int place, String name, String leaderName, int points) {
        this.place = place;
        this.name = name;
        this.leaderName = leaderName;
        this.points = points;
        this.image = Utils.getLeagueImage(points);
    }
}
