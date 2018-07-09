/**
 * Created by CHENKAI on 2018/2/12.
 */
function fdate(time){
    console.log("time=",time);
    if(time.length>20){
    	 var arr1=time.substring(0,10);
    	 var arr2=time.substring(11,19);
    	 return arr1+' '+arr2;
    }
    return time;
   
}

export { fdate }
