/**
 * 医生呼叫websocket类
 */
(function () {
    function QueueCall(options) {
        var defaultOption = {
            target: "ws://" + zqUtils.getHost() + "/" + zqUtils.getProjectName() + "/websocket/call"
        };
        this.options = $.extend(defaultOption, options);
        this.webSocket = null;
    }

    /**
     * 开启连接并且获取called信息
     */
    QueueCall.prototype.start = function () {
        var ctx = this;
        this.webSocket = new ZQWebSocket({
            target: ctx.options.target,
            onopen: function(event){
                var json = {
                    type: "called"
                }
                ctx.webSocket.send(JSON.stringify(json));
            },
            onmessage: function (event) {
                if (event.data) {
                    ctx.handle($.parseJSON(event.data));
                }
            }
        });
        ctx.bindEvent();
    };

    QueueCall.prototype.close = function () {
        if (this.webSocket) {
            this.webSocket.close();
        }
    }

    /**
     * 接收消息
     * @param data
     */
    QueueCall.prototype.handle = function (data) {
        if(data.type == "called" || data.type == "calling" || data.type == "skip"){
            this.changeView(data);
        }
        if(data.type == "waiting"){
            $("#waiting").html(data.data);
        }
    };

    /**
     * 更新界面
     * @param data
     */
    QueueCall.prototype.changeView = function(data){
        $("#diagnosed").html(data.data.diagnosed);
        $("#waiting").html(data.data.waiting);
        var info = data.data.info || {
            number: "",
            patientName: "",
            patientCard: ""
        };
        $("#number").val(info.number);
        $("#patientName").val(info.patientName);
        $("#patientCard").val(info.patientCard);
    }

    /**
     * 绑定事件
     */
    QueueCall.prototype.bindEvent = function(){
        var ctx = this;
        $("#callBtn").click(function(){
            var json = {
                type: "calling"
            }
            ctx.webSocket.send(JSON.stringify(json));
        });

        $("#reCallBtn").click(function(){
            var json = {
                type: "recall"
            }
            ctx.webSocket.send(JSON.stringify(json));
        });

        $("#crossBtn").click(function(){
            var json = {
                type: "skip"
            }
            ctx.webSocket.send(JSON.stringify(json));
        });
    };

    /**
     * 保证一个页面只有一个websocket连接
     * @returns {QueueCall}
     */
    QueueCall.newInstance = function(){
        if (window.queueMessage) {
            window.queueMessage.close();
        }
        if (window.queueCall) {
            window.queueCall.close();
        }
        if (window.queueScreen) {
            window.queueScreen.close();
        }
        window.queueCall = new QueueCall();
        return window.queueCall;
    }

    window.QueueCall = QueueCall;
})();