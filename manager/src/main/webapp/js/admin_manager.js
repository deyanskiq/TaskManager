$(document).ready(function(){

  $('#menu-toggle').click(function() {

        $('.menu').toggle(400);
  });


  $(".flip").hover(function(){
        var
        var data_to_toggle = $(this).attr("data-inf");
        $("."+data_to_toggle).slideDown('400', function() {

            $(this).mouseout(function(event) {

                $(this).slideUp(400);
            });
        });

    });
  $(".sams").fadeIn(1000);

  $(".sams").hover(function(){


        $(".budism-elements").slideDown(900);
        $(this).hover(function() {
            $("#spiritsamurai").show(2500);
        });

  });


});
