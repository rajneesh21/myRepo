function opration(el) {
    
    var p = el.parentNode.parentNode.parentNode
    console.log(p.children)

    var docName = p.children[0].innerText
    var dateTime = p.children[1].innerText +" "+ p.children[2].innerText
    var title = p.children[3].innerText
    var name = document.getElementById("userName").innerText
    document.getElementById('setpatient').innerHTML = name
    document.getElementById('setDoc').innerHTML = docName.split('\n')[0]
    document.getElementById('setTime').innerHTML = dateTime
    document.getElementById('setTitle').innerHTML = title

    var modal = document.getElementById("myModal");
    modal.style.display = "block";
    var span = document.getElementsByClassName("close")[0];
    span.onclick = function () {
        modal.style.display = "none";
        // el.classList.toggle('selected');
    }

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
            // el.classList.toggle('selected');
        }
    }
}
function colorToggle(element) {
    // var element = document.getElementById("paneDashboard");
    element.classList.toggle("active");
  }