# problem
记录自己每日的问题code，以便复盘  

* 2019.07.09  
> 1、子类 instanceof 父类问题？  
>> instanceof关键字用于判断前面的对象是否是后面的类，或者其子类、实现类的实例。也就是说：使用instanceof关键字做判断时， instanceof 操作符的左右操作数必须有继承或实现关系。  

* 2019.07.10
> 1、为什么有常量还要有枚举类？
>> 先说一下两者的区别  
    1、常量就是一个值 ，枚举是一组不变的值  
    2、枚举是自己定义后再使用，有一定的约束，常量可以随便定义  
    3、枚举传入的值是固定的值不会出错，常量是一个值，传递的参数是任意的  
    4、限制用户不能随意赋值，只能在列举的值中选择  
   枚举的优点：
   1、枚举可以使代码更易于维护，有助于确保给变量指定合法的、期望的值  
   2、枚举使代码更清晰，允许用描述性的名称表示整数值，而不是用含义模糊的数来表示    
   3、枚举可以给出状态码的情况下再给出描述信息，常量类是做不到的    
   推荐枚举类型的使用，可以让代码结构更加清晰易懂可扩展。而常量类可以收集管理一些比较杂的一些常量。而接口的中的常量，在遵循开闭原则的基础上，向上抽象管理自己的内聚的常量。(不建议在接口中定义常量。如果某个实现了常量接口的类被修改不再需要常量了，也会因为序列化兼容原因不得不保持该实现，而且非final类实现常量接口会导致所有子类被污染)

> 2、一方库、一方库、一方库都指的是什么？
>>  一方库：本工程中的各模块的相互依赖  
  二方库：公司内部的依赖库，一般指公司内部的其他项目发布的jar包  
  三方库：公司之外的开源库， 比如apache、ibm、google等发布的依赖  
  
> 3、Objects.equals()方法传入两个对象会走第一个对象重equals方法还是Object类的equals方法？
>> 如果第一个对象重写了equals方法，走的就是对象的equals方法，如果没有重写，走Object类中的equals方法。  

> 4、Integer.parseInt()和Integer.parseInt()的区别是什么?  
>> parseInt()返回的为int类型，valueOf()返回的是Integer类型  

* 2019.07.11
> 1、JPA Example是否可以进行and查询？  
>> 可以的，直接在实体当中为多个域进行赋值即可。  

> 2、工具类是单例的吗？比如我有一个工具类是用来上传文件的，那如果他是单例的，如果第一个任务在使用这个工具类，那么第二个任务呢？  
>> 单例并非同一时间段只能被一个任务使用。其次工具类也不能说是单例的，因为工具类中的所有方法都是静态的，根本不涉及对象。而单例模式是针对于对象来说的。

> 3、Executors中submit()和execute()的区别?  
>> (1)submit()方法有返回值，  
   (2)使用submit()方法可以让调用者感知线程中的异常。  
    其实submit()是先构造出一个RunnableFuture(FutureTask) 然后再去调用execute方法。不管你submit的时候传入的是Runnable还是Callable，  
最后RunnableFuture(FutureTask)里面都会生成Callable对象。任务调用的时候调用RunnableFuture(FutureTask)的run方法，run方法调用Callable对象的call方法。

* 2019.07.15  
> 1、线程池如何在web项目中正确使用？  
>> 定义一个全局的静态变量  
    eg: `static final ExecutorService executors = Executors.newCachedThreadPool();`  
    当有请求过来需要处理的时候：`executors.submit(new StudentCallable);`  
    该线程池不要shutdown，线程池跟随整个服务的生命周期。  

> 2、Java各类对象的作用？  
>> • DO(Data Object):此对象与数据库表结构一一对应，通过 DAO 层向上传输数据源对象。  
   • DTO(Data Transfer Object):数据传输对象，Service 或 Manager 向外传输的对象。  
   • BO(Business Object):业务对象，由 Service 层输出的封装业务逻辑的对象。  
   • AO(Application Object):应用对象，在 Web 层与 Service 层之间抽象的复用对象模型，极为贴近展示层，复用度不高。  
   • VO(View Object):显示层对象，通常是 Web 向模板渲染引擎层传输的对象。  
   • Query:数据查询对象，各层接收上层的查询请求。注意超过 2 个参数的查询封装，禁止使用 Map 类来传输。  
     
![](pic/对象分类.png)

* 2019.07.16
> 1、集合完成初始化后，分配内存空间了吗？  
>> ArrayList集合指定初始容量进行初始化的时候内部会new出一个指定初始容量的数组出来，但是我们为什么new完了不add元素，进行get元素的时候会数组越界呢？  
这是因为在ArrayList中有一个size的原因，这个size代表的是数组的当前非空长度。初始化的时候虽然new出指定 初始容量的数组，但是并没有将初始容量赋值给size。所有get的时候会抛出异常。 最终结论：集合完成初始化后是分配了内存空间的。  

> 2、数组构造完成后有默认值吗？  
>> 有的  

> 3、List类中add()方法和set()方法的区别？  
>> add()方法为集合添加元素，set()方法为修改集合中已存在的元素。  
  
> List类中set()方法可不可以元素为空的地址set值？  
>> set()方法不可以为元素为空的地址set值，会抛出IndexOutOfBoundsException   
 
> 4、包装对象可以二次赋值吗？  
>>  不可以，因为包装对象为不可变对象。二次赋值实际上会改变变量指向的内存空间地址。
 
> 5、为什么可以直接打印集合，数组就不行呢？
>> 因为ArrayList继承AbstractCollection，此类重写了Object类中的toString()方法，而数组并没有重写toString()方法。

> 6、为什么数组不重写toString()方法呢？  
>> 为了节省内存，首先数组也是类 数组类是虚拟机自动生成的 继承自Object 本身没有任何字段和方法 实现了Cloneable和Serializable接口数组和arraylist的区别是 arraylist是一个类 是有限的，而数组理论上是无限的 有int数组 byte数组 string数组 任何类型 都可以对应到几百个数组 一维数组 二维数组到最多255维数组 因此如果虚拟机实现把自动生成的数组类加一个重写后的toString方法 那么如果有大量的不同类型的数组 将会增加大量的内存占用 因为重写的方法的字节码是在虚拟机内存中保存的。  

> 7、不可变对象的优缺点？  
>> 优点:   
(1)构造、测试和使用都很简单  
(2)线程安全且没有同步问题，不需要担心数据会被其它线程修改  
(3)当用作类的属性时不需要保护性拷贝   
(4)可以很好的用作Map键值和Set元素  
缺点:  
(1)不可变对象最大的缺点就是创建对象的开销，因为每一步操作都会产生一个新的对象。  

> 8、如何编写一个不可变类？
>>  (1)确保类不能被继承 - 将类声明为final, 或者使用静态工厂并声明构造器为private  
(2) 声明属性为private 和 final  
(3) 不要提供任何可以修改对象状态的方法(不仅仅是set方法, 还有任何其它可以改变状态的方法)  
(4) 如果类有任何可变对象属性, 那么当它们在类和类的调用者间传递的时候必须被保护性拷贝  

> 9、唯一约束和唯一索引在 MySQL 数据库里区别？  
(1)概念上不同，约束是为了保证数据的完整性，索引是为了辅助查询；    
(2)创建唯一约束时，会自动的创建唯一索引；  
(3)在理论上，不一样，在实际使用时，基本没有区别。  

* 2019.07.17
> 1、获取class对象的三种方式？
![](pic/获取class对象的三种方式.png)

> 2、通过反射创建对象的方式？  
![](pic/反射创建对象.png)

> 3、基本类型或数组类型调用getDeclaredFields()方法会返回一个长度为0的数组  
  
> 4、反射中 getMethods返回当前类中的共有方法和从父类继承的公有方法，getDeclaredMethods返回的只有当前类中的所有方法  
  
> 5、Field类中get与set方法    
![](pic/field类get、set方法.png)  
  
> 6、通过反射扩展泛型数组   
![](pic/使用反射扩展泛型数组.png)   

> 7、继承设计的技巧  
>> (1)将公共操作和域放在超类中   
   (2)不要使用受保护的域(任何人都能够从某个类派生出子类、同包中的类  这两者都可以直接访问protected域，破坏了封装性)  
   (3)当两个类为"is-a"关系时使用继承  
   (4)除非所有继承的方法都有意义，否则不要使用继承  
   (5)在覆盖方法时，不要改变预期的行为   
   (6)不要过多的使用反射，反射可以在运行时查看域和方法，让人们编写更具有通用型的程序。反射对于编写系统程序来说很实用，但是不适合编写应用程序  
  
* 2019.07.18  
> 1、git如何从指定的远程分支pull代码到当前分支？
>> `git pull origin <远程分支名>`  

* 2019.07.19
> 1、如果先在一个接口中将一个方法定义为默认方法，然后又在超类或另一个接口中定义了同样的方法，会发生什么情况?  
>> (1)超类优先，如果超类提供了一个具体方法，同名而且有相同参数类型的默认方法会被忽略。这正式"类优先"原则，类优先规则可以确保与Java SE 7的兼容性。如果为一个接口增加默认方法，这对于有这个默认方法之前能正常工作的代码不会有任何影响。  
   (2)接口冲突，如果一个超接口提供了一个默认方法，另一个接口提供了一个同名而且参数类型(不论是否是默认参数)相同的方法，必须覆盖这个方法来解决冲突。  
   
* 2019.07.22
> 1、Maven scope?  
>> `compile` 默认scope为compile，表示为当前依赖参与项目的编译、测试和运行阶段，属于强依赖。打包时会一块打进去。
   `test` 该依赖仅仅参与测试相关的内容，包括测试用例的编译和执行，比如定性的Junit。  
   `runtime` 依赖仅参与运行周期中的使用。一般这种类库都是接口与实现相分离的类库，比如JDBC类库，在编译之时仅依赖相关的接口，在具体的运行之时，才需要具体的mysql、oracle等等数据的驱动程序。此类的驱动都是为runtime的类库。
   `provided` 该依赖在打包过程中，不需要打进去，这个由运行的环境来提供，比如tomcat或者基础类库等等，事实上，该依赖可以参与编译、测试和运行等周期，与compile等同。区别在于打包阶段进行了exclude操作。    
   `system` 使用上与provided相同，不同之处在于该依赖不从maven仓库中提取，而是从本地文件系统中提取，其会参照systemPath的属性进行提取依赖。  
   `import` 只能在dependencyManagement的中使用，能解决maven单继承问题，import依赖关系实际上并不参与限制依赖关系的传递性。   
> 2、Maven scope import如何理解及使用？  
>>  [YouDao note link](http://note.youdao.com/noteshare?id=91003db7c643c9499b81f49c1c3ea7de)
  
> 3、偶然发现服务在启动时日志当中有这样的信息`Could not safely identify store assignment for repository candidate interface com.kkcode.kkclass.repository.BoughtCourseRepository.`  
>> [答案](https://www.oschina.net/question/574036_2286640)
  
> 4、Comparable 和 Comparator 有什么区别?  
>> (1) Comparable 是排序接口。若一个类实现了Comparable接口，就意味着“该类支持排序”。  
(2)Comparator 是比较器接口。我们若需要控制某个类的次序，而该类本身不支持排序(即没有实现Comparable接口)；那么，我们可以建立一个“该类的比较器”来进行排序。这个“比较器”只需要实现Comparator接口即可。也就是说，我们可以通过“实现Comparator类来新建一个比较器”，然后通过该比较器对类进行排序。
(3)Comparable相当于"内部比较器"，Comparator相当于"外部比较器"。  

> 5、protected修饰符问题(由Object类clone方法引申出该问题，Object类中的clone方法为受保护的权限，我使用一个实例化子类对象无法调用该clone方法)?  
>> 当创建子类对象调用父类的protected成员变量时，必须要注意：子类对象和子类是对应的！当一个包外子类继承保护成员时，该成员在这个子类内实际上变为私有。包外子类有权访问超类成员，它指子类继承该成员，然而这并不意味着包外子类能够使用超类实例的引用访问该成员。  
    
> 6、git使用命令如何删除本次分支和远程分支？  
>> 删除本地分支: `git branch -d <BranchName>`    
   删除远程分支：`git branch -r -d origin/branch-name`    
　　　　　　　`git push origin :branch-name`  

* 2019.07.23  
> 1、git pull提示：  
There is no tracking information for the current branch.  
Please specify which branch you want to merge with.  
See git-pull(1) for details.  
    git pull remote branch  
If you wish to set tracking information for this branch you can do so with:  
    git branch --set-upstream-to=origin/branch master  
>> (1)`git pull <remote> <branch>`  
(2)`git branch --set-upstream-to=origin/branch master`  

> 2、JPA常用注解
>> `@Entity`：用于实体类声明语句之前，指出该Java实体类将映射到指定的数据库表。如声明一个实体类 Customer，它将映射到数据库中的 customer 表上。  
`@Table`：当实体类与其映射的数据库表名不同名时需要使用 @Table 标注说明，该标注与 @Entity 标注并列使用，置于实体类声明语句之前。@Table 标注的常用选项是 name，用于指明数据库的表名，@Table标注还有其他的选项catalog、schema、indexes，分别用于设置表所属的数据库目录、模式(通常为数据库名)、索引。    
`@Id`：用于声明一个实体类的属性映射为数据库的主键列。该属性通常置于属性声明语句之前。
`@GeneratedValue`：用于标注主键的生成策略，通过 strategy 属性指定。默认情况下，JPA 自动选择一个最适合底层数据库的主键生成策略：SqlServer 对应 identity，MySQL 对应 auto increment。 在 javax.persistence.GenerationType 中定义了以下几种可供选择的策略：  
(1)IDENTITY：采用数据库 ID自增长的方式来自增主键字段，Oracle 不支持这种方式；  
(2)AUTO： JPA自动选择合适的策略，是默认选项；  
(3)SEQUENCE：通过序列产生主键，通过 @SequenceGenerator 注解指定序列名，MySql 不支持这种方式;    
(4)TABLE：通过表产生主键，框架借由表模拟序列产生主键，使用该策略可以使应用更易于数据库移植。
`@Lob`：表示属性将被持久化为Blob或者Clob类型, 具体取决于属性的类型：java.sql.Clob, Character[],char[] 和java.lang.String这些类型的属性都被持久化为Clob类型, 而java.sql.Blob,Byte[], byte[] 和 serializable类型则被持久化为Blob类型。  
`@Basic`：表示一个简单的属性到数据库表的字段的映射。  
   fetch: 表示该属性的读取策略,有EAGER和LAZY两种,分别表示主支抓取和延迟加载,默认为EAGER    
   (1)、FetchType.LAZY：懒加载，加载一个实体时，定义懒加载的属性不会马上从数据库中加载。  
   (2)、FetchType.EAGER：急加载，加载一个实体时，定义急加载的属性会立即从数据库中加载。  
   (3)、比方User类有两个属性，name跟address，就像百度知道，登录后用户名是需要显示出来的，此属性用到的几率极大，要马上到数据库查，用急加载;而用户地址大多数情况下不需要显示出来，只有在查看用户资料是才需要显示，需要用了才查数据库，用懒加载就好了。并不是一登录就把用户的所有资料都加载到对象中，于是有了这两种加载模式。       
    optional：定义字段或属性的值是否为null，默认为true   
`@Column`：当实体的属性与其映射的数据库表的列不同名时需要使用@Column 标注说明。  
name:用于设置映射数据库表的列名  
nullable:表示该字段是否允许为null,默认为true  
unique:表示该字段是否是唯一标识,默认为false  
length:表示该字段的大小,仅对String类型的字段有效  
insertable:表示在ORM框架执行插入操作时,该字段是否应出现INSERT语句中,默认为true  
updateable:表示在ORM框架执行更新操作时,该字段是否应该出现在UPDATE语句中,默认为 true。对于一经创建就不可以更改的字段,该属性非常有用,如对于birthday字段  
columnDefinition:表示该字段在数据库中的实际类型。通常ORM框架可以根据属性类型自动判断数据库中字段的类型，但是对于Date类型仍无法确定数据库中字段类型究竟是DATE，TIME还是TIMESTAMP。此外String的默认映射类型为VARCHAR, 如果要将 String 类型映射到特定数据库的 BLOB 或TEXT 字段类型则需要使用该属性。    

> 3、JPA特殊注解
>> `@Transient`：表示该属性并非一个到数据库表的字段的映射，ORM框架将忽略该属性。如果一个属性并非数据库表的字段映射，就务必将其标示为@Transient。否则ORM框架默认其注解为@Basic。  
`@Temporal`：在核心的 Java API 中并没有定义Date类型的精度(temporal precision)。 而在数据库中表示 Date 类型的数据有 DATE, TIME, 和 TIMESTAMP 三种精度(即单纯的日期,时间,或者两者 兼备)。在进行属性映射时可使用@Temporal注解来调整精度。  

> 4、单项关联与双向关联的区别
>> 单向关联是你在A类里面，可以访问到B类数据，但是在B类里面无法访问A类的数据，也就是访问不到A表的数据。  
   双向关联是在B类里也可以访问A类的数据。比如：班级表和学生表，对应的Java类是班级类和学生类，假如我在显示学生信息的时候需要同时显示其所属的班级信息，那么就可以建立学生类到班级类的单向关联，如果我还想在显示班级基本信息的同时显示其所对应的学生信息，那么这时就可以建立双向关联了。  

> 5、JDK8 方法引用  
>> Method Reference：有时候可能已经有现成的方法可以完成你想要传递到其他代码的某个动作。  

> 6、lambda 表达式的组成部分  
>>  (1)一个代码块;    
    (2)参数;    
    (3)自由变量的值， 这是指非参数而且不在代码中定义的变量。  

> 7、    

   



                                              
   

