package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 饼图的数据
 * @author: cangxiamy
 * @date: 2022.05.12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PieChartData {
    Long value;
    String name;
}
