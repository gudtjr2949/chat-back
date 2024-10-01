package com.example.chatnormal.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@NoArgsConstructor
@Configuration
public class ParameterStoreProperties {

    @Value("${db_url}")
    private String dbUrl;

    @Value("${db_username}")
    private String dbUserName;

    @Value("${db_password}")
    private String dbPassword;
}