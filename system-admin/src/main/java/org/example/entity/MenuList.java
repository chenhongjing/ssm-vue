package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description: 菜单列表（项），包含多级菜单项
 * @author: cangxiamy
 * @date: 2022.05.02
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuList {
    private Menu current;
    private List<Menu> children;
}
