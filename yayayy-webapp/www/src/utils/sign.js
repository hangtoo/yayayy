import {MD5_KEY} from '../config/md5_key';
import util from './common';
import {SparkMD5} from './md5';

function isEmpty(obj) {
	if(typeof obj == 'number' && obj == 0){
		return false;
	}
	if (!obj) {
		return true;
	}
	if (parseJsonToString(obj) == '{}'){
			return true;
		}
		return false;
	}


	//此方法数组不算对象
	function isObject(obj) {
		if (obj instanceof Object) {
			if(obj instanceof Array){
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	function parseJsonToString(obj) {
		return util.JSONstringify(obj);
	}

	//生序排列对象key
	function objKeySort(obj) {
		//不是对象直接返回
		if (!isObject(obj)) {
			return obj;
		}
		var newkey = Object.keys(obj).sort();
		var newObj = {};
		for (var i = 0; i < newkey.length; i++) {
			newObj[newkey[i]] = obj[newkey[i]];
		}
		return newObj;
	}

	//递归对象以及下面所有属性对象key生序排列
	function objSort(obj) {
		for (let key in obj) {
			if (isObject(obj[key])) {
				obj[key] = objSort(obj[key]);
			}
		}
		return objKeySort(obj);
	}

	function getSign(paramObj) {
		let signStr = getSignWithoutMd5(paramObj);
		return md5(signStr);
	}

	function getSignWithoutMd5(paramObj) {
		if (isEmpty(paramObj)) {
			return "";
		}
		let signStr = genSignStr(objKeySort(paramObj));
		return signStr;
	}

	function genSignStr(obj) {
		let arr = [];
		for (let key in obj) {
			if (key == "sign" || key == "sign_type") {
				continue;
			}
			if (isEmpty(obj[key])) {
				continue;
			}
			if(!isObject(obj[key])){
				arr.push(key + "=" + parseJsonToString(obj[key]).replace(/\"/g,''));
			} else {
				arr.push(key + "=" + parseJsonToString(objSort(obj[key])));
			}
		}
		arr.push("key=" + MD5_KEY);
		let signStr = arr.join("&");
		return signStr;
	}

	function md5(str){
		return SparkMD5.hash(str);
	}
	export {
		getSign,
		getSignWithoutMd5
	};