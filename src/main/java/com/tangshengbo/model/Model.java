package com.tangshengbo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by tangshengbo on 2017/1/5.
 */
@Data
@Log4j
@AllArgsConstructor
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "model", locations = "classpath:/model/model.properties")
public class Model {

    private String  id;

    private String name;

    private LocalTime time;

    private LocalDate date;

    private String password;

    private Integer status;

    private String descn;

}
