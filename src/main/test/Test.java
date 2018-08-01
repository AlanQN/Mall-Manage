import cn.edu.scau.controller.OrderController;
import cn.edu.scau.service.IOrderService;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring-beans.xml", "classpath:mybatis-config.xml" })
public class Test {


    @Autowired
    private IOrderService orderService;
    @Autowired
    private OrderController orderController;

    @org.junit.Test
    public void testOrderShipping(){

        orderService.getOrderInfoById(0,2,2);

    }

    @org.junit.Test
    public void testDelete(){
        Integer[] ids = {13,14};
        orderService.delete(ids);
    }
}
