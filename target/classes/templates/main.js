var today = new Date();

var el = document.getElementById('data');
el.innerHTML = today.toDateString();

var cr = document.getElementById('footer');
cr.innerHTML = today.getFullYear() + ' Copyright©';


function showContent(id) {
    var contents = document.getElementsByClassName('content');
    for (var i = 0; i < contents.length; i++) {
        contents[i].style.display = 'none';
    }
    document.getElementById(id).style.display = 'block';
};