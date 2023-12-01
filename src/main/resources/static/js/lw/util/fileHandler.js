/*
    Utility functions for File i/o

*/
/*
    unitBuilder->EXPORT->JSON from UnitRow table to Array of JSON obj.
        this is for csv-writer compat.
*/
function file_unitBuild_export_jsonRowArray(htmlUnitTable){
    let exportData = [];
    if(htmlUnitTable.rows.length <= 1){
        return exportData;
    }

    for(i = 1; i < htmlUnitTable.rows.length; i++){
        let rowItem = htmlUnitTable.rows[i];
        let select = document.getElementById(rowItem.id + '_select').checked;

        if(select){
            let data = {};
            data.unitName = document.getElementById(rowItem.id + '_name').value;
            data.size = parseInt(document.getElementById(rowItem.id + '_size').value);
            data.move = parseInt(document.getElementById(rowItem.id + '_move').value);
            data.evade = parseInt(document.getElementById(rowItem.id + '_evade').value);
            data.dmgMelee = parseInt(document.getElementById(rowItem.id + '_DMGM').value);
            data.dmgRange = parseInt(document.getElementById(rowItem.id + '_DMGR').value);
            data.range = parseInt(document.getElementById(rowItem.id + '_range').value);
            data.armor = parseInt(document.getElementById(rowItem.id + '_armor').value);
            data.points = parseInt(document.getElementById(rowItem.id + '_points').innerHTML);
            data.tags = "";

            let tagArray = row_tagArrays[rowItem.id];
            
            if(tagArray !== undefined && tagArray.length > 0){
                for(let tag in tagArray){
                    data.tags += tagArray[tag] + ' ';    
                }
            }
            else{
                data.tags = "";
            }
            data.tagTotal = parseInt(document.getElementById(rowItem.id + '_tagTotal').innerHTML);
            data.completeTotal = parseInt(document.getElementById(rowItem.id + '_total').innerHTML);
            exportData.push(data);
        }
    }
    return exportData;
}

/*
    unitBuilder->EXPORT->CSV from UnitRow table to array[array] format.
    CSV-compliant.
*/
function file_unitBuild_export_rawCSV(htmlUnitTable){
    let exportData = [];
    if(htmlUnitTable.rows.length <= 1){
        return exportData;
    }
	
	//generate header
	let header = [];
	header.push("unitName");
	header.push("size");
	header.push("move");
	header.push("evade");
	header.push("dmgMelee");
	header.push("dmgRange");
	header.push("range");
	header.push("armor");
	header.push("points");
	header.push("tags");
	header.push("tagTotal");
	header.push("completeTotal");
	
	exportData.push(header);
	
    for(i = 1; i < htmlUnitTable.rows.length; i++){
        let rowItem = htmlUnitTable.rows[i];
        let select = document.getElementById(rowItem.id + '_select').checked;

        if(select){
            let data = [];
            data.push(document.getElementById(rowItem.id + '_name').value);
            data.push(document.getElementById(rowItem.id + '_size').value);
            data.push(document.getElementById(rowItem.id + '_move').value);
            data.push(document.getElementById(rowItem.id + '_evade').value);
            data.push(document.getElementById(rowItem.id + '_DMGM').value);
            data.push(document.getElementById(rowItem.id + '_DMGR').value);
            data.push(document.getElementById(rowItem.id + '_range').value);
            data.push(document.getElementById(rowItem.id + '_armor').value);
            data.push(document.getElementById(rowItem.id + '_points').innerHTML);
            
            let tagArray = row_tagArrays[rowItem.id];
            let tagString = "";
            if(tagArray !== undefined && tagArray.length > 0){
                for(let tag in tagArray){
                    tagString += tagArray[tag] + ' ';    
                }
            }
            else{
                tagString = "";
            }
            data.push(tagString);
            
            data.push(document.getElementById(rowItem.id + '_tagTotal').innerHTML);
            data.push(document.getElementById(rowItem.id + '_total').innerHTML);
            
            exportData.push(data);
        }
    }
    return exportData;
}

/*
    unitBuilder->EXPORT->JSON from UnitRow table to FILE
*/
function file_unitBuild_export(htmlUnitTable){
    let exportData = {};

    if(htmlUnitTable.rows.length <= 1){
        return;
    }

    exportData["header"] = {
        appName : 'LANDWAR-GDK',
        version : '1.0.0',
        rules : 'LANDWAR Core v1.0.0'
    };

    exportData["data"] = [];
    for(i = 1; i < htmlUnitTable.rows.length; i++){
        let rowItem = htmlUnitTable.rows[i];
        let select = document.getElementById(rowItem.id + '_select').checked;
        if(select){
            let data = {};
            data.unitName = document.getElementById(rowItem.id + '_name').value;
            data.size = parseInt(document.getElementById(rowItem.id + '_size').value);
            data.move = parseInt(document.getElementById(rowItem.id + '_move').value);
            data.evade = parseInt(document.getElementById(rowItem.id + '_evade').value);
            data.dmgMelee = parseInt(document.getElementById(rowItem.id + '_DMGM').value);
            data.dmgRange = parseInt(document.getElementById(rowItem.id + '_DMGR').value);
            data.range = parseInt(document.getElementById(rowItem.id + '_range').value);
            data.armor = parseInt(document.getElementById(rowItem.id + '_armor').value);
            data.points = parseInt(document.getElementById(rowItem.id + '_points').innerHTML);
            
            let tagArray = row_tagArrays[rowItem.id];
            if(tagArray !== undefined && tagArray.length > 0){
				data.tags = [];
				tagArray.forEach((itm)=>{
					
					data.tags.push({'id':itm, 'rulesId':1, 'name':tagInfo.data[itm].title});	
				});
            }
            else{
                data.tags = [];
            }
            data.tagTotal = parseInt(document.getElementById(rowItem.id + '_tagTotal').innerHTML);
            data.completeTotal = parseInt(document.getElementById(rowItem.id + '_total').innerHTML);
            exportData["data"].push(data);
        }
    }
    return exportData;
}


/*
    unitBuilder->IMPORT->JSON to UnitRow Table
        assumes [] of json objects.

    todo - fileDataObj.data[] for 'single unit' / 'army list' files.
*/
function file_unitBuild_import(fileDataArray){
    if(fileDataArray.length <= 0){
        console.log("warn: file_unitBuild_import(fileDataArray) was empty.");
        return;
    }
    let fileDataObj = JSON.parse(fileDataArray);
    if(fileDataObj.length <= 0){
        console.log("warn: file_unitBuild_import[fileDataObj] was empty.");
        return;
    }

    for(let objIdx in fileDataObj){
        let objData = fileDataObj[objIdx];

        if(objData !== undefined){
            let newRowId = ub_row_add();
            doc.querySelector("#" + newRowId + '_name').val(objData.unitName);
            doc.querySelector("#" + newRowId + '_size').val(objData.size);
            doc.querySelector("#" + newRowId + '_move').val(objData.move) ;
            doc.querySelector("#" + newRowId + '_evade').val(objData.evade) ;
            doc.querySelector("#" + newRowId + '_DMGM').val(objData.dmgMelee) ;
            doc.querySelector("#" + newRowId + '_DMGR').val(objData.dmgRange) ;
            doc.querySelector("#" + newRowId + '_range').val(objData.range) ;
            doc.querySelector("#" + newRowId + '_armor').val(objData.armor) ;
            doc.querySelector("#" + newRowId + '_points').val(objData.points) ;

            let newArray = [];
            if(objData.tags.length > 0){
                let txtArray = objData.tags.split(' ');
                for(let i in txtArray){
                    if(txtArray[i].length > 0){
                        newArray.push(parseInt(txtArray[i]));
                    }
                }
            }
            row_tagArrays[newRowId] = newArray;
            ub_row_change_points(newRowId);
            ub_row_tag_validate(newRowId);

            let tagTotal = parseFloat(doc.querySelector("#" + newRowId + '_tagTotal').innerHTML)
            let unitTotal = parseFloat(doc.querySelector("#" + newRowId + '_points').innerHTML);
            let finalTotal = Math.round(((unitTotal + tagTotal) + Number.EPSILON) * 100) / 100;
            doc.querySelector("#" + newRowId + "_total").innerHTML =  finalTotal;
        }
    }
}

/**
 * 
 * @param {*} fileDataArray 
 */
function file_unitinfo_import(fileDataArray){
    let fileObjData = JSON.parse(fileDataArray);

    for(let objIdx in fileObjData){
        let objData = fileObjData[objIdx];

        if(objData !== undefined){

            let newRowId = uic_row_add();
            doc.querySelector("#" + newRowId + '> #name').append(objData.unitName);
            doc.querySelector("#" + newRowId + '> #size').append(objData.size);
            doc.querySelector("#" + newRowId + '> #move').append(objData.move) ;
            doc.querySelector("#" + newRowId + '> #evade').append(objData.evade) ;
            doc.querySelector("#" + newRowId + '> #melee').append(objData.dmgMelee) ;
            doc.querySelector("#" + newRowId + '> #range').append(objData.dmgRange) ;
            doc.querySelector("#" + newRowId + '> #dist').append(objData.range) ;
            doc.querySelector("#" + newRowId + '> #armor').append(objData.armor) ;
            doc.querySelector("#" + newRowId + '> #tags').append(objData.newRowId) ;
            doc.querySelector("#" + newRowId + '> #points').append(objData.completeTotal) ;

            let newArray = [];
            if(objData.tags.length > 0){
                let txtArray = objData.tags.split(' ');
                for(let i in txtArray){
                    if(txtArray[i].length > 0){
                        newArray.push(parseInt(txtArray[i]));
                    }
                }
            }
            row_tagArrays[newRowId] = newArray;

            uic_card_row_add(objData, newRowId);
        }
    }
}

/*
    unitBuilder->EXPORT->CSV from UnitRow table to FILE
        invokes ('csv-writer') dependency for ops.
*/
function file_unitBuild_export_csv(htmlUnitTable){
    if(htmlUnitTable.rows.length <= 1){
        return;
    }

    let exportData = file_unitBuild_export_jsonRowArray(htmlUnitTable);
    //TODO FIXME
    //window.api.send('ub-dialog-save-csv', dialogSaveOptionsUnitList, exportData);
}

function file_unitBuild_export_data(htmlUnitTable){
    if(htmlUnitTable.rows.length <= 1){
        return;
    }

    let exportData = file_unitBuild_export_rawCSV(htmlUnitTable);
    let csvData = arrayToCsv(exportData);
    
    if(csvData.length > 0){
		downloadBlob(csvData, "units.csv", 'text/csv;charset=utf-8;')
	}
}

function arrayToCsv(data){
  return data.map(row =>
    row
    .map(String)  // convert every value to String
    .map(v => v.replaceAll('"', ''))  // escape double quotes
    //.map(v => `"${v}"`)  // quote it
    .join(',')  // comma-separated
  ).join('\r\n');  // rows starting on new lines
}

/** Download contents as a file
 * Source: https://stackoverflow.com/questions/14964035/how-to-export-javascript-array-info-to-csv-on-client-side
 */
function downloadBlob(content, filename, contentType) {
  // Create a blob
  var blob = new Blob([content], { type: contentType });
  var url = URL.createObjectURL(blob);

  // Create a link to download it
  var pom = document.createElement('a');
  pom.href = url;
  pom.setAttribute('download', filename);
  pom.click();
}



/*

*/
function file_unitCard_export_pdf(unitCardData){
    let opt = pdfSaveOptions;
    opt.printBackground = true;
    
    //TODO FIXME
    //window.api.send('uic-save-sheet', dialogSavePDFOptions, opt, unitCardData);
}

/*
    Army Builder handlin
*/
function  file_unitInfo_forArmy(fileDataArray){
    let fileObjData = JSON.parse(fileDataArray);
    return fileObjData; //send to page's js
}

/*
    unitBuilder->EXPORT->CSV from UnitRow table to FILE
        invokes ('csv-writer') dependency for ops.
*/
function file_armyBuilder_exportList(htmlUnitTable){

    let exportData = [];
    
    if(htmlUnitTable.rows.length <= 1){
        return exportData;
    }

    for(i = 1; i < htmlUnitTable.rows.length; i++){
        let rowItem = htmlUnitTable.rows[i];

        let data = {};

        data.unitName = rowItem.querySelector('#name').innerHTML;
        data.size = parseInt(rowItem.querySelector('#size').innerHTML);
        data.move = parseInt(rowItem.querySelector('#move').innerHTML);
        data.evade = parseInt(rowItem.querySelector('#evade').innerHTML);
        data.dmgMelee = parseInt(rowItem.querySelector('#melee').innerHTML);
        data.dmgRange = parseInt(rowItem.querySelector('#range').innerHTML);
        data.range = parseInt(rowItem.querySelector('#dist').innerHTML);
        data.armor = parseInt(rowItem.querySelector('#armor').innerHTML);
        data.points = parseInt(rowItem.querySelector('#points').innerHTML);
        data.tags = rowItem.querySelector('#tags').innerText;
        data.tagTotal = parseInt(rowItem.querySelector('#tagTotal').innerHTML);
        data.completeTotal = parseInt(rowItem.querySelector('#total').innerHTML);
        exportData.push(data);
    }

    return exportData;
}

function file_armyBuild_export_data(htmlUnitTable){
    if(htmlUnitTable.rows.length <= 1){
        return;
    }

    let exportData = file_armyBuilder_exportList(htmlUnitTable);
    //TODO FIXME
    //window.api.send('ub-dialog-send-cardgen', exportData);
}

function file_armyBuild_export_tags(tagList, listName){
    if(tagList.length <= 0){
        return; 
    }
    
    let pageData = {fileName:listName, data:tagList};

	//TODO FIXME
    //window.api.send('ab-dialog-send-taglist', pageData);
}

