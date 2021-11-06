package net.abdymazhit.dangerzone;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Инструменты для облегчения работы
 *
 * @version   06.11.2021
 * @author    Islam Abdymazhit
 */
public class Utils {

    /**
     * Получает изображение лиги
     * @param points Очки
     * @return Изображение лиги
     */
    public static String getLeagueImage(int points) {
        if(points <= 800) {
            return "/images/leagues/1.svg";
        } else if(points <= 950) {
            return "/images/leagues/2.svg";
        } else if(points <= 1100) {
            return"/images/leagues/3.svg";
        } else if(points <= 1250) {
            return"/images/leagues/4.svg";
        } else if(points <= 1400) {
            return"/images/leagues/5.svg";
        } else if(points <= 1550) {
            return"/images/leagues/6.svg";
        } else if(points <= 1700) {
            return"/images/leagues/7.svg";
        } else if(points <= 1850) {
            return"/images/leagues/8.svg";
        } else if(points <= 2000) {
            return"/images/leagues/9.svg";
        } else {
            return"/images/leagues/10.svg";
        }
    }

    /**
     * Отправляет GET запрос по URL
     * @param url URL
     * @return Результат запроса в типе String
     */
    public static String sendGetRequest(String url) {
        HttpGet request = new HttpGet(url);
        try (CloseableHttpClient httpClient = HttpClients.createDefault(); CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
