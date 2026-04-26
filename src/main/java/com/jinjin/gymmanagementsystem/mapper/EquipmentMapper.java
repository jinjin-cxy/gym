package com.jinjin.gymmanagementsystem.mapper;

import com.jinjin.gymmanagementsystem.pojo.Equipment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author shenjin
 * @date 2026/4/25
 */
@Mapper
public interface EquipmentMapper {
    Integer selectEquipmentCount();

    Equipment selectEquipmentByEquipmentId(Integer equipmentId);

    Boolean insertEquipment(Equipment equipment);

    List<Equipment> findAll();

    Boolean updateEquipmentByEquipmentId(Equipment equipment);

    Boolean deleteEquipmentByEquipmentId(Integer equipmentId);
}
