package hello;

import java.io.File;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import cn.edu.cqu.engine.Machine;
import cn.edu.cqu.engine.Move;

@Controller
public class ResponseController {
    int messageID = 0;
    Machine AI = null;

    // Initialize
    @MessageMapping("/init")
    @SendTo("/topic/init")
    public ResponseMessage init(String value) throws Exception {
        Thread.sleep(1000);



        // Data from FE
        System.out.println(value);
        
        // AI Init
        AI =  new Machine(2, new File("棋盘/"+ value +".txt"));


        // Backend Processing
        System.out.println(messageID ++);


        // return to FE
        return new ResponseMessage(AI.getCurrentChessboard().toJSON());
    }

    // AI Play
    @MessageMapping("/playing")
    @SendTo("/topic/playing")
    public ResponseMessage transfer(ClientMessage message) throws Exception {
        


        // Data from FE
        String data = message.getFromTo();
        String moveData[] =data.split("") ;
       
        


        // Backend Processing
        Move userMove = new Move( Integer.valueOf(moveData[0]),Integer.valueOf( moveData[1]), Integer.valueOf(moveData[2]), Integer.valueOf(moveData[3]));
        AI.makeMove(userMove.sp, userMove.ep);
        Move move = AI.search();
        System.out.println("搜索出的步骤�?" + move);
        String response = "" + move.sp.x + move.sp.y + move.ep.x +  move.ep.y;
        
        // return to FE
        return new ResponseMessage(response);
    }

}