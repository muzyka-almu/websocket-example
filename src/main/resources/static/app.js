var stompClient = null;
var sendToLink = "";

function setConnected(connected) {
    $("#connectNotification").prop("disabled", connected);
    $("#connectMonologue").prop("disabled", connected);
    $("#connectDialog").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connectNotification() {
    sendToLink = "/app/notification";
    connect("/topic/greetings")
}

function connectMonologue() {
    sendToLink = "/app/monologue";
    connect("/user/queue/individual/message");
}

function connectDialog() {
    sendToLink = "/app/dialog";
    connect("/user/"+$("#connectAs").val()+"/personal");
}

function connect(subscribeLink) {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe(subscribeLink, function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send(sendToLink, {}, JSON.stringify({'value': $("#name").val()}));
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $( "#connectNotification" ).click(connectNotification);
    $( "#connectMonologue" ).click(connectMonologue);
    $( "#connectDialog" ).click(connectDialog);
    $( "#disconnect" ).click(disconnect);
    $( "#send" ).click(sendName);
});