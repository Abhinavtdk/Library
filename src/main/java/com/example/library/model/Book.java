package com.example.library.model;

import com.example.library.model.accessInfo.AccessInfo;
import com.example.library.model.layerInfo.LayerInfo;
import com.example.library.model.salesInfo.SaleInfo;
import com.example.library.model.volumeInfo.VolumeInfo;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {
    private String kind;
    private String id;
    private String etag;
    private String selfLink;
    private VolumeInfo volumeInfo;
    private LayerInfo layerInfo;
    private SaleInfo saleInfo;
    private AccessInfo accessInfo;
}
