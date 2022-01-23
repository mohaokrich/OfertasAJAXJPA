//CARGAR DOM
document.addEventListener("DOMContentLoaded", function () {
	obtenerOfertas();
});
//ELIMINAR FILA
$(document).on('click', '#borrar', function() {
 	  	var tr = $(this).closest("tr");
 	  	var id = tr[0].childNodes[0].innerText;
	   	let ruta = "/oferta/borrar/"+id;
	    window.location.href = ruta;
	    $(this).closest('tr').remove(); 
});

//MOSTRAR VENTANA MODAL CON INFORMACION SOBRE LA OFERTA
$(document).on('click', '#info', function() {
	$("#modal").modal('show');
	
	$(".btn-close").on('click', function(){
		$("#modal").modal("hide");
	});
	
 	 var tr = $(this).closest("tr");
 	 var id = tr[0].childNodes[0].innerText;
 	fetch('/oferta/oferta'+id, {headers: {"Content-Type": "application/json; charset=utf-8"}})
        .then(res => res.json()) // parse response as JSON (can be res.text() for plain response)
        .then(response => {

	
					let ModalBody = document.getElementsByClassName("modal-body")[0];
					ModalBody.replaceChildren();
					let ModalTitle = document.getElementsByClassName("modal-title");
					
					let infoId = document.createElement('p');
					infoId.textContent = 'ID: ' + response.id;
					let infoNombre = document.createElement('p');
					infoNombre.textContent = 'NOMBRE: ' + response.nombre;
					let infoFecha = document.createElement('p');
					infoFecha.textContent = 'FECHA PUBLICACIÓN: ' +response.fecha_publicacion;
					let infoPrecio = document.createElement('p');
					infoPrecio.textContent = 'PRECIO: ' +response.precio;
					let infoPrioridad = document.createElement('p');
					infoPrioridad.textContent = 'PRIORIDAD: ' +response.prioridad;
					let infoEnlace = document.createElement('p');
					infoEnlace.textContent = 'HIPERVINCULO: ' +response.hiperenlace;
					
					ModalBody.appendChild(infoId);
					ModalBody.appendChild(infoNombre);
					ModalBody.appendChild(infoFecha);
					ModalBody.appendChild(infoPrioridad);
					ModalBody.appendChild(infoEnlace);
					ModalBody.appendChild(infoPrecio);
			

         
    });
});
//MONTAR Y OBTENER LA TABLA DE OFERTAS
function obtenerOfertas() {
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





