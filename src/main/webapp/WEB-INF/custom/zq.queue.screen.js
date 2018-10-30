/**
 * 屏幕websocket连接
 */
(function () {
    function QueueScreen(options) {
        var defaultOption = {
            target: "ws://" + zqUtils.getHost() + "/" + zqUtils.getProjectName() + "/websocket/screen"
        };
        this.options = $.extend(defaultOption, options);
        this.webSocket = null;
        this.interval = null;
    }

    QueueScreen.prototype.start = function () {
        var ctx = this;
        this.webSocket = new ZQWebSocket({
            target: ctx.options.target,
            onopen: function (event) {
                var json = {
                    type: "screen"
                }
                ctx.webSocket.send(JSON.stringify(json));
            },
            onmessage: function (event) {
                if (event.data) {
                    ctx.handle($.parseJSON(event.data));
                }
            }
        });
    };

    QueueScreen.prototype.close = function () {
        if (this.webSocket) {
            this.webSocket.close();
        }
        this.clearInterval();
    };

    /**
     * 处理消息
     * @param data
     */
    QueueScreen.prototype.handle = function (data) {
        this.flushData(data);
    };

    /**
     * 更新数据
     * @param data
     */
    QueueScreen.prototype.flushData = function (data) {
        var queueData = $.extend(this.getTableConfig(), {
            columns:
                [
                    {field: "typeName", title: "业务类型", align: "center", valign: "middle"},
                    {field: "number", title: "排队号码", align: "center", valign: "middle"},
                    {field: "patientName", title: "病人姓名", align: "center", valign: "middle"},
                    {field: "createTime", title: "取号时间", align: "center", valign: "middle"},
                    {field: "roomName", title: "诊室", align: "center", valign: "middle"}
                ],
            data: []
        });
        var queue = $('#screenTable').bootstrapTable(queueData);

        $('#screenTable').bootstrapTable("load", data.data.calling);
        this.speaking(data.data.speaking);
        this.startInterval();
    };

    QueueScreen.prototype.getTableConfig = function () {
        return {
            cache: false,
            height: "100%",
            striped: true,
            pagination: true,
            pageSize: 8,
            pageNumber: 1,
            paginationLoop: true,
            pageList: [10, 20, 50, 100, 200, 500],
            sidePagination: 'client',
            clickToSelect: true,
            rowStyle: {
                css: {"height": "100px"}
            }
        }
    };

    /**
     * 语音播报
     * @param info
     */
    QueueScreen.prototype.speaking = function (info) {
        if(info){
            this.speak("请," + info.number + "号," + info.patientName + "到" +  info.roomName + "就诊")
        }
    };

    QueueScreen.prototype.speak = function (text) {
        var speech = new SpeechSynthesisUtterance();
        speech.text = text;
        speech.volume = 1;
        speech.rate = 1;
        speech.pitch = 1;
        window.speechSynthesis.speak(speech);
    };

    /**
     * 每隔10秒滚动屏幕
     */
    QueueScreen.prototype.startInterval = function () {
        this.clearInterval();
       this.interval = setInterval(function(){
           $(".page-link").last().trigger("click");
        },10000);
    };

    /**
     * 清除定时器
     */
    QueueScreen.prototype.clearInterval = function(){
        if(this.interval){
            clearInterval(this.interval);
        }
    };


    QueueScreen.newInstance = function () {
        if (window.queueMessage) {
            window.queueMessage.close();
        }
        if (window.queueCall) {
            window.queueCall.close();
        }
        if (window.queueScreen) {
            window.queueScreen.close();
        }
        window.queueScreen = new QueueScreen();
        return window.queueScreen;
    };

    window.QueueScreen = QueueScreen;
})();