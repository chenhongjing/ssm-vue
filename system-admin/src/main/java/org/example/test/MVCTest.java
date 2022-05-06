package org.example.test;

import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * 使用Spring测试模块提供的测试请求功能
 * （用postman也行）
 */
@ExtendWith(SpringExtension.class)
// 红下划线但是没有错
@ContextConfiguration(locations={"classpath:applicationContext.xml", "file:web/WEB-INF/dispatcherServlet-servlet.xml"})
@WebAppConfiguration
public class MVCTest {
    // 传入SpringMVC的ioc
    @Autowired
    WebApplicationContext context;
    // 虚拟mvc请求，获取到处理结果
    MockMvc mockMvc;

    @BeforeEach
    public void initMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testPage() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.
                get("/emps").param("pageNum", "1")).andReturn();
        // 取出请求域中的pageInfo进行验证
        MockHttpServletRequest request = result.getRequest();
        PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
        System.out.println("Current Page:" + pageInfo.getPageNum());
        System.out.println("Total:" + pageInfo.getTotal());

    }
}
