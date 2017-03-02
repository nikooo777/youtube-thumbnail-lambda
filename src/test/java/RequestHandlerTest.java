import com.lbry.youtube.thumbnail.InputData;
import com.lbry.youtube.thumbnail.RequestHandler;
import org.json.JSONObject;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by niko on 3/2/17.
 */
public class RequestHandlerTest {
    @Test
    public void testRequest() {
        InputData inputData = new InputData();
        JSONObject inputDataObject = new JSONObject();
        inputDataObject.put("videoid", "xyz");
        //inputData.setBody();
        RequestHandler requestHandler = new RequestHandler();
        Map response = requestHandler.handleRequest(inputData, null);
        System.out.println(response);
    }
}
