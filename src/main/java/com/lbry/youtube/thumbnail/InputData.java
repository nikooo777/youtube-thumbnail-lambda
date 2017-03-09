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
        }
    }

    /**
     * true if at least a channel id or a video id is specified
     * false if no key is specified
     *
     * @return boolean
     */
    public boolean isCorrectlyInitialized() {
        return hasChannelID() || hasVideoID();
    }

    /**
     * true if a video ID was provided
     * false otherwise
     *
     * @return boolean
     */
    public boolean hasVideoID() {
        return videoID != null;
    }

    /**
     * true if a channel ID was provided
     * false otherwise
     *
     * @return boolean
     */
    public boolean hasChannelID() {
        return channelID != null;
    }

    private void setVideoID(String videoID) {
        this.videoID = videoID;
    }

    /**
     * Retrieves the video ID
     *
     * @return String
     */
    public String getVideoID() {
        return videoID;
    }


    private void setChannelID(String channelID) {
        this.channelID = channelID;
    }

    /**
     * Retrieves the channel ID
     *
     * @return String
     */
    public String getChannelID() {
        return channelID;
    }
}
