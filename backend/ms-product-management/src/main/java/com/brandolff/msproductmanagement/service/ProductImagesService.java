package com.brandolff.msproductmanagement.service;

import com.brandolff.msproductmanagement.dto.ProductImagesDTO;
import org.springframework.web.multipart.MultipartFile;

public interface ProductImagesService{
    ProductImagesDTO saveImages( MultipartFile file, Integer id );
}
