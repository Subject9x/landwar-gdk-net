/*
    Javascript layer for page: UnitInfoCard Generator window
*/

let doc = document;
let unitTableRowCount = 0;
let row_tagArrays = {};

function uic_print_sheet(event){
	event.preventDefault();
	window.print();
}

/*
    UNIT ROW FUNCTIONS
*/
function uic_row_add(){
    let table = doc.querySelector("#unitTable");
    let newRow = table.insertRow();

    let rowTemplate = window.nodeFileSys.loadHTML('layout/pages/unitCardGen/unitRow.html');
    newRow.innerHTML = rowTemplate;

    unitTableRowCount += 1;
    newRow.setAttribute('id', 'unitRow' + unitTableRowCount);

    return newRow.id;
}

function uic_card_row_add(objData, newRowId){
    let cardTable = doc.querySelector('#cardTable');

    let cardDiv = document.createElement('div');
    cardDiv.style = "float: left;";
    cardDiv.setAttribute('id', 'unit'+ unitTableRowCount); //already incremented to right value by preceding uic_row_add() call.
    cardDiv.innerHTML = window.nodeFileSys.loadHTML('layout/pages/unitCardGen/unitInfoCardraw.html');

    cardDiv.querySelector("#ucName").innerHTML = objData.unitName;

    cardDiv.querySelector("#ucSize").innerHTML = objData.size;
    
    cardDiv.querySelector("#ucMove").innerHTML = objData.move;
    
    cardDiv.querySelector("#ucEvade").innerHTML = objData.evade;
    
    cardDiv.querySelector("#ucMel").innerHTML = objData.dmgMelee;
    
    cardDiv.querySelector("#ucRange").innerHTML = objData.dmgRange;
    
    cardDiv.querySelector("#ucDist").innerHTML = objData.range + '"';
    
    cardDiv.querySelector("#ucArmor").innerHTML = objData.armor;
    
    //cardDiv.querySelector("#ucStruc").innerHTML = objData.structure;
    
    cardDiv.querySelector("#ucPoints").innerHTML = '<b>' + objData.completeTotal + '</b>';

    let tagArr = row_tagArrays[newRowId];
    let tagList = cardDiv.querySelector("#ucKeywords");
    let tagItem;
    if(tagArr.length > 0){
        for(let tagNum in tagArr){
            tagItem = document.createElement('li');
            tagItem.innerHTML = tagInfo.data[tagArr[tagNum]].title;
            tagList.appendChild(tagItem);
        }
    }
    cardTable.appendChild(cardDiv);

}