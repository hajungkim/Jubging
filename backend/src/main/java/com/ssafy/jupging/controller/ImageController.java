package com.ssafy.jupging.controller;

import com.ssafy.jupging.dto.ImageUploadResponseDto;
import com.ssafy.jupging.service.S3Uploader;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RequiredArgsConstructor
@RestController
@RequestMapping("/images")
public class ImageController {

    private final S3Uploader s3Uploader;

    @ApiOperation(value = "이미지 업로드", notes = "이미지 url, key 반환", response = ControllerResponse.class)
    @PostMapping
    public ControllerResponse uploadImage(@RequestParam("file")MultipartFile multipartFile) throws IOException {
        ControllerResponse response = null;

        try {
            String imgUrl = s3Uploader.upload(multipartFile, "static");

            response = new ControllerResponse("success", imgUrl);
        } catch (Exception e) {
            response = new ControllerResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "url로 이미지 삭제", notes = "성공 시 '이미지 삭제 성공' 반환", response = ControllerResponse.class)
    @DeleteMapping
    public ControllerResponse deleteImage(@RequestParam String imgUrl) {
        ControllerResponse response = null;

        try {
            String[] imgUrlArr = imgUrl.split("#");
            for (String url : imgUrlArr) {
                String[] urlArr = url.split("amazonaws.com/");
                String key = urlArr[1];
                s3Uploader.delete(key);
            }
            response = new ControllerResponse("success", "이미지 삭제 성공");
        } catch (Exception e) {
            response = new ControllerResponse("fail", e.getMessage());
        }
        return response;
    }


}
