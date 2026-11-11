package game.leaderboard;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.util.*;

public class FirebaseService {

    private String firebaseDatabaseUrl = "https://game-leaderboard-7bc66-default-rtdb.europe-west1.firebasedatabase.app/leaderboard.json";

    // Method to fetch the top 5 players and display a specific player's rank if they are not in the top 5
    public List<Player> getTop5AndPlayerRank(String playerName, int playerScore) throws Exception {
        // Fetch the entire leaderboard
        String leaderboardData = fetchLeaderboard();

        // Parse the JSON response into a list of Player objects
        List<Player> players = parseLeaderboardData(leaderboardData);

        // Sort the players by score (highest first)
        players.sort(Comparator.comparingInt(Player::getScore).reversed());

        // Get the top 5 players
        List<Player> top5Players = players.subList(0, Math.min(5, players.size()));

        // Check if the player is in the top 5
        boolean playerInTop5 = false;
        for (Player player : top5Players) {
            if (player.getName().equalsIgnoreCase(playerName)) {
                playerInTop5 = true;
                break;
            }
        }

        // If the player is not in the top 5, add them separately with their actual rank
        if (!playerInTop5) {
            int playerRank = getPlayerRank(players, playerName);
            System.out.println("Player Rank: " + playerRank);
            Player player = new Player(playerName, playerScore);
            top5Players.add(player);  // Add the player separately
        }

        return top5Players;
    }

    // Helper method to get the player's rank
    public int getPlayerRank(List<Player> players, String playerName) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getName().equalsIgnoreCase(playerName)) {
                return i + 1;  // Rank is 1-based, not 0-based
            }
        }
        return -1;  // Player not found
    }
    
    public List<Player> getAllPlayers() throws Exception {
        // Fetch the entire leaderboard
        String leaderboardData = fetchLeaderboard();
        List<Player> players = parseLeaderboardData(leaderboardData);

        // Sort the players by score (highest first)
        players.sort(Comparator.comparingInt(Player::getScore).reversed());
        return players;
    }

 // Method to parse leaderboard data from JSON into Player objects
    private List<Player> parseLeaderboardData(String leaderboardData) {
        List<Player> players = new ArrayList<>();
        
        // Check if leaderboardData is null or empty
        if (leaderboardData == null || leaderboardData.isEmpty()) {
            System.out.println("No data returned from Firebase.");
            return players; // Return an empty list
        }
        
        JsonObject leaderboardJson = JsonParser.parseString(leaderboardData).getAsJsonObject();
        
        for (Map.Entry<String, JsonElement> entry : leaderboardJson.entrySet()) {
            JsonObject playerData = entry.getValue().getAsJsonObject();
            String playerName = playerData.get("playerName").getAsString();
            int score = playerData.get("score").getAsInt();
            players.add(new Player(playerName, score));
        }
        return players;
    }

    // Method to fetch the leaderboard from Firebase
    public String fetchLeaderboard() throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(firebaseDatabaseUrl);
            request.setHeader("Accept", "application/json");

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                String jsonResponse = EntityUtils.toString(response.getEntity());
                return jsonResponse;  // Return the leaderboard data as a JSON string
            }
        }
    }
    private boolean isUsernameUnique(String playerName) throws Exception {
        // Fetch the current leaderboard
        String leaderboardData = fetchLeaderboard();

        // Parse the JSON response using Gson
        JsonObject leaderboardJson = JsonParser.parseString(leaderboardData).getAsJsonObject();

        // Iterate through the entries to see if the username exists
        for (Map.Entry<String, JsonElement> entry : leaderboardJson.entrySet()) {
            JsonObject playerData = entry.getValue().getAsJsonObject();
            String existingPlayerName = playerData.get("playerName").getAsString();
            if (existingPlayerName.equalsIgnoreCase(playerName)) {
                // Username already exists
                return false;
            }
        }
        // Username is unique
        return true;
    }
    private void submitScore(String playerName, int score) throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost request = new HttpPost(firebaseDatabaseUrl);
            String jsonInputString = String.format("{\"playerName\": \"%s\", \"score\": %d}", playerName, score);
            StringEntity entity = new StringEntity(jsonInputString);
            request.setEntity(entity);
            request.setHeader("Content-Type", "application/json");

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                System.out.println("Response Code: " + response.getCode());
                System.out.println("Response: " + EntityUtils.toString(response.getEntity()));
            }
        }
    }
    public boolean submitScoreIfUnique(String playerName, int score) throws Exception {
        // Check if the username is unique
        if (isUsernameUnique(playerName)) {
            // Username is unique, proceed to submit the score
            submitScore(playerName, score);
            return true;  // Successfully submitted
        } else {
            // Username is not unique, return false
            System.out.println("Username already exists: " + playerName);
            return false;  // Username is not unique
        }
    }
}
