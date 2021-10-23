package net.abdymazhit.dangerzone.models;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Представляет собой модель трансляций
 *
 * @version   23.10.2021
 * @author    Islam Abdymazhit
 */
@Entity
public class StreamModel {

    /** Id трансляции */
    @Id
    public Integer id;

    /** Имя игрока трансляции */
    public String username;

    /** Ссылка трансляции */
    public String link;

}