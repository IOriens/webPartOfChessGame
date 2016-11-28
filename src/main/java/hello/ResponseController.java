package hello;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ResponseController {
    int messageID = 0;

    // 初始化
    @MessageMapping("/init")
    @SendTo("/topic/init")
    public ResponseMessage init(ClientMessage message) throws Exception {
        Thread.sleep(1000);



        // 得到前端得数据
        System.out.println(message.getFromTo());


        // 调用AI处理
        String response = "初始化成功";
        System.out.println(messageID ++);


        // 返回给前端
        return new ResponseMessage(response);
    }

    // AI 处理前端数据并返回
    @MessageMapping("/playing")
    @SendTo("/topic/playing")
    public ResponseMessage transfer(ClientMessage message) throws Exception {
        Thread.sleep(1000);



        // 得到前端得数据
        System.out.println(message.getFromTo());


        // 调用AI处理
        String response = "7062";
        System.out.println(messageID ++);


        // 返回给前端
        return new ResponseMessage(response);
    }

}