/*
    Page: TAG Library page
*/


function tag_rules_pdf_core(){
   // window.api.send('tag-save-core', dialogSavePDFOptions, pdfSaveOptions);
}

function tag_rules_pdf_getHtml(){
    //return window.nodeFileSys.loadHTML("layout/pages/tagLibrary/tagLibView.html");
}

let loadedTable = false;

function tl_buildTable(){
    if(loadedTable){
        return;
    }
    var tagTable = document.querySelector('#tagLib_list>tbody');
    var celCount = 0;
    var rowCount = 0;
    var tagRow = tagTable.insertRow();
   
   	for (var i = 0; i < sortedTags.length; i++) { 
        var tagItem = sortedTags[i];
        
        if(celCount === 5){
            tagRow = tagTable.insertRow();
            celCount = 0;
        }
        
        var cel = tagRow.insertCell(celCount);
        cel.id = "tgView_" + i;
        cel.style = 'text-align:center;'
        
       	var btnFrag = document.createDocumentFragment();
       	var btn = document.createElement('input');
       	btn.type = 'button';
       	btn.classList.add("btn");
       	btn.classList.add("tagViewButton");
        btn.value = tagItem.title
        btn.id = "tg_" + i;
        btn.setAttribute('data-index-number', tagItem.id);
        btn.addEventListener('click', (e)=>{
            tl_showTag(e.srcElement.dataset.indexNumber);
        });
        
        btnFrag.appendChild(btn);
        document.getElementById(cel.id).append(btnFrag);

        celCount++;
    }
    loadedTable = true;
}

function tl_showTag(celData){
    let tagData = sortedTags.find(isTag, parseInt(celData));
    
    let tagViewPanel = document.querySelector("#tagLibViewPanel");
    
    tagViewPanel.removeAttribute('hidden');
    
    let tagView = document.querySelector("#tagView");
    tagView.style.display = 'none';
    
    document.querySelector("#tagWindow_descTitle").innerHTML = '<h4>'+ tagData.title + '</h4>';
    document.querySelector("#tagWindow_descText").innerHTML = tagData.desc;
    document.querySelector("#tagWindow_equation").innerHTML = tagData.eqt;
    document.querySelector("#tagWindow_descWarn").innerHTML = tagData.reqs('blank');
    
    tagView.style.display = 'block';
    
}