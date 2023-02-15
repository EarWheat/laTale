package com.laTale.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @Desc:
 * @Author: 泽露
 * @Date: 2023/2/14 7:19 PM
 * @Version: 1.initial version; 2023/2/14 7:19 PM
 */
@Data
@AllArgsConstructor
public class Location implements Serializable {


    private static final long serialVersionUID = -683370964673661228L;
    public int x;
    public int y;
}
