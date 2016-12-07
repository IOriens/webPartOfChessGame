package hello;

import java.io.File;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
	public JSONArray toJSON(Chessman[][] chessboard) {

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
		return chess2DBoard;
	}

	// Initialize
	@SuppressWarnings("unchecked")
	@MessageMapping("/init")
	@SendTo("/topic/init")
	public ResponseMessage init(String value) throws Exception {

		// Data from FE
		System.out.println(value);

		// AI Init
		String fileName = "ÆåÅÌ/" + Integer.valueOf(value) + ".txt";
		System.out.println(fileName);

		if (AI != null) {
			
//			AI.close();
//			System.out.println("## AI CLOSE");
		}

		AI = new Machine(6, new File(fileName));

		// Backend Processing
		//System.out.println(messageID++);

		// Response JSON
		JSONObject obj = new JSONObject();
		obj.put("player", AI.getCurrentChessboard().isRedStep() ? 1 : 0); // true
																			// ->
																			// ºì
		obj.put("chessboard", toJSON(AI.getCurrentChessboard().chessboard));

		// return to FE
		return new ResponseMessage(obj.toJSONString());
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
		//System.out.println(messageID++);

		// return to FE
		return new ResponseMessage("done");
	}

	@SuppressWarnings("unchecked")
	// AI Play
	@MessageMapping("/playing")
	@SendTo("/topic/playing")
	public ResponseMessage transfer(ClientMessage message) throws Exception {

		// Data from FE
		String data = message.getFromTo();
		if (!data.equalsIgnoreCase("0000")) {
			String moveData[] = data.split("");

			// Backend Processing
			Move userMove = new Move(Integer.valueOf(moveData[0]), 9 - Integer.valueOf(moveData[1]),
					Integer.valueOf(moveData[2]), 9 - Integer.valueOf(moveData[3]));
			AI.makeMove(userMove.sp, userMove.ep);
		}
		// Response JSON
		JSONObject obj = new JSONObject();

		// AI Move
		Move move = AI.search();
		System.out.println("## AI PLAYING: " + move);
		String response = "" + move.sp.x + (9 - move.sp.y) + move.ep.x + (9 - move.ep.y);
		obj.put("aiMove", response);
		obj.put("aiWhy", AI.getReasonList().toString());
		if (move.redWin != null) {
			AI.close();
		} else {
			// Help Message
			Move helpMove = AI.helpSearch();
			String helpMoveString = "" + helpMove.sp.x + (9 - helpMove.sp.y) + helpMove.ep.x + (9 - helpMove.ep.y);
			obj.put("helpMove", helpMoveString);
			obj.put("helpWhy", AI.getReasonList().toString());
		}

		// return to FE
		return new ResponseMessage(obj.toJSONString());
	}

}