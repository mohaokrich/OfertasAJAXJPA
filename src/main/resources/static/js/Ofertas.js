//CARGAR DOM
document.addEventListener("DOMContentLoaded", function() {
	obtenerOfertas();
	$("#enviar").click(crearOferta);
	$("#filtrarPorPrioridad").click(filtrarOferta);
	$("#editarOferta").click(editarOferta);
});

//ELIMINAR FILA
$(document).on('click', '#borrar', function() {
	var tr = $(this).closest("tr");
	var id = tr[0].childNodes[0].innerText;
	let ruta = "/oferta/borrar/" + id;
	window.location.href = ruta;
	$(this).closest('tr').remove();
});

//MONTAR EL DOM DE LA FILA
function obtenerFilaDom(id, nombre, precio) {
	let Body = document.getElementById("idbody");
	let tr = document.createElement('tr');

	let tdId = document.createElement('td');
	tdId.textContent = id;
	let tdNombre = document.createElement('td');
	tdNombre.textContent = nombre;
	let tdPrecio = document.createElement('td');
	tdPrecio.textContent = precio;


	let tdInfo = document.createElement('td');
	let bottonInfo = document.createElement('button');
	let tdBorrar = document.createElement('td');
	let bottonBorrar = document.createElement('button');

	bottonInfo.textContent = 'Info';
	bottonInfo.setAttribute('class', 'btn btn-info info');
	bottonInfo.setAttribute('id', 'info');
	bottonInfo.setAttribute('type', 'button');

	bottonBorrar.textContent = 'Borrar';
	bottonBorrar.setAttribute('class', 'btn btn-danger borrar');
	bottonBorrar.setAttribute('id', "borrar");
	bottonBorrar.setAttribute('type', 'button');


	tr.appendChild(tdId);
	tr.appendChild(tdNombre);
	tr.appendChild(tdPrecio);
	tdInfo.appendChild(bottonInfo);
	tdBorrar.appendChild(bottonBorrar);
	tr.appendChild(tdInfo);
	tr.appendChild(tdBorrar);
	Body.appendChild(tr);


}

//OBTENER LA TABLA DE OFERTAS
function obtenerOfertas() {
	fetch('/ofertas', { headers: { "Content-Type": "application/json; charset=utf-8" } })
		.then(res => res.json()) // parse response as JSON (can be res.text() for plain response)
		.then(ofertas => {
			for (let oferta of ofertas) {
				obtenerFilaDom(oferta.id, oferta.nombre, oferta.precio);

				if (oferta.prioridad == "baja") {
					$("tr").last().addClass("table-active");
				} else if (oferta.prioridad == "media") {
					$("tr").last().addClass("table-warning");
				} else if (oferta.prioridad == "alta") {
					$("tr").last().addClass("table-danger");
				}
			}
		});
}

//CREAR OFERTA
function crearOferta() {
	if ($('#inputNombre').val() != "" && $('#selectProducto').val() != ""
		&& $('#inputPrecio').val() != "" && $('#inputEnlace').val() != ""
		&& $('#inputDescripcion').val() != "") {
		fetch('/oferta/crear', {
			headers: {
				'Content-type': 'application/json'
			},
			method: 'POST',
			body: JSON.stringify({
				nombre: $('#inputNombre').val(), prioridad: $('#selectProducto').val()
				, precio: $('#inputPrecio').val(), hiperenlace: $('#inputEnlace').val(), descripcion: $('#inputDescripcion').val()
			})
		})
			.then(function(response) {
				if (response.ok) {
					return response.json()
				} else {
					throw "Error";
				}
			});
	};
}
function filtrarOferta() {
	let prioridad = "";
	if (document.getElementById("prioridadBaja").checked) {
		prioridad = "baja";
	} else if (document.getElementById("prioridadMedia").checked) {
		prioridad = "media";
	} else if (document.getElementById("prioridadAlta").checked) {
		prioridad = "alta";
	}
	fetch('/oferta/filtrar' + '?prioridad=' + prioridad, { headers: { "Content-Type": "application/json; charset=utf-8" } })
		.then(res => res.json()) // parse response as JSON (can be res.text() for plain response)
		.then(ofertas => {
			let Body = document.getElementById("idbody");
			Body.replaceChildren();
			for (let oferta of ofertas) {
				obtenerFilaDom(oferta.id, oferta.nombre, oferta.precio);

				if (oferta.prioridad == "baja") {
					$("tr").last().addClass("table-active");
				} else if (oferta.prioridad == "media") {
					$("tr").last().addClass("table-warning");
				} else if (oferta.prioridad == "alta") {
					$("tr").last().addClass("table-danger");
				}

			}
		});
}
//MOSTRAR VENTANA MODAL CON INFORMACION SOBRE LA OFERTA
$(document).on('click', '#info', function() {
	$("#modal").modal('show');

	$(".btn-close").on('click', function() {
		$("#modal").modal("hide");
	});

	var tr = $(this).closest("tr");
	var id = tr[0].childNodes[0].innerText;
	fetch('/oferta/oferta' + id, { headers: { "Content-Type": "application/json; charset=utf-8" } })
		.then(res => res.json()) // parse response as JSON (can be res.text() for plain response)
		.then(response => {


			let ModalBody = document.getElementsByClassName("modal-body")[0];
			ModalBody.replaceChildren();
			//let ModalFooter = document.getElementsByClassName("modal-footer")[0];
			//ModalFooter.replaceChildren();
			let ModalTitle = document.getElementsByClassName("modal-title")[0];

			ModalTitle.textContent = response.nombre;

			let tituloId = document.createElement('h6');
			tituloId.textContent = "ID";

			let infoId = document.createElement('p');
			infoId.setAttribute("name", "id");
			infoId.setAttribute("id", "infoId");
			infoId.textContent = response.id;


			let tituloNombre = document.createElement('h6');
			tituloNombre.textContent = "NOMBRE";

			let infoNombre = document.createElement('p');
			infoNombre.setAttribute("id", "infoNombre");
			infoNombre.textContent = response.nombre;


			let tituloFecha = document.createElement('h6');
			tituloFecha.textContent = "FECHA";

			let infoFecha = document.createElement('p');
			infoFecha.setAttribute("id", "infoFecha");
			infoFecha.textContent = response.fecha_publicacion;


			let tituloPrecio = document.createElement('h6');
			tituloPrecio.textContent = "PRECIO";

			let infoPrecio = document.createElement('p');
			infoPrecio.setAttribute("id", "infoPrecio");
			infoPrecio.textContent = response.precio;


			let tituloPrioridad = document.createElement('h6');
			tituloPrioridad.textContent = "PRIORIDAD";

			let infoPrioridad = document.createElement('p');
			infoPrioridad.setAttribute("id", "infoPrioridad");
			infoPrioridad.textContent = response.prioridad;


			let tituloHipervinculo = document.createElement('h6');
			tituloHipervinculo.textContent = "ENLACE";


			let infoEnlace = document.createElement('p');
			infoEnlace.setAttribute("id", "infoEnlace");
			infoEnlace.textContent = response.hiperenlace;

			let tituloDescripcion = document.createElement('h6');
			tituloDescripcion.textContent = "DESCRIPCION";


			let infoDescripcion = document.createElement('p');
			infoDescripcion.setAttribute("id", "infoDescripcion");
			infoDescripcion.textContent = response.descripcion;

			ModalBody.appendChild(tituloId);
			ModalBody.appendChild(infoId);

			ModalBody.appendChild(tituloNombre);
			ModalBody.appendChild(infoNombre);

			ModalBody.appendChild(tituloFecha);
			ModalBody.appendChild(infoFecha);

			ModalBody.appendChild(tituloPrioridad);
			ModalBody.appendChild(infoPrioridad);

			ModalBody.appendChild(tituloHipervinculo);
			ModalBody.appendChild(infoEnlace);

			ModalBody.appendChild(tituloPrecio);
			ModalBody.appendChild(infoPrecio);

			ModalBody.appendChild(tituloDescripcion);
			ModalBody.appendChild(infoDescripcion);
		});
});
function editarOferta() {
	document.getElementById("cerrar-modal").setAttribute("onClick", "window.location.reload();");
	//var tr = $(this).closest("tr");
	let infoNombre = document.getElementById("infoNombre");
	let infoPrioridad = document.getElementById("infoPrioridad");
	let infoEnlace = document.getElementById("infoEnlace");
	let infoPrecio = document.getElementById("infoPrecio");
	let infoDescripcion = document.getElementById("infoDescripcion");

	var nombre = infoNombre.textContent;
	var prioridad = infoPrioridad.textContent;
	var enlace = infoEnlace.textContent;
	var precio = infoPrecio.textContent;
	var descripcion = infoDescripcion.textContent;

	var inputNombreInfo = document.createElement("input");
	inputNombreInfo.setAttribute("type", "text");
	inputNombreInfo.setAttribute("name", "nombre");
	inputNombreInfo.setAttribute("id", "inputNombreInfo");
	inputNombreInfo.setAttribute("value", nombre);


	var inputPrioridadInfo = document.createElement("select");
	inputPrioridadInfo.setAttribute("name", "prioridad");
	inputPrioridadInfo.setAttribute("type", "select");
	inputPrioridadInfo.setAttribute("id", "inputPrioridadInfo");
	var opcionPrioridadBaja = document.createElement("option");
	var opcionPrioridadMedia = document.createElement("option");
	var opcionPrioridadAlta = document.createElement("option");
	
	if(prioridad == 'baja'){
		opcionPrioridadBaja.setAttribute("selected","selected");
	}else if(prioridad == 'media'){
		opcionPrioridadMedia.setAttribute("selected","selected");
	}else if(prioridad == 'media'){
		opcionPrioridadAlta.setAttribute("selected","selected");
	}
	
	opcionPrioridadBaja.textContent = "baja";
	inputPrioridadInfo.appendChild(opcionPrioridadBaja);
	opcionPrioridadMedia.textContent = "media";
	inputPrioridadInfo.appendChild(opcionPrioridadMedia);
	opcionPrioridadAlta.textContent = "alta";
	inputPrioridadInfo.appendChild(opcionPrioridadAlta);


	var inputEnlaceInfo = document.createElement("input");
	inputEnlaceInfo.setAttribute("name", "hiperenlace");
	inputEnlaceInfo.setAttribute("type", "text");
	inputEnlaceInfo.setAttribute("id", "inputEnlaceInfo");
	inputEnlaceInfo.setAttribute("value", enlace);

	var inputPrecioInfo = document.createElement("input");
	inputPrecioInfo.setAttribute("name", "precio");
	inputPrecioInfo.setAttribute("type", "number");
	inputPrecioInfo.setAttribute("id", "inputPrecioInfo");
	inputPrecioInfo.setAttribute("value", precio);

	var inputDescripcionInfo = document.createElement("input");
	inputDescripcionInfo.setAttribute("name", "descripcion");
	inputDescripcionInfo.setAttribute("type", "text");
	inputDescripcionInfo.setAttribute("id", "inputDescripcionInfo");
	inputDescripcionInfo.setAttribute("value", descripcion);
	//-------------------------------------------------

	infoNombre.replaceChildren(inputNombreInfo);
	infoPrioridad.replaceChildren(inputPrioridadInfo);
	infoEnlace.replaceChildren(inputEnlaceInfo);
	infoPrecio.replaceChildren(inputPrecioInfo);
	infoDescripcion.replaceChildren(inputDescripcionInfo);

	let modalFooter = document.getElementsByClassName("modal-footer")[0];
	modalFooter.replaceChildren();

	let botonGuardar = document.createElement("button");
	botonGuardar.setAttribute("type", "submit");
	botonGuardar.setAttribute("id", "guardarCambios");
	botonGuardar.classList.add("btn", "btn-success");
	botonGuardar.textContent = "GUARDAR CAMBIOS";

	let botonCancelar = document.createElement("button");
	botonCancelar.setAttribute("type", "button");
	botonCancelar.setAttribute("id", "cancelarCambios");
	botonCancelar.classList.add("btn", "btn-danger");
	botonCancelar.textContent = "CANCELAR"

	modalFooter.appendChild(botonGuardar);
	modalFooter.appendChild(botonCancelar);
	
	var id = document.getElementById("infoId").textContent;
	let form = document.getElementsByClassName("formularioEditar")[0];
	form.setAttribute("action",'/editar/oferta/'+id);
}

$("#cancelarCambios").click(function() {
	$("#modal").modal('hide');
	window.location.reload();
});
$("#guardarCambios").click(function() {
	var id = document.getElementById("infoId").textContent;
	if ($('#inputNombreInfo').val() != "" && $('#inputPrioridadInfo').val() != ""
		&& $('#inputEnlaceInfo').val() != "" && $('#inputPrecioInfo').val() != ""
		&& $('#inputDescripcionInfo').val() != "") {
		fetch('/editar/oferta/'+id, {
			headers: {
				'Content-type': 'application/json'
			},
			method: 'POST',
			body: JSON.stringify({
				nombre: $('#inputNombreInfo').val(), prioridad: $('#inputPrioridadInfo').val()
				, precio: $('#inputPrecioInfo').val(), hiperenlace: $('#inputEnlaceInfo').val(), descripcion: $('#inputDescripcionInfo').val()
			})
		})
	};
});
