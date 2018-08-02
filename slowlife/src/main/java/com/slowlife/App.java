package com.slowlife;

import com.alibaba.druid.pool.DruidDataSource;
import com.blade.Blade;
import com.slowlife.utils.AllowCrossOriginHook;
import io.github.biezhi.anima.Anima;

public class App {

    public static void main(String[] args) {

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/slowlife?useSSL=false&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        Anima.open(dataSource);

//        Blade.me().get("/",((request, response) -> response.text("Hello World"))).addStatics("/templates").start(App.class,args);

        Blade.of().use(new AllowCrossOriginHook()).addStatics("/templates")
                .get("/",(routeContext -> routeContext.response().text("Hello World")))
                .start(App.class,args);

    }

}
