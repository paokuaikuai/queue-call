(function () {
    function ZqUtils() {}

    /**
     * 获取项目路径
     * @returns {string}
     */
    ZqUtils.prototype.getRootPath =  function(){
        var curPath = window.document.location.href;
        var pathName = window.document.location.pathname;
        var pos = curPath.indexOf(pathName);
        var localhostPaht = curPath.substring(0,pos);
        var projectName = pathName.substring(0,pathName.substr(1).indexOf('/')+1);
        return localhostPaht + projectName;
    };

    /**
     * 获取项目名
     * @returns {string}
     */
    ZqUtils.prototype.getProjectName =  function(){
        var pathName=window.document.location.pathname;
        return pathName.substring(1, pathName.substr(1).indexOf('/') + 1);
    };

    /**
     * 获取host
     * @returns {string}
     */
    ZqUtils.prototype.getHost =  function(){
        return window.location.host;
    };

    window.zqUtils = window.zqUtils || new ZqUtils();
})();