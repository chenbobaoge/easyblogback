##引入热加载的插件，springboot 1.3开始就有的

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <optional>true</optional>
</dependency>
```

##配置
1、点击： file ，Settings ，Build ,Execution,Deplment -->build project auto
2、组合键：Shift+ALT+Ctrl+/ ，选择“Registry”，回车，找到“complier.automake.allow.when.app.running” 
3.设置完一定要重启。

4会报错

org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'org.springframework.cloud.netflix.eureka.EurekaClientAutoConfiguration': Unexpected exception during bean creation; nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'org.springframework.core.env.ConfigurableEnvironment' available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {}
只在idea里面运行才报错
打包成jar没有问题