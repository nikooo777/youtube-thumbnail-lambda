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
        System.out.println(response);
    }
}
