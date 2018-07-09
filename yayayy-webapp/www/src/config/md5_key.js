import {config} from '../config/config';
let MD5_KEY;
if(config.debug){
	MD5_KEY = "b31eac33a526f30e6762b3df0235d7c5";
} else {
	MD5_KEY = "b31eac33a526f30e6762b3df0235d7c5";
}

export { MD5_KEY }