import aesjs from 'aes-js';

export default {
  encryptAES:function (keystr,data){
    var key = aesjs.utils.utf8.toBytes(keystr);
    var iv=key;

    // Convert text to bytes
    var text = JSON.stringify(data);

    //console.log('cbc:'+text);
    var textBytes = aesjs.utils.utf8.toBytes(text);
    var mm=(textBytes.length % 16);
    var value=16-mm;
    if(value==0){
      value=16;
    }
    var zeroo=String.fromCharCode(0);
    for(var i=0;i<value;i++){
      text=text+zeroo;
    }
    textBytes = aesjs.utils.utf8.toBytes(text);

    var aesEcb = new aesjs.ModeOfOperation.cbc(key,iv);
    var encryptedBytes = aesEcb.encrypt(textBytes);
    //console.log('cbc:'+encryptedBytes);

    // To print or store the binary data, you may convert it to hex
    var encryptedHex = aesjs.utils.hex.fromBytes(encryptedBytes);
    //console.log('cbc:'+encryptedHex);
    return encryptedHex;
  },
  decryptAES:function (keystr,text){
    var key = aesjs.utils.utf8.toBytes(keystr);
    var iv=key;

    var textBytes = aesjs.utils.hex.toBytes(text);

    var aesEcb = new aesjs.ModeOfOperation.cbc(key,iv);
    var decryptBytes = aesEcb.decrypt(textBytes);
    //console.log('decrypt cbc:'+decryptBytes);

    // To print or store the binary data, you may convert it to hex
    var decryptedText = aesjs.utils.utf8.fromBytes(decryptBytes);
    //console.log('decrypt cbc:'+decryptHex);
    return decryptedText;
  }

}
