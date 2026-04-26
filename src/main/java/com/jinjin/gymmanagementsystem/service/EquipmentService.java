package com.jinjin.gymmanagementsystem.service;

import com.jinjin.gymmanagementsystem.pojo.Equipment;

import java.util.List;

/**
 * @author shenjin
 * @date 2026/4/24
 */
public interface EquipmentService {
    Integer selectTotalCount();

    Equipment selectEquipmentByEquipmentId(Integer equipmentId);

    List<Equipment> selectAllEquipment();

    Boolean insertEquipment(Equipment equipment);

    Boolean deleteEquipmentByEquipmentId(Integer equipmentId);

    Boolean updateEquipmentByEquipmentId(Equipment equipment);
}
