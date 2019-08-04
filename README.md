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
    (3)自由变量的值，这是指非参数而且不在代码中定义的变量。  

> 7、lambda/lazy包下为lambda表达式延迟执行的demo。  

* 2019.07.24  
> 1、lambda表达式只能引用值不会改变的变量，那为什么捕获List集合类型的变量后还是可以改变呢？  
>> 不能改变被捕获变量的的引用地址，但是其内部的属性是可以修改的。  

> 2、内部类的对象总有一个隐式引用，它指向了创建它的外部类对象，这个引用在内部类的定义中是不可见的。    

> 3、new内部类对象的时候会触发外部类对象的构造方法吗？  
>> 如果内部类为普通内部类，肯定是先要通过外部类对象进行new内部类对象的  
如果内部类为静态内部类，new静态内部类对象时不会触发外部类对象的构造方法。

> 4、嵌套类有两个好处: 命名控制和访问控制  

> 5、内部类的几种：  
`成员内部类`：在内部成员类中可以定义自己的成员变量和方法(如function1())，也可以定义自己的构造方法。成员内部类可以看成是外层类的一个成员，因此可以访问外层类的所有成员，包括私有成员。
在外层类的方法中可以直接创建内部类的实例。在外层类的外面要创建内部类的实例必须要先创建一个外层类的对象，因为内部类对象对外层类对象有一个隐含的引用。  
    注意：  
      (1)成员内部类中不能定义static变量和static方法  
      (2)成员内部类也可以使用abstract和final修饰，含义与其他类一样  
      (3)成员内部类还可以使用private、public、protected或包可访问修饰符          
`局部内部类`：可以在方法体或语句块内定义类。在方法体或语句块（包括方法、构造方法、局部块、初始化块或静态初始化块）内部定义的类称为局部内部类。
局部内部类不能视为外部类的成员，只对局部块有效，同局部变量一样，在说明它的块之外完全不能访问，因此也不能有任何访问修饰符。    
注意：  
(1)局部内部类同方法局部变量一样，不能使用private、protected和public等访问修饰符，也不能使用static修饰，但可以使用final或abstract修饰。  
(2)局部内部类可以访问外部类的成员，若要访问其所在方法的参数和局部变量，这些参数和局部变量不能修改(使用final修饰)。  
(3)static方法中定义的局部内部类，可以访问外层类定义的static成员，不能访问外层类的实例成员。  
`匿名内部类`：定义类的最终目的是创建一个类的实例。但如果某个类的实例只使用一次，可以将类的定义和实例的创建在一起完成。   
注意：匿名内部类可以继承一个类或实现一个接口，且不需要使用extends或implements关键字。匿名内部类不能同时继承一个类和实现一个接口，也不能实现多个接口。
因为匿名内部类没有名称，所以类体中不能定义构造方法。又因为不知道类名，所以只能在定义类的同时用new关键字创建类的实例。实际上匿名内部类的定义、创建对象发生在同一地方。被匿名内部类访问的局部变量必须被final修饰。        
```java
class Animal{  
    public void eat(){  
       System.out.println("i like eat anything.");
    }
 }
 public class AnimalTest{
      public static void main(String[] args){
          Animal dog = new Animal(){
          @override
          public void eat(){
               System.out.println(" i like eat bones.");
               }
          };    
          dog.eat();
      }
 }    
 ```  
>>`静态内部类`：与类的其他成员类似，静态内部类使用static修饰，静态内部类也称嵌套类。静态内部类实际上是一种外部类，它不存在对外部类的引用，
不通过外部类的实例可以创建一个对象。程序中的静态内部类的完整名称为Myouter.MyInner，此时必须使用完整的类名创建对象。因此有时候将静态内部类称为顶层类。
静态内部类不具有任何对外层类实例的引用，因此静态内部类中的方法不能使用this关键字访问外层类的实例成员，然而这些方法可以访问外层类的static成员。这一点与一般类的static方法的规则相同。  
静态内部类与成员内部类的行为完全不同，下面是他们的不同之处：  
(1)静态内部类中可以定义静态成员，而成员内部类不能；  
(2)静态内部类只能访问外层类的静态成员，成员内部类可以访问外层类的实例成员和静态成员；  
(3)创建静态内部类的实例不需要先创建一个外层类的实例；相反创建成员内部类实例，必须先创建一个外层类的实例。    

> 6、为什么局部内部类和匿名内部类访问局部变量的时候都必须将局部变量声明为final呢？
>> 首先先说匿名内部类，匿名内部类之所以可以访问局部变量，是因为在底层将这个局部变量的值传入到了匿名内部类中，并且以匿名内部类的成员变量的形式存在，这个值的传递过程是通过匿名内部类的构造器完成的。
用final修饰实际上就是为了保护数据的一致性。这里所说的数据一致性，对引用变量来说是引用地址的一致性，对基本类型来说就是值的一致性。  
为什么要用final保护数据的一致性呢？  
因为将数据拷贝完成后，如果不用final修饰，则原先的局部变量可以发生变化。这里到了问题的核心了，如果局部变量发生变化后，匿名内部类是不知道的（因为他只是拷贝了局不变量的值，并不是直接使用的局部变量）。
这里举个栗子：原先局部变量指向的是对象A，在创建匿名内部类后，匿名内部类中的成员变量也指向A对象。但过了一段时间局部变量的值指向另外一个B对象，但此时匿名内部类中还是指向原先的A对象。
那么程序再接着运行下去，可能就会导致程序运行的结果与预期不同出现一些问题。

> 7、内部类中声明的所有静态域都必须是final。
原因很简单。我们希望一个静态域只有一个实例，不过对于每个外部对象，会分别有一个单独的内部类实例。如果这个域不是final,它可能就不是唯一的。  

> 8、代理可以在运行时创建一个实现了一组给定接口的新类 

> 9、双大括号初始化语法是什么操作？
>> 双括号初始化(double brace initialization)这里利用了内部类语法。外层{} 建立了Student的一个匿名子类的一个匿名对象，
内层{} 为初始块，只要构造类的对象，这些块就会被执行。

* 2019.07.25  
> 1、`InvocationHandler` Object invoke(Object proxy, Method method, Object[] args) 定义了代理对象调用方法时希望执行的动作。    
`Proxy` static Class<?> getProxyClass(ClassLoader loader, Class<？>... interfaces) 返回实现指定接口的代理类。  
`Proxy` static Object newProxyInstance(ClassLoader loader, Class<？>[] interfaces, InvocationHandler handler) 构造实
现指定接口的代理类的一个新实例，所有方法会调用给定处理器对象的invoke方法。    
`Proxy` static boolean isProxyClass(Class<？> cl) 如果cl是一个代理类则返回true。  


> 2、接口、lambda表达式和内部类是经常使用的几个概念。克隆和代理是库设计者和工具构造者感兴趣的高级技术，对应用程序员来说，它们并不十分重要。  

> 3、System.out 定义为public final static PrintStream out = null; 是如何做到在使用的时候获取的非null流对象？  
>> 在System类中有这样的一个静态代码块  
```java
static {
    registerNatives();
}
```
>> 看一下注释：大概意思是将通过静态初始化注册native方法，该方法会令vm通过调用initializeSystemClass方法来完成初始化工作。该方法中  
`setOut0(newPrintStream(fdOut, props.getProperty("sun.stdout.encoding")));` 就是为out变量进行初始化。  

> 4、 System.out变量不是被static 和 final进行修饰了吗，为什么可以在运行期间改变引用了呢？  (接上面第3个问题继续提问的问题)
>> setOut()是一段本地C/C++程序，直接进行操作内存的。而用static final修饰的out只是在编译阶段会禁止修改,在运行期间仍然可以直接通过内存修改。

> 5、 git 撤销文件修改命令  
>> git add之前：`git checkout xxx.java`  
git add之后(暂存区)：`git reset HEAD xxx.java`(加入到暂存区的文件重新放回到工作区)  
git commit之后：`git reset --soft HEAD^ || git reset --soft commitid`(文件版本回退)
git push之后：`git reset --soft HEAD^ || git reset --soft commitid`之后再进行 `git push -f` (如果分支已经被合并最好不要这样使用)  
      
*  2019.07.29  
> 1、git diff的用法  
>>  `git diff xxx.java`: 比较工作区与暂存区的(git add 后)的差别，一个文件可以在commit之前多次add。(git add之后就看不到了)  
`git diff --cached || --staged xxx.java`：比较`暂存区`文件与`上一次commit`的差别(git diff --staged 和–cached的时候发现结果都一样，其实git diff --staged是–cached的同义词。git add之后可以查看 commit之后就看不到了)    
`git diff HEAD xxx.java`：比较`工作区`文件与`最新本地版本库`的差别 (git commit之后就看不到了)
`git diff master origin/master`：比较本地分支与远程分支之间的差别  

> 2、对于那些可能被他人使用的Java方法，应该根据异常规范(exception specification), 在方法的首部声明这个方法可能抛出的异常。
如果一个方法有可能抛出多个受查异常类型，那么就必须在方法的首部列出所有的异常类。每个异常类之间用逗号隔开。
不需要声明Java的内部错误，即从Error继承的错误。任何程序代码都具有抛出那些异常的潜能，而我们对其没有任何控制能力。
同样，也不应该声明从RuntimeException继承的那些非受查异常。这些运行时错误完全在我们的控制之下，如果特别关注数组下标引发的错误， 
就应该将更多的时间花费在修正程序中的错误上，而不是说明这些错误发生的可能性上。
```java
class MyAnimation {
    public Image loadImage(String s) throws FileNotFoundException, EOFException {
        System.out.println(s);
    }
}
```   

> 3、如果调用了一个抛出受查异常的方法，就必须对它进行处理，或者继续传递。哪种方法更好呢？通常，应该捕获那些知道如何处理的异常，而将那些不知道怎样处理的异常继续进行传递。
特殊情况：如果编写一个覆盖超类的方法，而这个方法又没有抛出异常(如JComponent中的paintComponent)，那么这个方法就必须捕获方法代码中出现的每一个受查异常。
不允许在子类的throws说明符中出现超过超类方法所列出的异常类范围。  

> 4、堆栈轨迹(stack trace)是一个方法调用过程的列表，它包含了程序执行过程中方法调用的特定位置。  
有两种方式可以获取堆栈轨迹：  
(1) `StackTraceElement[] frames = Thread.currentThread().getStackTrace();`  
(2) `Throwable t = new Throwable();`  
`StackTraceElement[] frames = t.getStackTrace();`  

> 5、在 Java 语言中，给出了3种处理系统错误的机制:   
(1)抛出一个异常  
(2)日志  
(3)使用断言  
什么时候应该选择使用断言呢? 请记住下面几点:    
(1)断言失败是致命的、不可恢复的错误。   
(2)断言检查只用于开发和测阶段。因此，不应该使用断言向程序的其他部分通告发生了可恢复性的错误，或者说断言不应该作为程序向用户通告问题的手段。断言只应该用于在开发和测试阶段确定程序内部的错误位置。
切记：必须不依赖断言完成任何程序实际所需的行为。   

* 2019.07.30  
> 1、泛型提供了一个更好的解决方案:类型参数(type parameters)。ArrayList类有一个类型参数用来指示元素的类型  
`ArrayList<String> files = new ArrayList<String>()`  
在Java SE 7及以后的版本中，构造函数中可以省略泛型类型:  
`ArrayList<String> files = new ArrayList<>();` 省略的类型可以从变量的类型推断得出。  
此时调用get()方法时无需再将对象进行强转，使用add()方法进行添加元素时如果添加的对象类型不为String类型则无法通过编译器。类型参数的魅力在于: 使得程序具有更好的可读性和安全性。    

> 2、泛型方法可以定义在普通类中，也可以定义在泛型类中。定义泛型方法时类型变量放在修饰符的后面，返回类型的前面。   
 
* 2019.07.31
> 1、一个类型变量或通配符可以有多个限定，例如: `T extends Comparable & Serializable`。限定类型用“ & ” 分隔，逗号用来分隔类型变量。
在Java的继承中，可以根据需要拥有多个接口超类型，但限定中至多有一个类。如果用一个类作为限定，它必须是限定列表中的第一个。

> 2、虚拟机没有泛型类型对象————所有对象都属于普通类。无论何时定义一个泛型类型，都自动提供了一个相应的原始类型(raw type)。
原始类型的名字就是删去类型参数后的泛型类型名。擦除(erased)类型变量并替换为限定类型(无限定的变量用Object)，原始类型用类型变量中
第一个限定的类型变量来替换，如果没有给定的使用Object来替换。      

> 3、`class Interval<T extends Serializable & Comparable>`会发生什么？  
>> 如果这样做，原始类型用Serializable替换T，而编译器在必要时要向Comparable插入强制类型转换。为了提高效率，应该将标签tagging接口(即没有方法的接口)放在边界列表的末尾。
在编译过程中，正确检验泛型结果后，在运行期间会将泛型的相关信息进行擦除，并且在对象进入和离开方法的边界处添加类型检查和类型转换的方法。泛型信息不会进入到运行期间。  

> 4、(1)虚拟机中没有泛型，只有普通的类和方法。  
(2)所有的类型参数都用它们的限定类型替换。  
(3)桥方法被合成来保持多态。 桥接方法是JDK1.5引入泛型后，为了使Java的泛型方法生成的字节码和1.5版本前的字节码相兼容，由编译器自动生成的方法。  
就是说一个子类在继承(或实现)一个父类(或接口)的泛型方法时，在子类中明确指定了泛型类型，那么在编译时编译器会自动生成桥接方法(当然还有其他情况会生成桥接方法)。
由于Java泛型的擦除特性，如果不生成桥接方法，那么与1.5之前的字节码就不兼容了。https://blog.csdn.net/mhmyqn/article/details/47342577    
(4)为保持类型安全性，必要时插人强制类型转换。 

> 5、 泛型的本质是为了参数化类型（在不创建新的类型的情况下，通过泛型指定的不同类型来控制类型形参具体限制的类型）。也就是说在泛型使用过程中，
操作的数据类型被指定为一个参数，这种参数类型可以用在类、接口和方法中，分别被称为泛型类、泛型接口、泛型方法。  

> 6、类型通配符一般是使用？代替具体的类型实参，是具体的类型实参，而并非为类型形参。再直白点的意思就是，此处的？和Number、String、Integer一样都是一种实际的类型，
可以把？看成所有类型的父类。是一种真实的类型。可以解决当具体类型不确定的问题。当操作类型时，不需要使用类型的具体功能时，只使用Object类中的功能。那么可以用 ? 通配符来表未知类型。  

> 7、泛型类，是在实例化类的时候指明泛型的具体类型；泛型方法，是在调用方法的时候指明泛型的具体类型。  

> 8、静态方法有一种情况需要注意一下，那就是在类中的静态方法使用泛型：静态方法无法访问类上定义的泛型；如果静态方法操作的引用数据类型不确定的时候，必须要将泛型定义在泛型方法上。  

> 9、泛型方法和非泛型方法的区别：非泛型方法依赖创建类时给定的泛型实际类型，泛型方法反之。泛型方法能使方法独立于类而产生变化。
泛型方法的上下边界添加，必须与泛型的声明在一起。   


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





                                              
   

