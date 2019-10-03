##                                简单的ERP项目

------

​                 **技术** : Spring + SpringMvc + MyBatis + tomcat + quartz  + EasyUI 等技术;相对于现在的SpringBoot 的情况下,这套技术的使用程序还是想对比较繁琐的，需要很多配置啊, 然后启动的便捷程度也没有SpringBoot 那么迅速，就是给人一种不是很清晰的层次感觉。但是这个项目;要是用来学习或者给刚刚了解这些东西的人给看,还是很好的,还是有一个很好的成长空间的。毕竟在你学习东西的过程,是一个这样的循环渐进的过程，一步一步的往上结合,而不是一步就可以直接到的.所以单体的SSM(或者SSH);然后到SpringBoot ; 可能这中间就可以开始穿插分布式的知识(这里了解的分布式知识一般都是基于 dubbo + Zookeeper 来实现),然后就是传说中的SpringCloud 微服务。

------

​              **导入项目的过程 : 这里我使用的IDEA**:

- 导入项目项目进去
- 选择Pom依赖,选择erpparent 的模块,这是一个父依赖的模块。
- 将Sql脚本导入到数据库中
- 修改erpweb 下的 resources 下的 config 目录下的 db.properties 中关于连接MySql 信息的配置.
- 点开右侧的Maven , 选择 erpweb --> Plugins --> tomcat 7 ---> run 就可以让项目启动起来.



   ***导入操作 ：*** 

![Image](https://github.com/baoyang23/erpdb/blob/master/images/erp_basci_one.png)



  ***启动成功;访问登陆页面 :*** 

​         ![Image](https://github.com/baoyang23/erpdb/blob/master/images/erp_login_one.png)



***菜单界面展示 :*** 

​         ![Image](https://github.com/baoyang23/erpdb/blob/master/images/erp_success_one.png)



***关于权限的操作说明,不同权限的人登陆进来的菜单展示都不一样 :*** 

​       ![Image](https://github.com/baoyang23/erpdb/blob/master/images/erp_success_auth.png)



输入htttp://localhost:8080/erp  admin admin  密码采用了shrio盐值加密;还有其他人员账号的使用,这个自己也可以在页面中进行添加等操作。然后可自行登陆测试。



**www.baoyang23.cn/erp 目前还有部署在服务器上,admin / admin。如果你需要在线看下的话,那么数据就不要乱修改。因为代码写到比较早，当时在技术方面也是比较菜的,就没有写到那么好的，所以请各位大佬手下留情。**