/**
 * ChinaChess - in html5 http://www.jnzo.com/chess/ @ author 一叶孤舟 @ mail
 * itlwei@163.com @ QQ 28701884
 */

var play = play || {};


play.initMMMap = function (map) {

	for (var i in com.mans) {
		com.mans[i].isShow = false;
	}

	// 初始化棋子
	for (var i = 0; i < map.length; i++) {
		for (var n = 0; n < map[i].length; n++) {
			var key = map[i][n];
			if (key) {
				// console.log(key)
				com.mans[key].x = n;
				com.mans[key].y = i;
				com.mans[key].isShow = true;
			}
		}
	}
}

play.init = function (map, player) {

	play.my = player; // 玩家方
	play.changeTip(0)
	if (map) {
		com.currentInitMap = com.json2arr(map)
			// console.log(com.currentInitMap)
	}
	play.map = com.arr2Clone(com.currentInitMap); // 初始化棋盘
	console.log('play.map:\n', play.map)
	play.nowManKey = false; // 现在要操作的棋子
	play.pace = []; // 记录每一步
	play.isPlay = true; // 是否能走棋
	play.mans = com.mans; // 棋子集合
	play.bylaw = com.bylaw; // 棋子能走的着点
	play.show = com.show; // 显示列表
	play.showPane = com.showPane; // 显示移动的棋子外框
	play.isOffensive = true; // 是否先手
	play.depth = play.depth || 3; // 搜索深度


	play.isFoul = false; // 是否犯规长将

	com.pane.isShow = false; // 隐藏方块

	play.initMMMap(play.map)

	document.getElementById('explain').innerHTML = ""
	play.show();

	// 绑定点击事件
	com.canvas.addEventListener("click", play.clickCanvas)
		// clearInterval(play.timer);
		// com.get("autoPlay").addEventListener("click", function(e) {
		// clearInterval(play.timer);
		// play.timer = setInterval("play.AIPlay()",1000);
		// play.AIPlay()
		// })
		/*
		 * com.get("offensivePlay").addEventListener("click", function(e) {
		 * play.isOffensive=true; play.isPlay=true ;
		 * com.get("chessRight").style.display = "none"; play.init(); })
		 * 
		 * com.get("defensivePlay").addEventListener("click", function(e) {
		 * play.isOffensive=false; play.isPlay=true ;
		 * com.get("chessRight").style.display = "none"; play.init(); })
		 */

	com.get("regretBn").addEventListener("click", function (e) {
		// play.regret();
		play.socket.sendMessage("/app/regret", "regret")
	})

	/*
	 * var initTime = new Date().getTime(); for (var i=0; i<=100000; i++){
	 * 
	 * var h="" var h=play.map.join(); //for (var n in play.mans){ // if
	 * (play.mans[n].show) h+=play.mans[n].key+play.mans[n].x+play.mans[n].y //} }
	 * var nowTime= new Date().getTime(); z([h,nowTime-initTime])
	 */

	if (player === 0) {
		play.socket.sendMessage("/app/playing", {
			fromTo: "0000"
		})
	}

}

play.connectServer = function () {
	play.socket = new Socket()
	play.socket.connect({
		"playing": play.AIPlay,
		"init": function (data) {
			data = JSON.parse(data)
			console.log(data)
			play.init(data['chessboard'], data['player'])
		},
		"regret": play.regret,
		"help": function (data) {
			data = JSON.parse(data)
			var helpTxt = ""
			if (data['helpMove']) {
				helpTxt += `机器助手提示：你可从（${data['helpMove'][0]}，${data['helpMove'][1]}）走到（${data['helpMove'][2]}，${data['helpMove'][3]}）<br>`
			}
			if (data["helpWhy"]) {
				helpTxt += `机器助手推理链: ${data["helpWhy"]}`
			}
			document.getElementById('helpMessage').innerHTML = helpTxt
		}
	})
}

// 悔棋
play.regret = function () {
	var map = com.arr2Clone(com.currentInitMap);
	// 初始化所有棋子
	play.initMMMap(map)
	var pace = play.pace;
	pace.pop();
	pace.pop();

	for (var i = 0; i < pace.length; i++) {
		var p = pace[i].split("")
		var x = parseInt(p[0], 10);
		var y = parseInt(p[1], 10);
		var newX = parseInt(p[2], 10);
		var newY = parseInt(p[3], 10);
		var key = map[y][x];
		// try{

		var cMan = map[newY][newX];
		if (cMan)
			com.mans[map[newY][newX]].isShow = false;
		com.mans[key].x = newX;
		com.mans[key].y = newY;
		map[newY][newX] = key;
		delete map[y][x];
		if (i == pace.length - 1) {
			com.showPane(newX, newY, x, y)
		}
		// } catch (e){
		// com.show()
		// z([key,p,pace,map])

		// }
	}
	play.map = map;
	play.my = 1;
	play.changeTip(0)
	play.isPlay = true;
	com.show();
}

// 点击棋盘事件
play.clickCanvas = function (e) {
	if (!play.isPlay || play.my != 1)
		return false;
	var key = play.getClickMan(e);
	var point = play.getClickPoint(e);

	var x = point.x;
	var y = point.y;

	if (key) {
		play.clickMan(key, x, y);
	} else {
		play.clickPoint(x, y);
	}
	play.isFoul = play.checkFoul(); // 检测是不是长将
}

// 点击棋子，两种情况，选中或者吃子
play.clickMan = function (key, x, y) {

	document.getElementById('explain').innerHTML = ""

	var man = com.mans[key];
	// 吃子
	if (play.nowManKey && play.nowManKey != key &&
		man.my != com.mans[play.nowManKey].my) {
		// man为被吃掉的棋子
		if (play.indexOfPs(com.mans[play.nowManKey].ps, [x, y])) {
			man.isShow = false;
			var pace = com.mans[play.nowManKey].x + "" +
				com.mans[play.nowManKey].y
				// z(bill.createMove(play.map,man.x,man.y,x,y))
			delete play.map[com.mans[play.nowManKey].y][com.mans[play.nowManKey].x];
			play.map[y][x] = play.nowManKey;
			com.showPane(com.mans[play.nowManKey].x,
				com.mans[play.nowManKey].y, x, y)
			com.mans[play.nowManKey].x = x;
			com.mans[play.nowManKey].y = y;
			com.mans[play.nowManKey].alpha = 1

			var record = pace + x + y;

			play.pace.push(record);
			play.nowManKey = false;
			com.pane.isShow = false;
			com.dot.dots = [];
			com.show()
				// com.get("clickAudio").play();
				// setTimeout(play.AIPlay, 500);

			play.my = -1
			play.changeTip(1)
			setTimeout(function () {
				if (key == "j0") {
					play.socket.sendMessage("/app/manwin", {
						fromTo: record
					})
					play.showWin(-1, 3);
				} else if (key == "J0") {
					play.socket.sendMessage("/app/manwin", {
						fromTo: record
					})
					play.showWin(1, 4);
				} else {
					play.socket.sendMessage("/app/playing", {
						fromTo: record
					})
				}
			}, 300);

		}
		// 选中棋子
	} else {
		if (man.my === 1) {
			if (com.mans[play.nowManKey])
				com.mans[play.nowManKey].alpha = 1;
			man.alpha = 0.6;
			com.pane.isShow = false;
			play.nowManKey = key;
			com.mans[key].ps = com.mans[key].bl(play.map); // 获得所有能着点
			com.dot.dots = com.mans[key].ps
			com.show();
			// com.get("selectAudio").start(0);
			// com.get("selectAudio").play();
		}
	}
}

// 点击着点
play.clickPoint = function (x, y) {
	document.getElementById('explain').innerHTML = ""


	var key = play.nowManKey;
	var man = com.mans[key];
	if (play.nowManKey) {
		if (play.indexOfPs(com.mans[key].ps, [x, y])) {
			var pace = man.x + "" + man.y
				// z(bill.createMove(play.map,man.x,man.y,x,y))
			delete play.map[man.y][man.x];
			play.map[y][x] = key;
			com.showPane(man.x, man.y, x, y)
			man.x = x;
			man.y = y;
			man.alpha = 1;
			var record = pace + x + y;

			play.pace.push(record);
			// console.log("red: " + record)
			play.nowManKey = false;
			com.dot.dots = [];
			com.show();
			// com.get("clickAudio").play();

			// 发送数据到后端
			play.my = -1;
			play.changeTip(1)
			play.socket.sendMessage("/app/playing", {
					fromTo: record
				})
				// setTimeout(play.AIPlay, 500);

		} else {
			// alert("不能这么走哦！")
		}
	}
}

// Ai自动走棋
play.AIPlay = function (data) {
	data = JSON.parse(data)
	console.log(data)


	// console.log('pace-param: ' + pace)

	// return


	// 接受后端数据
	// var pace = AI.init(play.pace.join(""))
	// pace = ["7", "0", "6", "2"]
	var pace = data["aiMove"]
	console.log(pace)
	pace = pace.split("").map(function (item) {
			return parseInt(item)
		})
		// console.log('pace-afer: ' + pace)

	if (!pace) {
		play.showWin(1, 5);
		return;
	}
	play.pace.push(pace.join(""));
	// console.log('AI: ' + pace.join(''))

	console.log(pace)
	var key = play.map[pace[1]][pace[0]]
	play.nowManKey = key;

	var key = play.map[pace[3]][pace[2]];
	if (key) {
		play.AIclickMan(key, pace[2], pace[3]);
	} else {
		play.AIclickPoint(pace[2], pace[3]);
	}


	play.changeTip(0)
		// com.get("clickAudio").play();


	var helpTxt = ""
	helpTxt += `AI行走推理链: ${data["aiWhy"]}<br>`
	document.getElementById('explain').innerHTML = helpTxt
	setTimeout(function () {
		// var helpTxt = ""
		// helpTxt += `机器助手提示：你可从（${data['helpMove'][0]}，${data['helpMove'][1]}）走到（${data['helpMove'][2]}，${data['helpMove'][3]}）<br>`
		// helpTxt += `AI行走推理链: ${data["aiWhy"]}<br>`
		// helpTxt += `机器助手推理链: ${data["helpWhy"]}`
		// document.getElementById('explain').innerHTML = helpTxt

		play.my = 1;
		if (key == "j0")
			play.showWin(-1, 1);
		if (key == "J0")
			play.showWin(1, 2);
	}, 300)

}

play.changeTip = function (flag) {
	if (flag) {
		document.getElementById('tip').innerHTML = "电脑正在思考哦"
	} else {
		document.getElementById('tip').innerHTML = "该你下棋咯"
	}
}

// 检查是否长将
play.checkFoul = function () {
	var p = play.pace;
	var len = parseInt(p.length, 10);
	if (len > 11 && p[len - 1] == p[len - 5] && p[len - 5] == p[len - 9]) {
		return p[len - 4].split("");
	}
	return false;
}

play.AIclickMan = function (key, x, y) {
	var man = com.mans[key];
	// 吃子
	man.isShow = false;
	delete play.map[com.mans[play.nowManKey].y][com.mans[play.nowManKey].x];
	play.map[y][x] = play.nowManKey;
	play.showPane(com.mans[play.nowManKey].x, com.mans[play.nowManKey].y, x, y)

	com.mans[play.nowManKey].x = x;
	com.mans[play.nowManKey].y = y;
	play.nowManKey = false;

	com.show()
}

play.showWin = function (flag, tag) {

	console.log(tag)
	document.getElementById('tip').innerHTML = "&nbsp"
	var outcome = 0
	if (flag === 1) {
		outcome = window.confirm('红方胜利')
	} else {
		outcome = window.confirm('黑方胜利')
	}
	if (outcome) {
		var index = document.getElementById('chessboard').value;
		play.socket.sendMessage("/app/init", parseInt(index))
	}
}

play.AIclickPoint = function (x, y) {
	var key = play.nowManKey;
	var man = com.mans[key];
	if (play.nowManKey) {
		delete play.map[com.mans[play.nowManKey].y][com.mans[play.nowManKey].x];
		play.map[y][x] = key;

		com.showPane(man.x, man.y, x, y)

		man.x = x;
		man.y = y;
		play.nowManKey = false;

	}
	com.show();
}

// 判断xy点是否可走
play.indexOfPs = function (ps, xy) {
	for (var i = 0; i < ps.length; i++) {
		if (ps[i][0] == xy[0] && ps[i][1] == xy[1])
			return true;
	}
	return false;
}

// 获得点击的着点
play.getClickPoint = function (e) {
	var domXY = com.getDomXY(com.canvas);
	var x = Math.round((e.pageX - domXY.x - com.pointStartX - 20) / com.spaceX)
	var y = Math.round((e.pageY - domXY.y - com.pointStartY - 20) / com.spaceY)
	return {
		"x": x,
		"y": y
	}
}

// 获得棋子
play.getClickMan = function (e) {
	var clickXY = play.getClickPoint(e);
	var x = clickXY.x;
	var y = clickXY.y;
	if (x < 0 || x > 8 || y < 0 || y > 9)
		return false;
	return (play.map[y][x] && play.map[y][x] != "0") ? play.map[y][x] : false;
}