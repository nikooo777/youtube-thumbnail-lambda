package com.lbry.youtube.thumbnail;

import org.json.JSONObject;

import java.util.LinkedHashMap;

/**
 * Created by niko on 3/2/17.
 */
public class InputData {
    private String videoID = null;
    private String channelID = null;

    public void setBody(LinkedHashMap<String, Object> body) {
        JSONObject jsonBody = new JSONObject(body);
        if (jsonBody.has("videoid"))
            setVideoID(jsonBody.getString("videoid"));
        else if (jsonBody.has("channelid")) {
            setChannelID(jsonBody.getString("channelid"));
        } else
            throw new InsufficientDataException("Both videoID and channelID are missing. Please specify at least one.");
    }

    public boolean hasVideoID() {
        return videoID != null;
    }

    public boolean hasChannelID() {
        return channelID != null;
    }

    public void setVideoID(String videoID) {
        this.videoID = videoID;
    }

    public String getVideoID() {
        return videoID;
    }

    public void setChannelID(String channelID) {
        this.channelID = channelID;
    }

    public String getChannelID() {
        return channelID;
    }
}
