package net.abdymazhit.dangerzone.customs;

import javax.persistence.Id;

/**
 * Представляет собой последнюю игру
 *
 * @version   23.10.2021
 * @author    Islam Abdymazhit
 */
public class LatestGame {

    /** Id игры */
    @Id
    public Integer id;

    /** Название карты игры */
    public String mapName;

    /** Формат игры */
    public String format;

    /** Vime id игры */
    public String matchId;

    /** Время завершения игры (в минутах) */
    public int finishedAt;

    /** Изменение рейтинга первой команды */
    public String firstTeamRatingChanges;

    /** Изменение рейтинга второй команды */
    public String secondTeamRatingChanges;

    /**
     * Инициализирует последнюю игру
     * @param id Id игры
     * @param mapName Название карты игры
     * @param format Формат игры
     * @param matchId Vime id игры
     * @param finishedAt Время завершения игры (в минутах)
     * @param firstTeamRatingChanges Изменение рейтинга первой команды
     * @param secondTeamRatingChanges Изменение рейтинга второй команды
     */
    public LatestGame(int id, String mapName, String format, String matchId, int finishedAt, int firstTeamRatingChanges, int secondTeamRatingChanges) {
        this.id = id;
        this.mapName = mapName;
        this.format = format;
        this.matchId = matchId;
        this.finishedAt = finishedAt;
        if(firstTeamRatingChanges >= 0) {
            this.firstTeamRatingChanges = "+" + firstTeamRatingChanges;
        } else {
            this.firstTeamRatingChanges = String.valueOf(firstTeamRatingChanges);
        }
        if(secondTeamRatingChanges >= 0) {
            this.secondTeamRatingChanges = "+" + secondTeamRatingChanges;
        } else {
            this.secondTeamRatingChanges = String.valueOf(secondTeamRatingChanges);
        }
    }
}
