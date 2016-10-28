var form = document.getElementById("queryForm");
var code = document.getElementsByClassName("captcha")[0];
/*code.src="http://haoma.baidu.com/captcha/image/fcf759174ebdb9b16dea3f5f0236e776dfc625d3/";*/
console.log(code.src);
var phoneNumber = document.getElementById("id_phone");
phoneNumber.value="10010";
var codeResult = document.getElementById("id_captcha_1");
codeResult.value = 8;
//form.submit();