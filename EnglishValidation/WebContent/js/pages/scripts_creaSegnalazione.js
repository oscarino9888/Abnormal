$(document)
		.ready(
				function() {

					$(document)
							.on(
									'submit',
									'#form-invia',
									function(e) {
										var testo_msg = $('#testo').val();
										var email = $('#email').val();
											$
													.ajax({
														url : absolutePath
																+ "/ServletSegnalazione",
														type : "POST",
														dataType : 'JSON',
														async : false,
														data : {
															"testo" : testo,
															"email" : email,
															"flag" : 1
															
														},
														success : function(msg) {
															if (!msg.result) {
																showAlert(
																		1,
																		msg.error);
															} else {
																showAlert(
																		0,
																		msg.content);

																setTimeout(
																		function() {
																			window.location.href = msg.redirect;
																		}, 2000);
															}
														},
														error : function(msg) {
															showAlert(1,
																	"Impossibile Recuperare i dati.");
														}
													});
									});

				});
$(document)
.ready(
		function() {

			$(document)
					.on(
							'submit',
							'#form-risposta',
							function(e) {
								var testo_msg = $('#testo-risposta').val();
								var email = $('#email-risposta').val();
									$
											.ajax({
												url : absolutePath
														+ "/ServletSegnalazione",
												type : "POST",
												dataType : 'JSON',
												async : false,
												data : {
													"testo" : testo,
													"email" : email,
													"flag" : 1
													
												},
												success : function(msg) {
													if (!msg.result) {
														showAlert(
																1,
																msg.error);
													} else {
														showAlert(
																0,
																msg.content);

														setTimeout(
																function() {
																	window.location.href = msg.redirect;
																}, 2000);
													}
												},
												error : function(msg) {
													showAlert(1,
															"Impossibile Recuperare i dati.");
												}
											});
							});

		});