package com.jinjin.gymmanagementsystem.service.ipml;

import com.jinjin.gymmanagementsystem.mapper.EquipmentMapper;
import com.jinjin.gymmanagementsystem.pojo.Equipment;
import com.jinjin.gymmanagementsystem.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shenjin
 * @date 2026/4/25
 */
@Service
public class EquipmentServiceIpml implements EquipmentService {
    @Autowired
    private EquipmentMapper equipmentMapper;
    
    @Override
    public Integer selectTotalCount() {
        return equipmentMapper.selectEquipmentCount();
    }

    @Override
    public Equipment selectEquipmentByEquipmentId(Integer equipmentId) {
        return equipmentMapper.selectEquipmentByEquipmentId(equipmentId);
    }

    @Override
    public List<Equipment> selectAllEquipment() {
        return equipmentMapper.findAll();
    }

    @Override
    public Boolean insertEquipment(Equipment equipment) {
        return equipmentMapper.insertEquipment(equipment);
    }

    @Override
    public Boolean deleteEquipmentByEquipmentId(Integer equipmentId) {
        return equipmentMapper.deleteEquipmentByEquipmentId(equipmentId);
    }

    @Override
    public Boolean updateEquipmentByEquipmentId(Equipment equipment) {
        return equipmentMapper.updateEquipmentByEquipmentId(equipment);
    }
}
