let date = new Date();
var todayDate = date.toDateString();

var headerDate = document.getElementById('data');

headerDate.innerHTML = todayDate;


var dropdown = document.getElementsByClassName("dropdown-btn");
var i;

for (i = 0; i < dropdown.length; i++) {
    dropdown[i].addEventListener("click", function() {
        this.classList.toggle("active");
        var dropdownContent = this.nextElementSibling;
        if (dropdownContent.style.display === "block") {
            dropdownContent.style.display = "none";
        } else {
            dropdownContent.style.display = "block";
        }
    });
}

function showContent(id) {
    var contents = document.getElementsByClassName('content');
    for (var i = 0; i < contents.length; i++) {
        contents[i].style.display = 'none';
    }
    document.getElementById(id).style.display = 'block';
};

const currentDate = document.querySelector(".current-date");
daysTag = document.querySelector(".days");
prevNextIcon = document.querySelectorAll(".icons span");

currentYear = date.getFullYear();
currentMonth = date.getMonth();

const monthName = ["January", "February", "March", "April", "May", "June", "July", "September",
    "August", "October", "November", "December"];

const renderCalendar = () => {
    let firstDayOfMonth = new Date(currentYear, currentMonth, 0).getDay();
    lastDateOfMonth = new Date(currentYear, currentMonth + 1, 0).getDate();
    lastDayOfMonth = new Date(currentYear, currentMonth, lastDateOfMonth).getDay();
    lastDateOfLastMonth = new Date(currentYear, currentMonth, 0).getDate();
    let liTag = "";

    for (let i = firstDayOfMonth; i > 0; i--) {  
        liTag += `<li class="inactive">${lastDateOfLastMonth - i + 1}</li>`;
    }

    for (let index = 1; index <= lastDateOfMonth; index++) {
        let isToday = index === date.getDate() && currentMonth === date.getMonth() && currentYear === date.getFullYear() ? "activeDays" : "";
        liTag += `<li class="${isToday}">${index}</li>`;
    }

    for (let i = lastDayOfMonth; i <= 6; i++) {
        liTag += `<li class="inactive">${i - lastDayOfMonth + 1}</li>`
    }

    currentDate.innerHTML = `${monthName[currentMonth]} ${currentYear}`;
    daysTag.innerHTML = liTag;
};
renderCalendar();

prevNextIcon.forEach(icon => {
    icon.addEventListener("click", () => {
        currentMonth = icon.id === "prev" ?  currentMonth - 1 : currentMonth + 1;

        if(currentMonth < 0 || currentMonth > 11) {
            date = new Date(currentYear, currentMonth);
            currentYear = date.getFullYear();
            currentMonth = date.getMonth();
        } else {
            date = new Date();
        }
        renderCalendar();
    }) 
});

google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawVisualization);

      function drawVisualization() {
        // Some raw data (not necessarily accurate)
        var data = google.visualization.arrayToDataTable([
          ['Month', 'Bolivia', 'Ecuador', 'Madagascar', 'Papua New Guinea', 'Rwanda', 'Average'],
          ['2004/05',  165,      938,         522,             998,           450,      614.6],
          ['2005/06',  135,      1120,        599,             1268,          288,      682],
          ['2006/07',  157,      1167,        587,             807,           397,      623],
          ['2007/08',  139,      1110,        615,             968,           215,      609.4],
          ['2008/09',  136,      691,         629,             1026,          366,      569.6]
        ]);

        var options = {
          title : 'Monthly Coffee Production by Country',
          vAxis: {title: 'Cups'},
          hAxis: {title: 'Month'},
          seriesType: 'bars',
          series: {5: {type: 'line'}}
        };

        var chart = new google.visualization.ComboChart(document.getElementById('chart_div'));
        chart.draw(data, options);
      }