/*
    Renderer.js component for Electron
*/
api.handle( 'ab-dialog-load-response', ( event, ...data ) => function( event, ...data) {
    let parsedData = file_unitInfo_forArmy(data[0]);
    ab_armyList_parseData(parsedData);
    if(data[1] != undefined && data[1].length > 0){

        let path = data[1];
        let fileName = path.substring(path.lastIndexOf('\\') + 1, path.lastIndexOf('.csv'));
        
        document.getElementById("fileName").innerHTML = "<h4>" + fileName + "</h4>";
        document.getElementById("file").innerHTML = fileName;
    }
}, event);

api.handle( 'ab-dialog-load-response-unitinfo', (event, ...data) => function(event, ...data){
    let parsedData = file_unitInfo_forArmy(data[0]);
    ab_unitInfo_addData(parsedData);
}, event);
