function deleteBook(isbn){
	$.ajax({
        url: "books/" + isbn,
        type: "DELETE"
    }).then(function() {
    	var trId = "#tr" + isbn;
    	$(trId).css( "display", "none" )
    });
	return false;
}

function goToUserProfile(username) {
    //document.getElementById('profilePageLink').click();
    document.location.href = "users/" + username + "?r=" + (Math.random());
    // var Http = new XMLHttpRequest();
    // var url = "users/"+username;
    // Http.open("GET", url);
    // Http.send();
}

function buyBook(username, isbn){
    $.ajax({
        url: "users/" + username + "/orders",
		data: {"isbn":isbn, "quantity": $("#quantity"+isbn).val()},
        type: "POST",
		success: goToUserProfile(username),
		error: goToUserProfile(username)
    });
}
function deleteOrder(orderNo){
    $.ajax({
        url: "/orders/" + orderNo,
        type: "DELETE"
    }).then(function() {
        var trId = "#tr" + orderNo;
        $(trId).css( "display", "none" )
    });
    return false;
}

function approveOrder(orderNo){
    $.ajax({
        url: "/orders/" + orderNo,
        type: "POST"
    }).then(function() {
        window.location.reload();
        // var tdId = "order-"+orderNo;
        // document.getElementById(tdId).innerHTML = "1";
    });
    return false;
}

function deleteComment(isbn, id){
    id *= 1;
    $.ajax({
        url: isbn + "/comments/" + id,
        type: "DELETE"
    }).then(function() {
        var div = "#comment" + id;
        $(div).css( "display", "none" )
    });
    return false;
}