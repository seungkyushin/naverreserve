function myAjax(url,http,func){
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function(){
		 if (this.readyState == 4 && this.status == 200) {
			func(this);
		 }
		 
	}
	xhttp.open(url, http);
	xhttp.send();
}
