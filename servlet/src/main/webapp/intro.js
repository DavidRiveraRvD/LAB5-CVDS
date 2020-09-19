<script>
  function validateForm() {
  var x = document.forms["formulario"]["id"].value;
  if (x != parseInt(x)) {
    alert("Ingrese un numero valido");
    return false;
  }
}
</script>