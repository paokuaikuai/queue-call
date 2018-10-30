/**
 * websocket连接类
 * 依赖
 * Jquery
 * zq.utils.js
 */
(function () {
    function ZQWebSocket(options) {
        var defaultOption = {
            target: "",
            onmessage: null,
            onerror: null,
            onopen: null,
            onclose: null,
            onmessage: null,
            sendbefore: null
        };
        this.options = $.extend(defaultOption, options);
        this.webSocket = null;
        this.stop = false;
        this.init();
        this.heartCheck();
    };

    /**
     * 初始化
     */
    ZQWebSocket.prototype.init = function () {
        var target = this.options.target;
        if (!window.WebSocket) {
            console.log("您的浏览器不支持websocket");
            return;
        }
        if ("WebSocket" in window) {
            this.webSocket = new WebSocket(target);
        } else if ("MozWebSocket" in window) {
            this.webSocket = new MozWebSocket(target);
        } else {
            alert('您的浏览器不支持websocket！');
            return;
        }
        this.bindEvent();
    };
    /**
     * 健康检查
     */
    ZQWebSocket.prototype.heartCheck = function () {
        var ctx = this;
        var heartCheck = {
            timeout: 20000,
            timeoutObj: null,
            serverTimeoutObj: null,
            reset: function () {
                clearTimeout(this.timeoutObj);
                return this;
            },
            start: function () {
                var self = this;
                this.timeoutObj = setInterval(function () {
                }, self.timeout);
            }
        }
        heartCheck.start();
    };

    /**
     * 绑定事件
     */
    ZQWebSocket.prototype.bindEvent = function () {
        var ctx = this;
        this.webSocket.onmessage = function (event) {
            console.log(event);
            if (ctx.options.onmessage) {
                ctx.options.onmessage(event);
            }
        };

        this.webSocket.onerror = function (event) {
            console.log(event);
            ctx.reconnection();
            if (ctx.onerror) {
                ctx.options.onerror(event);
            }
        };

        /**
         * 连接开启回调
         * @param event
         */
        this.webSocket.onopen = function (event) {
            console.log(event);
            if (ctx.options.onopen) {
                ctx.options.onopen(event);
            }
        };

        /**
         * 连接关闭回调
         * @param event
         */
        this.webSocket.onclose = function (event) {
            console.log(event);
            ctx.reconnection();
            if (ctx.options.onclose) {
                ctx.options.onclose(event);
            }
        };

        /**
         * 接收消息回调
         * @param event
         */
        this.webSocket.onmessage = function (event) {
            console.log(event);
            if (event.data == "heartCheck") {
                console.log("heart check...");
                return;
            }
            if (ctx.options.onmessage) {
                ctx.options.onmessage(event);
            }
        };

        //断线重连
        this.reconnection = function () {
            if (this.stop) {
                return;
            }
            console.log("reconnection...");
            console.log(this.webSocket);
            var ctx = this;
            //与服务器已经建立连接
            if (this.webSocket && this.webSocket.readyState == 1) {
                clearTimeout(this.t);
            } else {
                //已经关闭了与服务器的连接
                if (this.webSocket.readyState == 3) {
                    ctx.init();
                }
                //0正尝试与服务器建立连接,2正在关闭与服务器的连接
                ctx.t = setTimeout(function () {
                    ctx.reconnection();
                }, 1000);
            }
        }
    };

    /**
     * 关闭连接
     */
    ZQWebSocket.prototype.close = function () {
        this.stop = true;
        if (this.webSocket) {
            this.webSocket.close();
        }
    };

    /**
     * 发送消息
     * @param message
     */
    ZQWebSocket.prototype.send = function (message) {
        if(this.options.sendbefore){
            this.options.sendbefore();
        }
        this.webSocket.send(message);
    };

    window.ZQWebSocket = ZQWebSocket;
})();