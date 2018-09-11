package com.yongjun.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

/**
 * Created by lyl on 2017/6/4.
 */
@Controller
@ComponentScan(basePackageClasses = MyWebSocketController.class)
public class MyWebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    /**
     * Comment is created by lyl on 2017/6/4 下午6:26.
     *
     * 当浏览器向服务器端发送请求时 通过@MessageMapping映射/welcime这个地址 类似于@RequestMapping
     * 当服务端有消息时 会对订阅了@SendTo中的路径的浏览器发送消息
     */

    @MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public HelloResponse say(HelloMessage message) throws Exception{
        System.out.println("name:" + message.getName());
        Thread.sleep(3000);
        return new HelloResponse("Welcome, " + message.getName() + "!");
    }

    /**
     * Comment is created by lyl on 2017/6/4 下午9:15.
     *
     * 注册一个名为/endpointchat的endpoint
     * 点对点式应增加一个/queue消息代理
     */
    @MessageMapping("/chat")
    public void handleChat(Principal principal, String msg){
        if (principal.getName().equals("lyl")){
            messagingTemplate.convertAndSendToUser("ymf", "/queue/notifications",
                    principal.getName() + "-send:" + msg);
            System.out.println(principal.getName() + ":" + msg);
        }else if(principal.getName().equals("ymf")){
            messagingTemplate.convertAndSendToUser("lyl", "/queue/notifications",
                    principal.getName() + "-send:" + msg);
            System.out.println(principal.getName() + ":" + msg);
        }
    }

}
