package com.iade.mobilemechanics.controllers;

import com.iade.mobilemechanics.models.Brand;
import com.iade.mobilemechanics.models.exceptions.AlreadyExistsException;
import com.iade.mobilemechanics.models.exceptions.NotFoundException;
import com.iade.mobilemechanics.models.exceptions.Response;
import com.iade.mobilemechanics.models.repositories.BrandRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/brands")
public class BrandController {
    private final Logger logger = LoggerFactory.getLogger(BrandController.class);

    @Autowired
    private BrandRepository brandRepository;

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Brand> getBrands() {
        logger.info("Send all Brands to Request");
        return brandRepository.findAll();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Brand getBrand(@PathVariable int id) {
        logger.info("Send Brand with id " + id + "to Request");
        Optional<Brand> _brand = brandRepository.findById(id);
        if (!_brand.isPresent()) throw
                new NotFoundException("" + id, "Brand", "id");
        else
            return _brand.get();
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Brand saveBrand(@RequestBody Brand brand) {
        Optional<Brand> _brand = brandRepository.findByBrandName(brand.getBrandName());
        if (_brand.isPresent()) {
            throw new AlreadyExistsException(brand.getBrandName(), "Brand", "name ");
        }
        Brand saveBrand = brandRepository.save(brand);
        logger.info("Save Brand id " + saveBrand.getId() + " to Database");
        return saveBrand;
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteBrand(@PathVariable int id) {
        logger.info("Delete Brand with id " + id + "to Request");
        Optional<Brand> _brand = brandRepository.findById(id);
        if (!_brand.isPresent()) throw
                new NotFoundException("" + id, "Brand", "id");
        else {
            brandRepository.deleteById(id);
            return "Deleted";
        }
    }

}
