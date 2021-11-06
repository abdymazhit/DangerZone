package net.abdymazhit.dangerzone.controllers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.abdymazhit.dangerzone.customs.LatestGame;
import net.abdymazhit.dangerzone.customs.Stream;
import net.abdymazhit.dangerzone.models.LatestGameModel;
import net.abdymazhit.dangerzone.models.StreamModel;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Отвечает за работу главных страниц
 *
 * @version   06.11.2021
 * @author    Islam Abdymazhit
 */
@Controller
public class MainController {

    /** Менеджер работы базы данных */
    private final EntityManager entityManager;

    /** Последнее время обновления информации */
    private Timestamp lastUpdate;

    /** Последние игры последнего обновления */
    private List<LatestGame> latestGames;

    /** Активные трансляций последнего обновления */
    private List<Stream> streams;

    /**
     * Инициализирует контролер
     * @param entityManager Менеджер работы базы данных
     */
    public MainController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Выдает страницу Single Rating
     * @param model Модель страницы
     * @return Страница Single Rating
     */
    @GetMapping("/")
    public String index(Model model) {
        Timestamp currentTime = Timestamp.from(Instant.now());

        // Проверка, можно ли обновить рейтинг
        if(lastUpdate == null || currentTime.getTime() - lastUpdate.getTime() >= 30000) {
            Query singleQuery = entityManager.createNativeQuery(
                    "SELECT id, map_name, format, match_id, finished_at, first_team_rating_changes, second_team_rating_changes " +
                "FROM single_finished_games_history ORDER BY finished_at DESC LIMIT 4", LatestGameModel.class);
            List<LatestGameModel> singleLatestGameModels = singleQuery.getResultList();

            Query teamQuery = entityManager.createNativeQuery(
                    "SELECT id, map_name, format, match_id, finished_at, first_team_rating_changes, second_team_rating_changes " +
                "FROM team_finished_games_history ORDER BY finished_at DESC LIMIT 4", LatestGameModel.class);
            List<LatestGameModel> teamLatestGameModels = teamQuery.getResultList();

            List<LatestGameModel> latestGameModels = new ArrayList<>();
            latestGameModels.addAll(singleLatestGameModels);
            latestGameModels.addAll(teamLatestGameModels);

            List<LatestGameModel> latestGameModelsList = latestGameModels.stream()
                    .sorted(Comparator.comparing(LatestGameModel::getFinishedAt)).collect(Collectors.toList());
            List<LatestGameModel> latestGameModelsSorted = new ArrayList<>();
            for(int i = latestGameModelsList.size() - 1; i >= 0; i--) {
                latestGameModelsSorted.add(latestGameModelsList.get(i));
            }

            latestGames = new ArrayList<>();
            for(int i = 0; i < 4; i++) {
                if(latestGameModelsSorted.size() >= i + 1) {
                    LatestGameModel latestGameModel = latestGameModelsSorted.get(i);

                    Timestamp timestamp = Timestamp.from(Instant.now());
                    long milliseconds = timestamp.getTime() - latestGameModel.finishedAt.getTime();
                    int minutes = (int) (milliseconds / (60 * 1000));

                    LatestGame latestGame = new LatestGame(latestGameModel.id, latestGameModel.mapName, latestGameModel.format,
                            latestGameModel.matchId, minutes, latestGameModel.firstTeamRatingChanges, latestGameModel.secondTeamRatingChanges);
                    latestGames.add(latestGame);
                }
            }

            Query query = entityManager.createNativeQuery(
                    "SELECT u.id, u.username, link FROM streams as s " +
                "INNER JOIN users AS u ON u.id = s.youtuber_id", StreamModel.class);
            List<StreamModel> streamModels = query.getResultList();
            streams = new ArrayList<>();
            for(StreamModel streamModel : streamModels) {
                String streamId = streamModel.link.replace("https://www.youtube.com/watch?v=", "");
                String image = "https://img.youtube.com/vi/" + streamId + "/maxresdefault.jpg";
                String title = getTitle(streamId);
                if(title != null) {
                    Stream stream = new Stream(streamModel.id, streamModel.username, streamModel.link, image, title);
                    streams.add(stream);
                }
            }

            lastUpdate = currentTime;
        }

        model.addAttribute("latestGames", latestGames);
        model.addAttribute("streams", streams);

        return "index";
    }

    /**
     * Получает название трансляции
     * @param streamId Id трансляции
     * @return Название трансляции
     */
    public static String getTitle(String streamId) {
        HttpGet request = new HttpGet("https://www.googleapis.com/youtube/v3/videos?id=" + streamId + "&key=%token%&part=snippet"
                .replace("%token%", System.getenv("VIME_TOKEN")));
        try (CloseableHttpClient httpClient = HttpClients.createDefault(); CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            String jsonString = EntityUtils.toString(entity);
            JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();

            JsonArray itemsArray = jsonObject.get("items").getAsJsonArray();
            if(!itemsArray.isEmpty()) {
                JsonObject itemObject = itemsArray.get(0).getAsJsonObject();
                JsonObject snippetObject = itemObject.get("snippet").getAsJsonObject();
                return snippetObject.get("title").getAsString();
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}