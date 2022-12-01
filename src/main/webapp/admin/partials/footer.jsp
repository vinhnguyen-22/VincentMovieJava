<script
  src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
  integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
  crossorigin="anonymous"
></script>
<script src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>

<script>
  $('.data-table').DataTable();
  setTimeout(() => {
    $('.alert').alert('close');
  }, 5000);

  let arrow = document.querySelectorAll('.arrow');
  for (var i = 0; i < arrow.length; i++) {
    arrow[i].addEventListener('click', (e) => {
      let arrowParent = e.target.parentElement.parentElement; //selecting main parent of arrow
      arrowParent.classList.toggle('showMenu');
    });
  }
  let sidebar = document.querySelector('.sidebar');
  let sidebarBtn = document.querySelector('#menu-toggle');

  $(function () {
    resizeScreen();
    $(window).resize(function () {
      resizeScreen();
    });
    $('#menu-toggle').click(function () {
      if ($('.sidebar').hasClass('close')) $(this).css('transform', 'translateX(0)');
      else $(this).css('transform', 'translateX(-85px)');

      if (document.body.clientWidth > 400) {
        $('.sidebar').toggleClass('close');
      } else {
        $('.sidebar').toggleClass('small-screen');
      }
    });
    function resizeScreen() {
      if (document.body.clientWidth < 400) {
        $('.sidebar').addClass('close');
      } else {
        $('.sidebar').removeClass('close');
      }
    }
  });
</script>
