* 2019.08.02
> 1、编译器简单地将“ foreach” 循环翻译为带有迭代器的循环。 "for each” 循环可以与任何实现了Iterable接口(该接口仅有一个返回Iterator对象的抽象方法)的对象一起工作。Collection接口扩展了 Iterable接口。因此， 对于标准类库中的任何集合都可以使用“ for each” 循环。  
> 2、如果调用remove之前没有调用next将是不合法的。如果这样做，将会抛出一个IllegalStateException异常。如果想删除两个相邻的元素，不能直接地这样调用: 
```java
it.next();
it.remove();
it.remove(); // Error!
```  
必须先调用 next 越过将要删除的元素。  

> 3、Hashtable散列表由链表数组实现，每个列表被称为桶(bucket)。散列表为每个对象计算一个整数，称为散列码(hashcode。)散列码是由对象的实例域产生的一个整数。
`hashcode() % 桶的总数 = 保存这个元素的桶的索引` 在插入时，如果这个索引对应的位置没有元素，那么可以直接将元素放进去，如果出现了散列冲突，需要使用对应的解决方法重新计算该元素存放的桶位置。  

> 4、视图：keySet()方法返回一个实现Set接口的类对象，这个类的方法对原映射进行操作。这种集合称为视图。  

* 2019.08.01 
> 1、以下的配置项和pom依赖的作用分别是:
```
management.security.enabled=false		#actuator是否需要安全保证
management.endpoints.web.exposure.include=*	 加载所有的端点/默认只加载了/info / health 

spring-boot-actuator是一个spring-boot提供的用于监控组件，只需要在代码中加入依赖就可以了
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

```
spring.jmx.enabled=false  是否将管理bean公开给JMX域。Java管理扩展(JMX)提供了一种监视和管理应用程序的标准机制，默认情况下，Spring Boot将管理端点公开为org.springframework.boot域中的JMX mbean。
management.endpoints.jmx.exposure.exclude=*  不希望在JMX上公开的端点
```