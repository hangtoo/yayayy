import config from '../config/config';

export default {
	log (msg) {
		if(config.debug){
			window.console && console.log(msg);
		}
	}
}
