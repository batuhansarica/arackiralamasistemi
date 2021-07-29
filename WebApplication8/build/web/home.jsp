<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html >
<head>
  <title>Araç Kiralama</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <style type="text/css">
      
      
 
.dropdown-menu.show{
    width: auto;
}

.panel-danger.panel-colorful {
  background-color: #f76c51;
  border-color: #f76c51;
  color: #fff;
}

.panel-primary.panel-colorful {
  background-color: #5fa2dd;
  border-color: #5fa2dd;
  color: #fff;
}

.panel-info.panel-colorful {
  background-color: #4ebcda;
  border-color: #4ebcda;
  color: #fff;
}

.panel-body {
  padding: 25px 20px;
}

.panel hr {
  border-color: rgba(0,0,0,0.1);
}

.mar-btm {
  margin-bottom: 15px;
}

h2, .h2 {
  font-size: 28px;
}

.deneme ul  {
    float: left;
    position: relative;
    width: 150px;
    font-size: 20px;
}
.text-thin {
  font-weight: 300;
}

</style>
</head>
<body background="http://www.duzcekartalturizm.com/wp-content/uploads/2019/01/mercedes-e250.jpg" >
    
    <nav class="navbar navbar-dark bg-dark">
  
    <a class="navbar-brand" href="#">
      <ul class="nav navbar-nav  navbar-right" >
      
         <a class="navbar-brand"  href="<%=request.getContextPath()%>/home">Araç Kiralama Sistemi</a>
      </ul>
    </a>
      <ul class="nav navbar-nav  navbar-right" >
          <div class="dropdown">
  <button type="button" class="btn btn-danger dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false" href="#">${loggedUser.firstName} ${loggedUser.lastName}
 
  </button>
  <ul class="dropdown-menu">
    <li><a class="dropdown-item" href="<%=request.getContextPath()%>/profilim">Profilim</a></li>
    <li><a class="dropdown-item" href="<%=request.getContextPath()%>/cikisYap">Çıkış Yap</a></li>
    
  </ul>
</div>
       </ul>     
  
</nav>
  

        
        
        
        
   <div class="deneme">
        <ul class="nav navbar-nav">
            
       <c:if test="${loggedUser.email == 'admin' && loggedUser.password == 'admin'}">     
     <div class="btn-group dropend">
  <button type="button" class="btn btn-secondary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
    User islemleri
  </button>
       
  <ul class="dropdown-menu">
    <li><a class="dropdown-item" href="<%=request.getContextPath()%>/new">User Ekle</a></li>
     
    <li><a class="dropdown-item" href="<%=request.getContextPath()%>/listUser">User Listesi</a></li>
    
  </ul>
</div>
        </c:if>
        <div class="btn-group dropend">
  <button type="button" class="btn btn-secondary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
    Arac islemleri
  </button>
       
  <ul class="dropdown-menu">
    <li><a class="dropdown-item" href="<%=request.getContextPath()%>/AracServlet">Araç Listesi</a></li>
    <c:if test="${loggedUser.email == 'admin' && loggedUser.password == 'admin'}"> 
    <li><a class="dropdown-item" href="<%=request.getContextPath()%>/AracServlet/new">Araç Ekle</a></li>
    </c:if>
  </ul>
</div>
  
    <c:if test="${loggedUser.email == 'admin' && loggedUser.password == 'admin'}">
   <div class="btn-group dropend">
  <button type="button" class="btn btn-secondary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
    Musteri islemleri
  </button>
       
  <ul class="dropdown-menu">
    <li><a class="dropdown-item" href="<%=request.getContextPath()%>/MusteriServlet">Müşteri Listesi</a></li>
    <li><a class="dropdown-item" href="<%=request.getContextPath()%>/MusteriServlet/new">Müşteri Ekle</a></li>
  </ul>
</div>
    </c:if>
    <div class="btn-group dropend">
  <button type="button" class="btn btn-secondary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
    Arac Kiralama islemleri
  </button>
        
  <ul class="dropdown-menu">
      
      
      <li><a class="dropdown-item"  href="<%=request.getContextPath()%>/KiralamaServlet/list">Kiralanan Araçlar Listesi</a></li>
    <li><a class="dropdown-item" href="<%=request.getContextPath()%>/KiralamaServlet/new">Araç Kirala</a></li>
    <li><a class="dropdown-item" href="<%=request.getContextPath()%>/listAracTeslim">Araç Teslim Listesi</a></li>
      
  </ul>
    
</div>
    
    
    
          
           
    </ul>
</div>






<div class="container">
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
<div class="container bootstrap snippets bootdey">
    <div class="row">
        <c:if test="${loggedUser.email == 'admin' && loggedUser.password == 'admin'}">
        <div class="col-md-3 col-sm-6 col-xs-12">
           <a href="<%=request.getContextPath()%>/AracServlet/new"> 
               <div class="panel panel-info panel-colorful">
                <div class="panel-body text-center">
                	
                	<i class="fa fa-car fa-5x"></i>
                        <i class="fa fa-plus fa-5x"></i>
                	<hr>
                	<p class="h2 text-thin text-uppercase">Araç Ekle</p>
                </div>
            </div>
            </a>
        </div>
                
        <div class="col-md-3 col-sm-6 col-xs-12">
        	<a href="<%=request.getContextPath()%>/MusteriServlet/new">
                    <div class="panel panel-danger panel-colorful">
        		<div class="panel-body text-center">
        			<i class="fa fa-users fa-5x"></i>
        			<hr>
        			<p class="h2 text-thin text-uppercase">Müşteri Ekle</p>
        		</div>
        	</div></a>
        </div>
                        </c:if>
        <div class="col-md-3 col-sm-6 col-xs-12">
        	<a href="<%=request.getContextPath()%>/KiralamaServlet/new">
                    <div class="panel panel-primary panel-colorful">
        		<div class="panel-body text-center">
        		
        			<i class="fa fa-shopping-cart fa-5x"></i>
        			<hr>
        			<p class="h2 text-thin text-uppercase">Araç Kirala</p>
        		</div>
        	</div></a>
        </div>  
	</div>
</div>
</div>

     <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</body>
</html>
