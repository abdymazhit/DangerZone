package net.abdymazhit.dangerzone.models;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Представляет собой модель игрока игры
 *
 * @version   06.11.2021
 * @author    Islam Abdymazhit
 */
@Entity
public class GamePlayerModel {

    /** Id игрока */
    @Id
    public Integer playerId;

    /** Очки игрока */
    public Integer points;
}