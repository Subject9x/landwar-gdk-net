/*
    Core Unit cost calculator function.

    Unit Builder Rules v0.5

        Core Stat Equations, TAGS are elsewhere.

        these are distinct from the unitbuilder bindings to make them accessible anywhere.
*/
function uc_calc_Size(sizeVal){
    return sizeVal * 2;
}

function uc_calc_Move(moveVal, sizeVal){
    if(moveVal === 0){
        return 0;
    }
    return (moveVal + sizeVal) / 2;
}

function uc_calc_Evade(sizeVal, evadeVal, moveVal){
    if(evadeVal === 0){
        return 0;
    }
    return ((sizeVal / 1.5) * evadeVal) + (moveVal / 2);
}

function uc_calc_Damage_Melee(meleeDamageVal, moveVal){
    if(meleeDamageVal === 0){
        return 0;
    }
    return (meleeDamageVal * 2) + (moveVal / 4) ;
}

function uc_calc_Damage_Range(rangeDamageVal){
    return rangeDamageVal * 4;
}

function uc_calc_Range(moveVal, rangeVal, rangeDamageVal){
    if(rangeVal === 0){
        return 0;
    }
    if(rangeDamageVal === 0){
        return 0;
    }
    return Math.max(0, (moveVal / 2) + ((rangeVal / 16) * rangeVal) + (rangeDamageVal / 2));
}

function uc_calc_Armor(armorVal, sizeVal){
    if(armorVal === 0){
        return 0;
    }
    return Math.max(0, (armorVal * 4) - (sizeVal * 2));
}

//function uc_calc_Structure(structVal,sizeVal){
//    return Math.max(0, structVal - sizeVal);
//}

function uc_calc_baseCost(sizeCost, moveCost, evadeCost, meleeCost, rangeDamageCost, rangeCost, armorCost){ //, structCost){
    return sizeCost + moveCost + evadeCost +  meleeCost +  rangeDamageCost +  rangeCost +  armorCost;// +  structCost;
}