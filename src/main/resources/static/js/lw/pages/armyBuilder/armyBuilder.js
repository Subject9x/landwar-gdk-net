let unitDataTableRowCount = 0;
let armyListTableRowCount = 0;

let unitData = []
let armyListData = []
let shapeshiftArr = [];

function ab_util_hasShifter(unitName){
    return shapeshiftArr.findIndex((element) => element == unitName);
}
/*
    
    //shapeshiftArray
    let unitName =  $("#" + unitRowId + '_name')[0].value;
    let shapeshiftId = shapeshiftArray.findIndex((element) => element == unitName);

    if(ub_tags_checkByName('Shapeshifter')){
        if(!shapeshiftArray.includes(unitName)){
            shapeshiftArray.push(unitName);
        }
    }
    else{
        shapeshiftArray.splice(shapeshiftId, 1);
        console.log("spliced array at " + shapeshiftId);    //debug
    }
*/

let tagHover;

function ab_tags_checkByName(tagName, tagArr){
    if(tagArr.length === 0){
        return false;
    }
    for(let tagIdx in tagArr){
        let tagId = tagArr[parseInt(tagIdx)];
        if(!Number.isNaN(tagId)){
            if(tagInfo.data[tagId].title === tagName){
                return true;
            }
        }
    }
    return false;
}

function ab_util_string_to_arr(rowTags, tagArr){
    if(rowTags !== undefined && rowTags.length > 0){
        let rowTagsArr = rowTags.split(" ");
        if(rowTagsArr.length > 1){
            for(let rowTagIdx in rowTagsArr){
                let tagStr = rowTagsArr[rowTagIdx];
                if(tagStr != " " && tagStr != ""){
                    tag = parseInt(tagStr);
                    let add = true;
                    for(let i in tagArr){
                        let tagId = tagArr[i];
                        if(tagId == tag){
                            add = false;
                        }
                    }
                    if(add){
                        tagArr.push(tag);
                    }
                }
            }
        }
    }
}

/*
    check duplicates in the unitTable (info, not army!)
*/
function ab_util_check_unit(unitObjData){
    let validate = 13;

    if(unitData.length == 0){
        return validate;
    }

    for(let unitIdx in unitData){
        let unitItem = unitData[unitIdx];
        let keys = Object.keys(unitObjData);

        validate = 13;

        keys.forEach((key, index) => {
            if(unitItem[key] === unitObjData[key]){
                validate -= 1;
            }
        });
        
        if(validate == 0){
            //found duplicate
            break;
        }
    }

    return validate;
}

function ab_sheet_close_window(event){
    event.preventDefault();
    window.api.send('close-window', dialogLoadOptionsUnitList);
}

function ab_control_loadfile(event){
    window.api.send('ab-open-sheet-import-info', dialogLoadOptionsUnitList);
    event.preventDefault();
}

function ab_control_save_listfile(event){
    
    event.preventDefault();
    let tableData = $("#armyListDisplayTable>tbody")[0];
    let cnt = 0;

    if(tableData.rows <= 1){
        return;
    }

    
    $("#armyListDisplayTable>tbody>tr").each((index, tr)=>{
        if(index != 0){
            cnt++;
        }
    });
    let exportData;
    if(cnt >0){
        exportData = file_armyBuilder_exportList(tableData);
    }
    if(exportData !== undefined){
        window.api.send('ub-dialog-save-csv', dialogSaveOptionsUnitList, exportData);
    }
    
}


function ab_control_new_print(event){
    event.preventDefault();
    let tableData = $("#armyListDisplayTable>tbody")[0];
    let cnt = 0;

    if(tableData.rows <= 1){
        return;
    }

    $("#armyListDisplayTable>tbody>tr").each((index, tr)=>{
        if(index != 0){
            cnt++;
        }
    });

    if(cnt >0){
        file_armyBuild_export_data(tableData);
    }
}

/**
 * Creates list of all unique tags in Army and makes a nice printable sheet from it.
 * @param {*} event 
 */
function ab_control_print_tags(event){

    let file = document.getElementById("file").textContent;
    let sendTags = [];    

    $("#armyListDisplayTable>tbody>tr").each((index, tr)=>{
        if(index != 0){
            let rowTagsStr = tr.querySelector("#tags").innerHTML;
            ab_util_string_to_arr(rowTagsStr, sendTags);
        }
    });
    file_armyBuild_export_tags(sendTags, file);
}


/*
    util function, just generalizes adding an HTML row to #unitTable.
*/
function ab_unitInfo_row_add(){
    let table = $("#unitTable")[0];
    let newRow = table.insertRow();
    let rowTemplate = window.nodeFileSys.loadHTML('layout/pages/armyBuilder/unitDataRow.html');

    newRow.innerHTML = rowTemplate;
    unitDataTableRowCount += 1;

    let newRowId = 'unitRow' + unitDataTableRowCount;
    newRow.setAttribute('id', newRowId);

    return newRowId;
}


/*
    called by response from loading existing army list file.
*/
function ab_unitInfo_addData(parsedData){

    for(let objIdx in parsedData){
        let objData = parsedData[objIdx];

        if(objData !== undefined){
            let valid = ab_util_check_unit(objData);
            if(valid > 0){
                unitData.push(objData); 

                let newRowId = ab_unitInfo_row_add();
                let row = $("#" + newRowId)[0];
    
                row.querySelector("#name").innerHTML = objData.unitName;
                let unitName = objData.unitName;

                row.querySelector("#size").innerHTML = objData.size;
                row.querySelector("#move").innerHTML = objData.move;
                row.querySelector("#evade").innerHTML = objData.evade;
                row.querySelector("#melee").innerHTML = objData.dmgMelee;
                row.querySelector("#range").innerHTML = objData.dmgRange;
                row.querySelector("#dist").innerHTML = objData.range;
                row.querySelector("#armor").innerHTML = objData.armor;
                row.querySelector("#tags").innerText = objData.tags;
                row.querySelector("#tagTotal").innerHTML = objData.tagTotal;
                row.querySelector("#points").innerHTML = objData.points;
                row.querySelector("#total").innerHTML = objData.completeTotal;
                
                let tagSpan = row.querySelector('#tagDiv');
                
                if(objData.tags !== undefined && objData.tags.length > 0){
                    tagSpan.innerHTML = '<span class=" ui-icon ui-icon-white ui-icon-gear"></span>';
                    tagSpan.onmouseover = function(event){
                        ab_tagRow_show(newRowId, event);
                    };
                    
                    tagSpan.onmouseleave = function(event){
                        //leaveab_tagRow_hide
                        ab_tagRow_hide();
                    };
                  
                    //Shapeshifter meta check
                    let checkTags = [];
                    ab_util_string_to_arr(objData.tags, checkTags);

                    if(ab_tags_checkByName("Shapeshifter", checkTags)){
                        if(!ab_util_hasShifter(unitName)){
                            shapeshiftArr.push(row.querySelector("#name").innerHTML);
                        }
                    }
                }       

                let addBtn = row.querySelector('button');
                addBtn.setAttribute('id', 'btnAdd');
                addBtn.addEventListener("click", function(){
                    ab_unitInfo_addToList(newRowId);
                    event.preventDefault();
                });
            }
        }
    }
}


/**
 * called from renderArmyBuilderSheet,
 *  receives parsed CSV data for storage
 * @param {*} parsedData 
 */
function ab_armyList_parseData(parsedData){
    let armyListTable = $('#armyListDisplayTable')[0];

    for(let objIdx in parsedData){
        let objData = parsedData[objIdx];

        if(objData !== undefined){
            let valid = ab_util_check_unit(objData);
            if(valid > 0){
                armyListData.push(objData); 

                let newListRow = armyListTable.insertRow();
                newListRow.setAttribute('id', 'armyRow' + armyListTableRowCount);
            
                let rowTemplate = window.nodeFileSys.loadHTML('layout/pages/armyBuilder/armyListDataRow.html');
                newListRow.innerHTML = rowTemplate;
    
                newListRow.querySelector("#name").innerHTML = objData.unitName;
                newListRow.querySelector("#size").innerHTML = objData.size;
                newListRow.querySelector("#move").innerHTML = objData.move;
                newListRow.querySelector("#evade").innerHTML = objData.evade;
                newListRow.querySelector("#melee").innerHTML = objData.dmgMelee;
                newListRow.querySelector("#range").innerHTML = objData.dmgRange;
                newListRow.querySelector("#dist").innerHTML = objData.range;
                newListRow.querySelector("#armor").innerHTML = objData.armor;
                newListRow.querySelector("#tags").innerText = objData.tags;
                newListRow.querySelector("#tagTotal").innerHTML = objData.tagTotal;
                newListRow.querySelector("#points").innerHTML = objData.points;
                newListRow.querySelector("#total").innerHTML = objData.completeTotal;
    
                armyListTableRowCount += 1;
            
                let tagSpan =  newListRow.querySelector('#tagDiv');
                if(objData.tags !== undefined && objData.tags.length > 0){
                    tagSpan.innerHTML = '<span class=" ui-icon ui-icon-white ui-icon-gear"></span>';
                    tagSpan.onmouseover = function(event){
                        //enter
                        ab_tagRow_show(newListRow.id, event);
                    };
                    
                    tagSpan.onmouseleave = function(event){
                        //leave
                        ab_tagRow_hide();
                    };
                    
                    let checkTags = [];
                    ab_util_string_to_arr(objData.tags, checkTags);

                    if(ab_tags_checkByName("Shapeshifter", checkTags)){
                        if(!ab_util_hasShifter(unitName)){
                            shapeshiftArr.push(row.querySelector("#name").innerHTML);
                        }
                    }
                } 

                let btnRemove = newListRow.querySelector('button');
                btnRemove.setAttribute('id', 'btnRemove');
                btnRemove.addEventListener("click", function(){
                    ab_armyList_removeEntry(newListRow.id);
                    event.preventDefault();
                });
            
                ab_armyList_adjustTotalPoints(parseFloat(newListRow.querySelector('#total').innerHTML));
            }
        }
    }
}


function ab_unitData_addtoList(unitRowId, unitRowdiv){

}

/**
 * Triggered by unitTable btnAdd click events.
 *  copy unit data to armyListDataList
 *  update armyListDisplayTable
 *  update armyList running points Tally
 * 
 * @param {*} unitRowId 
 */
function ab_unitInfo_addToList(unitRowId){
    
    let unitRow = $('#' + unitRowId)[0];
    armyListTableRowCount += 1;

    let armyListTable = $('#armyListDisplayTable')[0];

    let newListRow = armyListTable.insertRow();
    newListRow.setAttribute('id', 'armyRow' + armyListTableRowCount);

    let rowTemplate = window.nodeFileSys.loadHTML('layout/pages/armyBuilder/armyListDataRow.html');
    newListRow.innerHTML = rowTemplate;
    
    newListRow.querySelector('#name').innerHTML = unitRow.querySelector('#name').innerHTML;
    newListRow.querySelector('#size').innerHTML = unitRow.querySelector('#size').innerHTML;
    newListRow.querySelector('#move').innerHTML = unitRow.querySelector('#move').innerHTML;
    newListRow.querySelector('#evade').innerHTML = unitRow.querySelector('#evade').innerHTML;
    newListRow.querySelector('#melee').innerHTML = unitRow.querySelector('#melee').innerHTML;
    newListRow.querySelector('#range').innerHTML = unitRow.querySelector('#range').innerHTML;
    newListRow.querySelector('#dist').innerHTML = unitRow.querySelector('#dist').innerHTML;
    newListRow.querySelector('#armor').innerHTML = unitRow.querySelector('#armor').innerHTML;
    newListRow.querySelector('#points').innerHTML = unitRow.querySelector('#points').innerHTML;
    newListRow.querySelector('#total').innerHTML = unitRow.querySelector('#total').innerHTML;
    newListRow.querySelector('#tags').innerText = unitRow.querySelector('#tags').innerText;
    newListRow.querySelector('#tagTotal').innerText = unitRow.querySelector('#tagTotal').innerText;

    let tagSpan =  newListRow.querySelector('#tagDiv');

    if(unitRow.querySelector('#tagTotal').innerText !== undefined && unitRow.querySelector('#tagTotal').innerText.length > 0){
        tagSpan.innerHTML = '<span class=" ui-icon ui-icon-white ui-icon-gear"></span>';
        tagSpan.onmouseover = function(event){
            //enter
            ab_tagRow_show(newListRow.id, event);
        };
        
        tagSpan.onmouseleave = function(event){
            //leave
            ab_tagRow_hide();
        };

        let checkTags = [];
        ab_util_string_to_arr(newListRow.querySelector('#tags').innerText, checkTags);

        if(ab_tags_checkByName("Shapeshifter", checkTags)){
            //TODO - shapeshifter
        }
      
    } 

    let btnRemove = newListRow.querySelector('button');
    btnRemove.setAttribute('id', 'btnRemove');
    btnRemove.addEventListener("click", function(){
        ab_armyList_removeEntry(newListRow.id);
        event.preventDefault();
    });



    ab_armyList_adjustTotalPoints(parseFloat(unitRow.querySelector('#total').innerHTML));
}

function ab_armyList_removeEntry(entryRowId){

    let table = $('#armyListDisplayTable')[0];
    if(table.rows.length < 1){
        return;
    }
    let row = $('#' + entryRowId)[0];
    let removePoints = parseFloat(row.querySelector('#total').innerHTML);
    ab_armyList_adjustTotalPoints(0 - removePoints);
    
    table.querySelector('#'+entryRowId).remove();
}

function ab_armyList_adjustTotalPoints(pointAdjust){

    let textVal = $('#armyListPointTotal')[0].innerText;
    if(textVal == ""){
        textVal = "0.0";
    }
    let runningTotal = parseFloat(textVal);

    runningTotal += pointAdjust;

    $('#armyListPointTotal')[0].innerText = runningTotal;

}


function ab_tagRow_show(rowId, event){
    //enter
    let unitRow = $('#' + rowId)[0];
    let unitTags = unitRow.querySelector('#tags').innerText.split(" ");
    
    if(unitTags.length <= 0 || unitTags[0] == ""){
        tagHover.style.display = 'none';
        return;
    }
    tagHover.style.display = 'block';
    
    tagHover.innerHTML = window.nodeFileSys.loadHTML('layout/pages/armyBuilder/tagsRollover.html');

    //let tagArr = row_tagArrays[newRowId];
    let tagList = tagHover.querySelector("#abInfoKeywords");
    tagList.innerHTML = '';

    let tagItem;
    if(unitTags.length > 0){
        for(let tagNum in unitTags){
            if(unitTags[tagNum] != ""){
                tagItem = document.createElement('li');
                tagItem.innerHTML = tagInfo.data[unitTags[tagNum]].title;
                tagList.appendChild(tagItem);
            }
        }
    }
    tagHover.style.position = 'absolute';
    tagHover.style.top = window.scrollY + event.clientY  + 'px';
    tagHover.style.left = window.scrollX + event.clientX + 'px';
}

function ab_tagRow_hide(){
    //leave
    if(tagHover != undefined){
        tagHover.style.display = 'none';
     }
}