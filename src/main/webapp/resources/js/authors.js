$(document).ready(function () {
    // Create a "close" button and append it to each list item
    var myNodelist = document.getElementsByClassName("closeLi");
    var i;
    for (i = 0; i < myNodelist.length; i++) {
        var span = document.createElement("SPAN");
        var txt = document.createTextNode("\u00D7");
        span.className = "close";
        span.appendChild(txt);
        myNodelist[i].appendChild(span);
    }

    // Click on a close button to hide the current list item
    var close = document.getElementsByClassName("close");
    var i;
    for (i = 0; i < close.length; i++) {
        close[i].onclick = function() {
            var div = this.parentElement;
            div.style.display = "none";
            $(div).removeAttr("id");
        }
    }

    $("#myInput").autocomplete({
        source: "/author/search",
        minLength: 2,
        messages: {
            noResults: '',
            results: function() {},
            __response: function() {}
        },
        select: function(event, ui) {
            $("#myInput").attr("authorId", ui.item.data);
            return false;
        }
    });

    $('#myInput').keydown(function (event) {
        var keypressed = event.keyCode || event.which;
        if (keypressed == 13) {
            newElement();
        }
    });
});

// Create a new list item when clicking on the "Add" button
function newElement() {
    var li = document.createElement("li");
    li.setAttribute("class", "closeLi");
    li.setAttribute("id", $("#myInput").attr("authorId"));
    var inputValue = document.getElementById("myInput").value;
    var t = document.createTextNode(inputValue);
    li.appendChild(t);
    if (inputValue === '') {
        alert("You must write something!");
    } else {
        document.getElementById("myUL").appendChild(li);
    }
    document.getElementById("myInput").value = "";

    var span = document.createElement("SPAN");
    var txt = document.createTextNode("\u00D7");
    span.className = "close";
    span.appendChild(txt);
    li.appendChild(span);

    var close = document.getElementsByClassName("close");
    for (i = 0; i < close.length; i++) {
        close[i].onclick = function() {
            var div = this.parentElement;
            div.style.display = "none";
            $(div).removeAttr("id");
        }
    }
}