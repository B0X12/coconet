package com.coconet.server.repository;

import com.coconet.server.entity.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageDataRepository extends JpaRepository<ImageData, Integer> {

    ImageData findByNum(int num);

}