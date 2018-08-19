package com.code;

import com.code.scanner.Invoker;
import com.code.scanner.InvokerHoler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Invoker invoker = InvokerHoler.getInvoker((short) 1, (short) 1);
        invoker.invoke(null);

    }
}
