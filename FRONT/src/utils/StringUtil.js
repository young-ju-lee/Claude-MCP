export default {   
    // 전화번호 마스킹 
    phoneFormatter(num){           
      if(num == null){
        return;
      }

      if(num.indexOf("82") == 0){  
        num = "0" + num.substr(2,num.length);
      }

      if(num.length == 12){
        num = num.substr(1,num.length);
      }      
      var formatNum = "";      
      if(num.length == 11 && num.indexOf('02') != 1 ){        
        //formatNum = num.replace(/(\d{3})(\d{4})(\d{4})/,"$1-$2-****");
        formatNum = num.replace(/(\d{3})(\d{0,2})(\d{2,4})(\d{4})/,"$1-$2**-$4");
      } else if(num.length == 8){          
          formatNum = num.replace(/(\d{4})(\d{4})/,"$1-****");
      } else {
        if(num.indexOf('02') == 1){
          num = num.substr(1,num.length);
        }
        if(num.indexOf('02') == 0){                      
          //formatNum = num.replace(/(\d{2})(\d{4})(\d{4})/,"$1-$2-****");
          formatNum = num.replace(/(\d{2})(\d{0,2})(\d{2,4})(\d{4})/,"$1-$2**-$4");
        } else {          
          formatNum = num.replace(/(\d{3})(\d{3})(\d{4})/,"$1-$2-****");
        }
      }          
      return formatNum;
    },
    // 고객명 마스킹
    cusNmFormatter(name){

      if(name == null){
        return;
      }

      name = name.substr(0,name.length-1) + '*';      
      return name;
    },
    // yyyy-mm-dd hh:mi
    dateFormatter(firstRsvWishDate, firstRsvWishTime){   
      if(firstRsvWishDate == null || firstRsvWishDate == ''){
        return;
      }
      if(firstRsvWishTime == null || firstRsvWishTime == ''){
        return;
      }
      let wishDate = '';
      let wishTime = '';
      if(firstRsvWishDate.length == 8){
        wishDate = 
        firstRsvWishDate.substr(0,4) + "-" + 
        firstRsvWishDate.substr(4,2) + "-" +
        firstRsvWishDate.substr(6,2)
      }
      if(firstRsvWishTime.length == 4){
        wishTime = 
        firstRsvWishTime.substr(0,2) + ":" + 
        firstRsvWishTime.substr(2,2)          
      }
      return wishDate + " " + wishTime;
    }, 
    // yyyy-mm-dd hh:mi:ss
    dateFormatter1(workFinisDateTime){   
      if(workFinisDateTime == null || workFinisDateTime == '')     {
        return;
      }
      let timeZone = 9 * 60 * 60 * 1000;
      let d = new Date(workFinisDateTime);
      let date = new Date(d.getTime() + timeZone).toISOString().split('T')[0];
      let time = d.toTimeString().split(' ')[0];      
      return date + ' ' + time
    },
    replaceStrAll(str, searchStr, replaceStr){
      if(str == ""){
        return "";
      }
      return str.split(searchStr).join(replaceStr);
    },
    cleanXSS(value){
      const filstr = "javascript,vbscript,expression,applet,meta,xml,blink,link,style,script,embed,object,iframe,frame,frameset,ilayer,layer,bgsound,title,base,eval,innerHTML,charset,document,string,create,append,binding,alert,msgbox,refresh,cookie,void,href,onabort,onactivae,onafterprint,onafterupdate,onbefore,onbeforeactivate,onbeforecopy,onbeforecut,onbeforedeactivate,onbeforeeditfocus,onbeforepaste,onbeforeprint,onbeforeunload,onbeforeupdate,onblur,onbounce,oncellchange,onchange,onclick,oncontextmenu,oncontrolselect,oncopy,oncut,ondataavailable,ondatasetchanged,ondatasetcomplete,ondblclick,ondeactivate,ondrag,ondragend,ondragenter,ondragleave,ondragover,ondragstart,ondrop,onerror,onerrorupdate,onfilterchange,onfinish,onfocus,onfocusin,onfocusout,onhelp,onkeydown,onkeypress,onkeyup,onlayoutcomplete,onload,onlosecapture,onmousedown,onmouseenter,onmouseleave,onmousemove,onmouseout,onmouseover,onmouseup,onmousewheel,onmove,onmoveend,onmovestart,onpaste,onpropertychange,onreadystatechange,onreset,onresize,onresizeend,onresizestart,onrowenter,onrowexit,onrowsdelete,onrowsinserted,onscroll,onselect,onselectionchange,onselectstart,onstart,onstop,onsubmit,onunload,prompt";

      if (value != "") { 
        this.replaceStrAll(filstr," ",""); 
        let st = filstr.split(","); 
        for( let x = 0; x < st.length; x++ ) { 
          value = this.replaceStrAll(value, st[x], "_"+st[x]+"_"); 
        } 
      }
      value = this.replaceStrAll(value, "<", "&lt;");
      value = this.replaceStrAll(value, ">", "&gt;");
      value = this.replaceStrAll(value, "(", "&#40;");
      value = this.replaceStrAll(value, ")", "&#41;");
      value = this.replaceStrAll(value, "'", "&#39;");            
      value = this.replaceStrAll(value, "\"","&#34;");
      value = this.replaceStrAll(value, "&lt;br&gt;", "<br>");
    
      return value;
    },
    //종료일자 구하기  (시작일자 기준 +dt 일자 구하기)
    lastDay(fullday, dt){
      var year  = fullday.substr(0,4);
      var month = fullday.substr(4,2);
      var day   = fullday.substr(6,2);

      var lstDt = new Date(year, month, 0).getDate();
      var e_day, to_Dt, r_day;
      
      ////희망종료일 구하기(23.11)      
      e_day = parseInt(day)+dt;

      if(e_day > lstDt){  // ex) 33 > 31 
        r_day = e_day - lstDt;
        month = parseInt(month)+1;
        if(month > 12){
          month = 1;
          year = parseInt(year)+1;
        }
        
        if(month <= 9){
          month = "0" + month;
        }
      }else {
        r_day = e_day;
      }

      if(r_day <= 9){
        r_day = "0" + r_day;
      }

      to_Dt = year + "-" + month + "-" + r_day;

      return to_Dt
    },
    //시작일자 구하기  (종료일자 기준 -dt 일자 구하기)
    strtDay(fullday, dt){
      var year  = fullday.substr(0,4);
      var month = fullday.substr(4,2);
      var day   = fullday.substr(6,2);

      var lstDt = new Date(year, (month-1), 0).getDate(); // 이전달의 마지막 날자 구하기
      var e_day, to_Dt, r_day;
      
      ////희망종료일 구하기(23.11)      
      e_day = parseInt(day)-dt;

      if(e_day <= 0){  // ex) lstDt - e_day
        if(e_day <0) e_day = e_day*-1;
        
        r_day = lstDt - e_day;
        month = parseInt(month)-1;
        if(month == 0 ){
          month = "12";
          year = parseInt(year)-1;
        }
        
        if(month <= 9){
          month = "0" + month;
        }
      }else {
        r_day = e_day;
      }

      if(r_day <= 9){
        r_day = "0" + r_day;
      }

      to_Dt = year + "-" + month + "-" + r_day;

      return to_Dt
    }
}