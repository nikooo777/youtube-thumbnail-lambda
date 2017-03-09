import com.lbry.youtube.thumbnail.InputData;
import com.lbry.youtube.thumbnail.RequestHandler;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by niko on 3/2/17.
 */
public class RequestHandlerTest {
    @Test
    public void testRequest() {
        InputData inputData = new InputData();
        LinkedHashMap<String, Object> body = new LinkedHashMap<>();

        body.put("videoid", "DjouYBEkQPY");
        inputData.setBody(body);

        RequestHandler requestHandler = new RequestHandler();
        Map response = requestHandler.handleRequest(inputData, null);
        assertEquals(0, (int) response.get("error"));
        assertTrue(response.containsKey("url"));
    }

    @Test
    public void testInvalidRequest() {
        InputData inputData = new InputData();
        LinkedHashMap<String, Object> body = new LinkedHashMap<>();

        body.put("invalidstuff", "asd");
        inputData.setBody(body);

        RequestHandler requestHandler = new RequestHandler();
        Map response = requestHandler.handleRequest(inputData, null);
        assertEquals(1, (int) response.get("error"));
        assertEquals("Both videoID and channelID are missing. Please specify at least one.", (String) response.get("message"));
    }

    @Test
    public void testUnsupportedOperation() {
        InputData inputData = new InputData();
        LinkedHashMap<String, Object> body = new LinkedHashMap<>();

        body.put("channelid", "123");
        inputData.setBody(body);

        RequestHandler requestHandler = new RequestHandler();
        Map response = requestHandler.handleRequest(inputData, null);
        assertEquals(1, (int) response.get("error"));
        assertEquals("Channel IDs are not yet supported", (String) response.get("message"));
    }

    @Test
    public void testDownloadFailure() {
        InputData inputData = new InputData();
        LinkedHashMap<String, Object> body = new LinkedHashMap<>();

        body.put("videoid", "123");
        inputData.setBody(body);

        RequestHandler requestHandler = new RequestHandler();
        Map response = requestHandler.handleRequest(inputData, null);
        assertEquals(1, (int) response.get("error"));
        assertEquals("Failed retrieving thumbnail with status code: 404", (String) response.get("message"));
    }
}