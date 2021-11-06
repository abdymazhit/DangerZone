package net.abdymazhit.dangerzone.customs;

import net.abdymazhit.dangerzone.customs.events.Event;

import java.util.List;

/**
 * Представляет собой Single рейтинг игру
 *
 * @version   06.11.2021
 * @author    Islam Abdymazhit
 */
public class SingleFinishedGame {

    /** Имя помощника игры */
    public String assistantName;

    /** Название первой команды */
    public String firstTeamName;

    /** Название второй команды */
    public String secondTeamName;

    /** Изменение рейтинга первой команды */
    public int firstTeamRatingChanges;

    /** Изменение рейтинга второй команды */
    public int secondTeamRatingChanges;

    /** Название карты игры */
    public String mapName;

    /** Длительность игры */
    public String time;

    /** Список игроков первой команды */
    public List<GamePlayer> firstTeamPlayers;

    /** Список игроков второй команды */
    public List<GamePlayer> secondTeamPlayers;

    /** Список игровых событий */
    public List<Event> events;

    /**
     * Инициализирует Single рейтинг игру
     * @param assistantName Имя помощника игры
     * @param firstTeamName Название первой команды
     * @param secondTeamName Название второй команды
     * @param firstTeamRatingChanges Изменение рейтинга первой команды
     * @param secondTeamRatingChanges Изменение рейтинга второй команды
     * @param mapName Название карты игры
     * @param time Длительность игры
     * @param firstTeamPlayers Список игроков первой команды
     * @param secondTeamPlayers Список игроков второй команды
     * @param events Список игровых событий
     */
    public SingleFinishedGame(String assistantName, String firstTeamName, String secondTeamName, int firstTeamRatingChanges, int secondTeamRatingChanges,
                              String mapName, int time, List<GamePlayer> firstTeamPlayers, List<GamePlayer> secondTeamPlayers, List<Event> events) {
        this.assistantName = assistantName;
        this.firstTeamName = firstTeamName;
        this.secondTeamName = secondTeamName;
        this.firstTeamRatingChanges = firstTeamRatingChanges;
        this.secondTeamRatingChanges = secondTeamRatingChanges;
        this.mapName = mapName;
        int sec = time % 60;
        int min = (time / 60) % 60;
        int hours = (time / 60) / 60;
        this.time = "";
        if(hours > 0) {
            this.time += hours + " ч. ";
        }
        if(min > 0) {
            this.time += min + " мин. ";
        }
        if(sec > 0) {
            this.time += sec + " сек. ";
        }
        this.firstTeamPlayers = firstTeamPlayers;
        this.secondTeamPlayers = secondTeamPlayers;
        this.events = events;
    }
}
