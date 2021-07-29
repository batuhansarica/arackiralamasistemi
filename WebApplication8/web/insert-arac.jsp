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
      :root {
  --input-padding-x: 1.5rem;
  --input-padding-y: .75rem;
}
.dropdown-menu.show{
    width: auto;
}
.deneme ul  {
    float: left;
    position: relative;
    width: 150px;
    font-size: 20px;
}
.card-signin {
  border: 0;
  border-radius: 1rem;
  box-shadow: 0 0.5rem 1rem 0 rgba(0, 0, 0, 0.1);
    background: #007bff;
  background: linear-gradient(to right, #0062E6, #33AEFF);
}

.card-signin .card-title {
  margin-bottom: 2rem;
  font-weight: 300;
  font-size: 40px;
  font-family: Arial, Helvetica, sans-serif;
}
.card-body a{
    border-radius: 1rem;
    font-size: 15px;
}
.card-signin .card-body {
  padding: 2rem;
}

.form-signin {
  width: 100%;
}

.form-signin .btn {
  font-size: 20px;
  border-radius: 5rem;
  letter-spacing: .1rem;
  font-weight: bold;
  padding: 1rem;
  transition: all 0.2s;
}

.form-label-group {
  position: relative;
  margin-bottom: 1rem;
}

.form-label-group input {
  height: auto;
  border-radius: 2rem;
}

.form-label-group>input,
.form-label-group>label {
  padding: var(--input-padding-y) var(--input-padding-x);
}

.form-label-group>label {
  position: absolute;
  top: 0;
  left: 0;
  display: block;
  width: 100%;
  margin-bottom: 0;
  line-height: 1.5;
  color: #495057;
  border: 1px solid transparent;
  border-radius: .25rem;
  transition: all .1s ease-in-out;
}

.form-label-group input::-webkit-input-placeholder {
  color: transparent;
}

.form-label-group input:-ms-input-placeholder {
  color: transparent;
}

.form-label-group input::-ms-input-placeholder {
  color: transparent;
}

.form-label-group input::-moz-placeholder {
  color: transparent;
}

.form-label-group input::placeholder {
  color: transparent;
}

.form-label-group input:not(:placeholder-shown) {
  padding-top: calc(var(--input-padding-y) + var(--input-padding-y) * (2 / 3));
  padding-bottom: calc(var(--input-padding-y) / 3);
}

.form-label-group input:not(:placeholder-shown)~label {
  padding-top: calc(var(--input-padding-y) / 3);
  padding-bottom: calc(var(--input-padding-y) / 3);
  font-size: 12px;
  color: #777;
}

.btn-google {
  color: white;
  background-color: #ea4335;
}




@supports (-ms-ime-align: auto) {
  .form-label-group>label {
    display: none;
  }
  .form-label-group input::-ms-input-placeholder {
    color: #777;
  }
}


@media all and (-ms-high-contrast: none),
(-ms-high-contrast: active) {
  .form-label-group>label {
    display: none;
  }
  .form-label-group input:-ms-input-placeholder {
    color: #777;
  }
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
      
    <li><a class="dropdown-item" href="<%=request.getContextPath()%>/KiralamaServlet/list">Kiralanan Araçlar Listesi</a></li>
    <li><a class="dropdown-item" href="<%=request.getContextPath()%>/KiralamaServlet/new">Araç Kirala</a></li>
    <li><a class="dropdown-item" href="<%=request.getContextPath()%>/listAracTeslim">Araç Teslim Listesi</a></li>
      
  </ul>
</div>
    
    
    
          
           
    </ul>
</div>
	<br>
	<div class="container">
    <div class="row">
      <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
        <div class="card card-signin my-5">
            <div class="card-body">
				<c:if test="${arac != null}">
					<form action="AracServlet/update" method="post">
				</c:if>
				<c:if test="${arac == null}">
					<form action="AracServlet/insert" method="post">
				</c:if>

				<caption>
                                    <h3 class="card-title text-center"><strong>
						<c:if test="${arac != null}">
            			Araç Güncelle
            		</c:if>
						<c:if test="${arac == null}">
            			Araç Ekle
            		</c:if>
					</strong></h3>
				</caption>

				<c:if test="${arac != null}">
					<input type="hidden" name="id" value="<c:out value='${arac.id}' />" />
				</c:if>

                                        <div class="form-label-group">
                                    <input type="text"  id="inputplakaNo" placeholder="Kullanici Adi"   value="<c:out value='${arac.plaka}' />" class="form-control" name="plakaNo" required="required">
                                    <label for="inputplakaNo" style="cursor:text">Plaka No</label> 
				</div>

				 <div class="form-label-group">
                                    <input type="text"  id="inputMarka" placeholder="Marka"   value="<c:out value='${arac.marka}' />" class="form-control" name="marka">
                                    <label for="inputMarka" style="cursor:text">Marka</label>
				</div>
				 <div class="form-label-group">
                                    <input type="text"  id="inputModel" placeholder="Model"  value="<c:out value='${arac.model}' />" class="form-control"name="model">
                                    <label for="inputModel" style="cursor:text">Model</label> 
				</div>
                                <div class="form-label-group">
                                    <input type="text"  id="inputRenk" placeholder="Renk"  value="<c:out value='${arac.renk}' />" class="form-control"name="renk">
                                    <label for="inputRenk" style="cursor:text">Renk</label>
                                </div>
				<button type="submit" class="btn btn-lg btn-google btn-block text-uppercase">Kaydet</button>
				</form>
			</div>
		</div>
	</div>
</div>
</div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</body>
</html>