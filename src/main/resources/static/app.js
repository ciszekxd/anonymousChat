var stompClient = null;
var serverPath = "/app/hello";
var userId = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#chat").html("");
}

function connect() {
    var socket = new SockJS('/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect('', '', function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/message-channel/content', function (content) {
            messageProcessor(JSON.parse(content.body));
        });
        stompClient.send(serverPath, {}, JSON.stringify({'status': 'new_user'}));
    });
}

function messageProcessor(content){
    console.log(content);
    if (content.type === "status") {
        handleStatus(content)
    }else if(content.type === "message"){
        displayMessage();
    }else if(content.type === "new_user_id"){
        userId = content.user_id;
        console.log("new user id set: " + userId)
    }
}

function displayMessage(){

}

function handleStatus(content){

}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
}

function showGreeting(message) {
    console.log("fire")
    $("#chat").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});