document.addEventListener("DOMContentLoaded", function () {
	obtenerUsuarios();
});

$(document).on('click', '#borrar', function() {
 	  	var tr = $(this).closest("tr");
 	  	var id = tr[0].childNodes[0].innerText;
	   	let ruta = "/oferta/borrar/"+id;
	    window.location.href = ruta;
	    $(this).closest('tr').remove(); 
});

$(document).on('click', '.info', function() {
 	fetch('/ofertas', {headers: {"Content-Type": "application/json; charset=utf-8"}})
        .then(res => res.json()) // parse response as JSON (can be res.text() for plain response)
        .then(response => {
			let divModal = document.createElement("div");
			divModal.set
			
			$('#myModalExito').modal('show'); 
            for (let ofertas of response) {
				
				

            }
    });
});
function obtenerUsuarios() {
    fetch('/ofertas', {headers: {"Content-Type": "application/json; charset=utf-8"}})
        .then(res => res.json()) // parse response as JSON (can be res.text() for plain response)
        .then(response => {

            for (let ofertas of response) {
                let Body = document.getElementById("idbody");
            
                let tr = document.createElement('tr');

				                
                if (ofertas.prioridad == 'baja') {
                    tr.setAttribute("class", "table-active");
                } else if(ofertas.prioridad == 'media'){
                    tr.setAttribute("class", "table-warning");
                } else if(ofertas.prioridad == 'alta'){
                    tr.setAttribute("class", "table-danger");
                }
                
            
                let thId = document.createElement('td');
                thId.textContent = ofertas.id;
                let tdNombre = document.createElement('td');
                tdNombre.textContent = ofertas.nombre;
                let tdPrecio = document.createElement('td');
                tdPrecio.textContent = ofertas.precio;
                
                
                let tdInfo = document.createElement('td');
                		let bottonInfo = document.createElement('button');
                let tdBorrar = document.createElement('td');
                		let bottonBorrar = document.createElement('button');
                
                bottonInfo.textContent = 'Info';
                bottonInfo.setAttribute('class','btn btn-info info');
                bottonInfo.setAttribute('id','info');
                bottonInfo.setAttribute('type','button');
                
                bottonBorrar.textContent = 'Borrar';
                bottonBorrar.setAttribute('class','btn btn-danger borrar');
                bottonBorrar.setAttribute('id',"borrar");
                bottonBorrar.setAttribute('type','button');
                
                
                //thId.setAttribute('scope','row');
				//tr.setAttribute("style","display: flex; flex-direction: row;");


                
                tr.appendChild(thId);
                tr.appendChild(tdNombre);
                tr.appendChild(tdPrecio);
                    tdInfo.appendChild(bottonInfo);
                	tdBorrar.appendChild(bottonBorrar);
                tr.appendChild(tdInfo);
                tr.appendChild(tdBorrar);
             	Body.appendChild(tr);
                
            }
        });
}


function stopDefAction(evt) {
  evt.preventDefault();
}





