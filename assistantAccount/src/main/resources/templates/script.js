var sidebar = document.querySelector(".sidebar");
var menuIcon = document.querySelector(".menu-icon");

menuIcon.onclick = function()
{
    sidebar.classList.toggle("small-sidebar");
}