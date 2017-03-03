import com.lbry.youtube.thumbnail.InputData;
import com.lbry.youtube.thumbnail.RequestHandler;
import org.json.JSONObject;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by niko on 3/3/17.
 */
public class InputDataTest {

    @Test
    public void testGettersAndSetters() {
        InputData inputData = new InputData();
        inputData.setVideoID("videoidtest");
        assertEquals("videoidtest",inputData.getVideoID());

        inputData.setChannelID("channelidtest");
        assertEquals("channelidtest",inputData.getChannelID());

        inputData = new InputData();
        LinkedHashMap<String, Object> body = new LinkedHashMap<>();
        body.put("videoid", "xyz");
        inputData.setBody(body);
        assertTrue(inputData.hasVideoID());
        assertFalse(inputData.hasChannelID());
        assertEquals("xyz",inputData.getVideoID());

        inputData = new InputData();
        body.clear();
        body.put("channelid", "xyz");
        inputData.setBody(body);
        assertTrue(inputData.hasChannelID());
        assertFalse(inputData.hasVideoID());
        assertEquals("xyz",inputData.getChannelID());
    }
}
