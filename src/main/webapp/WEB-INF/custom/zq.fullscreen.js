//全屏及退出全屏方法
function fullScreen(el) {
    var rfs = el.requestFullScreen || el.webkitRequestFullScreen || el.mozRequestFullScreen || el.msRequestFullScreen,
        wscript;

    if(typeof rfs != "undefined" && rfs) {
        rfs.call(el);
        return;
    }

    if(typeof window.ActiveXObject != "undefined") {
        wscript = new ActiveXObject("WScript.Shell");
        if(wscript) {
            wscript.SendKeys("{F11}");
        }
    }
}

function exitFullScreen(el) {
    var el= document,
        cfs = el.cancelFullScreen || el.webkitCancelFullScreen || el.mozCancelFullScreen || el.exitFullScreen,
        wscript;

    if (typeof cfs != "undefined" && cfs) {
        cfs.call(el);
        return;
    }

    if (typeof window.ActiveXObject != "undefined") {
        wscript = new ActiveXObject("WScript.Shell");
        if (wscript != null) {
            wscript.SendKeys("{F11}");
        }
    }
}