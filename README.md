# 客户端代码自动生成

- 使用jdk自带wsimport命令生成
```
wsimport -p [指定路径，不指定默认在打开目录下] -keep [保存生成的.java文件] -encoding [指定生成代码字符集：utf-8] -verbose [显示详细信息] [wsdl文件访问地址]
```

- example

```
                                        
```

# 服务调用测试类

```
springboot-cxf-client模块下的src/test/com/zhq/cxf/test
```

# 调用服务xml

- 可以直接通过postman进行测试

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:user="http://cxf.zhq.com/ws/userPairing">
   <soapenv:Header>
   	<auth>
     <username>李四</username>
   	 <password>11223</password>
   	</auth>
   </soapenv:Header>
   <soapenv:Body>
      <user:getAppropriateUser>
         <!--Optional:-->
         <userInfo>
            <!--Optional:-->
            <addr>上海市翻斗大街翻斗花园二号楼1001室</addr>
            <age>25</age>
            <height>180</height>
            <!--Optional:-->
            <name>张三</name>
            <!--Optional:-->
            <national>汉族</national>
            <!--Optional:-->
            <sex>男</sex>
            <weight>140</weight>
         </userInfo>
         <minAge>18</minAge>
         <maxAge>28</maxAge>
      </user:getAppropriateUser>
   </soapenv:Body>
</soapenv:Envelope>
```


在实际对接WebService接口的时候，遇到了许多问题，这个项目上面就不演示了，毕竟这只是个demo。不过我会在下面简单说明一下我遇到的问题。
我做的对接稍微有点麻烦，而且对于WebService我完全是新手，所以对接的时候也是麻烦重重。简单来说就是有这么一个调用流程：A系统调用B系统，B系统再去调用C系统，从C系统获取到的结果再返回A系统
原来的B系统现在停运了，而我需要做的就是代替B系统，让A系统除了修改调用地址外，什么都不用修改。而我手上的资料有B系统的wsdl文档、C系统的wsdl文档、A系统调用B系统的demo和B系统调用C系统的demo
也就是说我现在需要根据这些资料还原出B系统的服务，这就是项目背景了

首先就是根据B系统的wsdl文档大致搭出服务结构，把服务先发布起来。然后尝试用demo调用，遇到问题是必然的，让我们来看下
第一步就遇到了大问题，B系统的wsdl文档结构是这样描述的，type里面2个schema，xSchema里面用到了ySchema定义的类型，上来就来个我无法理解的问题，真让人头秃。
没办法啊，还能不管了吗，然后就是网上疯狂查资料，加上我的猜测，大概猜出来了参数类型
在定义服务方法的时候，有个@WebParam的注解，它里面有个targetNamespace参数，填写的就是xSchema指向的地址，然后怎么引用ySchema呢，重新定义一个Bean，作为入参类型

```java
// 服务方法

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

public Results getAppropriateUser(@WebParam(name = "inParam", targetNameSpace = "http://xxxxxxx") Param inParam){
    
}

// 参数类型Bean
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "http://yyyyyyyy")
public class User {
    
}
```
这样就基本能做到在xSchema中引用ySchema定义的类型了，然后把入参什么都改好，启动服务
服务启动异常：2 counts of IllegalAnnotationExceptions  
Two classes have the same XML type name "{http://xxxxxxx}createProcessResponse". Use @XmlType.name and @XmlType.namespace to assign different names to them.
网上查资料大概理解了这个问题，就是项目在生成wsdl文档的时候，服务中的方法会给你生成一个名字，入参就是方法名，出参就是方法名+Response
这个跟你定义的返回值名称冲突了，就是@WebResult中的name和@WebParam中的name和方法名冲突（原本上面的参数类型Bean如果没有指定其他的namespace的话，name也会冲突的，但是现在已经是不同的namespace了，就不存在冲突的问题了，不确定奥，我猜的）
解决办法就是修改方法名或者修改生成的名字，当然我当时冲突不是因为这个，是因为没有添加ySchema，后来是添加了不同的namespace问题就自动解决了

然后是服务启动成功了，尝试调用
然而我做的项目有个比较坑的地方，就是它参数的内部嵌套了好几层（A包含了B，B包含了C，C内部是个字符串），在我把参数都填好服务也发布的情况下，尝试调用的时候提示如下：
意外的元素（uri:"http://xxxxxxx", inParam）。所需元素为<{}inParam>
这个错误意思是入参inParam指定了uri，也就是namespace，但是服务端接收参数却没有指定，解决办法是在参数类型嵌套的地方添加@XmlElement注解，配置namespace即可

```java
public class User{
	@XmlElement(namespace = "http://yyyyyy")
	private Car car;
}
```

上面解决之后，再次调用
这次报错是：Unexpected subelement outParam
英文版的意外的元素，我这里是忘了在服务方法的@WebResult中添加namespace了，

```java
@WebMethod
@WebResult(name = "outParam", targetNamespace = "http://xxxxxxx")
public Results getAppropriateUser(@WebParam(name = "inParam", targetNameSpace = "http://xxxxxxx") Param inParam){
    
}
```
添加上之后成功调用，一波三折终于解决了，太累了。。。。

