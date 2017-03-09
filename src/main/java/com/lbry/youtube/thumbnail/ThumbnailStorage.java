package com.lbry.youtube.thumbnail;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;

import java.io.ByteArrayInputStream;

/**
 * Created by niko on 3/4/17.
 */
public class ThumbnailStorage {
    public static void persist(byte[] thumbnail, String videoID) {
        String accessKey = System.getenv("s3_access_key");
        String secretKey = System.getenv("s3_secret_key");
        String bucketName = System.getenv("s3_bucket_name");
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 client = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2).withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
        Long contentLength = (long) thumbnail.length;
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(contentLength);
        client.putObject(
                new PutObjectRequest(
                        bucketName,
                        "thumbnails/" + videoID,
                        new ByteArrayInputStream(thumbnail),
                        metadata
                ).withCannedAcl(CannedAccessControlList.PublicRead)
        );
    }
}
