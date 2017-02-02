/**
 * Created by alexa on 31.01.2017.
 */
var ws;

function init() {
    ws = new WebSocket("ws://localhost:8080/listen");
    ws.onopen = function (event) {
        console.log(">> Open connection");
    }
    ws.onmessage = function (event) {
        var data = JSON.parse(event.data.toString());
        var login = data["login"];
        var text = data["text"];
        var date = data["date"];
        var status = data["status"];
        if (status == "INFO"){
            if (text.search("joined") >= 0){
                addOnlineUser(text.split(" ")[0])
            }
            if (text.search("left") >= 0){
                removeOnlineUser(text.split(" ")[0])
            }
        }
        if (text[0]=='@'){
            var p = text.search(" ");
            var to = text.substr(1,p);
            text = text.substr(p+1);
            if (status=="DIRECT") {
                text = "<font style='color:#ae81ff;'>" + to + "</font>" + text;
            }else{
                text = "<font style='color:#66d9ef;'>" + to + "</font>" + text;
            }
        }
        showMessage(login,date,text,status);
    }
    ws.onclose = function (event) {
        console.log("<< Close connection");
    }
};
function sendMessage() {
        if (window.event.keyCode==13) {
            var message = document.getElementById("chatinput").value;
            ws.send(message);
            var input = document.getElementById("chatinput");
            input.value = "";
        }
}
function showMessage(login, date, text, status) {
    var messages = document.getElementById("messages");
    var msgStatus = "message";
    if (status=="INFO"){
        msgStatus = "message info";
    }
    if (status=="ME"){
        msgStatus = "message me";
    }
    var newMessage = document.createElement("div");
    newMessage.setAttribute("class",msgStatus);
        var nick = document.createElement("span");
        nick.setAttribute("class","nick");
            if (msgStatus != "message info") {
                var trip = document.createElement("span");
                trip.setAttribute("class", "trip");
                trip.innerHTML = date + " ";
                nick.appendChild(trip);
            }
            var a = document.createElement("a");
            a.setAttribute("onclick","directMessage('"+login+"');");
            a.innerHTML = login;
            nick.appendChild(a);
    newMessage.appendChild(nick);
    var pre = document.createElement("pre");
    pre.setAttribute("class","text");
    pre.innerHTML = text;
    newMessage.appendChild(pre);
    messages.appendChild(newMessage);
}

function deleteMessages() {
    var messages = document.getElementById("messages");
    messages.innerHTML = "";
}

function addOnlineUser(user) {
    var list = document.getElementById("users");
    var li = document.createElement("li");
    li.setAttribute("id",user);
    li.innerHTML = "<a onclick='deleteMessages('"+user+"')'>"+user+"</a>";
    list.appendChild(li);
}

function removeOnlineUser(user) {
    var elem = document.getElementById(user);
    elem.remove();
}

function directMessage(user) {
    var input = document.getElementById("chatinput");
    input.value = "@"+user+" "+input.value;
}