package com.ww.pojo.son;

import com.ww.pojo.father.Father;

/**
 * @author: Sun
 * @create: 2019-07-22 19:17
 * @version: v1.0
 */
public class SecondSon extends Father {
    void callFatherMethod() {
        // 直接调用
        father();

        // super关键字调用，其实前两种等同
        super.father();

        /**
         * new子类对象FirstSon进行调用父类方法
         * 报错原因：son.father()这种调用属于子类对象调用，并不是子类调用。
         * 当创建子类对象调用父类的protected成员变量时，必须要注意：子类对象和子类是对应的！
         * 通过这个例子其实也可以看出来：当一个包外子类继承保护成员时，该成员在这个子类内实际上变为私有。
         * 结论：包外子类有权访问超类成员(在当前子类中而并非为子类对象)，然而这并不意味着在包外子类能够使用超类实例的引用访问该成员。
         */
        FirstSon son = new FirstSon();
        // son.father();   不注释会报错，因为FirstSon并不是在本类中进行调用的
    }
}
