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

function toggleRepairDetails(radio) {
    var repairDetails = document.getElementById('repairDetails');
    repairDetails.style.display = radio.checked ? 'block' : 'none';
}

function validateForm() {
    var radios = document.querySelectorAll("input[type='radio'][name='serviceType']");
    var isChecked = Array.prototype.slice.call(radios).some(x => x.checked);

    return true;
}