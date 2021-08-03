package com.ssafy.jupging.controller;

import com.ssafy.jupging.dto.ImageUploadResponseDto;
import com.ssafy.jupging.service.S3Uploader;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController("/images")
public class ImageController {

    private final S3Uploader s3Uploader;

    @ApiOperation(value = "이미지 업로드", notes = "이미지 url, key 반환", response = ControllerResponse.class)
    @PostMapping
    public ControllerResponse uploadImage(@RequestParam("file")MultipartFile multipartFile) throws IOException {
        ControllerResponse response = null;

        try {
            ImageUploadResponseDto responseDto = s3Uploader.upload(multipartFile, "static");

            response = new ControllerResponse("success", responseDto);
        } catch (Exception e) {
            response = new ControllerResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "게시글 이미지 key 저장하기", notes = "", response = ControllerResponse.class)
    @PostMapping("/article")
    public ControllerResponse saveArticle(@RequestParam String key) {
        ControllerResponse response = null;

        try {

        } catch (Exception e) {

        }
        return response;
    }

    @ApiOperation(value = "프로필 이미지 key 저장하기", notes = "", response = ControllerResponse.class)
    @PostMapping("/profile")
    public ControllerResponse saveProfile(@RequestParam String key) {
        ControllerResponse response = null;

        try {

        } catch (Exception e) {

        }
        return response;
    }

    @ApiOperation(value = "key로 이미지 삭제", notes = "성공 시 '이미지 삭제 성공' 반환", response = ControllerResponse.class)
    @DeleteMapping
    public ControllerResponse deleteImage(@RequestParam String key) {
        ControllerResponse response = null;

        try {
            s3Uploader.delete(key);
            response = new ControllerResponse("success", "이미지 삭제 성공");
        } catch (Exception e) {
            response = new ControllerResponse("fail", e.getMessage());
        }
        return response;
    }


}
