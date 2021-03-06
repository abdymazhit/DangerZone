package net.abdymazhit.dangerzone.models;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Представляет собой модель игрока
 *
 * @version   23.10.2021
 * @author    Islam Abdymazhit
 */
@Entity
public class PlayerModel {

    /** Id игрока */
    @Id
    public Integer id;

    /** Место игрока */
    public Integer place;

    /** Имя игрока */
    public String name;

    /** Очки игрока */
    public Integer points;
}