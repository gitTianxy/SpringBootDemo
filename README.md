# SpringBootDemo

---
### CONTENT
I. project structure / core components of Spring Boot project
II. building blocks of Spring Boot
III. main applications of Spring Boot

---
## II. building blocks of Spring Boot

### [Actuator -- build-in health indicator of Spring Boot](http://javabeat.net/spring-boot-actuator/)
#### * about monitoring
    - 指标: CPU的利用率、内存的利用率、数据库连接是否正常, 以及在给定时间段内有多少客户请求
    - 展示: 图表、控制面板
> 如果给定一个具体的时间，我们希望知道此时CPU的利用率、内存的利用率、数据库连接是否正常以及在给定时间段内有多少客户请求等指标；不仅如此，
我们希望通过图表、控制面板来展示上述信息。最重要的是：老板和业务人员希望看到的是图表，这些比较直观易懂。

#### * HTTP Endpoints
* build-in endpoints
    - /env: 提供应用程序环境变量的运行时取值--有可能在运行过程中被覆盖而异于配置文件值; actuator同时还提供具体指的查询接口, 
    如http://localhost:8080/env/book.counter.rate查询book.counter.rate的取值
    - /info: 显示应用程序的基本描述，属性值来自appliaction.properties--其中任何以info.开头的属性.
    - /metrics: Metrics about memory, heap, etc. for the currently running application
    - /health: Health of the application.
    - /dump : Performs a thread dump.
    - /mappings : Displays a list of all @RequestMapping paths.
    - /beans : This endpoint lists all the beans loaded by the application.
    - ...
> Enable/disable endpoint: In the above list, only few are enabled by default for the security reasons. 
Developers can override the properties to enable them. 

* self-defined endpoint: 
    - Spring Boot allows defining self endpoint, through implements and configure XXXIndicator,XXXAggregator, etc. ...

* define path of http endpoints -- in application.properties/yaml:
    - 访问端口: 还可以通过设置management.port=-1关闭endpoint的HTTP访问接口，或者是设置其他的端口
    - 访问ip限制: 设置仅仅让本地访问，只需要设置management.address=127.0.0.1
    - 访问根路径: management.context-path=/admin

```yaml
# application.yaml
management:  
  port: 54001  
  health:  
    mail:  
      enabled: false  
```
