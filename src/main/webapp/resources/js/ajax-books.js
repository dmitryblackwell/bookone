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

function goToUserProfile() {
    //document.getElementById('profilePageLink').click();
    document.location.href = "/getCurrentUser";
    // var Http = new XMLHttpRequest();
    // var url = "users/"+username;
    // Http.open("GET", url);
    // Http.send();
}

function buyBook(username, isbn){
    var quantity = $("#quantity"+isbn).val();
    if (quantity == undefined)
        quantity = 1;
    $.ajax({
        url: "/users/" + username + "/orders",
		data: {"isbn":isbn, "quantity": quantity},
        type: "POST",
		success: goToUserProfile(),
		error: goToUserProfile()
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
        url: "comments/" + id,
        type: "DELETE"
    }).then(function() {
        var div = "#comment" + id;
        $(div).css( "display", "none" )
    });
    return false;
}

function saveBook() {
    $('<select>').attr({
        multiple: 'multiple',
        name: 'authors',
        id: 'authorsSelect'
    }).appendTo('#bookform');

    $('#myUL li').each(function(i) {
        addAuthorToSelect($(this).attr("id"));
    });

    document.getElementById('bookform').submit();
}

function addAuthorToSelect(author) {
    $('<option>').attr({
        value: author,
        selected: 'selected'
    }).appendTo('#authorsSelect')
}
