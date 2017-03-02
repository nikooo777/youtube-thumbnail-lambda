/**
 * Created by niko on 3/2/17.
 */

package com.lbry.youtube.thumbnail;

import com.amazonaws.services.lambda.runtime.Context;

import java.util.HashMap;
import java.util.Map;

public class RequestHandler implements com.amazonaws.services.lambda.runtime.RequestHandler<InputData, Map<String, Object>> {
    public Map<String, Object> handleRequest(InputData inputData, Context context) {
        HashMap<String, Object> response = new HashMap<>();
        if (inputData.hasVideoID()) {
            response.put("url", inputData.getVideoID());
        }
        return response;
    }
}
