function closeDialogIfSucess(xhr, status, args, dialogWidget, dialogId) {
	if (args.validationFailed || args.KEEP_DIALOG_OPENED) {
		jQuery('#'+dialogId).effect("bounce", {times : 4, distance : 20}, 100);
	} else {
		dialogWidget.hide();
	}
}

PrimeFaces.locales['pt_BR'] = {  
        closeText: 'Fechar',  
        prevText: 'Anterior',  
        nextText: 'Pr�ximo',  
        currentText: 'Come�o',  
        monthNames: ['Janeiro','Fevereiro','Marco','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],  
        monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun', 'Jul','Ago','Set','Out','Nov','Dez'],  
        dayNames: ['Domingo','Segunda','Ter�a','Quarta','Quinta','Sexta','S�bado'],  
        dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','S�b'],  
        dayNamesMin: ['D','S','T','Q','Q','S','S'],  
        weekHeader: 'Semana',  
        firstDay: 1,  
        isRTL: false,  
        showMonthAfterYear: false,  
        yearSuffix: '',  
        timeOnlyTitle: 'S� Horas',  
        timeText: 'Tempo',  
        hourText: 'Hora',  
        minuteText: 'Minuto',  
        secondText: 'Segundo',  
        currentText: 'Data Atual',  
        ampm: false,  
        month: 'M�s',  
        week: 'Semana',  
        day: 'Dia',  
        allDayText : 'Todo Dia'  
    };  

jQuery(document).ready(function($) {
	 // Chamada da funcao upperText(); ao carregar a pagina
	 upperText();
	 // Funcao que faz o texto ficar em uppercase
	 function upperText() {
	// Para tratar o colar
	 $(".maiusculo").bind('paste', function(e) {
	 var el = $(this);
	 setTimeout(function() {
	 var text = $(el).val();
	 el.val(text.toUpperCase());
	 }, 100);
	 });
	 
	// Para tratar quando � digitado
	 $(".maiusculo").keypress(function() {
	 var el = $(this);
	 setTimeout(function() {
	 var text = $(el).val();
	 el.val(text.toUpperCase());
	 }, 100);
	 });
	 }
	 });

