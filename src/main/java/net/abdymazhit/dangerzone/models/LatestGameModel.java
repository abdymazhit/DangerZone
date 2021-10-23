package net.abdymazhit.dangerzone.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Представляет собой модель последней игры
 *
 * @version   23.10.2021
 * @author    Islam Abdymazhit
 */
@Entity
public class LatestGameModel {

    /** Id игры */
    @Id
    public Integer id;

    /** Название карты игры */
    public String mapName;

    /** Формат игры */
    public String format;

    /** Vime id игры */
    public String matchId;

    /** Время завершения игры */
    public Timestamp finishedAt;

    /** Изменение рейтинга первой команды */
    public Integer firstTeamRatingChanges;

    /** Изменение рейтинга второй команды */
    public Integer secondTeamRatingChanges;

    /**
     * Получает время завершения игры
     * @return Время завершения игры
     */
    public Timestamp getFinishedAt() {
        return finishedAt;
    }
}