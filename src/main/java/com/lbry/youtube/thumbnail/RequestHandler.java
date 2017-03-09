/**
 * Created by niko on 3/2/17.
 */

package com.lbry.youtube.thumbnail;

import com.amazonaws.services.lambda.runtime.Context;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RequestHandler implements com.amazonaws.services.lambda.runtime.RequestHandler<InputData, Map<String, Object>> {
    public Map<String, Object> handleRequest(InputData inputData, Context context) {
        HashMap<String, Object> response = new HashMap<>();
        if (inputData.isCorrectlyInitialized()) {
            if (inputData.hasVideoID()) {
                ThumbnailScraper thumbnailScraper = new ThumbnailScraper(inputData.getVideoID());
                try {
                    byte[] thumbnail = thumbnailScraper.getThumbnail();
                    ThumbnailStorage.persist(thumbnail, inputData.getVideoID());
                    String endpoint = System.getenv("s3_resource_endpoint");
                    response.put("url", endpoint + inputData.getVideoID());
                    response.put("error", 0);
                } catch (IOException e) {
                    response.put("error", 1);
                    response.put("message", "An error occurred while persisting the thumbnail");
                } catch (ThumbnailFetchException e) {
                    response.put("error", 1);
                    response.put("message", e.getMessage());
                }
            } else {
                response.put("error", 1);
                response.put("message", "Channel IDs are not yet supported");
            }
        } else {
            response.put("error", 1);
            response.put("message", "Both videoID and channelID are missing. Please specify at least one.");
        }
        return response;
    }
}
