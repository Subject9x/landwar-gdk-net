/*
    Include for any page that needs file options
*/

const dialogSaveOptionsUnitList = {
    title : 'Save File',
    buttonLabel: 'Save',
    filters : [{name : 'CSV Files', extensions : ['csv']}],
    properties : ['createDirectory', 'showOverwriteConfirmation']
}

const dialogLoadOptionsUnitList = {
    title : 'Load Unit List',
    buttonLabel: 'Open',
    filters : [{name : 'CSV Files', extensions : ['csv']}],
    properties : ['openFile']
}


const dialogSavePDFOptions ={
    title : 'save rules pdf',
    buttonLabel : 'Save',
    filters : [{name : 'PDF Files', extensions : ['pdf']}],
    properties : ['createDirectory', 'showOverwriteConfirmation']
}

const pdfSaveOptions = {
    marginsType: 0,
    pageSize: 'A4',
    printBackground: false,
    printSelectionOnly: false,
    landscape: false,
}