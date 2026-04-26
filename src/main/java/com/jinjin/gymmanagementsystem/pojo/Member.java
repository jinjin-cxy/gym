package com.jinjin.gymmanagementsystem.pojo;

import lombok.Data;

/**
 * @author shenjin
 * @date 2026/4/25
 */
@Data
public class Member {
    private Integer memberAccount;
    private String memberPassword;
    private String memberName;
    private String memberGender;
    private Integer memberAge;
    private Integer memberHeight;
    private Integer memberWeight;
    private Long memberPhone;
    private String cardTime;
    private Integer cardClass;
    private Integer cardNextClass;
}
