package com.coconet.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageData {

    @Id
    private int num;

    @NotNull
    private String fileName;

    @NotNull
    private long fileSize;

    @NotNull
    private String filePath;

    @NotNull
    private String fileExtension;
}