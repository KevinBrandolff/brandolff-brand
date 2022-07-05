package com.brandolff.msproductmanagement.service.impl;

import com.brandolff.msproductmanagement.dto.ProductImagesDTO;
import com.brandolff.msproductmanagement.entity.ProductImagesEntity;
import com.brandolff.msproductmanagement.repository.ProductImagesRepository;
import com.brandolff.msproductmanagement.repository.ProductRepository;
import com.brandolff.msproductmanagement.service.ProductImagesService;
import com.brandolff.msproductmanagement.util.ManageFiles.ManageFiles;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductImagesServiceImpl implements ProductImagesService {

    private final ProductImagesRepository repository;
    private final ProductRepository productRepository;
    private final ManageFiles manageFiles;

    public ProductImagesServiceImpl( ProductImagesRepository repository, ProductRepository productRepository, ManageFiles manageFiles ) {
        this.repository = repository;
        this.productRepository = productRepository;
        this.manageFiles = manageFiles;
    }

    @Override
    public ProductImagesDTO saveImages( MultipartFile file, Integer id ) {
        String url = manageFiles.saveFiles( file );
        ProductImagesEntity productImagesEntity = ProductImagesEntity.builder().build();
        productImagesEntity.setImageUrl( url );
        productImagesEntity.setProduct( productRepository.findById(id).get() );
        return new ProductImagesDTO( repository.save( productImagesEntity ) );
    }
}
