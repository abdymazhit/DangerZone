package net.abdymazhit.dangerzone.models;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Представляет собой модель Single рейтинг игру
 *
 * @version   06.11.2021
 * @author    Islam Abdymazhit
 */
@Entity
public class SingleFinishedGameModel {

    /** Id игры */
    @Id
    public Integer id;

    /** Id капитана первой команды */
    public Integer firstTeamCaptainId;

    /** Id капитана второй команды */
    public Integer secondTeamCaptainId;

    /** Формат игры */
    public String format;

    /** Название карты */
    public String mapName;

    /** Id матча игры */
    public String matchId;

    /** Id помощника игры */
    public Integer assistantId;

    /** Изменение рейтинга первой команды */
    public Integer firstTeamRatingChanges;

    /** Изменение рейтинга второй команды */
    public Integer secondTeamRatingChanges;
}
