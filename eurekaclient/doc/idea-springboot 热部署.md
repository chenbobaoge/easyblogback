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