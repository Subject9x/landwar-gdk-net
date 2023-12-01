

const tagInfo = {
    id : "core",
    data :[ 
        {
            title : 'Advanced Gun Sights',
            desc : '<p><i>Combat Phase</i></p><p><b>Target</b> of this Unit <b>cannot</b> have <b>+1 DEF</b> when attacked at <b>Long Range</b>.</p>',
            func : (rowId) =>{
                let moveVal = parseInt(document.getElementById(rowId + '_move').value);
                let rangeDamageVal = parseInt(document.getElementById(rowId + '_DMGR').value);
                let rangeVal = parseInt(document.getElementById(rowId + '_range').value);
                return ((moveVal / 4) + (rangeDamageVal / 2) + (rangeVal / 2));
            },
            reqs : (rowId) =>{
                let warn = '';
                let rangeDamageVal = parseInt(document.getElementById(rowId + '_DMGR').value);
                let rangeVal = parseInt(document.getElementById(rowId + '_range').value);
                if(rangeDamageVal < 1){
                    warn = warn + "<p>Cannot have <b>[Range Damage]</b> of 0.</p>";
                }
                if(rangeVal < 1){
                    warn = warn + "<p>Cannot have <b>[Range Distance]</b> of 0.</p>";
                }

                return warn;
            },
            eqt:'(<b>Move</b> / 2) + (<b>DMG-R</b> / 2) + (<b>Range</b> / 2)'
        },
        {
            title : 'Afterburner',
            desc : '<p><i>Movement Phase</i></p><p>During the <i>Movement Phase</i>, Unit may forego any <i>Attack</i> this turn to move <b>double</b> its <b>[Move]</b>.</p>',
            func : (rowId) => {
                let moveVal = parseInt(document.getElementById(rowId + '_move').value);
                let armorVal = parseInt(document.getElementById(rowId + '_armor').value);
                return ((armorVal/2) + (moveVal/2));
            },
            reqs : (rowId) => {
                let warn = '';
                let moveVal = parseInt(document.getElementById(rowId + '_move').value);
                if(moveVal <= 0){
                    warn = warn + '<p>Unit must have a <b>[Move]</b> greater than 0.</p>';
                }
                if(ub_tags_checkByName('Stable Fire Platform')){
                    warn = warn + '<p>Unit <i>already has</i> [Stable Fire Platform] tag.</p>';
                }
                return warn;
            },
            eqt:'(<b>Move</b> / 2) + (<b>Armor</b> / 2)'
        },
        
        {
            title : 'Alt-Mode',
            desc : "<p>Pick a Unit with <i>[Multi-Mode]</i> at <b>equal or greater</b> points cost. <i>This</i> Unit becomes a <i>mode</i> for the <i>[Multi-Mode]</i> Unit to transform into during the game. <b>Only</b> the mode with the <b>highest</> <i>Total points cost</i> is paid for and becomes the <i>[Multi-Mode]</i> unit.</p>",
            func : (rowId) => {
                return 0;/*TODO*/
            },
            reqs : (rowId) => {
                let warn = '';

                let unitName = document.getElementById(rowId + '_name').value;
                if( unitName === undefined || unitName.length == 0){
                    warn = warn + '<p>Unit <b>must have</b> a <i>Unit Name</i> value for keyword matching.</p>';
                }

                return warn;
            },
            eqt:'Use the <b>highest</b> single Unit Cost from all shapes.'
        },
        {
            title : 'Area Denial',
            desc : "<p><i>Resolution Phase</i></p><p>When checking for <i>Local Objectives</i> and comparing remaining total Armor; <b>add 50% <i>Size Value</i></b> of Unit to Unit's <b>current</b> <i>Armor</i>.</p>",
            func : (rowId) => {
                let sizeVal = parseInt(document.getElementById(rowId + '_size').value);
                if(sizeVal == 0){
                    sizeVal = 1;
                }
                return sizeVal * 1.5;
            },
            reqs : (rowId) => {
                let moveVal = parseInt(document.getElementById(rowId + '_move'));
                let warn = '';
                if(moveVal <= 0){
                    warn = warn + '<p>Unit must have a <b>[Move]</b> greater than 0.</p>';
                }
                let rangeVal = parseInt(document.getElementById(rowId + '_range').value);
                if(rangeVal <= 0){
                    warn = warn + '<p>Unit must have a <b>[Range]</b> greater than 0.</p>';
                }
                let rangeDamageVal = parseInt(document.getElementById(rowId + '_DMGR').value);
                let meleeDamageVal = parseInt(document.getElementById(rowId + '_DMGM').value);
                if(rangeDamageVal <= 0 || meleeDamageVal <= 0){
                    warn = warn + '<p>Unit must have <i>either</i> <b>Range Damage</b> <i>or</i> <b>Melee Damage</b> greater than 0.</p>';
                }
                return warn;
            },
            eqt:'<b>Size</b> * 1.5'
        },
        {
            title : 'Armor Piercing - Melee',
            desc : "<p><i>Combat Phase</i></p><p>When applying Damage from this unit's <i>Melee</i> attack; <b>If</b> Target has the <i>[Heavy Armor]</b> tag, <b>ignore it</b>. If Target does not have this tag, Target suffers <b>+2 Stress</b> along with the damage of the attack.</p>",
            func : (rowId) => {
                let moveVal = parseInt(document.getElementById(rowId + '_move').value);
                let meleeDamageVal = parseInt(document.getElementById(rowId + '_DMGM').value);
                return (uc_calc_Damage_Melee(meleeDamageVal, moveVal) * 0.8);
            },
            reqs : (rowId) => {
                let warn = '';
                let meleeDamageVal = parseInt(document.getElementById(rowId + '_DMGM').value);
                if(meleeDamageVal <= 0){
                    warn = warn + '<p>Unit must have a <b>[Melee Damage]</b> greater than 0.</p>';
                }
                return warn;
            },
            eqt:'<i>Melee Damage COST</i> * 80%'
        },
        {
            title : 'Armor Piercing - Ranged',
            desc : "<p><i>Combat Phase</i></p><p>When applying Damage from this unit's <i>Ranged</i> attack; <b>If</b> Target has the <i>[Heavy Armor]</b> tag, <b>ignore it</b>. If Target does not have this tag, Target suffers <b>+2 Stress</b> along with the damage of the attack.</p>",
            func : (rowId) => {
                let rangeDamageVal = parseInt(document.getElementById(rowId + '_DMGR').value);
                return (uc_calc_Damage_Range(rangeDamageVal) * 0.8);
            },
            reqs : (rowId) => {
                let warn = '';
                let rangeDamageVal = parseInt(document.getElementById(rowId + '_DMGR').value);
                if(rangeDamageVal < 4){
                    warn = warn + '<p>Unit must have a <b>[Range Damage]</b> greater than 3.</p>';
                }
                return warn;
            },
            eqt:'<i>Range Damage COST</i> * 80%'
        },
        {
            title : 'Battery',
            desc : '<p><i>Combat Phase</i></p><p>Unit may divide total Ranged DMG up into several attacks at different targets.</p>',
            func : (rowId) => {
                let sizeVal = parseInt(document.getElementById(rowId + '_size').value);
                if(sizeVal == 0){
                    sizeVal = 1;
                }
                let rangeDamageVal = parseInt(document.getElementById(rowId + '_DMGR').value);
                let rangeVal = parseInt(document.getElementById(rowId + '_range').value);
                return Math.max(0, ((rangeVal/2) + rangeDamageVal) - sizeVal);
            },
            reqs : (rowId) => {
                let warn = '';
                let rangeDamageVal = parseInt(document.getElementById(rowId + '_DMGR').value);
                if(rangeDamageVal <= 0){
                    warn = warn + '<p>Unit must have a <b>[Damage-Range]</b> greater than 0.</p>';
                }
                let rangeVal = parseInt(document.getElementById(rowId + '_range').value);
                if(rangeVal <= 0){
                    warn = warn + '<p>Unit must have a <b>[Range]</b> greater than 0.</p>';
                }

                if(ub_tags_checkByName('Blast')){
                    warn = warn + '<p>Unit already has the <b>[Blast]</b> tag.</p>';
                }

                return warn;
            },
            eqt:'((<b>Range</b> / 2) + <b>Damage-Range</b>) - <b>Size</b>'
        },
        {
            title : 'Blast',
            desc : '<p><i>Combat Phase</i></p><p>When this Unit makes a <i>Ranged Attack</i>, select a Target unit as normal, and make the attack roll. <b>If</b> the attack hits, Target takes <b>25% round down</b> damage. The remaining damage is split <b>equally</b> across units within a 6" radius of the <b>Target regardless of LoS.</b></p><p>Attacker picks which units are hit first. <i>Stationary</i> units must also be picked first and are hit automatically <b>even if they are friendly</b>, other units may avoid damage on 1 D6 roll of 5+. </p>',
            func : (rowId) => {
                let moveVal = parseInt(document.getElementById(rowId + '_move').value);
                let rangeDamageVal = parseInt(document.getElementById(rowId + '_DMGR').value);
                let rangeVal = parseInt(document.getElementById(rowId + '_range').value);

                return (moveVal / 4) + (rangeVal / 3) + (rangeDamageVal / 2);
            },
            reqs : (rowId) => {
                let warn = '';
                let rangeDamageVal = parseInt(document.getElementById(rowId + '_DMGR').value);
                if(rangeDamageVal <= 0){
                    warn = warn + '<p>Unit must have a <b>[Range Damage]</b> greater than 0.</p>';
                }

                if(ub_tags_checkByName('Battery')){
                    warn = warn + '<p>Unit already has the <b>[Battery]</b> tag.</p>';
                }

                return warn;
            },
            eqt:'(<b>Move</b> / 4) + (<b>Range</b> / 3) + (<b>Damage-Range</b> / 2)'
        },
        {
            title : 'Blink',
            desc : '<p><i>Movement Phase</i></p><p>This Unit may <b>ignore</b> <i>Overwatch</i> and <i>Terrain</i> during the <i>Movement Phase</i>.</p>',
            func : (rowId) => {
                let sizeVal = parseInt(document.getElementById(rowId + '_size').value);
                if(sizeVal == 0){
                    sizeVal = 1;
                }
                let moveVal = parseInt(document.getElementById(rowId + '_move').value);

                return uc_calc_Move(moveVal, sizeVal) * 2;
            },
            reqs : (rowId) => {
                let warn = '';
                let moveVal = parseInt(document.getElementById(rowId + '_move'));
                if(moveVal <= 0){
                    warn = warn + '<p>Unit must have a <b>[Move]</b> greater than 0.</p>';
                }
                if(ub_tags_checkByName('Bomber-Area')){
                    warn = warn + '<p>Unit <i>cannot have</i> [Bomber-Area] tag.</p>';
                }
                if(ub_tags_checkByName('Bomber-Dive')){
                    warn = warn + '<p>Unit <i>cannot have</i> [Bomber-Dive] tag.</p>';
                }
                if(ub_tags_checkByName('High Altitude Flyer')){
                    warn = warn + '<p>Unit already has [High Altitude Flyer] tag.</p>';
                }
                if(ub_tags_checkByName('Flyer')){
                    warn = warn + '<p>Unit already has [Flyer] tag.</p>';
                }
                if(ub_tags_checkByName('Jump Jets')){
                    warn = warn + '<p>Unit already has [Jump Jets] tag.</p>';
                }
                return warn;
            },
            eqt:'<b>Move COST</b> * 2'
        },
        {
            title : 'Bomber-Area',
            desc : '<p><i>Combat Phase</i></p><p>When Unit makes their <i>Ranged Attack</i> this Turn, Unit may make an <b>additional</b> <i>Ranged Attack</i> on <b>each</b> enemy Unit that it moves <b>over</b> during the <i>Movement Phase</i> within <b>2"</b> of the Unit.<ul><li><i>Damage</i> of each attack is 33% of total <b>Damage</b> value <b>rounded up</b>.</li><li> This attack <b>cannot be</b> <i>Indirect Fire</i></li><li>Treat like an <i>Overwatch</i> attack on the target.</li></ul></p>',
            func : (rowId) => {
                let sizeVal = parseInt(document.getElementById(rowId + '_size').value);
                let moveVal = parseInt(document.getElementById(rowId + '_move').value);
                let rangeDamageVal = parseInt(document.getElementById(rowId + '_DMGR').value);

                return uc_calc_Move(moveVal, sizeVal) * 0.5 + uc_calc_Damage_Range(rangeDamageVal) * 0.6;
            },
            reqs : (rowId) => {
                let warn = '';
                let hasJets = ub_tags_checkByName('Jump Jets');
                let hasFly = ub_tags_checkByName('Flyer');
                let hasHighFly = ub_tags_checkByName('High Altitude Flyer');
                let rangeDamageVal = parseInt(document.getElementById(rowId + '_DMGR').value);

                if(rangeDamageVal < 1){
                    warn = warn + '<p><b>Range Damage</b> must be <i>greater than</i> 0.</p>';
                }
                if(ub_tags_checkByName('Blink')){
                    warn = warn + '<p>Unit <i>cannot have</i> [Blink] tag.</p>';
                }
                if(ub_tags_checkByName('Bomber-Dive')){
                    warn = warn + '<p>Unit <i>cannot have</i> [Bomber-Dive] tag.</p>';
                }
                if(!hasJets && !hasFly && !hasHighFly){
                    warn = warn + '<p>Unit <i>must have</i> [Flyer] <b>or</b> [High Altitude Flyer] <b>or</b> [Jump Jets] tags.</p>';
                }
                return warn;
            },
            eqt:'(<b>Move Cost</b> / 2) + (<b>Range Damage Cost</b> * 0.6)'
        },
        {
            title : 'Bomber-Dive',
            desc : '<p><i>Combat Phase</i></p><p>Instead of making a normal <i>Ranged Attack</i>, This Unit may make 1 <i>Ranged Attack</i> on a single target Unit that is within <b>2"</b> of this Units <i>end position</i> after its move. Any unit that is a target of a [Bomber-Dive] attack may make a <b>free</b> <i>Overwatch</i> Attack on this unit at <b>-1 ATK</b> (instead of -2). <b>Damage</b> is 50% of total <b>Damage-Ranged</b> (round up, minimum of 1).</p>',
            func : (rowId) => {
                let sizeVal = parseInt(document.getElementById(rowId + '_size').value);
                let moveVal = parseInt(document.getElementById(rowId + '_move').value);
                let rangeDamageVal = parseInt(document.getElementById(rowId + '_DMGR').value);
                return Math.max(0, (moveVal / 2)- (sizeVal * 1.25) + (uc_calc_Damage_Range(rangeDamageVal) * 0.33 ));
            },
            reqs : (rowId) => {
                let warn = '';
                let hasJets = ub_tags_checkByName('Jump Jets');
                let hasFly = ub_tags_checkByName('Flyer');
                let hasHighFly = ub_tags_checkByName('High Altitude Flyer');
                let rangeDamageVal = parseInt(document.getElementById(rowId + '_DMGR').value);

                if(rangeDamageVal < 1){
                    warn = warn + '<p><b>Range Damage</b> must be <i>greater than</i> 0.</p>';
                }
                if(ub_tags_checkByName('Bomber-Area')){
                    warn = warn + '<p>Unit requires [Bomber-Area] tag.</p>';
                }
                if(ub_tags_checkByName('Charger')){
                    warn = warn + '<p>Unit <i>cannot have</i> [Charger] tag.</p>';
                }
                if(!hasJets && !hasFly && !hasHighFly){
                    warn = warn + '<p>Unit <i>must have</i> [Flyer] <b>or</b> [High Altitude Flyer] <b>or</b> [Jump Jets] tags.</p>';
                }
                return warn;
            },
            eqt:'(<b>Move</b> / 2) - (<b>Size</b> * 1.25) + (<b>Damage-Ranged Cost</b> / 3)'
        },
        {
            title : 'Brawler',
            desc : '<p><i>Combat Phase</i></p><p><b>+1 ATK</b> and <b>+1 DEF</b> in <i>Melee Attacks</i>.</p>',
            func : (rowId) => {
                let moveVal = parseInt(document.getElementById(rowId + '_move').value);
                let meleeDamageVal = parseInt(document.getElementById(rowId + '_DMGM').value);
                return uc_calc_Damage_Melee(meleeDamageVal, moveVal) * 0.67;
            },
            reqs : (rowId) => {
                let meleeDamageVal = parseInt(document.getElementById(rowId + '_DMGM').value);
                if(meleeDamageVal < 1){
                    return 'Unit must have a <b>[Melee Damage]</b> greater than 0.';
                }
                return '';
            },
            eqt:'<b>Damage-Melee COST</b> * 67%'
        },
        {
            title : 'Broadside Fire Arc',
            desc : '<p><i>Combat Phase</i></p><p>Unit may <b>only</b> make <i>Ranged Attacks</i> against targets that are <i>LEFT or RIGHT</i> of Units <i>Forward facing</i>, <b>but</b> Unit may make <b>1</b> <i>Ranged Attacks</i> per side of Unit.</p><p>Target models must be <i>inside</i> this Units left or right side, and cannot be counted for <i>both</i> at the same time.</p>',
            func : (rowId) => {
                let rangeDamageVal = parseInt(document.getElementById(rowId + '_DMGR').value);

                return uc_calc_Damage_Range(rangeDamageVal) * 0.5;
            },
            reqs : (rowId) => {
                let warn = '';
                if(ub_tags_checkByName('Limited Fire Arc')){
                    warn = warn + '<p>Unit <i>already has</i> [Limited Fire Arc] tag.</p>';
                }
                if(ub_tags_checkByName('Narrow Fire Arc')){
                    warn = warn + '<p>Unit <i>already has</i> [Limited Fire Arc] tag.</p>';
                }
                return warn;
            },
            eqt:'(<b>Damage-Range<b> <i>COST</i> * 0.5)'
        },
        {
            title : 'Cargo',
            desc : '<p><i>Movement Phase</i></p><p>Unit can instantly Pick Up an object if equal or smaller Size. \n <b>Size</b> of 0 is 1 for this tag <i>but not for tag cost</i>.</p>',
            func : (rowId) => {
                let sizeVal = parseInt(document.getElementById(rowId + '_size').value);
                let moveVal = parseInt(document.getElementById(rowId + '_move').value);
                let armorVal = parseInt(document.getElementById(rowId + '_armor').value);
                return Math.max(0,(((moveVal / 2) + armorVal) - (sizeVal*2)));
            },
            reqs : (rowId) => {
                return '';
            },
            eqt:'(<b>Move</b> /2) + <b>Armor</b> - (<b>Size</b> * 2)'
        },
        {
            title : 'Charger',
            desc : '<p><i>Movement Phase</i></p><p>Unit does not provoke <i>Overwatch</i> attacks.</p>',
            func : (rowId, ) => {
                let sizeVal = parseInt(document.getElementById(rowId + '_size').value);
                if(sizeVal == 0){
                    sizeVal = 1;
                }
                let moveVal = parseInt(document.getElementById(rowId + '_move').value);
                return (sizeVal *2) + (moveVal / 2);
            },
            reqs : (rowId) => {
                let warn = '';
                let moveVal = parseInt(document.getElementById(rowId + '_move'));
                if(moveVal <= 0){
                    warn = warn + '<p>Unit must have a <b>[Move]</b> greater than 0.</p>';
                }
                if(ub_tags_checkByName('Bomber-Dive')){
                    warn = warn + '<p>Unit <i>cannot have</i> [Bomber-Dive] tag.</p>';
                }
                return warn;
            },
            eqt:'(<b>Size</b> * 2) + (<b>Move</b> / 2)'
        },
        {
            title : 'Cloaking Systems',
            desc : '<p><i>Initiative Phase</i> <b>before</b> the roll off!</p><p>Unit <i>Ini value</i> unmodified but <b>Move</b> will be halved <b>before</b> other modifiers.</p><p>Unit <b>+3 DEF</b> and <b>cannot</b> attack this turn.</p>',
            func : (rowId, ) => {
                let sizeVal = parseInt(document.getElementById(rowId + '_size').value);
                if(sizeVal == 0){
                    sizeVal = 1;
                }
                let moveVal = parseInt(document.getElementById(rowId + '_move').value);
                if(moveVal == 0){
                    moveVal = 1;
                }
                let moveCost = uc_calc_Move(moveVal, sizeVal);
                
                return (moveCost * 0.35) + (sizeVal / 2);
            },
            reqs : (rowId) => {
                let warn = '';
                if(ub_tags_checkByName('Heavy Armor')){
                    warn = warn + '<p>Unit <i>cannot have</i> [Heavy Armor] tag.</p>';
                }
                return warn;
            },
            eqt:'(<b>Move COST</b> 35%) + (<b>Size</b> / 2)'
        },
        {
            title : 'Counter-Battery',
            desc : '<p><i>Combat Phase</i></p><p>Unit may use <b>Indirect Fire</b> on a Target this <i>Attack Phase</i> when the Target has attempted an <b>Indirect Fire</b> attack location within Unit`s <b>Range</b>.</p>',
            func : (rowId) => {
                let moveVal = parseInt(document.getElementById(rowId + '_move').value);
                let rangeVal = parseInt(document.getElementById(rowId + '_range').value);
                let rangeDamageVal = parseInt(document.getElementById(rowId + '_DMGR').value);
                return (moveVal / 3) + (rangeVal / 3) + (rangeDamageVal / 3); /*TODO*/
            },
            reqs : (rowId) => {
                let warn = '';
                let rangeVal = parseInt(document.getElementById(rowId + '_range').value);
                if(rangeVal <= 0){
                    warn = warn + '<p>Unit must have a <b>[Range]</b> greater than 0.</p>';
                }
                let rangeDamageVal = parseInt(document.getElementById(rowId + '_DMGR').value);
                if(rangeDamageVal <= 0){
                    warn = warn + '<p>Unit must have a <b>[Range Damage]</b> greater than 0.</p>';
                }
                return warn;
            },
            eqt:'(<b>Damage-Range</b> / 3) + (<b>Range</b> / 3) + (<b>Move</b> / 3)'
        },
        {
            title : 'Courage-I',
            desc : '<p><i>Resolution Phase</i>.</p><p>When Unit is making a <i>Stress Check</i>, Unit gets <b>+1</b> to the D6 roll.</p>',
            func : (rowId) => {
                let sizeVal = parseInt(document.getElementById(rowId + '_size').value);
                let moveVal = parseInt(document.getElementById(rowId + '_move').value);
                let armorVal = parseInt(document.getElementById(rowId + '_armor').value);
                //let structVal = parseInt(document.getElementById(rowId + '_structure').value);

                if(sizeVal == 0){
                    sizeVal = 1;
                }
                if(moveVal == 0){
                    moveVal = 6;
                }

                val = moveVal + armorVal + sizeVal; //+ structVal;
                val = val / 4;

                return val * 3;
            },
            reqs : (rowId) => {
                let warn = '';
                let armorVal = parseInt(document.getElementById(rowId + '_armor').value);
                if(armorVal <= 0){
                    warn = warn + '<p>Unit must have a <b>[Armor]</b> greater than 0.';
                }
                if(ub_tags_checkByName('Courage-II')){
                    warn = warn + '<p>Unit <i>already has</i> [Courage-II] tag.</p>';
                }
                if(ub_tags_checkByName('Fearless')){
                    warn = warn + '<p>Unit <i>already has</i> [Fearless] tag.</p>';
                }
                if(ub_tags_checkByName('Overheat')){
                    warn = warn + '<p>Unit <i>already has</i> [Overheat] tag.</p>';
                }
                return warn;
            },
            eqt:'<i>average</i> [<b>Size</b>, <b>Move</b>, <b>Armor</b>] * 2'
        },
        {
            title : 'Courage-II',
            desc : '<p><i>Resolution Phase</i>.</p><p>When Unit is making a <i>Stress Check</i>, Unit gets <b>+2</b> to the D6 roll.</p>',
            func : (rowId) => {
                let sizeVal = parseInt(document.getElementById(rowId + '_size').value);
                let moveVal = parseInt(document.getElementById(rowId + '_move').value);
                let armorVal = parseInt(document.getElementById(rowId + '_armor').value);
                //let structVal = parseInt(document.getElementById(rowId + '_structure').value);

                if(sizeVal == 0){
                    sizeVal = 1;
                }
                if(moveVal == 0){
                    moveVal = 6;
                }

                val = moveVal + armorVal + sizeVal;//+ structVal;
                val = val / 4;

                return val * 5;
            },
            reqs : (rowId) => {
                let warn = '';
                let armorVal = parseInt(document.getElementById(rowId + '_armor').value);
                if(armorVal <= 0){
                    warn = warn + '<p>Unit must have a <b>[Armor]</b> greater than 0.</p>';
                }
                if(ub_tags_checkByName('Courage-I')){
                    warn = warn + '<p>Unit <i>already has</i> [Courage-I] tag.</p>';
                }
                if(ub_tags_checkByName('Fearless')){
                    warn = warn + '<p>Unit <i>already has</i> [Fearless] tag.</p>';
                }
                if(ub_tags_checkByName('Overheat')){
                    warn = warn + '<p>Unit <i>already has</i> [Overheat] tag.</p>';
                }
                return warn;
            },
            eqt:'<i>average</i> [<b>Size</b>, <b>Move</b>, <b>Armor</b>] * 5'
        },
        {
            title : 'Crew-I',
            desc : '<p><i>Resolution Phase</i>.</p>For stress rolls, roll 2 and take the highest (represents crew morale and squad morale). Limit of Crew Points is (Size / 3)  + 2.',
            func : (rowId) => {
                let sizeVal = parseInt(document.getElementById(rowId + '_size').value);
                let moveVal = parseInt(document.getElementById(rowId + '_move').value);
                if(sizeVal == 0){
                    sizeVal = 1;
                }
                if(moveVal == 0){
                    moveVal = 3;
                }
                return Math.max(0, ((1/sizeVal^2) * 12) - sizeVal + moveVal / 3);
            },
            reqs : (rowId) => {
                let warn = '';
                
                let sizeVal = parseInt(document.getElementById(rowId + '_size').value);
                if(sizeVal == 0){
                    warn = warn + '<p>Unit must have <i>Size</i> > 0.</p>';
                }

                if(ub_tags_checkByName('Courage-I')){
                    warn = warn + '<p>Unit <i>already has</i> [Courage-I] tag.</p>';
                }
                if(ub_tags_checkByName('Courage-II')){
                    warn = warn + '<p>Unit <i>already has</i> [Courage-II] tag.</p>';
                }
                if(ub_tags_checkByName('Fearless')){
                    warn = warn + '<p>Unit <i>already has</i> [Fearless] tag.</p>';
                }
                if(ub_tags_checkByName('Crew-II')){
                    warn = warn + '<p>Unit <i>already has</i> [Crew-II] tag.</p>';
                }
                if(ub_tags_checkByName('Overheat')){
                    warn = warn + '<p>Unit <i>already has</i> [Overheat] tag.</p>';
                }
                return warn;
            },
            eqt:'((1 / <b>Size</b> ^ 2) * 12) - <b>Size</b> + <b>Move</b> / 3'
        },
        {
            title : 'Crew-II',
            desc : '<p><i>Resolution Phase</i>.</p>For stress rolls, roll 3 and take the highest (represents crew morale and squad morale). Limit of Crew Points is (Size / 3)  + 2.',
            func : (rowId) => {
                let sizeVal = parseInt(document.getElementById(rowId + '_size').value);
                let moveVal = parseInt(document.getElementById(rowId + '_move').value);
                if(sizeVal == 0){
                    sizeVal = 2;
                }
                if(moveVal == 0){
                    moveVal = 3;
                }
                return Math.max(0, ((1/sizeVal^2) * 15) - sizeVal + moveVal / 3);
            },
            reqs : (rowId) => {
                let warn = '';
                let sizeVal = parseInt(document.getElementById(rowId + '_size').value);
                if(sizeVal < 3){
                    warn = warn + '<p><b>[Size]</b> must be <i>greater than</i> 2.</p>';
                }
                if(ub_tags_checkByName('Courage-I')){
                    warn = warn + '<p>Unit <i>already has</i> [Courage-I] tag.</p>';
                }
                if(ub_tags_checkByName('Courage-II')){
                    warn = warn + '<p>Unit <i>already has</i> [Courage-II] tag.</p>';
                }
                if(ub_tags_checkByName('Fearless')){
                    warn = warn + '<p>Unit <i>already has</i> [Fearless] tag.</p>';
                }
                if(ub_tags_checkByName('Crew-I')){
                    warn = warn + '<p>Unit <i>already has</i> [Crew-I] tag.</p>';
                }
                if(ub_tags_checkByName('Overheat')){
                    warn = warn + '<p>Unit <i>already has</i> [Overheat] tag.</p>';
                }
                return warn;
            },
            eqt:'((1 / <b>Size</b> ^ 2) * 15) - <b>Size</b> + <b>Move</b> / 3'
        },
        {
            title : 'Fearless',
            desc : '<p><i>Resolution Phase</i>.</p><p>Unit <i>automatically</i> passes any <i>Stress Check</i>.</p>',
            func : (rowId) => {
                return ub_row_change_points(rowId) * 0.25;
            },
            reqs : (rowId) => {
                let warn = '';
                if(ub_tags_checkByName('Courage-I')){
                    warn = warn + '<p>Unit <i>already has</i> [Courage-I] tag.</p>';
                }
                if(ub_tags_checkByName('Courage-II')){
                    warn = warn + '<p>Unit <i>already has</i> [Courage-II] tag.</p>';
                }
                if(ub_tags_checkByName('Crew-I')){
                    warn = warn + '<p>Unit <i>already has</i> [Crew-I] tag.</p>';
                }
                if(ub_tags_checkByName('Crew-II')){
                    warn = warn + '<p>Unit <i>already has</i> [Crew-II] tag.</p>';
                }
                if(ub_tags_checkByName('Overheat')){
                    warn = warn + '<p>Unit <i>already has</i> [Overheat] tag.</p>';
                }
                if(ub_tags_checkByName('Hero')){
                    warn = warn + '<p>Unit <i>already has</i> [Hero] tag.</p>';
                }
                return warn;
            },
            eqt:'<i>Unit base total COST</i> * 25%'
        },
        {
            title : 'Flyer',
            desc : 'Unit is considered as permanently above the ground. Unit may move and shoot <b>over</b> enemy Units and Terrain. Unit cannot use <b>Cover Bonus</b> for defense and <b>all</b> units have <i>Line of sight</i> to this unit. <b>Only</b> Units with <b>[Flyer]</b> or <b>[Jump Jets]</b> can choose <i>Melee Attacks</i> when applicable. ',
            func : (rowId) => {
                let sizeVal = parseInt(document.getElementById(rowId + '_size').value);
                let moveVal = parseInt(document.getElementById(rowId + '_move').value);
                let armorVal = parseInt(document.getElementById(rowId + '_armor').value);

                return ((sizeVal - moveVal) / 2) + (armorVal * 2);
            },
            reqs : (rowId) => {
                let warn = '';
                if(ub_tags_checkByName('High Altitude Flyer')){
                    warn = warn + '<p>Unit already has [High Altitude Flyer].</p>';
                }
                if(ub_tags_checkByName('Blink')){
                    warn = warn + '<p>Unit already has [Blink].</p>';
                }
                if(ub_tags_checkByName('Jump Jets')){
                    warn = warn + '<p>Unit already has [Jump Jets].</p>';
                }
                return warn;
            },
            eqt:'((<b>Size</b> - <b>Move</b>) / 2) + (<b>Armor</b> * 2)'
        },
        {
            title : 'Fortification',
            desc : '<p><i>Movement Phase</i>.</p><p>Unit may make unlimited <i>Overwatch</i> attacks.</p>',
            func : (rowId) => {
                let sizeVal = parseInt(document.getElementById(rowId + '_size').value);
                let moveVal = parseInt(document.getElementById(rowId + '_move').value);
                let rangeDamageVal = parseInt(document.getElementById(rowId + '_DMGR').value);

                return Math.max(0, (moveVal - sizeVal)) + (rangeDamageVal / 2);
            },
            reqs : (rowId) => {
                return '';
            },
            eqt:'(<b>Move</b> - <b>Size</b>) + (<b>Damage-Range</b> / 2)'
        },
        {
            title : 'Forward Observer',
            desc : '<p><i>Movement Phase</i>.</p><p><b>Unit cannot be Panicked.</b></p><p>Unit suffers <b>-1 DEF</b> and <b>-2 Evade</b> to mark 1 enemy Unit in <i>Line of Sight</i>. Friendly Units may treat marked target as if in <i>Line of Sight</i> for this <i>Combat Phase</i>.</p>',
            func : (rowId) => {
                let sizeVal = parseInt(document.getElementById(rowId + '_size').value);
                if(sizeVal == 0){
                    sizeVal = 1;
                }
                let moveVal = parseInt(document.getElementById(rowId + '_move').value);
                if(moveVal == 0){
                    moveVal = 6;
                }

                return ((1/sizeVal^2) * 10) + (moveVal / 2);
            },
            reqs : (rowId) => {
                let warn = '';

                if(ub_tags_checkByName('Recon')){
                    warn = warn + '<p>Unit <i>already has</i> [Recon].';
                }
                if(ub_tags_checkByName('Mobile HQ')){
                    warn = warn + '<p>Unit <i>already has</i> [Mobile HQ].';
                }
                return warn;
            },
            eqt:'((1 / <b>Size</b> ^ 2) * 10 ) + (<b>Move</b> / 2)'
        },
        {
            title : 'Grappler',
            desc : '<p><i>Movement Phase</i></p><p>Unit may <b>ignore</b> the <i>Danger Close</i> movement rule when it moves.</p>',
            func : (rowId) => {
                let sizeVal = parseInt(document.getElementById(rowId + '_size').value);
                let moveVal = parseInt(document.getElementById(rowId + '_move').value);                
                
                let moveCost = uc_calc_Move(moveVal, sizeVal);

                if(sizeVal == 0){
                    sizeVal = 2;
                }

                if(moveVal == 0){
                    moveVal = 4;
                }

                return sizeVal + (moveCost * 0.25);
            },
            reqs : (rowId) => {
                let warn = '';
                let moveVal = parseInt(document.getElementById(rowId + '_move').value);
                let meleeDamageVal = parseInt(document.getElementById(rowId + '_DMGM').value);
                if(meleeDamageVal < 1){
                    warn = warn + '<p>Unit <b>Melee Damage</b> must be greater than <b>0</b>.</p>';
                }
                if(moveVal < 1){
                    warn = warn + '<p>Unit must have a <i>Move value</i>.</p>'
                }
                return warn;
            },
            eqt:'<b>Size</b> + (<i>Move Cost</i> * 0.25)'
        },
        {
            title : 'Heavy Armor',
            desc : '<p><i>Combat Phase</i>.</p><p>Unit may reduce <b>any</b> incoming <i>DMG</i> to itself by <b>half rounded down</b>, this occurs <b>before any other</b> TAGs are applied.</p>',
            func : (rowId) => {
                let sizeVal = parseInt(document.getElementById(rowId + '_size').value);
                let moveVal = parseInt(document.getElementById(rowId + '_move').value);
                let armorVal = parseInt(document.getElementById(rowId + '_armor').value);

                return uc_calc_Armor(armorVal, sizeVal) * 0.65 + moveVal;
            },
            reqs : (rowId) => {
                let warn = '';
                let evadeVal = parseInt(document.getElementById(rowId + '_evade').value);
                
                if(evadeVal > 1){
                    warn = warn + '<p>Unit <b>Evade<b> must be <i>less than</i> <b>2</b>.';
                }
                
                if(ub_tags_checkByName('Weak Rear Armor')){
                    warn = warn + "<p>Unit <i>already has</i> [Weak Rear Armor].";
                }
                return warn;
            },
            eqt:'<b>Armor Cost</b> * 0.65 + <b>Move</b>'
        },
        {
            title : 'Hero',
            desc : '<p><i>Resolution Phase</i>.</p><p>Hero may suffer <b>+2 Stress</b> Point to allow every Friendly Unit in 8" to <b>reroll</b> 1 failed <i>Stress Check</i> per Turn. <b>IF</b> [Hero] unit is <b>destroyed</b>, <b>all</b> friendly units <b>immediately</b> suffer <b>+2 Stress</b>.</p>',
            func : (rowId) => {
                let sizeVal = parseInt(document.getElementById(rowId + '_size').value);
                let moveVal = parseInt(document.getElementById(rowId + '_move').value);
                let evadeVal = parseInt(document.getElementById(rowId + '_evade').value);
                let armorVal = parseInt(document.getElementById(rowId + '_armor').value);

                return sizeVal + (armorVal / 2) + ((moveVal + evadeVal) / 2);
            },
            reqs : (rowId) => {
                let warn = '';
                if(ub_tags_checkByName('Rank - Green')){
                    warn = warn + '<p>Unit already has [Rank - Green] tag.</p>';
                }
                return warn;
            },
            eqt:'<b>Size</b> + (<b>Armor</b> / 2) + (<b>Move</b> + <b>Evade</b>) / 2'
        },
        {
            title : 'High Altitude Flyer',
            desc : '<b>Ignore</b> [Indirect Fire] attacks. <b>Ignore</b> <i>Overwatch</i> for <b>any</b> Unit missing the [High Altitude Flyer] or [Flyer] tag. Ground Units can only use <i>Long Range</i> attacks on this model. Any [High Altitude Flyer] or [Flyer] can use <i>Effective Range</i> and <i>Melee</i> attacks where applicable.',
            func : (rowId) => {
                let sizeVal = parseInt(document.getElementById(rowId + '_size').value);
                let moveVal = parseInt(document.getElementById(rowId + '_move').value);
                let armorVal = parseInt(document.getElementById(rowId + '_armor').value);

                return ((((moveVal / 2) + (sizeVal * 1.25)) / 2) + (armorVal * 0.7)) * 2;
            },
            reqs : (rowId) => {
                let warn = '';

                let moveVal = parseInt(document.getElementById(rowId + '_move').value);

                if(moveVal < 1){
                    warn = warn + '<p>Unit <i>must have</i> <b>Move Value</b> greater than <b>0</b>.</p>';
                }

                if(ub_tags_checkByName('Blink')){
                    warn = warn + '<p>Unit already has [Blink].</p>';
                }
                if(ub_tags_checkByName('Jump Jets')){
                    warn = warn + '<p>Unit already has [Jump Jets].</p>';
                }
                if(ub_tags_checkByName('Stable Fire Platform')){
                    warn = warn + '<p>Unit already has [Stable Fire Platform].</p>';
                }

                return warn;
            },
            eqt:'((((<b>Move</b> / 2) + (<b>Size</b> * 1.25) / 2) + (<b>Armor</b> * 0.7)) * 2'
        },
        {
            title : 'Hole where your house was',
            desc : '<p><i>Combat Phase</i>.<p>Once per game, once per this tag, <i>Player</i> may <b>remove 1</b> piece of Terrain when this unit is activated. For the next <b>2</b> <i>Turns</i>, Unit may only move as normal, and suffers <b>+2</b> Stress.</p>',
            func : (rowId) => {
                return 0;  /*TODO */
            },
            reqs : (rowId) => {
                return '';
            },
            eqt:''
        },
        {
            title : 'Hull Gun - I',
            desc : '<p><i>Combat Phase</i>.</p><p>Unit <b>may</b> fire as if it has the <i>Limit Fire Arc</i> tag but may add <b>+25%</b> rounded-up of its DMG-R value to the attack.</p>',
            func : (rowId) => {
                let rangeDamageVal = parseInt(document.getElementById(rowId + '_DMGR').value);

                return uc_calc_Damage_Range(rangeDamageVal) * 0.2;
            },
            reqs : (rowId) => {
                let warn = '';
                if(ub_tags_checkByName('Limited Fire Arc')){
                    warn = warn + '<p>Unit <i>already has</i> [Limited Fire Arc] tag.</p>';
                }
                if(ub_tags_checkByName('Narrow Fire Arc')){
                    warn = warn + '<p>Unit <i>already has</i> [Narrow Fire Arc] tag.</p>';
                }
                if(ub_tags_checkByName('Hull Gun - II')){
                    warn = warn + '<p>Unit <i>already has</i> [Hull Gun - II] tag.</p>';
                }
                return warn;
            },
            eqt:'<b>Damage-Range<b> COST * 0.2'
        },
        {
            title : 'Hull Gun - II',
            desc : '<p><i>Combat Phase</i>.</p><p>Unit <b>may</b> fire as if it has the <i>Limit Fire Arc</i> tag but may add <b>+50%</b> rounded-up of its DMG-R value to the attack.</p>',
            func : (rowId) => {
                let rangeDamageVal = parseInt(document.getElementById(rowId + '_DMGR').value);

                return uc_calc_Damage_Range(rangeDamageVal)*0.4;
            },
            reqs : (rowId) => {
                let warn = '';
                if(ub_tags_checkByName('Limited Fire Arc')){
                    warn = warn + '<p>Unit <i>already has</i> [Limited Fire Arc] tag.</p>';
                }
                if(ub_tags_checkByName('Narrow Fire Arc')){
                    warn = warn + '<p>Unit <i>already has</i> [Narrow Fire Arc] tag.</p>';
                }
                if(ub_tags_checkByName('Hull Gun - I')){
                    warn = warn + '<p>Unit <i>already has</i> [Hull Gun - I] tag.</p>';
                }
                return warn;
            },
            eqt:'<b>Damage-Range<b> COST * 0.4'
        },
        {
            title : 'Indirect Fire',
            desc : '<p><i>Combat Phase</i>.</p><p>Unit may select targets <b>outside</b> <i>Line of Sight</i> when making <i>Ranged Attacks</i>. <b>Each</b> Attack suffers <b>-2ATK</b> in Range, and <b>-3ATK</b> at <i>Long Range</i>.</p>',
            func : (rowId) => {
                let rangeDamageVal = parseInt(document.getElementById(rowId + '_DMGR').value);
                let rangeVal = parseInt(document.getElementById(rowId + '_range').value);

                return (rangeDamageVal / 2) + (rangeVal / 2)
            },
            reqs : (rowId) => {
                let warn = '';
                let rangeVal = parseInt(document.getElementById(rowId + '_range').value);
                if(rangeVal <= 0){
                    warn = warn + '<p>Unit must have a <b>[Range]</b> greater than 0.</p>';
                }
                let rangeDamageVal = parseInt(document.getElementById(rowId + '_DMGR').value);
                if(rangeDamageVal <= 0){
                    warn = warn + '<p>Unit must have a <b>[Range Damage]</b> greater than 0.</p>';
                }
                return warn;
            },
            eqt:'(<b>Damage-Range</b> / 2) + (<b>Range</b> / 2)'
        },
        {
            title : 'Inertial Dampers',
            desc : '<p><i>Movemnt Phase</i></p><p>"When Move is greater than 12", treat this Unit as having moved only 11" in the Combat Phase.</p>',
            func : (rowId) => {
                let sizeVal = parseInt(document.getElementById(rowId + '_size').value);
                if(sizeVal == 0){
                    sizeVal = 1;
                }
                let moveVal = parseInt(document.getElementById(rowId + '_move').value);
                let evadeVal = parseInt(document.getElementById(rowId + '_evade').value);

                return (sizeVal * 1.25) + ((moveVal + evadeVal) / 1.5)
            },
            reqs : (rowId) => {
                let warn = '';
                let moveVal = parseInt(document.getElementById(rowId + '_move').value);
                if(moveVal <= 0){
                    warn = warn + '<p>Unit must have a <b>[Move]</b> greater than 11".</p>';
                }
                if(ub_tags_checkByName('Stable Fire Platform')){
                    warn = warn + '<p>Unit <i>already has</i> [Stable Fire Platform] tag.</p>';
                }

                return warn;
            },
            eqt:'(<b>Size</b> * 1.25) + (<b>Move</b> + <b>Evade</b>) / 1.5'
        },
        {
            title : 'Inhibitor Munitions',
            desc : '<p><i>Combat Phase</i></p><p>Player must declare this <b>before</b> the Unit makes its <i>Ranged Attack</i>.</p><p>Units <i>Ranged Attack</i> <b>DMG set to 0</b>. <b>When</b> attack hits the target, the targets <b>next</b> <i>Movement Phase</i> move is reduced to 1/2" <b>before</b> any other modifiers. This <b>will</b> change targets <i>Ini value</i>.</p>',
            func : (rowId) => {
                let moveVal = parseInt(document.getElementById(rowId + '_move').value);
                let rangeVal = parseInt(document.getElementById(rowId + '_range').value);

                return (moveVal / 2) + (rangeVal / 2);
            },
            reqs : (rowId) => {
                return '';
            },
            eqt:'(<b>Move</b> / 2) + (<b>Range</b> / 2)'
        },
        {
            title : 'Juggernaut',
            desc : '<p><i>Movement Phase</i><p/><p>Units <i>Ranged Attacks</i> suffer <b>-1 ATK</b>.</p><p>Any time Unit enters <i>Melee Range</i> after moving <b>1/2 or more</b> of their current <i>Move Value</i>, <b>+Size/2</b> to Units next <i>Melee Attack</i>.</p>',
            func : (rowId) => {
                let sizeVal = parseInt(document.getElementById(rowId + '_size').value);
                let moveVal = parseInt(document.getElementById(rowId + '_move').value);
                let meleeDamageVal = parseInt(document.getElementById(rowId + '_DMGM').value);

                return ((sizeVal * 3) + (moveVal + (moveVal * 0.5)) + meleeDamageVal) / 2;
            },
            reqs : (rowId) => {
                let warn = '';
                let moveVal = parseInt(document.getElementById(rowId + '_move').value);
                if(moveVal <= 0){
                    warn = warn + '<p>Unit must have a <b>[Move]</b> greater than 0.</p>';
                }
                let armorVal = parseInt(document.getElementById(rowId + '_armor').value);
                if(armorVal <= 0){
                    warn = warn + '<p>Unit must have a <b>[Armor]</b> greater than 0.</p>';
                }
                let meleeDamageVal = parseInt(document.getElementById(rowId + '_DMGM').value);
                if(meleeDamageVal < 1){
                    warn = warn + '<p>Unit must have <b>Damage-Melee</b> value.';
                }
                return warn;
            },
            eqt:'<i>average</i> (<b>Size</b> * 3) + (<b>Move</b> + 50%) + <b>DMG-M</b>'
        },
        {
            title : 'Jump Jets',
            desc : '<p><i>Movement Phase</i></p><p>Unit may traverse terrain vertically, uses [Flyer] rules when moving and for <i>Overwatch</i> attacks, but is otherwise treated as a ground unit.</p>',
            func : (rowId) => {
                let sizeVal = parseInt(document.getElementById(rowId + '_size').value);
                if(sizeVal == 0){
                    sizeVal = 1;
                }
                let moveVal = parseInt(document.getElementById(rowId + '_move').value);
                if(moveVal == 0){
                    moveVal = 1;
                }
                return (sizeVal * 2) + (moveVal / 2);
            },
            reqs : (rowId) => {
                let warn = '';

                if(ub_tags_checkByName('Blink')){
                    warn = warn + '<p>Unit already has [Blink] tag.</p>'
                }
                if(ub_tags_checkByName('High Altitude Flyer')){
                    warn = warn + '<p>Unit already has [High Altitude Flyer] tag.</p>'
                }
                if(ub_tags_checkByName('Flyer')){
                    warn = warn + '<p>Unit already has [Flyer] tag.</p>'
                }
                return warn;
            },
            eqt:'(<b>Size</b> * 2) + (<b>Move</b> / 2)'
        },
        {
            title : 'Limited Fire Arc',
            desc : '<p><i>Combat Phase</i></p><p>Unit may only make <i>Ranged Attacks</i> in the Forward or Rear Arc, you must choose before the game starts.</p>',
            func : (rowId) => {
                let rangeDamageVal = parseInt(document.getElementById(rowId + '_DMGR').value);

                return 0 - (uc_calc_Damage_Range(rangeDamageVal) * 0.45);
            },
            reqs : (rowId) => {
                let warn = '';
                let rangeVal = parseInt(document.getElementById(rowId + '_range').value);
                let rangeDamageVal = parseInt(document.getElementById(rowId + '_DMGR').value);

                if(rangeVal <= 0){
                    warn = warn + '<p>Unit must have a <b>[Range]</b> greater than 0.</p>';
                }
                if(rangeDamageVal <= 0){
                    warn = warn + '<p>Unit must have a <b>[Range Damage]</b> greater than 0.</p>';
                }

                if(ub_tags_checkByName('Broadside Fire Arc')){
                    warn = warn + '<p>Unit <i>already has</i> [Broadside Fire Arc] tag.</p>';
                }
                if(ub_tags_checkByName('Hull Gun - I')){
                    warn = warn + '<p>Unit <i>already has</i> [Hull Gun - I] tag.</p>';
                }
                if(ub_tags_checkByName('Hull Gun - II')){
                    warn = warn + '<p>Unit <i>already has</i> [Hull Gun - II] tag.</p>';
                }
                if(ub_tags_checkByName('Narrow Fire Arc')){
                    warn = warn + '<p>Unit <i>already has</i> [Narrow Fire Arc] tag.</p>';
                }
                return warn;
            },
            eqt:'<i>Subtract</i> (<b>Damage-Range<b> <i>COST</i> * 45%)'
        },
        {
            title : 'Limited Use Weapon',
            desc : 'Unit has an extra weapon and use at specificed ATK and specified range in place of its normal attack or an overwatch attack. Discard after use.',
            func : (rowId) => {
                return 0; /*TODO*/
            },
            reqs : (rowId) => {
                return '';
            },
            eqt:'TODO'
        },
        {
            title : 'Minimum Range',
            desc : '<p><i>Combat Phase</i></p><p>When making a <b>Range Attack</b>, the Target <b>cannot be</b> 6" <i>or closer<i/> to the Unit.</p>',
            func : (rowId) => {
                let sizeVal = parseInt(document.getElementById(rowId + '_size').value)
                let moveVal = parseInt(document.getElementById(rowId + '_move').value)
                let rangeDamageVal = parseInt(document.getElementById(rowId + '_DMGR').value);
                let rangeVal = parseInt(document.getElementById(rowId + '_range').value);
                let evadeVal  = parseInt(document.getElementById(rowId + '_evade').value);

                if(sizeVal == 0){
                    sizeVal  = 1;
                }

                return (0 + (uc_calc_Evade(sizeVal, evadeVal, moveVal) * 0.1)) - ((uc_calc_Range(moveVal, rangeVal, rangeDamageVal) * 0.5) + sizeVal);
            },
            reqs : (rowId) => {
                let warn = '';
                let rangeVal = parseInt(document.getElementById(rowId + '_range').value);
                let rangeDamageVal = parseInt(document.getElementById(rowId + '_DMGR').value);

                if(rangeVal <= 0){
                    warn = warn + '<p>Unit must have a <b>[Range]</b> greater than 0.</p>';
                }
                if(rangeDamageVal <= 0){
                    warn = warn + '<p>Unit must have a <b>[Range Damage]</b> greater than 0.</p>';
                }
                return warn;
            },
            eqt:'<i>Subtract</i> (<b>Range<b> <i>COST</i> * 50%) and <b>Size</b> <i>Value</i> from total.'
        },
        {
            title : 'Mobile HQ',
            desc : '<p><b>Unit cannot be <i>Panicked</i>.</b></p><p>Each Friendly Unit <b>+1</b> to their <i>Ini Value</i> during <i>Movement Phase</i>.</p>',
            func : (rowId) => {
                return ub_row_change_points(rowId) * 0.25;
            },
            reqs : (rowId) => {
                let warn = '';
                if(ub_tags_checkByName('Forward Observer')){
                    warn += '<p>Unit <i>already has</i> [Forward Observer].</p>';
                }
                if(ub_tags_checkByName('Recon')){
                    warn += '<p>Unit <i>already has</i> [Recon].</p>';
                }
                return warn;
            },
            eqt:'<i>Unit base total COST</i> * 25%'
        },
        {
            title : 'Multi-Mode',
            desc : "<p>Pick a Unit with <i>[Alt-Mode]</i> at <b>equal or lesser</b> points cost. Instead of moving, you can replace this Unit with the selected <i>[Alt-Mode]</i> Unit. Replacement Unit <b>retains all damage and tokens</b>.</p><p><b>If</b> the damage exceeds that Unit's <b>Armor</b>, it is <b>destroyed</b>.</p>",
            func : (rowId) => {
                return 0;/*TODO*/
            },
            reqs : (rowId) => {
                let warn = '';

                let unitName = document.getElementById(rowId + '_name').value;
                if( unitName === undefined || unitName.length == 0){
                    warn = warn + '<p>Unit <b>must have</b> a <i>Unit Name</i> value for keyword matching.</p>';
                }

                return warn;
            },
            eqt:'Use the <b>highest</b> single Unit Cost from all shapes.'
        },
        {
            title : 'Narrow Fire Arc',
            desc : "<p><i>Combat Phase</i></p><p>Targets of this Unit's <i>Ranged Attacks</i> must be 1/4 model width inside the <b>width</b> of this Unit`s model. <i>Minimum width of 1\" for Unit width.</i>.</p>",
            func : (rowId) => {
                let rangeDamageVal = parseInt(document.getElementById(rowId + '_DMGR').value);

                return 0 - (uc_calc_Damage_Range(rangeDamageVal) * 0.75);
            },
            reqs : (rowId) => {
                let warn = '';
                let rangeVal = parseInt(document.getElementById(rowId + '_range').value);
                let rangeDamageVal = parseInt(document.getElementById(rowId + '_DMGR').value);

                if(rangeVal <= 0){
                    warn = warn + '<p>Unit must have a <b>[Range]</b> greater than 0.</p>';
                }
                if(rangeDamageVal <= 0){
                    warn = warn + '<p>Unit must have a <b>[Range Damage]</b> greater than 0.</p>';
                }

                if(ub_tags_checkByName('Broadside Fire Arc')){
                    warn = warn + '<p>Unit <i>already has</i> [Broadside Fire Arc] tag.</p>';
                }
                if(ub_tags_checkByName('Limited Fire Arc')){
                    warn = warn + '<p>Unit <i>already has</i> [Limited Fire Arc] tag.</p>';
                }
                if(ub_tags_checkByName('Hull Gun - I')){
                    warn = warn + '<p>Unit <i>already has</i> [Hull Gun - I] tag.</p>';
                }
                if(ub_tags_checkByName('Hull Gun - II')){
                    warn = warn + '<p>Unit <i>already has</i> [Hull Gun - II] tag.</p>';
                }
                return warn;
            },
            eqt:'<i>Subtract</i> (<b>Damage-Range<b> <i>COST</i> * 60%)'
        },
        {
            title : 'Overheat',
            desc : '<p><i>Combat Phase</i></p><p>During <i>Combat Phase</i>, Unit may suffer <b>4 Stress Points</b> to re-roll <i>up to 4</i> <b>ATK<.b> dice. <b>Cannot</b> be combine with <b>[Fearless]</b>.</p>',
            func : (rowId) => {
                let meleeDamageVal = parseInt(document.getElementById(rowId + '_DMGM').value);
                let rangeDamageVal = parseInt(document.getElementById(rowId + '_DMGR').value);

                return (meleeDamageVal * 2) + (rangeDamageVal * 2);
            },
            reqs : (rowId) => {
                let warn = '';
                if(ub_tags_checkByName('Fearless')){
                    warn = warn + '<p>Unit <i>already has</i> [Fearless] tag.</p>';
                }
                return warn;
            },
            eqt:'(<b>Damage-Melee</b> * 2) + (<b>Damage-Range</b> * 2)'
        },
        {
            title : 'Pack / Deploy',
            desc : '<p>Player must choose whether this unit <i>moves</i> <b>OR</b> <i>attacks</i> on this turn.</p><p>At the <b>end</b> of the <i>Initiative Phase</i>, Units with this tag must declare if they will move or shoot this turn.</p>',
            func : (rowId) => {
                let meleeDamageVal = parseInt(document.getElementById(rowId + '_DMGM').value);
                let rangeDamageVal = parseInt(document.getElementById(rowId + '_DMGR').value);

                let moveVal = parseInt(document.getElementById(rowId + '_move').value);
                let sizeVal = parseInt(document.getElementById(rowId + '_size').value);

                let moveCost = uc_calc_Move(moveVal, sizeVal) * 0.85;
                let rangeCost = uc_calc_Damage_Range(rangeDamageVal) * 0.85;
                let meleeCost = uc_calc_Damage_Melee(meleeDamageVal, moveVal) * 0.85;

                return 0 - (moveCost + rangeCost + meleeCost);
            },
            reqs : (rowId) => {
                let warn = '';
                let dmgVal = parseInt(document.getElementById(rowId + '_DMGM').value) + parseInt(document.getElementById(rowId + '_DMGR').value);
                let moveVal = parseInt(document.getElementById(rowId + '_move').value)

                if(dmgVal < 1){
                    warn = warn + '<p>Unit <b>must</b> have either a <i>Ranged</i> <b>or</b> <i>Melee</i> damage value.</p>';
                }
                if(moveVal < 1){
                    warn = warn + '<p>Unit <b>must</b> have a <i>Move</i> value greater than <b>0</b>.</p>';
                }
                return warn;
            },
            eqt:'<i>Subtract</i> 85% of <b>Move Cost</b> and 85% of each <b>Melee/Range</b> costs.'
        },
        {
            title : 'Rank - Green',
            desc : "Unit's <i>base</i> <b>ATK/DEF</b> change to <b>2 ATK</b> and <b>2 DEF</b>.",
            func : (rowId) => {
                return 0 - ub_row_change_points(rowId) * 0.67; 
            },
            reqs : (rowId) => {
                let warn = '';
                if(ub_tags_checkByName('Rank - Veteran')){
                    warn = warn + '<p>Unit <i>already has</i> <b>[Rank - Veteran]</b> tag.</p>';
                }
                if(ub_tags_checkByName('Rank - Elite')){
                    warn = warn + '<p>Unit <i>already has</i> <b>[Rank - Elite]</b> tag.</p>';
                }
                return warn;
            },
            eqt:'<i>subtract Unit base total COST</i> * 67%'
        },
        {
            title : 'Rank - Veteran',
            desc : "<p>Unit gains <b>+1 INI</b> in <i>Movement Phase</i>.</p><p>Unit may <i>re-roll</i> <b>1 ATK</b> and <b>1 DEF</b> <i>per-turn</i>.</p>",
            func : (rowId) => {
                return ub_row_change_points(rowId) * 0.4; 
            },
            reqs : (rowId) => {
                let warn = '';
                if(ub_tags_checkByName('Rank - Green')){
                    warn = warn + '<p>Unit <i>already has</i> <b>[Rank - Green]</b> tag.</p>';
                }
                if(ub_tags_checkByName('Rank - Elite')){
                    warn = warn + '<p>Unit <i>already has</i> <b>[Rank - Elite]</b> tag.</p>';
                }
                return warn;
            },
            eqt:'<i>Unit base total COST</i> * 40%'
        },
        {
            title : 'Rank - Elite',
            desc : "<p>Unit gains <b>+2 INI</b> in <i>Movement Phase</i>.</p><p>Unit may <i>re-roll</i> <b>1 ATK</b> and <b>1 DEF</b> <i>per-turn</i>.</p>",
            func : (rowId) => {
                return ub_row_change_points(rowId) * 0.5; 
            },
            reqs : (rowId) => {
                let warn = '';
                if(ub_tags_checkByName('Rank - Green')){
                    warn = warn + '<p>Unit <i>already has</i> <b>[Rank - Green]</b> tag.</p>';
                }
                if(ub_tags_checkByName('Rank - Veteran')){
                    warn = warn + '<p>Unit <i>already has</i> <b>[Rank - Veteran]</b> tag.</p>';
                }
                return warn;
            },
            eqt:'<i>Unit base total COST</i> * 60%'
        },
        {
            title : 'Recon',
            desc : "<p><i>Initiative Phase</i></p><p><b>Unit cannot be Panicked.</b></p><p>During <i>Initiative Phase</i> <b>when</b> this Unit has <i>Line of Sight</i> to <i>at least</i> <b>2 Enemy Units</b>, Player may give <b>+2 INI</b> to <i>another</i> Friendly Unit.</p><p>This bonus <b>does not stack</b> with other <i>[Recon]</i> tags.</p>",
            func : (rowId) => {
                let sizeVal = parseInt(document.getElementById(rowId + '_size').value);
                let moveVal = parseInt(document.getElementById(rowId + '_move').value);
                sizeVal = Math.max(1, sizeVal);
                moveVal = Math.max(1, moveVal);

                let sizeCost = uc_calc_Size(sizeVal);
                let moveCost = uc_calc_Move(moveVal, sizeVal);

                return sizeCost + sizeCost / moveCost * 10;
            },
            reqs : (rowId) => {
                let warn = '';

                if(ub_tags_checkByName('Mobile HQ')){
                    warn = warn + '<p>Unit <i>already has</i> [Mobile HQ].</p>';
                }
                if(ub_tags_checkByName('Forward Observer')){
                    warn = warn + '<p>Unit <i>already has</i> [Forward Observer].</p>';
                }

                return warn;
            },
            eqt:'<b>Size Cost</b> + (<b>Size Cost</b> / <b>Move Cost</b>) * 10.'
        },
        {
            title : 'Rolling Stop',
            desc : "<p><i>Movement Phase</i></p><p><b>If</b> Unit is declared <i>Stationary</i> and they moved the <i>previous Turn</i>, Unit <b>must</b> move a distance of <b>25% <i>Move</i></b>, minimum <b>2''</b>. <b>If</b> Unit enters melee range of an enemy, this <b>does not</b> count for <i>Danger Close</i> or any other melee, ramming effects.</p>",
            func : (rowId) => {
                let sizeVal = parseInt(document.getElementById(rowId + '_size').value);
                let moveVal = parseInt(document.getElementById(rowId + '_move').value);

                return 0 - (uc_calc_Move(moveVal, sizeVal) * 0.15);
            },
            reqs : (rowId) => {
                let warn = '';

                if(ub_tags_checkByName('Stall Speed')){
                    warn = warn + "<p>Unit <i>already has</i> [Stall Speed] tag.</p>";
                }

                return warn;
            },
            eqt:'<i>subtract</i>  15% of <b>Move COST</b>'
        },
        {
            title : 'Secondary Turrets',
            desc : "<p><i>Combat Phase</i>.</p><p>Unit <b>may</b> make a <i>Ranged Attack</i> <b>outside</b> its fire arc, but <i>damage</i> of attack is only <b>33% rounded up, Min 1</b> of the total.</p>",
            func : (rowId) => {
                let moveVal = parseInt(document.getElementById(rowId + '_move').value);
                let rangeDamageVal = parseInt(document.getElementById(rowId + '_DMGR').value);
                let rangeVal = parseInt(document.getElementById(rowId + '_range').value);
                
                let rangeDamageCost = uc_calc_Damage_Range(rangeDamageVal);
                let rangeCost = uc_calc_Range(moveVal, rangeVal, rangeDamageVal);

                
                return (rangeDamageCost * 0.2) + (rangeCost * 0.2);
            },
            reqs : (rowId) => {
                let warn = '';

                let rangeDamageVal = parseInt(document.getElementById(rowId + '_DMGR').value);
                if(rangeDamageVal <= 0){
                    warn = warn + '<p>Unit must have a <b>[Range Damage]</b> greater than 0".</p>';
                }

                if(!ub_tags_checkByName('Limited Fire Arc') && !ub_tags_checkByName('Narrow Fire Arc') && !ub_tags_checkByName('Broadside Fire Arc')){
                    warn = warn + '<p>Unit <i>must have</i> one of the following: [Broadside Fire Arc], [Limited Fire Arc], [Narrow Fire Arc] tags.</p>';
                }
                if(ub_tags_checkByName('Hull Gun - I')){
                    warn = warn + '<p>Unit <i>already has</i> [Hull Gun - I] tag.</p>';
                }
                if(ub_tags_checkByName('Hull Gun - II')){
                    warn = warn + '<p>Unit <i>already has</i> [Hull Gun - II] tag.</p>';
                }

                return warn;
            },
            eqt:'(20% of <b>Damage-Range COST</b>) + (20% of <b>Range COST</b>)'
        },
        {
            title : 'Self-Healing',
            desc : "<p><i>Movement Phase</i></p><p>Player declares using this tag, Unit's <i>Move Value</i> is <b>reduced by half</b> including <i>Ini Value</i>. Unit <b>may not make any attacks this turn</b>.</p><p>Unit may <b>regain</b> a number of <i>Armor</i> equal to <i>Size</i> value.</p>",
            func : (rowId) => {
                let sizeVal = parseInt(document.getElementById(rowId + '_size').value);
                let moveVal = parseInt(document.getElementById(rowId + '_move').value);
                
                let armorVal = parseInt(document.getElementById(rowId + '_armor').value);
                let armorCost = uc_calc_Armor(armorVal, sizeVal);

                return (armorCost * 0.2) + (moveVal / 2) + sizeVal;
            },
            reqs : (rowId) => {
                return '';
            },
            eqt:'(+20% of <b>Armor COST</b>) + (<b>Move</b> / 2) + <b>Size</b>'
        },
        {
            title : 'Sharpshooter',
            desc : "<p><i>Combat Phase</i></p></p>Unit may <b>subtract 1</b> from <i>Stress Penalty</i> to ranged attacks at non-closest target.</p>",
            func : (rowId) => {
                let sizeVal = parseInt(document.getElementById(rowId + '_size').value);
                let moveVal = parseInt(document.getElementById(rowId + '_move').value);
                let rangeDamageVal = parseInt(document.getElementById(rowId + '_DMGR').value);
                let rangeVal = parseInt(document.getElementById(rowId + '_range').value);

                return Math.max(0, ((rangeDamageVal / 2) + (rangeVal / 2) + (moveVal / 4)) - sizeVal);
            },
            reqs : (rowId) => {
                let warn = '';
                let rangeVal = parseInt(document.getElementById(rowId + '_range').value);
                if(rangeVal<= 0){
                    warn = warn + '<p>Unit must have a <b>[Range]</b> greater than 0".</p>';
                }
                let rangeDamageVal = parseInt(document.getElementById(rowId + '_DMGR').value);
                if(rangeDamageVal <= 0){
                    warn = warn + '<p>Unit must have a <b>[Range Damage]</b> greater than 0".</p>';
                }
                if(ub_tags_checkByName('Fearless')){
                    warn = warn + '<p>Unit <i>already has</i> [Fearless] tag.</p>';
                }
                return warn;
            },
            eqt:'(<b>Damage-Range</b> / 2) + (<b>Range</b> / 2) + (<b>Move</b> / 4) - <b>Size</b>'
        },
        {
            title : 'Stable Fire Platform',
            desc : "<p><i>Combat Phase</i></p><p>Unit may <b>reroll</b> up to <b>2</b> ATK dice when <b>Stationary</b> during the <i>Movement Phase</i>. Cost less for slower units.</p>",
            func : (rowId) => {
                let sizeVal = parseInt(document.getElementById(rowId + '_size').value);
                let moveVal = parseInt(document.getElementById(rowId + '_move').value);
                let evadeVal = parseInt(document.getElementById(rowId + '_evade').value);

                return Math.max(5, ((moveVal / sizeVal) * moveVal));
            },
            reqs : (rowId) => {
                let warn = '';
                if(ub_tags_checkByName('Afterburner')){
                    warn = warn + '<p>Unit <i>already has</i> [Afterburner] tag.</p>';
                }
                if(ub_tags_checkByName('Inertial Dampers')){
                    warn = warn + '<p>Unit <i>already has</i> [Inertial Dampers] tag.</p>';
                }
                if(ub_tags_checkByName('Supercharger')){
                    warn = warn + '<p>Unit <i>already has</i> [Supercharger] tag.</p>';
                }
                if(ub_tags_checkByName('Stall Speed')){
                    warn = warn + '<p>Unit <i>already has</i> [Stall Speed] tag.</p>';
                }
                return warn;
            },
            eqt:'(<b>Move</b> / <b>Size</b>) * <b>Size</b> | min cost 5pts.'
        },
        {
            title : 'Stall Speed',
            desc : "<p><i>Movement Phase</i></p><p>Unit now has a Minimum Move distance of 1/3 normal move. It must always move AT LEAST this far in its Movement Phase. IF unit cannot complete this minimum move, it is destroyed in the Resolution Phase. This model cannot take <i>[Stable Firing Platform]</i> along with this.</p>",
            func : (rowId) => {
                let moveVal = parseInt(document.getElementById(rowId + '_move').value);

                return 0 - (moveVal / 2);
            },
            reqs : (rowId) => {
                let warn = '';
                if(ub_tags_checkByName('Stable Fire Platform')){
                    warn = warn + '<p>Unit <i>already</i> has [Stable Fire Platform] tag.</p>';
                }
                return '';
            },
            eqt:'<i>subtract</i> (<b>Move</b> / 2)'
        },
        {
            title : 'Supercharger',
            desc : "<p><i>Movement Phase</i></p><p>If Unit moved in the <i>Movement Phase</i>, Unit may move up to 25% its total <b>Move</b> immediately after this Turn's <i>Attack Phase</i>.</p>",
            func : (rowId) => {
                let moveVal = parseInt(document.getElementById(rowId + '_move').value);
                let sizeVal = parseInt(document.getElementById(rowId + '_size').value);
                
                let cost = sizeVal / moveVal * moveVal;

                return cost;
            },
            reqs : (rowId) => {
                let warn = '';
                let moveVal = parseInt(document.getElementById(rowId + '_move').value);
                if(moveVal <= 0){
                    warn = warn + '<p>Unit must have a <b>[Move]</b> greater than 0".</p>';
                }
                if(ub_tags_checkByName('Stable Fire Platform')){
                    warn = warn + '<p>Unit <i>already has</i> [Stable Fire Platform] tag.</p>';
                }
                return warn;
            },
            eqt:'(<b>Size</b> / <b>Move</b>) * <b>Move</b>'
        },
        {
            title : 'Terrifying',
            desc : '<p><i>Movement Phase</i></p><p>When Unit has finished its move, <b>all</b> Enemy Units within 6" <b>immediately</b> suffer <b>1 Stress Point</b></p>',
            func : (rowId) => {
                let sizeVal = parseInt(document.getElementById(rowId + '_size').value);
                if(sizeVal == 0){
                    sizeVal = 1;
                }
                let moveVal = parseInt(document.getElementById(rowId + '_move').value);

                return ((1 / sizeVal^2) * 10) + moveVal;
            },
            reqs : (rowId) => {
                return '';
            },
            eqt:'((1 / <b>Size</b>^2) * 10) + (<b>Move</b> - 6) / 2'
        },
        {
            title : 'Thunderous Report',
            desc : '<p><i>Combat Phase</i></p><p>Target of this Unit suffers <b>+1 Stress</b> when taking <b>Damage</b> from <i>Ranged Attacks.</i></p>',
            func : (rowId) => {
                let moveVal = parseInt(document.getElementById(rowId + '_move').value);
                let rangeVal = parseInt(document.getElementById(rowId + '_range').value);
                let rangeDamageVal = parseInt(document.getElementById(rowId + '_DMGR').value);

                return (moveVal/2) + (rangeVal * 1.25) - rangeDamageVal;
            },
            reqs : (rowId) => {
                return '';
            },
            eqt:'(<b>Move</b> / 2) + (<b>Range</b> * 1.25) - (<b>Range Damage</b>)'
        },
        {
            title : 'Transport',
            desc : "<p><i>Movement Phase</i></p><p>Unit can carry a number of <i>Friendly Units</i> whose <b>Sizes</b> when totaled are equal to or less than half this Unit's Size. \nSize 0 becomes Size 1 for this.</p>",
            func : (rowId) => {
                let sizeVal = parseInt(document.getElementById(rowId + '_size').value);
                if(sizeVal === 0){
                    sizeVal = 1;
                }
                return sizeVal + 2;
            },
            reqs : (rowId) => {
                return '';
            },
            eqt:'(<b>Size</b> * 2) + (<b>Move</b> / 2) + (<b>Armor</b> / 3)'
        },
        {
            title : 'Weak Rear Armor',
            desc : "<p><i>Combat Phase</i></p><p><b>If</b> Unit takes <i>any</i> <b>DMG</b> and the source of the damage is in the Unit's <i>Rear Facing Arc</i>, then Unit suffers <i>extra</i> <b>50% DMG</b> (<i>round up</i>) of the current attack.</p>",
            func : (rowId) => {
                let sizeVal = parseInt(document.getElementById(rowId + '_size').value);
                let moveVal = parseInt(document.getElementById(rowId + '_move').value);
                let armorVal = parseInt(document.getElementById(rowId + '_armor').value);
                let armorCost = uc_calc_Armor(armorVal, sizeVal);

                return 0 - ((armorCost * 0.4) + (moveVal / 2));
            },
            reqs : (rowId) => {
                let warn = '';

                if(ub_tags_checkByName('Heavy Armor')){
                    warn = warn + "<p>Unit <i>already has</i> [Heavy Armor].";
                }

                return warn;
            },
            eqt:'<i>subtract</i> ((<b>Armor COST</b> * 0.4) + (<b>Move</b> / 2))'
        }
   ]
};

function tagInfo_hasTag(tagName){
    let tagId = -1;
    for(let tag in tagInfo.data){
        if(tagInfo.data[tag].title === tagName){
            tagId = tag;
        }
    }
    return tagId;
}