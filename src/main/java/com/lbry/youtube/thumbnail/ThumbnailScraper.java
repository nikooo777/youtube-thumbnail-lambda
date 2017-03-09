package com.lbry.youtube.thumbnail;

import com.amazonaws.util.IOUtils;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by niko on 3/2/17.
 */
public class ThumbnailScraper {
    private String videoID;

    public ThumbnailScraper(String videoID) {
        this.videoID = videoID;
    }

    public byte[] getThumbnail() throws IOException {
        String thumbUrl = "https://i.ytimg.com/vi/" + videoID + "/hqdefault.jpg";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(thumbUrl)
                .get()
                .build();
        Response response = client.newCall(request).execute();
        if (response.code() == 200) {
            InputStream inputStream = response.body().byteStream();
            byte[] thumbnail = IOUtils.toByteArray(inputStream);
            inputStream.close();
            return thumbnail;
        } else {
            response.body().close();
            throw new ThumbnailFetchException("Failed retrieving thumbnail with status code: " + response.code());
        }
    }
}
