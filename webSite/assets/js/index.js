// parallax
window.onload = function () {
    document.body.classList.add('loaded_hiding');
    window.setTimeout(function () {
    document.body.classList.add('loaded');
    document.body.classList.remove('loaded_hiding');
    }, 500);
}

let text = document.getElementById('text');
let description = document.getElementById('description');
let btn = document.getElementById('btn');
let header = document.querySelector('header');
let kapriz = document.getElementById('kapriz');

window.addEventListener('scroll', function(){
    let value = window.scrollY;
    text.style.marginTop = value * 1.5 + 'px';
    description.style.marginTop = value * 1.5 + 'px';
    btn.style.marginTop = value * 1.5 + 'px';
    header.style.top = value * 0.5 + 'px';
})

// движение стрелки

function loopDown(){
$(".arrows").animate({
    marginTop : 5
    },
    400, function() {
    loopUp();
});
}

function loopUp(){
$(".arrows").animate({
    marginTop : 0
    },
    400, function() {
    loopDown();
});
}

loopDown();

// анимации
function onEntry(entry) {
entry.forEach(change => {
if (change.isIntersecting) {
change.target.classList.add('element-show');
}
});
}


let options = {
threshold: [0.5] };
let observer = new IntersectionObserver(onEntry, options);
let elements = document.querySelectorAll('.element-animation');

for (let elm of elements) {
observer.observe(elm);
}



// окно со входом
//     $('.openmodal').click(function (e) {
// e.preventDefault();
// $('.kadobagud').addClass('midsalod');
// });
// $('.closemodal').click(function (e) {
// e.preventDefault();
// $('.kadobagud').removeClass('midsalod');
// });