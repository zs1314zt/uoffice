define('officeList',function(e,a,t){var r=function(e){var a=document.createElement("a");return a.href=e,{source:e,protocol:a.protocol.replace(":",""),host:a.hostname,port:a.port,query:a.search,params:function(){for(var e,t={},r=a.search.replace(/^\?/,"").split("&"),p=r.length,c=0;p>c;c++)r[c]&&(e=r[c].split("="),t[e[0]]=e[1]);return t}(),file:(a.pathname.match(/\/([^\/?#]+)$/i)||[,""])[1],hash:a.hash.replace("#",""),path:a.pathname.replace(/^([^\/])/,"/$1"),relative:(a.href.match(/tps?:\/\/[^\/]+(.+)/)||[,""])[1],segments:a.pathname.replace(/^\//,"").split("/")}},p=r(window.location.href).params.officeType,c=$(".J_page_item"),o=+p-1||0;c.removeClass("current_page_item"),c.eq(o||0).addClass("current_page_item")});
