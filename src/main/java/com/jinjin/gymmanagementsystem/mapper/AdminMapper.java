package com.jinjin.gymmanagementsystem.mapper;

import com.jinjin.gymmanagementsystem.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author shenjin
 * @date 2026/4/24
 */
@Mapper
public interface AdminMapper {
    Admin selectAdminByAccountAndPassword(Admin admin);
}
