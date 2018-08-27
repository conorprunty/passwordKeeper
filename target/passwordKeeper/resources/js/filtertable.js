//https://www.w3schools.com/howto/howto_js_filter_table.asp
function filterTable() {
  var input, filter, table, tr, td, i;
  input = document.getElementById("nameCheck");
  filter = input.value.toUpperCase();
  table = document.getElementById("wctable");
  tr = table.getElementsByTagName("tr");
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[0];
    if (td) {
      if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }       
  }
}