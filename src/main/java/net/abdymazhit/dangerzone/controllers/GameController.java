package net.abdymazhit.dangerzone.controllers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.abdymazhit.dangerzone.Utils;
import net.abdymazhit.dangerzone.customs.events.BedEvent;
import net.abdymazhit.dangerzone.customs.events.Event;
import net.abdymazhit.dangerzone.customs.GamePlayer;
import net.abdymazhit.dangerzone.customs.SingleFinishedGame;
import net.abdymazhit.dangerzone.customs.events.KillEvent;
import net.abdymazhit.dangerzone.models.GamePlayerModel;
import net.abdymazhit.dangerzone.models.SingleFinishedGameModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Отвечает за работу страницы показа игры
 *
 * @version   06.11.2021
 * @author    Islam Abdymazhit
 */
@Controller
public class GameController {

    /** Менеджер работы базы данных */
    private final EntityManager entityManager;

    /** Информация о игре последнего обновления */
    private final Map<Integer, SingleFinishedGame> singleFinishedGames;

    /**
     * Инициализирует контролер
     * @param entityManager Менеджер работы базы данных
     */
    public GameController(EntityManager entityManager) {
        this.entityManager = entityManager;
        singleFinishedGames = new HashMap<>();
    }

    @GetMapping("/single/game/{id}")
    public String game(@PathVariable(value = "id") int id, Model model) {
        if(!singleFinishedGames.containsKey(id)) {
            Query query = entityManager.createNativeQuery(
                    "SELECT * FROM single_finished_games_history WHERE id = ?", SingleFinishedGameModel.class);
            query.setParameter(1, id);
            SingleFinishedGameModel singleFinishedGameModel = (SingleFinishedGameModel) query.getSingleResult();

            Query assistantQuery = entityManager.createNativeQuery(
                    "SELECT username FROM users WHERE id = ?");
            assistantQuery.setParameter(1, singleFinishedGameModel.assistantId);
            String assistantName = (String) assistantQuery.getSingleResult();

            Query firstTeamNameQuery = entityManager.createNativeQuery(
                    "SELECT username FROM users WHERE id = ?");
            firstTeamNameQuery.setParameter(1, singleFinishedGameModel.firstTeamCaptainId);
            String firstTeamName = "team_" + firstTeamNameQuery.getSingleResult();

            Query secondTeamNameQuery = entityManager.createNativeQuery(
                    "SELECT username FROM users WHERE id = ?");
            secondTeamNameQuery.setParameter(1, singleFinishedGameModel.secondTeamCaptainId);
            String secondTeamName = "team_" + secondTeamNameQuery.getSingleResult();

            int firstTeamRatingChanges = singleFinishedGameModel.firstTeamRatingChanges;
            int secondTeamRatingChanges = singleFinishedGameModel.secondTeamRatingChanges;

            String mapName = singleFinishedGameModel.mapName;

            Query firstTeamPlayersQuery = entityManager.createNativeQuery(
                    "SELECT player_id, points FROM single_finished_games_players_history WHERE finished_game_id = ? AND team_id = 0;", GamePlayerModel.class);
            firstTeamPlayersQuery.setParameter(1, id);
            List<GamePlayerModel> firstTeamPlayersModel = firstTeamPlayersQuery.getResultList();
            List<GamePlayer> firstTeamPlayers = new ArrayList<>();
            for(GamePlayerModel gamePlayerModel : firstTeamPlayersModel) {
                Query playerNameQuery = entityManager.createNativeQuery("SELECT username FROM users WHERE id = ?");
                playerNameQuery.setParameter(1, gamePlayerModel.playerId);
                String username = (String) playerNameQuery.getSingleResult();
                firstTeamPlayers.add(new GamePlayer(username, gamePlayerModel.points));
            }

            Query secondTeamPlayersQuery = entityManager.createNativeQuery(
                    "SELECT player_id, points FROM single_finished_games_players_history WHERE finished_game_id = ? AND team_id = 1;", GamePlayerModel.class);
            secondTeamPlayersQuery.setParameter(1, id);
            List<GamePlayerModel> secondTeamPlayersModel = secondTeamPlayersQuery.getResultList();
            List<GamePlayer> secondTeamPlayers = new ArrayList<>();
            for(GamePlayerModel gamePlayerModel : secondTeamPlayersModel) {
                Query playerNameQuery = entityManager.createNativeQuery("SELECT username FROM users WHERE id = ?");
                playerNameQuery.setParameter(1, gamePlayerModel.playerId);
                String username = (String) playerNameQuery.getSingleResult();
                secondTeamPlayers.add(new GamePlayer(username, gamePlayerModel.points));
            }

            StringBuilder names = new StringBuilder();
            for(GamePlayer gamePlayer : firstTeamPlayers) {
                names.append(gamePlayer.username).append(",");
            }
            for(GamePlayer gamePlayer : secondTeamPlayers) {
                names.append(gamePlayer.username).append(",");
            }

            String vimeIdInfo = Utils.sendGetRequest("https://api.vimeworld.ru/user/name/%names%?token=%token%"
                    .replace("%names%", names)
                    .replace("%token%", System.getenv("VIME_TOKEN")));
            if(vimeIdInfo != null) {
                for(JsonElement jsonElement : JsonParser.parseString(vimeIdInfo).getAsJsonArray()) {
                    JsonObject jsonObject = jsonElement.getAsJsonObject();
                    String username = jsonObject.get("username").getAsString();
                    int vimeId = jsonObject.get("id").getAsInt();

                    for(GamePlayer gamePlayer : firstTeamPlayers) {
                        if(gamePlayer.username.equals(username)) {
                            gamePlayer.vimeId = vimeId;
                        }
                    }
                    for(GamePlayer gamePlayer : secondTeamPlayers) {
                        if(gamePlayer.username.equals(username)) {
                            gamePlayer.vimeId = vimeId;
                        }
                    }
                }
            }

            List<Event> events = new ArrayList<>();
            String matchInfo = Utils.sendGetRequest("https://api.vimeworld.ru/match/%match_id%?token=%token%"
                    .replace("%match_id%", singleFinishedGameModel.matchId)
                    .replace("%token%", System.getenv("VIME_TOKEN")));
            if(matchInfo != null) {
                JsonObject jsonObject = JsonParser.parseString(matchInfo).getAsJsonObject();

                int start = jsonObject.get("start").getAsInt();
                int end = jsonObject.get("end").getAsInt();

                int time = end - start;

                for(JsonElement jsonElement : jsonObject.get("players").getAsJsonArray()) {
                    JsonObject playerObject = jsonElement.getAsJsonObject();
                    int vimeId = playerObject.get("id").getAsInt();
                    int kills = playerObject.get("kills").getAsInt();

                    for(GamePlayer gamePlayer : firstTeamPlayers) {
                        if(gamePlayer.vimeId == vimeId) {
                            gamePlayer.kills = kills;
                        }
                    }
                    for(GamePlayer gamePlayer : secondTeamPlayers) {
                        if(gamePlayer.vimeId == vimeId) {
                            gamePlayer.kills = kills;
                        }
                    }
                }

                for(JsonElement jsonElement : jsonObject.get("teams").getAsJsonArray()) {
                    JsonObject playerObject = jsonElement.getAsJsonObject();
                    String team = playerObject.get("id").getAsString();

                    for(JsonElement membersElement : playerObject.get("members").getAsJsonArray()) {
                        int vimeId = membersElement.getAsInt();

                        for(GamePlayer gamePlayer : firstTeamPlayers) {
                            if(gamePlayer.vimeId == vimeId) {
                                gamePlayer.team = team;
                            }
                        }
                        for(GamePlayer gamePlayer : secondTeamPlayers) {
                            if(gamePlayer.vimeId == vimeId) {
                                gamePlayer.team = team;
                            }
                        }
                    }
                }

                for(JsonElement jsonElement : jsonObject.get("events").getAsJsonArray()) {
                    JsonObject eventObject = jsonElement.getAsJsonObject();

                    int eventTime = eventObject.get("time").getAsInt();

                    String type = eventObject.get("type").getAsString();
                    if(type.equals("kill")) {
                        int killer = eventObject.get("killer").getAsInt();
                        int target = eventObject.get("target").getAsInt();
                        String killerHealth = eventObject.get("killerHealth").getAsString();

                        String killerName = null;
                        String killerTeam = null;
                        String targetName = null;
                        String targetTeam = null;

                        for(GamePlayer gamePlayer : firstTeamPlayers) {
                            if(gamePlayer.vimeId == target) {
                                gamePlayer.deaths++;
                                targetName = gamePlayer.username;
                                targetTeam = gamePlayer.team;
                            }
                            if(gamePlayer.vimeId == killer) {
                                killerName = gamePlayer.username;
                                killerTeam = gamePlayer.team;
                            }
                        }
                        for(GamePlayer gamePlayer : secondTeamPlayers) {
                            if(gamePlayer.vimeId == target) {
                                gamePlayer.deaths++;
                                targetName = gamePlayer.username;
                                targetTeam = gamePlayer.team;
                            }
                            if(gamePlayer.vimeId == killer) {
                                killerName = gamePlayer.username;
                                killerTeam = gamePlayer.team;
                            }
                        }

                        if(killerName != null && killerTeam != null && targetName != null && targetTeam != null) {
                            String image = "https://skin.vimeworld.ru/helm/3d/%username%.png"
                                    .replace("%username%", killerName);
                            KillEvent event = new KillEvent(image, "kill", eventTime, killerName,
                                    killerTeam + "-color", targetName, targetTeam + "-color", killerHealth);
                            events.add(event);
                        }
                    } else if(type.equals("bedBreak")) {
                        String team = eventObject.get("team").getAsString();
                        int player = eventObject.get("player").getAsInt();

                        String image = null;
                        if(team.equals("blue")) {
                            image = "/images/icons/bed-blue.png";
                        } else if(team.equals("red")) {
                            image = "/images/icons/bed-red.png";
                        }

                        String playerName = null;
                        String playerTeam = null;
                        for(GamePlayer gamePlayer : firstTeamPlayers) {
                            if(gamePlayer.vimeId == player) {
                                playerName = gamePlayer.username;
                                playerTeam = gamePlayer.team;
                            }
                        }
                        for(GamePlayer gamePlayer : secondTeamPlayers) {
                            if(gamePlayer.vimeId == player) {
                                playerName = gamePlayer.username;
                                playerTeam = gamePlayer.team;
                            }
                        }

                        if(playerName != null && playerTeam != null && image != null) {
                            String targetTeam = null;
                            String targetTeamColor = null;
                            for(GamePlayer gamePlayer : firstTeamPlayers) {
                                if(gamePlayer.vimeId == player) {
                                    gamePlayer.isMvp = true;
                                    if(playerTeam.equals("red")) {
                                        targetTeam = secondTeamName;
                                        targetTeamColor = "blue-color";
                                    } else if(playerTeam.equals("blue")) {
                                        targetTeam = secondTeamName;
                                        targetTeamColor = "red-color";
                                    }
                                }
                            }
                            for(GamePlayer gamePlayer : secondTeamPlayers) {
                                if(gamePlayer.vimeId == player) {
                                    gamePlayer.isMvp = true;
                                    if(playerTeam.equals("red")) {
                                        targetTeam = firstTeamName;
                                        targetTeamColor = "blue-color";
                                    } else if(playerTeam.equals("blue")) {
                                        targetTeam = firstTeamName;
                                        targetTeamColor = "red-color";
                                    }
                                }
                            }

                            if(targetTeam != null) {
                                BedEvent event = new BedEvent(image, "bed", eventTime, playerName,
                                        playerTeam + "-color", targetTeam, targetTeamColor);
                                events.add(event);
                            }
                        }
                    }
                }

                List<GamePlayer> playersList = new ArrayList<>();
                playersList.addAll(firstTeamPlayers);
                playersList.addAll(secondTeamPlayers);

                List<GamePlayer> gamePlayersList = playersList.stream()
                        .sorted(Comparator.comparing(GamePlayer::getKills)).collect(Collectors.toList());
                if(!gamePlayersList.isEmpty()) {
                    gamePlayersList.get(gamePlayersList.size() - 1).isEvp = true;
                }

                SingleFinishedGame singleFinishedGame = new SingleFinishedGame(assistantName, firstTeamName, secondTeamName,
                        firstTeamRatingChanges, secondTeamRatingChanges, mapName, time, firstTeamPlayers, secondTeamPlayers, events);
                singleFinishedGames.put(id, singleFinishedGame);
                model.addAttribute("game", singleFinishedGame);
            }
        } else {
            SingleFinishedGame singleFinishedGame = singleFinishedGames.get(id);
            model.addAttribute("game", singleFinishedGame);
        }

        return "game";
    }
}
