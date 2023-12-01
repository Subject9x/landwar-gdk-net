/*
	used for print-pdf-on-tab-open documents like rules.
*/
window.onload = (event) => {
	document.body.addEventListener("afterprint", function(){
			window.close();
		});
  window.print();
};