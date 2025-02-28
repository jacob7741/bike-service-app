var today = new Date();

var el = document.getElementById('data');
el.innerHTML = today.toDateString();

var cr = document.getElementById('footer');
cr.innerHTML = today.getFullYear() + ' Copyright©';

