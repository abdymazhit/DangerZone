package net.abdymazhit.dangerzone.customs;

import net.abdymazhit.dangerzone.Utils;

/**
 * Представляет собой игрока игры
 *
 * @version   06.11.2021
 * @author    Islam Abdymazhit
 */
public class GamePlayer {

    /** Имя игрока */
    public String username;

    /** Очки игрока */
    public int points;

    /** Изображение лиги игрока */
    public String image;

    /** Vime id игрока */
    public int vimeId;

    /** Команда игрока */
    public String team;

    /** Количество убийств игрока */
    public int kills;

    /** Количество смертей игрока */
    public int deaths;

    /** Значение, является ли игрок MVP */
    public boolean isMvp;

    /** Значение, является ли игрок EVP */
    public boolean isEvp;

    /**
     * Инициализирует игрока игры
     * @param username Имя игрока
     * @param points Очки игрока
     */
    public GamePlayer(String username, int points) {
        this.username = username;
        this.points = points;
        image = Utils.getLeagueImage(points);
        deaths = 0;
        isMvp = false;
        isEvp = false;
    }

    /**
     * Получает количество убийств игрока
     * @return Количество убийств игрока
     */
    public int getKills() {
        return kills;
    }
}
