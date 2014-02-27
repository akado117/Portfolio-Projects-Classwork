<html>
    
    <head>
        <!нн Bootstrap нн>
        <link  href="css/bootstrap.min.css" rel="stylesheet" media="screen">
       
      
        
        
        
    </head>
    
<body>


<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Address Book</a>
        </div>
        <div class="collapse navbar-collapse">
          <ul class="nav navbar-nav navbar-right ">
            
            <li><a href="spring/displayAddresses">Addresses</a></li>
            
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>

    <div class="container">

      <div class="starter-template">
          <h1>Welcome to Address Book</h1>
          <input type="button"  class="btn btn-danger" onclick="location.href='spring/displayAddresses'" value="To Addresses" >
       
      </div>

    </div><!-- /.container -->







  <!нн jQuery (necessary for Bootstrap's JavaScript plugins) нн>
        <script src="js/jquery.js" ></script>
        <!нн Include all compiled plugins, or include individual files as needed нн>
        <script src="js/bootstrap.js" ></script>

</body>
</html>
