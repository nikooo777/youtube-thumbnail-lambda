import com.lbry.youtube.thumbnail.ThumbnailFetchException;
import com.lbry.youtube.thumbnail.ThumbnailScraper;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by niko on 3/3/17.
 */
public class ThumbnailScraperTest {
    @Test
    public void downloadTest() {
        ThumbnailScraper thumbnailScraper = new ThumbnailScraper("DjouYBEkQPY");
        assertNotNull(thumbnailScraper);
        try {
            thumbnailScraper.getThumbnail();
        } catch (IOException e) {
            throw new Error(e.getMessage());
        }
    }

    @Test
    public void downloadFailureTest() {
        ThumbnailScraper thumbnailScraper = new ThumbnailScraper("123");
        assertNotNull(thumbnailScraper);
        Exception storedException = null;
        try {
            thumbnailScraper.getThumbnail();
            throw new Error("We should not be here");
        } catch (IOException | ThumbnailFetchException e) {
            storedException = e;
        }
        assertNotNull(storedException);
    }
}
