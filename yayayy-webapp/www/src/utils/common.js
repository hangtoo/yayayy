export default {
	JSONparse : function(str) {
		if(window.JSON){
			return JSON.parse(str);
		}
		return '';
	},
	JSONstringify : function(obj){
		if(window.JSON){
			return JSON.stringify(obj);
		}
		return '';
	},
	getKey:function(n){
	  var keystr='';
	  var key = new Array(n);
	  for(var i=0;i<n;i++){
	    key[i]=Math.floor(Math.random()*10);
	    //key[i]=i%10;
	    keystr=keystr+key[i];
	  }
	  return keystr;
	}

}
