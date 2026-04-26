package com.jinjin.gymmanagementsystem.service.ipml;

import com.jinjin.gymmanagementsystem.mapper.AdminMapper;
import com.jinjin.gymmanagementsystem.pojo.Admin;
import com.jinjin.gymmanagementsystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author shenjin
 * @date 2026/4/24
 */
@Service
public class AdminServiceIpml implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin adminLogin(Admin admin) {
        return adminMapper.selectAdminByAccountAndPassword(admin);
    }
}
