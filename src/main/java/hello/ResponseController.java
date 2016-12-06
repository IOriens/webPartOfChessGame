package hello;

import java.io.File;

import org.json.simple.JSONArray;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import cn.edu.cqu.engine.Machine;
import cn.edu.cqu.engine.Move;
import cn.edu.cqu.kb.model.Chessman;

@Controller
public class ResponseController {
	int messageID = 0;
	Machine AI = null;

	@SuppressWarnings("unchecked")
	public String toJSON(Chessman[][] chessboard) {

		JSONArray chess2DBoard = new JSONArray();

		for (int y = chessboard[0].length - 1; y >= 0; y--) {
			JSONArray childJsonArray = new JSONArray();
			for (int x = 0; x < chessboard.length; x++) {
				Chessman man = chessboard[x][y];
				if (man == null) {
					childJsonArray.add(0);
				} else {
					childJsonArray.add(man.getChessid());
				}
			}
			chess2DBoard.add(childJsonArray);
		}
		return chess2DBoard.toJSONString();
	}

	// Initialize
	@MessageMapping("/init")
	@SendTo("/topic/init")
	public ResponseMessage init(String value) throws Exception {

		// Data from FE
		System.out.println(value);

		// AI Init
		String fileName = "ÆåÅÌ/" + Integer.valueOf(value) + ".txt";
		System.out.println(fileName);
		AI = new Machine(6, new File(fileName));

		// Backend Processing
		System.out.println(messageID++);

		// return to FE
		return new ResponseMessage(toJSON(AI.getCurrentChessboard().chessboard));
	}

	// Regret
	@MessageMapping("/regret")
	@SendTo("/topic/regret")
	public ResponseMessage regert(String value) throws Exception {
		

		// Data from FE
		System.out.println(value);

		// AI regret
		AI.moveBack();
		

		// Backend Processing
		System.out.println(messageID++);

		// return to FE
		return new ResponseMessage("done");
	}

	// AI Play
	@MessageMapping("/playing")
	@SendTo("/topic/playing")
	public ResponseMessage transfer(ClientMessage message) throws Exception {
		
//		Thread.sleep(5000);

		// Data from FE
		String data = message.getFromTo();
		String moveData[] = data.split("");

		// Backend Processing
		System.out.println(data + "," + moveData[0]);
		Move userMove = new Move(Integer.valueOf(moveData[0]), 9 - Integer.valueOf(moveData[1]),
				Integer.valueOf(moveData[2]), 9 - Integer.valueOf(moveData[3]));
		AI.makeMove(userMove.sp, userMove.ep);
		Move move = AI.search();
		System.out.println("ËÑË÷½á¹û£º" + move);
		String response = "" + move.sp.x + (9 - move.sp.y) + move.ep.x + (9 - move.ep.y);

		// return to FE
		return new ResponseMessage(response);
	}

}