const date = new Date();

var el = document.getElementById('data');
el.innerHTML = date.toDateString();

var cr = document.getElementById('footer');
cr.innerHTML = date.getFullYear() + ' Copyright©';


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

const currentDate = document.querySelector(".current-date");
daysTag = document.querySelector(".days");
prevNextIcon = document.querySelectorAll(".icons span");

currentYear = date.getFullYear();
currentMonth = date.getMonth();

const monthName = ["January", "February", "March", "April", "May", "June", "July", "September",
    "August", "October", "November", "December"];

const renderCalendar = () => {
    let lastDateOfMonth = new Date(currentYear, currentMonth + 1, 0).getDate();
    let liTag = "";

    for (let index = 1; index <= lastDateOfMonth; index++) {
        liTag += `<li>${index}</li>`;
    }

    daysTag.innerHTML = liTag;
    currentDate.innerHTML = `${monthName[currentMonth]} ${currentYear}`;
};
renderCalendar();

prevNextIcon.forEach(icon => {
    icon.addEventListener("click", () => {
        console.log(icon);
    })
});