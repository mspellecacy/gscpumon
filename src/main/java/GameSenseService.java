import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * Created by mspellecacy on 6/6/2016.
 */
public class GameSenseService {

    public static String GAMESENSE_HOST = "http://127.0.0.1:52083";

    public static String GAME_EVENT_PATH = "/game_event";

    public static String REGISTER_GAME_PATH = "/game_metadata";

    public static String REGISTER_GAME_EVENT_PATH = "/register_game_event";

    public static String BIND_GAME_EVENT_PATH = "/bind_game_event";

    //private HttpClient httpClient = HttpClientBuilder.create().build();

    public GameSenseService() {
    }

    public GameSenseService(String gameSenseHost) {
        this.GAMESENSE_HOST = gameSenseHost;
    }

    public Boolean registerGame(String jsonRegistation) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        Boolean postSuccess = false;

        try {
            HttpPost post = new HttpPost(GAMESENSE_HOST + REGISTER_GAME_PATH);
            StringEntity jsonPayload = new StringEntity(jsonRegistation);
            post.addHeader("content-type", "application/json");
            post.setEntity(jsonPayload);

            HttpResponse response = httpClient.execute(post);

            //System.out.println(response);

            postSuccess = true;
        } catch (Exception ex) {
        }

        return postSuccess;
    }


    public Boolean sendGameEvent(String jsonGameEvent) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        Boolean postSuccess = false;

        try {
            HttpPost post = new HttpPost(GAMESENSE_HOST + GAME_EVENT_PATH);
            StringEntity jsonPayload = new StringEntity(jsonGameEvent);
            post.addHeader("content-type", "application/json");
            post.setEntity(jsonPayload);

            HttpResponse response = httpClient.execute(post);

            //System.out.println(response);

            postSuccess = true;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return postSuccess;

    }

    public Boolean bindGameEvent(String jsonBindEvent) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        Boolean postSuccess = false;

        try {
            HttpPost post = new HttpPost(GAMESENSE_HOST + BIND_GAME_EVENT_PATH);
            StringEntity jsonPayload = new StringEntity(jsonBindEvent);
            post.addHeader("content-type", "application/json");
            post.setEntity(jsonPayload);

            HttpResponse response = httpClient.execute(post);

            //System.out.println(response);

            postSuccess = true;
        } catch (Exception ex) {
        }

        return postSuccess;
    }

    //TODO: Implement /register_game_event... a somewhat redundant endpoint.
    public void registerGameEvent(String jsonRegisterGameEvent) {
        HttpPost post = new HttpPost(GAMESENSE_HOST + REGISTER_GAME_EVENT_PATH);
    }

}
