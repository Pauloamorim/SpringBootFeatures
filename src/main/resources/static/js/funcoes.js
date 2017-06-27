$('#confirmacaoExclusaoModal').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget);
	
	var idTitulo = button.data('id');
	var descricao = button.data('descricao');
	var url = button.data('url');
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	action += url+'/';
	
	form.attr('action', action + idTitulo);
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir <strong>' + descricao + '</strong>?');
});

$(function() {
	$('.js-currency').maskMoney({decimal: ',', thousands: '.', allowZero: true});
});

