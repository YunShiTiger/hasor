加载Bean
------------------------------------
有时候我们希望有 Bean 可以在被创建时自动调用一个 init 方法，本小节就来向大家展示一下 Hasor 这方面的能力。

方式一：通过 @Init 注解，例如下面这样。

.. code-block:: java
    :linenos:

    public class PojoBean {
        @Init
        public void init(){
            ...
        }
    }


方式二：通过编码方式在 Module 初始化时指定，例如下面这样。

.. code-block:: java
    :linenos:

    public class MyModule implements Module {
        public void loadModule(ApiBinder apiBinder) throws Throwable {
            apiBinder.bindType(PojoBean.class).initMethod("init");
        }
    }



小贴士：如果您组合使用 @Singleton 注解和 @Init 注解，同时这个类在 Hasor 启动时通过 Module 预先注册到了 Hasor 容器中。那么 Hasor 会在启动时自动创建这个类并调用 init 方法。例如：

.. code-block:: java
    :linenos:

    @Singleton
    public class PojoBean {
        @Init
        public void init(){
            ...
        }
    }

    public class MyModule implements Module {
        public void loadModule(ApiBinder apiBinder) throws Throwable {
            apiBinder.bindType(PojoBean.class);
        }
    }


或者下面这样的方式也可以达到同样的效果：

.. code-block:: java
    :linenos:

    public class PojoBean {
        public void init(){
            ...
        }
    }

    public class MyModule implements Module {
        public void loadModule(ApiBinder apiBinder) throws Throwable {
            apiBinder.bindType(PojoBean.class)
                .initMethod("init")    // 初始化方法，相当于 @Init 注解
                .asEagerSingleton();   // 单例，相当于 @Singleton 注解
        }
    }

销毁Bean
------------------------------------
很遗憾，目前Hasor还没有这样一种能力可以在 Bean 级别上通知到 onStop 的通知，您需要通过 Event 来达到此目的。