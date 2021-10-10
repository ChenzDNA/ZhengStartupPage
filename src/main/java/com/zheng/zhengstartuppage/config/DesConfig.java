package com.zheng.zhengstartuppage.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author : 陈征
 * @date : 2021-10-10 11:56
 */

@Component
@ConfigurationProperties(prefix = "des")
public class DesConfig {
    @Setter
    @Getter
    public byte[] key;
}
