package com.jinjin.gymmanagementsystem.pojo;

import lombok.Data;

/**
 * @author shenjin
 * @date 2026/4/25
 */
@Data
public class Equipment {
    private Integer equipmentId;
    private String equipmentName;
    private String equipmentLocation;
    private String equipmentStatus;
    private String equipmentMessage;
}
