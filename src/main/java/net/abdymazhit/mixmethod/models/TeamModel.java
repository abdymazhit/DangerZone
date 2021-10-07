package net.abdymazhit.mixmethod.models;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Представляет собой модель команды
 *
 * @version   07.10.2021
 * @author    Islam Abdymazhit
 */
@Entity
public class TeamModel {

    /** Id команды */
    @Id
    public Integer id;

    /** Место команды */
    public Integer place;

    /** Имя команды */
    public String name;

    /** Имя лидера команды*/
    public String leaderName;

    /** Очки команды */
    public Integer points;
}