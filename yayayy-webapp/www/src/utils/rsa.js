import {JSEncrypt} from 'jsencrypt';
import public_key from '../config/rsa_public_key';
import private_key from '../config/rsa_private_key';

var e = new JSEncrypt();
e.setPublicKey(public_key);
e.setPrivateKey(private_key);

export default {

	encryptPublic:function(str){
	  return e.encrypt(str);
	},
	decryptPrivate:function(str){
	  return e.decrypt(str);
	}

};
