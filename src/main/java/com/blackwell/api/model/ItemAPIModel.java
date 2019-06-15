package com.blackwell.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemAPIModel {
    private String id;
    private VolumeInfoModel volumeInfo;
}
