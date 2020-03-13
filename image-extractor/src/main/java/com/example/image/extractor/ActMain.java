package com.example.image.extractor;

import java.io.File;
import java.io.IOException;

import com.drew.imaging.ImageProcessingException;
import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.mp4.Mp4MetadataReader;
import com.drew.metadata.Metadata;

public class ActMain {

    public static void main(String[] args) {
        try {
            Metadata metadata = JpegMetadataReader.readMetadata(new File("/Users/chuan/Desktop/2.jpeg"));
            metadata.getDirectories().iterator()
                    .forEachRemaining(v -> v.getTags().parallelStream().forEach(System.out::println));

            System.out.println("--------");
            metadata = Mp4MetadataReader.readMetadata(new File("/Users/chuan/Desktop/2.mp4"));
            metadata.getDirectories().iterator()
                    .forEachRemaining(v -> v.getTags().parallelStream().forEach(System.out::println));
        } catch (IOException | ImageProcessingException e) {
            e.printStackTrace();
        }
    }

}
