package net.abdymazhit.dangerzone.customs.events;

/**
 * Представляет собой событие
 *
 * @version   06.11.2021
 * @author    Islam Abdymazhit
 */
public class Event {

    /** Изображение события */
    public String image;

    /** Тип события */
    public String type;

    /** Время события */
    public String time;

    /**
     * Инициализирует событие
     * @param image Изображение события
     * @param type Тип события
     * @param time Время события
     */
    public Event(String image, String type, int time) {
        this.image = image;
        this.type = type;
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
    }
}
