package com.linesteams.linesapiserver.client.service;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.linesteams.linesapiserver.config.AppConfig;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class NcpService {

    private final AmazonS3 amazonS3;
    private final AppConfig appConfig;

    public NcpService(AmazonS3 amazonS3, AppConfig appConfig) {
        this.amazonS3 = amazonS3;
        this.appConfig = appConfig;
    }

    @SneakyThrows
    public String upload(MultipartFile multipartFile) {
        String fileKey = UUID.randomUUID().toString();
        try {
            ObjectMetadata objectMetadata = getObjectMetadata(multipartFile);
            amazonS3.putObject(appConfig.getNcp().getImageBucketName(), fileKey, multipartFile.getInputStream(), objectMetadata);
        } catch (SdkClientException e) {
            e.printStackTrace();
        }
        return fileKey;
    }

    private ObjectMetadata getObjectMetadata(MultipartFile multipartFile) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(multipartFile.getSize());
        objectMetadata.setContentDisposition("attachment; filename=\"" + multipartFile.getOriginalFilename() + "\"");
        return objectMetadata;
    }
}
