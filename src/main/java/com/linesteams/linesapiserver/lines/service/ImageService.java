package com.linesteams.linesapiserver.lines.service;

import com.linesteams.linesapiserver.lines.domain.Image;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {

    public Image createImage(MultipartFile image) {
        // TODO NCP 연동해서 파일 올리고 해당 path를 리턴할 수 있도록 처리
        return new Image("temp");
    }

}
