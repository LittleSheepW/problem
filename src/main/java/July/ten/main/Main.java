package July.ten.main;

import July.ten.pojo.Father;
import July.ten.pojo.Son;
import July.ten.service.Service;
import July.ten.service.impl.ServiceImpl;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;

/**
 * @author: Sun
 * @create: 2019-07-10 10:15
 * @version: v1.0
 */
@Slf4j
public class Main {
    public static void main(String[] args) {

        /**
         * <p>子类 instance 父类是否为true问题</p>
         */
        Son son = new Son();
        boolean b1 = son instanceof Father;
        log.info("[子类 instanceof 父类] [result:{}]", b1);

        Father father = new Father();
        boolean b2 = father instanceof Son;
        log.info("[父类 instanceof 子类] [result:{}]", b2);

        Service service = new ServiceImpl();
        boolean b3 = service instanceof Service;
        log.info("[实现类 instanceof 接口] [result:{}]", b3);
    }
}
