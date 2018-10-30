/**
 * 护士websocket连接类
 */
(function () {
    function QueueMessage(options) {
        var defaultOption = {
            target: "ws://" + zqUtils.getHost() + "/" + zqUtils.getProjectName() + "/websocket/queue"
        };
        this.options = $.extend(defaultOption, options);
        this.webSocket = null;
        this.addBtn = $("#printBtn");
    }

    /**
     * 开启连接，获取列表信息
     */
    QueueMessage.prototype.start = function () {
        var ctx = this;
        this.webSocket = new ZQWebSocket({
            target: ctx.options.target,
            onopen: function (event) {
                var json = {
                    type: "list"
                }
                ctx.webSocket.send(JSON.stringify(json));
            },
            sendbefore: function(){
                ctx.addBtn.button('loading');
                $("#patientCard").val("");
                $("#patientName").val("");
            },
            onmessage: function (event) {
                ctx.addBtn.button('reset');
                if (event.data) {
                    ctx.handle($.parseJSON(event.data));
                }
            }
        });
        ctx.bindEvent();
    };

    QueueMessage.prototype.close = function () {
        if (this.webSocket) {
            this.webSocket.close();
        }
    };

    /**
     * 处理消息
     * @param data
     */
    QueueMessage.prototype.handle = function (data) {
        this.flushData(data);
    };

    /**
     * 刷新数据
     * @param data
     */
    QueueMessage.prototype.flushData = function(data){

        var queueData = $.extend(this.getTableConfig(), {
            columns:
                [
                    {field: "number", title: "排队号码", align: "center", valign: "middle"},
                    {field: "patientCard", title: "病人卡号", align: "center", valign: "middle"},
                    {field: "patientName", title: "病人姓名", align: "center", valign: "middle"},
                    {field: "createTime", title: "取号时间", align: "center", valign: "middle"}
                ],
            data: []
        });

        var hisQueueData = $.extend(this.getTableConfig(), {
            columns:
                [
                    {field: "number", title: "排队号码", align: "center", valign: "middle"},
                    {field: "patientCard", title: "病人卡号", align: "center", valign: "middle"},
                    {field: "patientName", title: "病人姓名", align: "center", valign: "middle"},
                    {field: "roomName", title: "诊室", align: "center", valign: "middle"},
                    {field: "createTime", title: "取号时间", align: "center", valign: "middle"}
                ],
            data: []
        });

        var queue = $('#_table1').bootstrapTable(queueData);
        var hisQueue = $('#_table').bootstrapTable(hisQueueData);

        $('#_table1').bootstrapTable("load", data.data.queue.data);
        $('#_table').bootstrapTable("load", this.getHisQueueData(data.data.hisQueue.data).called.reverse());
    };

    /**
     * 获取已经诊断的信息
     * @param data
     * @returns {{calling: Array, called: Array}}
     */
    QueueMessage.prototype.getHisQueueData = function(data){
        var result = {
            calling : [],
            called: []
        };
        for (var i = 0; i < data.length; i++){
            var info = data[i];
            if(info.status == 2){
                result.calling.push(info);
            }
            if(info.status > 2){
                result.called.push(info);
            }
        }
        return result;
    };

    QueueMessage.prototype.bindEvent = function () {
        var ctx = this;
        ctx.addBtn.click(function () {

            var json = {
                type: "add",
                data: $.formJson('_addForm')
            }
            if ($('#_addForm').validate()) {
                ctx.webSocket.send(JSON.stringify(json));
            }
        });
    };

    QueueMessage.prototype.getTableConfig = function () {
        return {
            cache: false,
            height: 350,
            striped: true,
            pagination: true,
            pageSize: 6,
            pageNumber: 1,
            paginationLoop:false,
            paginationPreText:'上一页',
            paginationNextText:'下一页',
            pageList: [10, 20, 50, 100, 200, 500],
            sidePagination: 'client',
            clickToSelect: true
        }
    };

    /**
     * 保证一个页面只有一个websocket连接
     * @returns {QueueCall}
     */
    QueueMessage.newInstance = function () {
        if (window.queueMessage) {
            window.queueMessage.close();
        }
        if (window.queueCall) {
            window.queueCall.close();
        }
        if (window.queueScreen) {
            window.queueScreen.close();
        }

        window.queueMessage = new QueueMessage();
        return window.queueMessage;
    };

    window.QueueMessage = QueueMessage;
})();