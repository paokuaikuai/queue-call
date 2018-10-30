(function () {
	 function dateFun() {
		this.year = (new Date()).getFullYear();
		this.month = (new Date()).getMonth()+1;
		this.day = (new Date()).getDate();
		this.hours = (new Date()).getHours();
		this.mintes = (new Date()).getMinutes();
		this.seconds = (new Date()).getSeconds();
		this.weekArr=['星期日','星期一','星期二','星期三','星期四','星期五','星期六'];
		this.week = (new Date()).getDay();
		this.weekends= this.weekArr[this.week];
		this.YMD = (new Date()).toLocaleDateString();
		this.HMS = this.hours+':'+this.mintes+':'+this.seconds;
		
    }
	dateFun.prototype.getFull = function(){
		return this.HMS+'    '+this.YMD+'    '+this.weekends;
	};
	dateFun.prototype.getnowFull = function(){
		var hours  = (new Date()).getHours()<10  ? "0"+(new Date()).getHours() : (new Date()).getHours();
		var minutes = (new Date()).getMinutes()<10 ? "0"+(new Date()).getMinutes() : (new Date()).getMinutes();
		var seconds = (new Date()).getSeconds()<10 ? "0"+(new Date()).getSeconds() : (new Date()).getSeconds();
		return hours+':'+minutes+':'+seconds+'&nbsp;&nbsp;&nbsp;&nbsp;'
		+(new Date()).toLocaleDateString()+'&nbsp;&nbsp;&nbsp;&nbsp;'+this.weekends+'&nbsp;&nbsp;&nbsp;&nbsp;';
	};
	
	
	window.dateFun = window.dateFun || new dateFun();
})();