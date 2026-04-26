package com.jinjin.gymmanagementsystem.controller;

import com.jinjin.gymmanagementsystem.pojo.Equipment;
import com.jinjin.gymmanagementsystem.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shenjin
 * @date 2026/4/25
 */
@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    @RequestMapping("/selEquipmentList")
    public ResponseEntity<Map<String, Object>> getEquipmentList() {
        List<Equipment> equipments = equipmentService.selectAllEquipment();
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("equipmentList", equipments);
        return ResponseEntity.ok(response);
    }

    @RequestMapping("/queryEquipment/{equipmentId}")
    public ResponseEntity<Map<String, Object>> getEquipmentByEquipmentId(@PathVariable Integer equipmentId) {
        Equipment equipment = equipmentService.selectEquipmentByEquipmentId(equipmentId);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("equipment", equipment);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/addEquipment")
    public ResponseEntity<Map<String, Object>> addEquipment(@RequestBody Equipment equipment) {
        equipmentService.insertEquipment(equipment);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("equipment", equipment);
        response.put("message", "Equipment added successfully");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deleteEquipment/{equipmentId}")
    public ResponseEntity<Map<String, Object>> deleteEquipment(@PathVariable Integer equipmentId) {
        equipmentService.deleteEquipmentByEquipmentId(equipmentId);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Equipment deleted successfully");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updateEquipment")
    public ResponseEntity<Map<String, Object>> updateEquipment(@RequestBody Equipment equipment) {
        equipmentService.updateEquipmentByEquipmentId(equipment);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("equipment", equipment);
        response.put("message", "Equipment updated successfully");
        return ResponseEntity.ok(response);
    }
}
