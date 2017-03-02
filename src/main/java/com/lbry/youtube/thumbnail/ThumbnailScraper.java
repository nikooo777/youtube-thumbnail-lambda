package com.lbry.youtube.thumbnail;

import com.amazonaws.util.IOUtils;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by niko on 3/2/17.
 */
public class ThumbnailScraper {
    private String videoID;

    ThumbnailScraper(String videoID) {

        this.videoID = videoID;
    }

    byte[] getThumbnail() throws IOException {
        String thumbUrl = "https://i.ytimg.com/vi/" + videoID + "/hqdefault.jpg";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(thumbUrl)
                .get()
                .build();
        Response response = client.newCall(request).execute();
        InputStream inputStream = response.body().byteStream();
        System.out.println("File downloaded");
        return IOUtils.toByteArray(inputStream);
    }
}
