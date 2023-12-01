/*
    Javascript layer for page: UnitBuilder Sheet window
*/
const doc = document;

let unitTableRowCount = 0;
let searchArray = [];
let tagWindow_tagArray = [];
let row_tagArrays = {};

let hasSaved = false;   //save-prompt feature


function ub_row_tag_ini(rowId){
    let rowData = [];
    row_tagArrays[rowId] = rowData;
    return rowData;
}

function ub_row_tag_insert(rowId, newTag){
    let rowTags = row_tagArrays[rowId];
    rowTags.push(newTag);
}

/*
    util: convert tagArray to string
*/
function ub_tags_update_row_array(rowId, srcArray){
    let rowArray = row_tagArrays[rowId];
    ub_util_array_deepcpy(srcArray, rowArray)
}

function ub_util_array_deepcpy(srcArray, dstArray){
    dstArray.length = 0;
	srcArray.forEach(elem => {
	    dstArray.push(elem)
	});
}


/*
    unitBuilder -> Control Row buttons
*/
function ub_control_select_all(selectAll){
    let rowTableBody = doc.querySelector("#unitTable>tbody");
    
    if(rowTableBody.rows.length <= 1){
        return;
    }
    
    let rows = doc.querySelectorAll("#unitTable>tbody>tr");
    
    let index = 0;
    rows.forEach((row)=>{
		if(index > 0){
            let elm = doc.querySelector("#"+row.id + "_select");
            elm.checked = selectAll;
		}
		index += 1;
	});
};


function ub_control_delete_select(){
    let rowTableBody = doc.querySelector("#unitTable>tbody");
    
    if(rowTableBody.rows.length <= 1){
        return;
    }
    
    let rows = doc.querySelectorAll("#unitTable>tbody>tr");
    let index = 0;
    rows.forEach((row)=>{
		if(index > 0){
            let elm = doc.querySelector("#"+row.id + "_select");
            if(elm.checked === true){
                doc.querySelector("#"+row.id).remove();
            }
		}
		index += 1;
	});
}

function ub_control_save_select(event){
    event.preventDefault();
    let tableData = doc.querySelector("#unitTable>tbody");
    let cnt = 0;

    if(tableData.rows <= 1){
        return;
    }

    let rows = doc.querySelectorAll("#unitTable>tbody>tr");
    let index = 0;
    rows.forEach((row)=>{
		if(index > 0){
            let elm = doc.querySelector("#"+row.id + "_select");
            if(elm.checked == true){
                cnt++;
            }
		}
		index += 1;
	});

	//TODO - fix file saving
    if(cnt >0){
        file_unitBuild_export_csv(tableData);
    }
}

function ub_control_loadfile(event){
    event.preventDefault();
    //TODO FIXME
    //window.api.send('ub-dialog-load-async', dialogLoadOptionsUnitList);
}

function ub_control_new_print(event){
    event.preventDefault();
    let tableData = doc.querySelector("#unitTable>tbody");
    let cnt = 0;

    if(tableData.rows <= 1){
        return;
    }
	
	let rows = doc.querySelectorAll("#unitTable>tbody>tr");
    
    let index = 0;
    rows.forEach((row)=>{
		if(index > 0){
            let elm = doc.querySelector("#"+row.id + "_select");
            if(elm.checked == true){
                cnt++;
            }
		}
		index += 1;
	});

    if(cnt > 0){
        let dataObj = file_unitBuild_export(tableData);
        
        doc.querySelector("#submitPrintPdf>input").value = JSON.stringify(dataObj["data"]);
        
        doc.querySelector("#submitPrintPdf").submit();
        
    }
}


function ub_control_csv_download(event){
    event.preventDefault();
    let tableData = doc.querySelector("#unitTable>tbody");
    let cnt = 0;

    if(tableData.rows <= 1){
        return;
    }

	let rows = doc.querySelectorAll("#unitTable>tbody>tr");
    
    let index = 0;
    rows.forEach((row)=>{
		if(index > 0){
            let elm = doc.querySelector("#"+row.id + "_select");
            if(elm.checked == true){
                cnt++;
            }
		}
		index += 1;
	});
    
    if(cnt > 0){
        file_unitBuild_export_data(tableData);
        
    }
}


function ub_control_help(event){
    
}



/*
    Row Data manipulation
*/
/*
    row id without '_x'
*/
function ub_get_rowid(rowTagVal){
    let rowId = rowTagVal.substring(0,rowTagVal.indexOf('_'));
    return rowId;
}

/*
    check if #unitRow tag list has a given tag already.
*/
function ub_tags_checkExisting(tagId, srcArray){
    let tagVal = parseInt(tagId);
    if(srcArray.length === 0){
        return false;
    }
    for(let tagIdx in srcArray){
        if(tagVal !== NaN && tagVal === srcArray[tagIdx]){
            return true;
        }
    }
    return false;
}

function ub_tags_checkByName(tagName){
    if(tagWindow_tagArray.length === 0){
        return false;
    }
    for(let tagIdx in tagWindow_tagArray){
        let tagId = tagWindow_tagArray[parseInt(tagIdx)];
        if(!Number.isNaN(tagId)){
            if(tagInfo.data[tagId].title === tagName){
                return true;
            }
        }
    }
    return false;
}


/*
    update the _tagBuffer value.
*/
function ub_tagModal_update_tagArray(newVal, addMe){
    let intNewVal = parseInt(newVal);
    
    if(addMe){
        if(!ub_tags_checkExisting(newVal, tagWindow_tagArray)){
            tagWindow_tagArray.push(intNewVal);
        }
    }
    else{
        if(ub_tags_checkExisting(newVal, tagWindow_tagArray)){
            let idx = tagWindow_tagArray.indexOf(intNewVal);
            if(idx > -1){
                tagWindow_tagArray.splice(idx, 1);
            }
        }
    }
    return tagWindow_tagArray;
}

/*
    re-run all tag requirements on incoming row_tagArray value.

*/
function ub_tagModal_validate_tags(){
    let rowId = doc.querySelector("#tagWindow_rowId").value;
    let unitTotal = parseFloat(doc.querySelector("#tagWindow_baseCost").innerHTML);
    let tagTotalCost = parseFloat(doc.querySelector("#tagWindow_tagCost").innerHTML);

    let tagRuleList = doc.querySelector("#tagRulesListData>tbody");
    let tagRow = tagRuleList.childNodes[1];
    for(let tag in tagInfo.data){

        let tagId = tagRow.children[1].children[1].value;
        let tagObj = tagInfo.data[tagId];
        let isCheck = tagRow.children[1].children[0].checked;

        let cost = tagObj.func(rowId);
        cost = parseFloat(cost.toFixed(1));

        let warn = ub_tagModal_tagRow_reqs(tagRow);
        if(warn){
            isCheck = false;
            tagRow.children[1].children[0].checked = false;
            tagRow.children[1].children[0].setAttribute('disabled', 'true');
        }
        else{
            tagRow.children[1].children[0].removeAttribute('disabled');
        }
        
        if(warn && !isCheck){
            tagRow.classList.remove('tagRuleLineActive');
            tagRow.children[2].children[0].innerHTML = "";
            if(ub_tags_checkExisting(tagId, tagWindow_tagArray)){
                tagTotalCost = tagTotalCost - cost;
                tagWindow_tagArray = ub_tagModal_update_tagArray(tagId, isCheck);
            }
        }
        
        tagRow = tagRow.nextSibling;
    }

    doc.querySelector("#tagWindow_tagCost").innerHTML = Math.round((tagTotalCost + Number.EPSILON) * 100) / 100;
    doc.querySelector("#tagWindow_totalCost").innerHTML = Math.round(((unitTotal + tagTotalCost) + Number.EPSILON) * 100) / 100;
}

/*
    Running in the background whenever stats change.
*/
function ub_tagModal_tag_check_req(rowId){
    let unitTotal = parseFloat(doc.querySelector("#"+ rowId + '_points').innerHTML);
    let tagTotalCost = parseFloat(doc.querySelector("#"+ rowId + '_tagTotal').innerHTML);

    let tagCacheArray = row_tagArrays[rowId];
    //
    let tagId = 0;
    for(let tag in tagInfo.data){

        tagId = tag;
        let tagObj = tagInfo.data[tagId];

        let isCheck = ub_tags_checkExisting(tagId, tagCacheArray);

        if(isCheck){

            let warn = tagInfo.data[tagId].reqs(rowId);
            if(warn){
                isCheck = false;
            }
            
            let cost = tagObj.func(rowId);
            cost = parseFloat(cost.toFixed(1));

            if(!isCheck){
                if(ub_tags_checkExisting(tagId, tagCacheArray)){
                    tagTotalCost = tagTotalCost - cost; 
                }
                tagCacheArray = ub_tagModal_update_tagArray(tagId, isCheck);
            }
        }
    }
    doc.querySelector("#"+rowId + '_tagTotal').innerHTML = Math.round((tagTotalCost + Number.EPSILON) * 100) / 100;
}


/*
    tagModal window funcs
*/
function ub_tagModal_tagRow_clickInfo(tagRow){
    let tagText = doc.querySelector('#tagWindow_descText');
    let tagTitle = doc.querySelector('#tagWindow_descTitle');
    let tagEqt = doc.querySelector('#tagWindow_equation');
    let tagId = parseInt(tagRow.children[1].children[1].value);

    tagText.innerHTML = '';
    tagText.innerHTML = tagInfo.data[tagId].desc;

    tagTitle.innerHTML = '';
    tagTitle.innerHTML = '<h3>' + tagInfo.data[tagId].title + '</h3>';

    tagEqt.innerHTML = '';

    if( tagInfo.data[tagId].reqs !== undefined){
        let warn = ub_tagModal_tagRow_reqs(tagRow);
        if(warn){
            tagRow.children[1].children[0].checked = false;
            tagRow.children[1].children[0].setAttribute('disabled', 'true');
        }
        else{
            tagRow.children[1].children[0].removeAttribute('disabled');
        }
    }

    if(tagInfo.data[tagId].eqt !== undefined){
        tagEqt.innerHTML = tagInfo.data[tagId].eqt;
    }
}

/*
    tagModal/rulesValidate
        runs the reqs() function of the tag and returns true/false
*/
function ub_tagModal_tagRow_reqs(tagRow){
    let tagId = parseInt(tagRow.children[1].children[1].value);
    let warn = tagInfo.data[tagId].reqs(doc.querySelector('#tagWindow_rowId').value);
    if(warn === ''){
        doc.querySelector('#tagWindow_descWarn').innerHTML = "";
        tagRow.classList.remove('tagRuleLineDisable');
        return false;
    }
    doc.querySelector('#tagWindow_descWarn').innerHTML = warn;
    tagRow.classList.remove('tagRuleLineActive');
    tagRow.classList.add('tagRuleLineDisable');
    return true;
}
/*
    tagModal/tagWindow/tagRow/Checkbox
*/
function ub_tagModal_tagRow_check(tagRow){
    let isCheck = tagRow.children[1].children[0].checked;
    let tagId = tagRow.children[1].children[1].value;
    let tagObj = tagInfo.data[tagId];
    let rowId = doc.querySelector('#tagWindow_rowId').value;
    let tagCost = parseFloat(doc.querySelector('#tagWindow_tagCost').innerHTML);
    let unitTotal = parseFloat(doc.querySelector('#tagWindow_baseCost').innerHTML);

    let cost = tagObj.func(rowId);
    cost = parseFloat(cost.toFixed(1));

    if(isCheck){
        //adding tag to list, already has passed validation
        tagRow.classList.add('tagRuleLineActive');
        tagRow.children[2].children[0].innerHTML = cost;
        tagCost += cost;
    }
    else{
        //removing existing tag from list.
        //was on list, but now gone.
        tagRow.classList.remove('tagRuleLineActive');
        tagRow.children[2].children[0].innerHTML = "";
        tagCost = tagCost - cost; 
    }

    tagWindow_tagArray = ub_tagModal_update_tagArray(tagId, isCheck);

    doc.querySelector("#tagWindow_tagCost").innerHTML = Math.round((tagCost + Number.EPSILON) * 100) / 100;
    doc.querySelector("#tagWindow_totalCost").innerHTML = Math.round(((unitTotal + tagCost) + Number.EPSILON) * 100) / 100;

    ub_tagModal_validate_tags();
}
/*
    tagModal/close-or-save
*/
function ub_tagModal_close(doSave){
    let unitRowId = doc.querySelector('#tagWindow_rowId').value;
    if(doSave){
        // let unitTagList = document.getElementById(unitRowId + '_tagList');
        let unitTagCost = doc.querySelector("#" + unitRowId + '_tagTotal');

        unitTagCost.innerHTML = doc.querySelector('#tagWindow_tagCost').innerHTML;

        ub_tags_update_row_array(unitRowId, tagWindow_tagArray);
        
        let tagTotal = parseFloat(unitTagCost.innerHTML)
        let unitTotal = parseFloat(doc.querySelector("#" + unitRowId + '_points').innerHTML);
        let finalTotal = Math.round(((unitTotal + tagTotal) + Number.EPSILON) * 100) / 100;
        doc.querySelector("#" + unitRowId + "_total").innerHTML =  finalTotal;
    }
	
    tagModal.setAttribute('hidden','true');
}


/*
    On-click - instantiate the tagModalWindow,
        populate with tagInfo data, the source rowId, 
*/
function ub_row_tags_onclick(event){
    
    let rowId = event.currentTarget.getAttribute('data-index-number');
    let tagModal = doc.querySelector('#tagModal');
    let tagWindow = doc.querySelector('#tagWindow');
    
    tagModal.removeAttribute('hidden');
    
    //set hidden input to parent rowId from the unit table
    doc.querySelector('#tagWindow_rowId').value = rowId;
    tagWindow.style.display = 'block';

    //set base total display in tagWindow
    doc.querySelector('#tagWindow_baseCost').innerHTML = doc.querySelector("#"+ rowId + "_points").innerHTML;

    //zero-out the tag window array
    tagWindow_tagArray.length = 0;
    ub_util_array_deepcpy(row_tagArrays[rowId], tagWindow_tagArray);

    //build the complete TAG list in the tag table.
    let tagRuleList = doc.querySelector("#tagRulesListData>tbody");
    let tagCost = 0;
    
    for(let tag in tagInfo.data){
		
		let tagRuleRow = tagRuleList.children[parseInt(tag)];
		let isCheck = false;
		
		if(row_tagArrays[rowId].length > 0){
			isCheck = ub_tags_checkExisting(tag, tagWindow_tagArray);
		}
		
        if(isCheck == true){
            let cost = tagInfo.data[tag].func(rowId);
            cost = parseFloat(cost.toFixed(1));

            tagRuleRow.classList.add('tagRuleLineActive');
            tagRuleRow.children[2].children[0].innerHTML = cost;
            tagCost = tagCost + cost;
            tagRuleRow.querySelector("#tagCheck").checked = true;
        }
        else{
            tagRuleRow.classList.remove('tagRuleLineActive');
            tagRuleRow.children[2].children[0].innerHTML = "";
            tagRuleRow.querySelector("#tagCheck").checked = false;
       	}
    }

    //wonderful double loop - we can't write and validate the tag list in the same go...
    ub_tagModal_validate_tags();

    //zero-out cost totals first.
    let unitCost = parseFloat(doc.querySelector('#tagWindow_baseCost').innerHTML);

    doc.querySelector("#tagWindow_tagCost").innerHTML = Math.round((tagCost + Number.EPSILON) * 100) / 100;
    doc.querySelector("#tagWindow_totalCost").innerHTML = Math.round(((unitCost + tagCost) + Number.EPSILON) * 100) / 100;

    //clear out warn box
    doc.querySelector('#tagWindow_descWarn').innerHTML = '';
    doc.querySelector('#tagWindow_equation').innerHTML = '';
}

/*
    UNIT ROW FUNCTIONS
*/
function ub_row_select_check(rowData, celCount, rowId, celName){
    let rowSelectCheck = rowData.cells[celCount].getElementsByTagName('input')[0];
    rowSelectCheck.setAttribute('id', rowId + celName);

    return celCount + 1;
}
function ub_row_add_element_input_num(rowData, celCount, tagType, rowId, celName){
    rowData.cells[celCount].getElementsByTagName(tagType)[0].setAttribute('id', rowId + celName);
    rowData.cells[celCount].getElementsByTagName(tagType)[0].value = 0;
    document.getElementById(rowId + celName).addEventListener("input", ub_row_on_change_event);
    return celCount + 1;
}

function ub_row_add_element_input_name(rowData, celCount, tagType, rowId, celName){
    rowData.cells[celCount].getElementsByTagName(tagType)[0].setAttribute('id', rowId + celName);
    return celCount + 1;
}

function ub_row_add_element_label_points(rowData, celCount, tagType, rowId, celName){
    rowData.cells[celCount].getElementsByTagName(tagType)[0].setAttribute('id', rowId + celName);
    rowData.cells[celCount].getElementsByTagName(tagType)[0].innerHTML = "0";
    return celCount + 1;
}

function ub_row_add_element_tag(rowData, celCount, tagType, rowId, celName){
    
    let rowTagBtn = rowData.querySelector("#btnTags");
    rowTagBtn.setAttribute('id', rowId + celName)
	
	rowTagBtn.onclick = function(event){
		event.stopPropagation();
		event.preventDefault();
		ub_row_tags_onclick(event);
	};
    rowTagBtn.setAttribute('data-index-number', rowId);
    
    return celCount + 1;
}

function ub_row_add(){
	let table = doc.querySelector("#unitTable");
    let newRow = table.insertRow();
    let rowTemplate = doc.querySelector("#unitRowTmplt>tbody>tr");
    
    newRow.innerHTML = rowTemplate.innerHTML;
    
    unitTableRowCount += 1; //global counter to ensure each row is a true UID.
    let newRowId = 'unitRow' + unitTableRowCount;
    newRow.setAttribute('id', newRowId);
    
    let cellCount = 0;

    cellCount = ub_row_select_check(newRow, cellCount, newRowId, '_select');

    cellCount = ub_row_add_element_input_name(newRow, cellCount, 'input', newRowId, '_name');
    
    cellCount = ub_row_add_element_input_num(newRow, cellCount, 'input', newRowId, '_size');
    
    cellCount = ub_row_add_element_input_num(newRow, cellCount, 'input', newRowId, '_move');
    
    cellCount = ub_row_add_element_input_num(newRow, cellCount, 'input', newRowId, '_evade');
    
    cellCount = ub_row_add_element_input_num(newRow, cellCount, 'input', newRowId, '_DMGM');
    
    cellCount = ub_row_add_element_input_num(newRow, cellCount, 'input', newRowId, '_DMGR');
    
    cellCount = ub_row_add_element_input_num(newRow, cellCount, 'input', newRowId, '_range');
    
    cellCount = ub_row_add_element_input_num(newRow, cellCount, 'input', newRowId, '_armor');
    
    //cellCount = ub_row_add_element_input_num(newRow, cellCount, 'input', newRowId, '_structure');
    
    cellCount = ub_row_add_element_label_points(newRow, cellCount, 'label', newRowId, '_points');

    cellCount = ub_row_add_element_tag(newRow, cellCount, 'span', newRowId, '_tags');

    cellCount = ub_row_add_element_label_points(newRow, cellCount, 'label', newRowId, '_tagTotal');

    cellCount = ub_row_add_element_label_points(newRow, cellCount, 'label', newRowId, '_total');
    
    cellCount = ub_row_add_element_label_points(newRow, cellCount, 'label', newRowId, '_dbId');

    ub_row_tag_ini(newRowId);

    return newRowId;
}

function ub_row_remove(){
    let table = doc.querySelector('#unitTable');
    if(table.rows.length < 2){
        return;
    }
    
    let lastRowId = doc.querySelector('#unitTable tr:last-child').id;
    
    delete row_tagArrays[lastRowId];
    
    doc.querySelector('#unitTable tr:last-child').remove();
}


function ub_row_copy(){
    let table = doc.querySelector('#unitTable');
    if(table.rows.length < 2){
        return;
    }
   let lastRowId= doc.querySelector('#unitTable tr:last-child').id;

   let newRowId = ub_row_add();
   doc.querySelector("#" + newRowId + '_name').value = doc.querySelector("#" + lastRowId + '_name').value;
   doc.querySelector("#" + newRowId + '_size').value = parseInt(doc.querySelector("#" + lastRowId + '_size').value);
   doc.querySelector("#" + newRowId + '_move').value = parseInt(doc.querySelector("#" + lastRowId + '_move').value);
   doc.querySelector("#" + newRowId + '_evade').value = parseInt(doc.querySelector("#" + lastRowId + '_evade').value);
   doc.querySelector("#" + newRowId + '_DMGM').value = parseInt(doc.querySelector("#" + lastRowId + '_DMGM').value);
   doc.querySelector("#" + newRowId + '_DMGR').value = parseInt(doc.querySelector("#" + lastRowId + '_DMGR').value);
   doc.querySelector("#" + newRowId + '_range').value = parseInt(doc.querySelector("#" + lastRowId + '_range').value);
   doc.querySelector("#" + newRowId + '_armor').value = parseInt(doc.querySelector("#" + lastRowId + '_armor').value);
   //$("#" + newRowId + '_structure').val(  parseInt($("#" + lastRowId + '_structure')[0].value) ) ;
   doc.querySelector("#" + newRowId + '_points').value = parseInt(doc.querySelector("#" + lastRowId + '_points').value);

   let newArray = [...row_tagArrays[lastRowId]];

   row_tagArrays[newRowId] = newArray;
   ub_row_change_points(newRowId);
   ub_row_tag_validate(newRowId);

   let tagTotal = parseFloat(doc.querySelector("#" + newRowId + '_tagTotal').innerHTML)
   let unitTotal = parseFloat(doc.querySelector("#" + newRowId + '_points').innerHTML);
   let finalTotal = Math.round(((unitTotal + tagTotal) + Number.EPSILON) * 100) / 100;
   doc.querySelector("#" + newRowId + "_total").innerHTML =  finalTotal;

}


/*
    Binder functions for unitBuilder
*/

function ub_row_change_points(rowId){

    let sizeVal = parseInt(doc.querySelector("#"+ rowId + '_size').value);
    let moveVal = parseInt(doc.querySelector("#"+ rowId + '_move').value);
    let evadeVal = parseInt(doc.querySelector("#"+ rowId + '_evade').value);
    let dmgMeleeVal = parseInt(doc.querySelector("#"+ rowId + '_DMGM').value);
    let dmgRangeVal = parseInt(doc.querySelector("#"+ rowId + '_DMGR').value);
    let rangeVal = parseInt(doc.querySelector("#"+ rowId + '_range').value);
    let armorVal = parseInt(doc.querySelector("#"+ rowId + '_armor').value);
    //let structVal = parseInt($("#"+ rowId + '_structure')[0].value);

    let sizeCost = uc_calc_Size(sizeVal);
    let moveCost = uc_calc_Move(moveVal, sizeVal);
    let evadeCost = uc_calc_Evade(sizeVal, evadeVal, moveVal);
    let dmgMeleeCost = uc_calc_Damage_Melee(dmgMeleeVal, moveVal);
    let dmgRangeCost = uc_calc_Damage_Range(dmgRangeVal);
    let rangeCost = uc_calc_Range(moveVal, rangeVal, dmgRangeVal);
    let armorCost = uc_calc_Armor(armorVal, sizeVal);
    //let structCost = uc_calc_Structure(structVal,sizeVal);
    

    //DEBUG ONLY
    /*console.log('-------------change-------------------');
    console.log('sizeCost= ' + sizeCost);
    console.log('moveCost= ' + moveCost);
    console.log('evadeCost= ' + evadeCost);
    console.log('dmgMeleeCost= ' + dmgMeleeCost);
    console.log('dmgRangeCost= ' + dmgRangeCost);
    console.log('rangeCost= ' + rangeCost);
    console.log('armorCost= ' + armorCost);
    console.log('structCost= ' + structCost);*/


    let pointsVal = uc_calc_baseCost(sizeCost, moveCost, evadeCost, dmgMeleeCost, dmgRangeCost, rangeCost, armorCost);//, structCost);
    pointsVal = Math.round((pointsVal + Number.EPSILON) * 100) / 100;

    doc.querySelector("#" + rowId+'_points').innerHTML = pointsVal;

    
    return pointsVal;
}

/*
    Validates Row-tag list and removes invalid tags
*/
function ub_row_tag_validate(rowId){
    let rowArray = row_tagArrays[rowId];
    if(rowArray.length === 0){
        return;
    }

    let newTagCost = 0;
    let undoCost = 0;
    let tagTotal = 0;

    let removeThese = [];
    for(let idx in rowArray){
        let tagId = rowArray[idx];
        let tagData = tagInfo.data[tagId];
        let tagCost = tagData.func(rowId);
        if(tagData.reqs(rowId) !== ''){
            undoCost += (tagCost * -1);
            removeThese.push(idx);
        }
        else{
            newTagCost =  newTagCost + tagCost;
        }
    }
    tagTotal = newTagCost + undoCost;
    tagTotal = Math.round((tagTotal + Number.EPSILON) * 100) / 100;
    

    doc.querySelector("#" + rowId + '_tagTotal').innerHTML = tagTotal;

    for(let idx in removeThese){
        let index = removeThese[idx];
        rowArray = rowArray.splice(index, 1);
    }
}
/*
    TD <input> onChange binding.
*/
function ub_row_on_change_event(event){
    let thisRowId = ub_get_rowid(event.srcElement.id);
    if(event.srcElement.max){
        let maxVal = parseInt(event.srcElement.max);
        let testVal = parseInt(event.srcElement.value);
        if(testVal > maxVal){
            event.srcElement.value =  event.srcElement.max;
        }
    }
    ub_row_change_points(thisRowId);
    ub_row_tag_validate(thisRowId); //split from change_points becuase some req / cost funcs run change_points;

    let tagTotal = parseFloat(doc.querySelector("#" + thisRowId + '_tagTotal').innerHTML)
    let unitTotal = parseFloat(doc.querySelector("#" + thisRowId + '_points').innerHTML);
    let finalTotal = Math.round(((unitTotal + tagTotal) + Number.EPSILON) * 100) / 100;
    doc.querySelector("#" + thisRowId + "_total").innerHTML =  finalTotal;
    
    event.preventDefault();
}