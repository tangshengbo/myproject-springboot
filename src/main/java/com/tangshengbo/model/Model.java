package com.tangshengbo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by tangshengbo on 2017/1/5.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Model {

    private String  id;

    private String name;

    private Date  date;

}
