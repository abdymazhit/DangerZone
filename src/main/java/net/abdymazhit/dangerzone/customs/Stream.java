package net.abdymazhit.dangerzone.customs;

import javax.persistence.Id;

/**
 * Представляет собой трансляцию
 *
 * @version   23.10.2021
 * @author    Islam Abdymazhit
 */
public class Stream {

    /** Id трансляции */
    @Id
    public Integer id;

    /** Имя игрока трансляции */
    public String username;

    /** Ссылка трансляции */
    public String link;

    /** Изображение трансляции */
    public String image;

    /** Заговолок трансляции */
    public String title;

    /**
     * Инициализирует трансляцию
     * @param id Id трансляции
     * @param username Имя игрока трансляции
     * @param link Ссылка трансляции
     * @param image Изображение трансляции
     * @param title Заговолок трансляции
     */
    public Stream(int id, String username, String link, String image, String title) {
        this.id = id;
        this.username = username;
        this.link = link;
        this.image = image;
        this.title = title;
    }
}