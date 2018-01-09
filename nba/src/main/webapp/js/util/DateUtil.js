	Date.prototype.format = function(format)
	{
	    var o =
	    {
	        "M+" : this.getMonth()+1, //month
	        "d+" : this.getDate(),    //day
	        "H+" : this.getHours(),   //hour
	        "m+" : this.getMinutes(), //minute
	        "s+" : this.getSeconds(), //second
	        "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
	        "S" : this.getMilliseconds() //millisecond
	    }
	    if(/(y+)/.test(format))
	    format=format.replace(RegExp.$1,(this.getFullYear()+"").substr(4 - RegExp.$1.length));
	    for(var k in o)
	    if(new RegExp("("+ k +")").test(format))
	    format = format.replace(RegExp.$1,RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
	    return format;
	}
	/**
	 * 对日期进行截断
	 */
	Date.prototype.cut = function( type ){
		var y = this.getYear();
		var m = this.getMonth();
		var d = this.getDate();
		var h = this.getHours();
		var mi = this.getMinutes();
		var s = this.getSeconds();
		var ms = this.getMilliseconds();
		//
		if( type=="year" ){
			var date = new Date(y, 0, 1, 0, 0, 0);
			this.setTime( date.getTime() );
			return ;
		}
		if( type=="month" ){
			var date = new Date(y, m, 1, 0, 0, 0);
			this.setTime( date.getTime() );
			return ;
		}
		if( type=="day" || type=="date" ){
			var date = new Date(y, m, d, 0, 0, 0);
			this.setTime( date.getTime() );
			return ;
		}
		if( type=="hour" ){
			var date = new Date(y, m, d, h, 0, 0);
			this.setTime( date.getTime() );
			return ;
		}
		if( type=="minute" ){
			var date = new Date(y, m, d, h, mi, 0);
			this.setTime( date.getTime() );
			return ;
		}
		if( type=="second" ){
			var date = new Date(y, m, d, h, mi, s);
			this.setTime( date.getTime() );
			return ;
		}
		var date = new Date(y, m, d, h, mi, s, ms);
		this.setTime( date.getTime() );
		return ;
	}
	
	/**
	 * 对时间进行增减
	 */
	Date.prototype.add = function( type, amount ){
		var y = this.getFullYear();
		var m = this.getMonth();
		var d = this.getDate();
		var h = this.getHours();
		var mi = this.getMinutes();
		var s = this.getSeconds();
		var ms = this.getMilliseconds();
		//
		if( type=="year" ){
			var date = new Date(y+amount, m, d, h, mi, s, ms);
			this.setTime( date.getTime() );
			return ;
		}
		if( type=="month" ){
			var date = new Date(y, m+amount, d, h, mi, s, ms);
			this.setTime( date.getTime() );
			return ;
		}
		if( type=="day" || type=="date" ){
			var date = new Date(y, m, d+amount, h, mi, s, ms);
			this.setTime( date.getTime() );
			return ;
		}
		if( type=="hour" ){
			var date = new Date(y, m, d, h+amount, mi, s, ms);
			this.setTime( date.getTime() );
			return ;
		}
		if( type=="minute" ){
			var date = new Date(y, m, d, h, mi+amount, s, ms);
			this.setTime( date.getTime() );
			return ;
		}
		if( type=="second" ){
			var date = new Date(y, m, d, h, mi, s+amount, ms);
			this.setTime( date.getTime() );
			return ;
		}
		var date = new Date(y, m, d, h, mi, s, ms+amount);
		this.setTime( date.getTime() );
		return ;
	}