package com.linesteams.linesapiserver.lines.service;

import com.linesteams.linesapiserver.client.service.NcpService;
import com.linesteams.linesapiserver.lines.domain.Image;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {
    private final NcpService ncpService;

    public ImageService(NcpService ncpService) {
        this.ncpService = ncpService;
    }

    @SneakyThrows
    public Image createImage(MultipartFile image) {
        String fileKey = ncpService.upload(image);
        return new Image(fileKey);
    }

}
