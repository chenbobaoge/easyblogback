jpa 自动注入autowired报错  
1.Could not autowire

File-Project Structure 页面 Facets下删掉 Spring(直接右键Delete)

settings -> inspections -> spring -> spring core -> code -> autowiring for bean class，修改成黄色 warning 



2.Consider defining a bean of type 'com.bobochen.easyblogback.dao.EasyblogDao' in your configuration.


原因 SpringBootApplication注解只会找相同包下面有没有合适的注入对象。
解决办法：
1 .将接口与对应的实现类放在与application启动类的同一个目录或者他的子目录下，这样注解可以被扫描到，这是最省事的办法 
2 .在指定的application类上加上这么一行注解，手动指定application类要扫描哪些包下的注解
第一种方法可能比较好使，第二种貌似还是有问题

3.Failed to bind properties under 'spring.datasource.druid' to com.alibaba.druid.pool.DruidDataSource:

按代码编写DruidDataSource


4.Error creating bean with name 'easyblogDao': Invocation of init method failed; nested exception is java.lang.IllegalArgumentException: Not a managed type: class com.bobochen.easyblogback.entity.Easyblog
在实体类上加注解@Entity


5.Table 'easyblog.hibernate_sequence' doesn't exist

主键上加 @GeneratedValue(strategy = GenerationType.IDENTITY)
加 @GeneratedValue(strategy = GenerationType.AUTO) 或者 @GeneratedValue 都不行

6.org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'org.springframework.cloud.netflix.eureka.EurekaClientAutoConfiguration': Unsatisfied dependency expressed through constructor parameter 0; nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'org.springframework.core.env.ConfigurableEnvironment' available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {}
原因不明，但暂时感觉没什么问题


7.[ERROR] Failed to execute goal on project eurekaserver: Could not resolve dependencies for project com.bobochen:eurekaserver:jar:0.0.1-SNAPSHOT: The following artifacts could not be resolved: org.springframework.cloud:spring-cloud-starter-netflix-eureka-server:
  jar:2.0.1.BUILD-SNAPSHOT, org.springframework.cloud:spring-cloud-starter:jar:2.0.1.BUILD-SNAPSHOT, org.springframework.cloud:spring-cloud-context:jar:2.0.1.BUILD-SNAPSHOT, org.springframework.cloud:spring-cloud-commons:jar:2.0.1.BUILD-SNAPSHOT, org.springframew
  ork.cloud:spring-cloud-netflix-eureka-server:jar:2.0.1.BUILD-SNAPSHOT, org.springframework.cloud:spring-cloud-netflix-core:jar:2.0.1.BUILD-SNAPSHOT, org.springframework.cloud:spring-cloud-netflix-eureka-client:jar:2.0.1.BUILD-SNAPSHOT, org.springframework.cloud
  :spring-cloud-starter-netflix-archaius:jar:2.0.1.BUILD-SNAPSHOT, org.springframework.cloud:spring-cloud-netflix-ribbon:jar:2.0.1.BUILD-SNAPSHOT, org.springframework.cloud:spring-cloud-netflix-archaius:jar:2.0.1.BUILD-SNAPSHOT, org.springframework.cloud:spring-c
  loud-starter-netflix-ribbon:jar:2.0.1.BUILD-SNAPSHOT: Could not find artifact org.springframework.cloud:spring-cloud-starter-netflix-eureka-server:jar:2.0.1.BUILD-20180704.141040-135 in spring-snapshots (https://repo.spring.io/snapshot), try downloading from ht
  tps://github.com/spring-cloud -> [Help 1]
  [ERROR]
  [ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
  [ERROR] Re-run Maven using the -X switch to enable full debug logging.
  [ERROR]
  [ERROR] For more information about the errors and possible solutions, please read the following articles:
  [ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/DependencyResolutionException

原因：maven 文件丢失
解决办法：
1.maven 执行清理命令  mvn clean install -U
第一步完了之后貌似还是不行。
把mavnen仓库全部删掉，重新考了一个settings.xml到maven目录下，重新下包，重启之后，才正常。
