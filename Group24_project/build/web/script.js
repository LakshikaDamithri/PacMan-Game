
var response;
//get json objects from servlet GameServlet
var source = new EventSource('GameServlet');
source.onmessage = function (e) {
    console.log(e.data);
    response = JSON.parse(e.data);
    if(response.DOTS===null){
        window.alert("Game over! "+response.WINNER[0]+" wins.");
    }
};


//keyboard event controls
$(document).keydown(function (e) {
    var key = e.which;
    document.getElementById("keypress").value = key;
    sendPlayerPosition();

});
			   
function sendPlayerPosition() {
    if (true) {
        var xmlhttprequest = new XMLHttpRequest();
        var keypress = document.getElementById("keypress").value;
        //send keystroke to GameServlet
        xmlhttprequest.open("POST", "UpdateGame?keypress=" + keypress, true);
        xmlhttprequest.send();
    } else {
        return;
    }
}


var canvas = $("#canvas");
var ctx = canvas[0].getContext("2d");
var w = canvas.width();
var h = canvas.height();

var cw = 10; 

ctx.fillStyle = "white";
ctx.fillRect(0, 0, w, h);
ctx.strokeStyle = "black";
ctx.strokeRect(0, 0, w, h);


function init() {

    if (typeof game_loop !== "undefined")
        clearInterval(game_loop);
    game_loop = setInterval(paint, 100);

}

init();

function paint() {

    ctx.fillStyle = "white";
    ctx.fillRect(0, 0, w, h);
    ctx.strokeStyle = "black";
    ctx.strokeRect(0, 0, w, h);

    var players = response.PLAYERS;
    var pLength = players.length;

    for (i = 0; i < pLength; i++) {
        paint_player(players[i][0], players[i][2], players[i][3]);
        updateScoreboard(players[i][0], players[i][1]);
    }

 
    var foods = response.DOTS;
    var fLength = foods.length;

    for (i = 0; i < fLength; i++) {
        paint_food(foods[i][0], foods[i][1], foods[i][2]);
    }


}

paint();

function paint_player(type, x, y) {
    switch (type) {
        case "P1":
            ctx.fillStyle = "yellow";
            ctx.strokeStyle = "black";
            break;
        case "P2":
            ctx.fillStyle = "orange";
            ctx.strokeStyle = "black";
            break;
        case "P3":
            ctx.fillStyle = "cyan";
            ctx.strokeStyle = "black";
            break;
        case "P4":
            ctx.fillStyle = "pink";
            ctx.strokeStyle = "black";
            break;
        default:
            ctx.fillStyle = "yellow";
            ctx.strokeStyle = "black";
    }
    ctx.beginPath();
    ctx.arc((x * cw) + 5, (y * cw) + 5, 5, 0, 2 * Math.PI, false);
    ctx.fill();
    ctx.stroke();

}

function paint_food(type, x, y) {
    switch (type) {
        case "R":
            ctx.fillStyle = "red";
            ctx.strokeStyle = "red";
            break;
        case "G":
            ctx.fillStyle = "green";
            ctx.strokeStyle = "green";
            break;
        case "B":
            ctx.fillStyle = "blue";
            ctx.strokeStyle = "blue";
            break;
        default:
            ctx.fillStyle = "red";
            ctx.strokeStyle = "red";
    }
    ctx.beginPath();
    ctx.arc((x * cw) + 5, (y * cw) + 5, 5, 0, 2 * Math.PI, false);
    ctx.fill();
    ctx.stroke();
}

function updateScoreboard(id, score) {
    var scoreId = "td." + id;
    var scoreValue = "td.Score" + id;
    $(scoreId).text(id);
    $(scoreValue).text(score);
}




