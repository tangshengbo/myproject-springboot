/**
 * Created by Administrator on 2017/1/11.
 */
$(function () {

    $("#sangForm").submit(function (e) {
        e.preventDefault();

        var textArea = $("#sangForm").find('textarea[name="text"]');
        var text = textArea.val();

        sendSpittle(text);
        textArea.val('');
    });

    var sock = new SockJS("/endpointTang");
    var stomp = Stomp.over(sock);
    
    stomp.connect('guest', 'guest', function (frame) {
        stomp.subscribe("/user/queue/notifications", handleNotification);
    });

    function handleNotification(message) {
        $("#output").append("<b>Received： " + message.body + "</b><br/>")
    }

    function sendSpittle(text) {
        stomp.send("/chat", {}, text);
    }

    $("#stop").click(function () {
        sock.close();
    });


})