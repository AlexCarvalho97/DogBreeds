package com.alexc.dogbreeds.presentation.breedlist.viewmodel.utils

import com.alexc.dogbreeds.domain.models.Breed


fun List<Breed>.getListPaginated(page: Int, limit: Int): MutableList<Breed> {
    val list = mutableListOf<Breed>()
    val startIndex = (page * limit)

    val size = if (startIndex + limit > this.size) {
        this.size
    } else {
        startIndex + limit
    }

    for (i in startIndex until size) {
        list.add(this[i])
    }
    return list
}


fun List<Breed>.hasDuplicates(): Boolean {
    val sortedList = this.sortedBy { it.id }
    var previous: Breed? = null

    for (breed in sortedList) {
        if (breed.id == previous?.id) {
            return true
        }
        previous = breed
    }
    return false
}