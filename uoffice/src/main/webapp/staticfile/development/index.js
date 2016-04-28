
define('msg',function(require,exports,module){
    var _msg = 'not set msg!';
 
    exports.setMsg = function(msg){
        _msg = msg;
    }
 
    exports.getMsg =function(){
        return _msg;
    }
});
define('man',['msg'],function(require,exports,module){
    var msg = require('msg');
 
    var _name = 'tom';
    var _age = '20';
 
    exports.myName = _name;
 
    exports.say = function(){
        return msg.getMsg();
    }
 
    exports.getName = function(){
        return _name;
    }
 
    exports.getAge = function(){
        return _age;
    }
});
define('index',['man'],function(require,exports,module){
    var man = require('man');

    var $ = function(id){
        return document.getElementById(id);
    }
 
    exports.init = function(){
        var s1 = $('s1');
        var s2 = $('s2');
        var s3 = $('s3');
 
        var name = man.getName();
        var age = man.getAge();
        var msg = man.say();
 
        s1.innerHTML = name;
        s2.innerHTML = age;
        s3.innerHTML = msg;
    }
});
