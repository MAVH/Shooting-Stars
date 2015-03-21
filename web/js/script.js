document.addEventListener("DOMContentLoaded", function(event) {
    document.getElementById("button_add_wish").classList.add("btn-default");
    document.getElementById("button_add_wish").onclick = function() {
        var fields = (document).getElementsByName("wish");
        for(var i=0;i<fields.length;i++) {
            if(fields[i].classList.contains("hidden")) {
                fields[i].classList.remove("hidden");
                if(i == fields.length - 1) {
                    document.getElementById("button_add_wish").classList.add("hidden");
                }
                break;
            }
        }
        document.getElementById("add_wish_form").classList.remove("hidden");
        document.getElementById("wish_table").classList.add("table-striped");
        document.getElementById("wish_table").classList.add("table-hover");

    };
});

