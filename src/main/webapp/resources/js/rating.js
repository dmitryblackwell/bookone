
//initial setup
document.addEventListener('DOMContentLoaded', function(){
    addListeners();
    setRating();
});

function addListeners(){
    var stars = document.querySelectorAll('.star');
    [].forEach.call(stars, function(star, index){
        star.addEventListener('click', (function(idx){
            console.log('adding rating on', index);
            document.querySelector('.stars').setAttribute('data-rating',  idx + 1);
            $("#commentScore ").attr("value",idx+1);
            setRating();
        }).bind(window,index) );
    });
}

function setRating(){
    var stars = document.querySelectorAll('.star');
    var rating = parseInt( document.querySelector('.stars').getAttribute('data-rating') );
    $("#commentScore ").attr("value",rating);
    [].forEach.call(stars, function(star, index){
        if(rating > index){
            star.classList.add('rated');
        }else{
            star.classList.remove('rated');
        }
    });
}