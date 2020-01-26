package sl.demo.dubbo.consumer.service.impl;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;
import sl.demo.dubbo.api.service.provider.DemoService;

@Component
public class DemoServiceImpl {

    @Reference
    private DemoService demoService;

    public String doSayHello(String name) {
        return demoService.sayHello(name);
    }
}
