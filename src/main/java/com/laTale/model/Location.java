package com.laTale.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Desc:
 * @Author: 泽露
 * @Date: 2023/2/14 7:19 PM
 * @Version: 1.initial version; 2023/2/14 7:19 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location implements Serializable {


    private static final long serialVersionUID = -683370964673661228L;
    public int x;
    public int y;


    public boolean isFind(){
        return x != 0 && y != 0;
    }
}
