/**
 * Created by Пользователь on 13.03.2015.
 */
$(document).ready(onLoad);
function onLoad() {
    document.getElementById("button").onclick = function() {
       var fields = (document).getElementsByName("wish");
       for(var i=0;i<fields.length;i++) {
           if(fields[i].classList.contains("hidden")) {
               fields[i].classList.remove("hidden");
               break;
           }
       }
   };
}
