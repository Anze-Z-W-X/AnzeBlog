package com.anze.service;

import com.anze.domain.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

    ResponseResult uploadimg(MultipartFile img);
}
